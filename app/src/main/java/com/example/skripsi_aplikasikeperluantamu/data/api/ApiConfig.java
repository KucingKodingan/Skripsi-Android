package com.example.skripsi_aplikasikeperluantamu.data.api;

import com.example.skripsi_aplikasikeperluantamu.data.model.GuestModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiConfig {
    @GET("user.php")
    Call<ArrayList<UserModel>> getDataUser(@Query("username") String username);

    @GET("input_type.php")
    Call<ArrayList<InputTypeModel>> getDataTypeInstansi(@Query("username") String username);

    @POST("input_type.php")
    Call<GuestModel> postDataGuest(@Body ArrayList<GuestModel> guestModels);

    @GET("input_type.php")
    Call<ArrayList<InputTypeModel>> getCoba(@Query("username") String username);
}

