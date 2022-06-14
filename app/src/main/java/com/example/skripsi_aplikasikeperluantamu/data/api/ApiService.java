package com.example.skripsi_aplikasikeperluantamu.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String URL = "https://2bf2-2001-448a-70a6-2848-79eb-3cb4-7203-2687.ap.ngrok.io"; //Data akan terus berubah ubah
    private static String BASE_URL = URL+"/Aplikasi%20keperluan%20tamu/api/";
    private static Retrofit retrofit;

    public static ApiConfig getApiConfig(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiConfig.class);
    }
}
