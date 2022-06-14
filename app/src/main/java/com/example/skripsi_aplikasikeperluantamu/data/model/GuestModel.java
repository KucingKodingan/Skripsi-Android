package com.example.skripsi_aplikasikeperluantamu.data.model;

import com.google.gson.annotations.SerializedName;

public class GuestModel {
    @SerializedName("username")
    private String username;
    @SerializedName("keterangan")
    private String keterangan;

    public GuestModel(String username, String keterangan) {
        this.username = username;
        this.keterangan = keterangan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
