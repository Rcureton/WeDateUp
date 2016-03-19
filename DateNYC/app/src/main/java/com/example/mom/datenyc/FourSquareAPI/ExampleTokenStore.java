package com.example.mom.datenyc.FourSquareAPI;

/**
 * Created by MOM on 3/16/16.
 */
public class ExampleTokenStore {
    private static ExampleTokenStore sInstance;
    private String token;

    public static ExampleTokenStore get() {
        if (sInstance == null) {
            sInstance = new ExampleTokenStore();
        }

        return sInstance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
