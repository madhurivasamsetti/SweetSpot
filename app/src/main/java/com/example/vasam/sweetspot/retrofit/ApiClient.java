package com.example.vasam.sweetspot.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vasam on 8/1/2017.
 */
/*
this class is responsible for creating retrofit instance.
 */
public class ApiClient {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
