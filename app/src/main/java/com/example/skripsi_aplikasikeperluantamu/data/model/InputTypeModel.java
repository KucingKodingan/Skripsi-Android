package com.example.skripsi_aplikasikeperluantamu.data.model;

import com.google.gson.annotations.SerializedName;

public class InputTypeModel {
    @SerializedName("username")
    private String username;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("jumlah_kalimat")
    private int jumlah_kalimat;
    @SerializedName("nomor")
    private int nomor;

    public InputTypeModel(String username, String keterangan, int jumlah_kalimat, int nomor) {
        this.username = username;
        this.keterangan = keterangan;
        this.jumlah_kalimat = jumlah_kalimat;
        this.nomor = nomor;
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

    public int getJumlah_kalimat() {
        return jumlah_kalimat;
    }

    public void setJumlah_kalimat(int jumlah_kalimat) {
        this.jumlah_kalimat = jumlah_kalimat;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }
}
