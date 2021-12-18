package com.narayana.myfirstandroidapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("discover/movie?sort_by=vote_average.desc&api_key=8b6096d193e7816b7a33a91dcc46792a")
    Call<MoviesResponse> getMovies();


}
