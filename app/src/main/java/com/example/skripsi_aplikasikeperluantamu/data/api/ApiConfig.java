package com.example.skripsi_aplikasikeperluantamu.data.api;

import com.example.skripsi_aplikasikeperluantamu.data.model.GuestModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiConfig {
    @GET("user.php")
    Call<ArrayList<UserModel>> getDataUser(@Query("username") String username);

    @GET("input_type.php")
    Call<ArrayList<InputTypeModel>> getDataTypeInstansi(@Query("username") String username);

    @FormUrlEncoded
    @POST("guest_data.php")
    Call<GuestModel> postDataGuest(@Field("username") String username,
                                         @Field("keterangan") String keterangan);

}

