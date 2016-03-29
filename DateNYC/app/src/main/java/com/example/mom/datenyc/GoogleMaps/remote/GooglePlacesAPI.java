package com.example.mom.datenyc.GoogleMaps.remote;

import com.example.mom.datenyc.GoogleMaps.Data.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ra on 3/28/16.
 */
public interface GooglePlacesAPI {

    String BASE_URL= "https://maps.googleapis.com/maps/api/place/";
    String API_KEY= "AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs";
    String ANDROID_KEY= "AIzaSyDlBZB7bD8J_1_0hq5_z2j69-ke4ca7djY";
    String query= " ";

    @GET("textsearch/json?key="+ API_KEY+ "&sensor=false" )
    Call<Result> listPlaces(@Query("query")String query);

    class GooglePlaceAPIFactory{

        private static GooglePlacesAPI service;
        public static GooglePlacesAPI getInstance(){
            if(service == null){
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                 service = retrofit.create(GooglePlacesAPI.class);
                return service;
            }else {
                return service;
            }
        }

    }
}
