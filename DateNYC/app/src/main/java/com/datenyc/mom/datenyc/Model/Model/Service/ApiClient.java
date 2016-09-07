package com.datenyc.mom.datenyc.Model.Model.Service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ra on 8/24/16.
 */
public class ApiClient {
    private static String BASE_URL= "https://maps.googleapis.com/maps/api/place/";
    private static Retrofit retrofit= null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().
                    baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
