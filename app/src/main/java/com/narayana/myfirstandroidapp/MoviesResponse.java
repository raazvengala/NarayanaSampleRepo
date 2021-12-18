package com.narayana.myfirstandroidapp;

import java.io.Serializable;
import java.util.List;

public class MoviesResponse implements Serializable {


    List<Movie> results;


    public static class Movie implements Serializable{
        int id;
        float popularity;
        String title;
        String poster_path;


    }

}
