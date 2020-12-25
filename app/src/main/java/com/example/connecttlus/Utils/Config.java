package com.example.connecttlus.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    protected static final String JSON_URL = "https://api.github.com/";
    protected static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(JSON_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static ApiClient apiClient = retrofit.create(ApiClient.class);
}