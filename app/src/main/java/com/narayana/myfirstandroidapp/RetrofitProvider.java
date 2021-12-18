package com.narayana.myfirstandroidapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static RetrofitProvider retrofitProvider;

    private RetrofitProvider(){

    }

    public static RetrofitProvider getInstance(){
        if(retrofitProvider==null){
            retrofitProvider = new RetrofitProvider();
        }

        return retrofitProvider;
    }

    public ApiService getApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    public Retrofit getRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();



        return new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
