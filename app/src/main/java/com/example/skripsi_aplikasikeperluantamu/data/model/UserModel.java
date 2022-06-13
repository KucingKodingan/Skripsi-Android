package com.example.skripsi_aplikasikeperluantamu.data.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("username")
    private String username;
    @SerializedName("nama")
    private String nama;
    @SerializedName("icon")
    private String icon;

    public UserModel(String username, String nama, String icon){
        this.username = username;
        this.nama = nama;
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
