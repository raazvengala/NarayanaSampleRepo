package com.narayana.myfirstandroidapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {


    public static final String EXTRA_MOVIE = "MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ImageView movieImage = findViewById(R.id.iv_image);
        TextView title = findViewById(R.id.tv_title);

        MoviesResponse.Movie movie = (MoviesResponse.Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        if(movie.poster_path!=null){
            Picasso.get().load(MoviesAdapter.IMAGE_BASE_URL+movie.poster_path).into(movieImage);
        }
        title.setText(movie.title);


    }
}