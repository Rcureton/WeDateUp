package com.datenyc.mom.datenyc.Model.Model.Service;

import com.datenyc.mom.datenyc.Model.Model.Data.Model;
import com.datenyc.mom.datenyc.Model.Model.MovieData.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RestAPI {

    @GET("textsearch/json?")
    Observable<Model> getResults(@Query("key")String api, @Query("query") String query, @Query("minprice")
    String minPrice, @Query("location") String location, @Query("radius") String radius);

    @GET("textsearch/json?")
    Call<Model> getNextPage(@Query("key") String api,@Query("pagetoken") String token);

    @GET("details/json?")
    Observable<Model> getDetails(@Query("key") String api, @Query("placeid") String placeId);

    @GET("details/json?")
    Call<Model> getDeets(@Query("key") String api, @Query("placeid") String placeId);

    @GET("now_playing?")
    Call<Example> getMovies(@Query("api_key")String api);
}
