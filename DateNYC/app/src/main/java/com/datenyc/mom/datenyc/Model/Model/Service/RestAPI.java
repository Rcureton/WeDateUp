package com.datenyc.mom.datenyc.Model.Model.Service;

import com.datenyc.mom.datenyc.Model.Model.Data.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ra on 8/24/16.
 */
public interface RestAPI {

    @GET("textsearch/json?")
    Observable<Model> getResults(@Query("key")String api, @Query("query") String query, @Query("minprice")
    String minPrice, @Query("location") String location, @Query("radius") String radius);

    @GET("textsearch/json?")
    Call<Model> getNextPage(@Query("key") String api,@Query("pagetoken") String token);
}
