package com.datenyc.mom.datenyc.Model.Model.Service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ra on 9/6/16.
 */
public class MovieClient {
    private static String MOVIE_URL= "https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit= null;


    public static Retrofit getMovieClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().
                    baseUrl(MOVIE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
