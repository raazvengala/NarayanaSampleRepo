package com.narayana.myfirstandroidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private MovieClickListener clickListener;

    public MoviesAdapter(MovieClickListener listener){
        this.clickListener = listener;
    }

    private final ArrayList<MoviesResponse.Movie> movies = new ArrayList<>();
    public static final String IMAGE_BASE_URL ="https://image.tmdb.org/t/p/w185/";

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout, parent, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get().load(IMAGE_BASE_URL+movies.get(position).poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.textView.setText(movies.get(position).title);

        holder.itemView.setOnClickListener(view -> {
            clickListener.onMovieClick(movies.get(holder.getAdapterPosition()));
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<MoviesResponse.Movie> moviesListFromAPI){
        movies.clear();
        movies.addAll(moviesListFromAPI);
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_image);
            textView = itemView.findViewById(R.id.movie_title);

        }
    }

    public interface MovieClickListener{
        void onMovieClick(MoviesResponse.Movie movie);
    }
}
