package com.example.skripsi_aplikasikeperluantamu.data.model;

import java.util.List;

public class MainModel {

//    List<Result> resultType;
//
//    public List<Result> getResult() {
//        return resultType;
//    }
//
//    public void setResult(List<Result> resultType) {
//        this.resultType = resultType;
//    }
//
//    public class Result {
//        String username;
//        String keterangan;
//        int jumlah_kalimat;
//
//        public Result(String username, String keterangan, int jumlah_kalimat){
//            this.username = username;
//            this.keterangan = keterangan;
//            this.jumlah_kalimat = jumlah_kalimat;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getKeterangan() {
//            return keterangan;
//        }
//
//        public void setKeterangan(String keterangan) {
//            this.keterangan = keterangan;
//        }
//
//        public int getJumlah_kalimat() {
//            return jumlah_kalimat;
//        }
//
//        public void setJumlah_kalimat(int jumlah_kalimat) {
//            this.jumlah_kalimat = jumlah_kalimat;
//        }
//    }

    //User
    List<ResultUser> resultUser;

    public List<ResultUser> getResultUser() {
        return resultUser;
    }
    public void setResultUser(List<ResultUser> resultUser) {
        this.resultUser = resultUser;
    }

    public class ResultUser {
        String username;
        String nama;
        String icon;

        public ResultUser(String username, String nama, String icon){
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
}
