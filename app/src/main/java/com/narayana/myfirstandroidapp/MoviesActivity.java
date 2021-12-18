package com.narayana.myfirstandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.MovieClickListener {

    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        RecyclerView rv = findViewById(R.id.rv_movies);
        adapter = new MoviesAdapter(this);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);

        loadMoviesFromApi();
    }

    private void loadMoviesFromApi() {
        Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit();
        ApiService apiService = RetrofitProvider.getInstance().getApiService(retrofit);
        apiService.getMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NotNull Call<MoviesResponse> call, @NotNull Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    MoviesResponse moviesResponse = response.body();
                    adapter.setMovies(moviesResponse.results);
                }
            }

            @Override
            public void onFailure(@NotNull Call<MoviesResponse> call, @NotNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error occured.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMovieClick(MoviesResponse.Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}