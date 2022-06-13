package com.example.skripsi_aplikasikeperluantamu.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASE_URL = "https://6d56-2001-448a-70ad-2033-fdc5-b4b8-4e46-d931.ap.ngrok.io/Aplikasi%20keperluan%20tamu/api/";
    private static Retrofit retrofit;

    public static ApiConfig getApiConfig(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiConfig.class);
    }
}
