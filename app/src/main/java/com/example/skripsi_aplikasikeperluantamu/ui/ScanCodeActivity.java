package com.example.skripsi_aplikasikeperluantamu.ui;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.skripsi_aplikasikeperluantamu.R;
import com.example.skripsi_aplikasikeperluantamu.data.api.ApiService;
import com.example.skripsi_aplikasikeperluantamu.data.model.GuestModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.UserModel;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanCodeActivity extends Activity {

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;

    public static String hasilScan=null;
    final static String TAG = "ScanCodeActivity";
    private Boolean tapDuaKali = false;

    ArrayList<InputTypeModel> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED
        ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 123);
        }else{
            startScanning();
        }
        ambilDataApi();
    }

    //Permission Camera
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            switch (grantResults[0]){
                case PackageManager.PERMISSION_DENIED:
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                    break;
                case PackageManager.PERMISSION_GRANTED:
                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show();
                    startScanning();
                    break;
            }
        }
    }

    //Hasil Scanning
    private void startScanning(){
        mCodeScannerView = findViewById(R.id.scan);

        mCodeScanner = new CodeScanner(this, mCodeScannerView);
        mCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
        mCodeScanner.setFormats(CodeScanner.ALL_FORMATS);
        mCodeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        mCodeScanner.setScanMode(ScanMode.SINGLE);
        mCodeScanner.setAutoFocusEnabled(true);
        mCodeScanner.setFlashEnabled(false);

        mCodeScanner.startPreview();

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hasilScan = result.getText().toString();
                        try{
                            ambilDataApi();
                        }
                        catch (Exception ex){
                            Toast.makeText(ScanCodeActivity.this, "Maaf data tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCodeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                Toast.makeText(ScanCodeActivity.this, "error pada: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mCodeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    private void ambilDataApi(){
        ApiService.getApiConfig().getDataUser(hasilScan).enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                ArrayList<UserModel> results = response.body();

                if(results.size() == 1 || results.size()>0){
                    Toast.makeText(ScanCodeActivity.this, "Selamat Datang di\n"+results.get(0).getNama(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ScanCodeActivity.this, MainActivity.class);
                    intent.putExtra("username", results.get(0).getUsername());
                    intent.putExtra("nama", results.get(0).getNama());
                    intent.putExtra("icon", results.get(0).getIcon());
                    intent.putExtra("jumlahInputan", results.get(0).getJumlahInputan());
                    startActivity(intent);
                }
                Log.d(TAG, "onResponse: "+results.get(0).getNama());
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

//    private void ambilDataApi(){
//        ApiService.getApiConfig().getDataUser(hasilScan).enqueue(new Callback<ArrayList<UserModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
//                ArrayList<UserModel> results = response.body();
//
//                if(results.size() == 1 || results.size()>0){
//                    Toast.makeText(ScanCodeActivity.this, "hasil: "+results.get(0).getUsername(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ScanCodeActivity.this, MainActivity.class);
//                    intent.putExtra("username", results.get(0).getUsername());
//                    intent.putExtra("nama", results.get(0).getNama());
//                    intent.putExtra("icon", results.get(0).getIcon());
//                    startActivity(intent);
//                }
//                Log.d(TAG, "onResponse: "+results.get(0).getNama());
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//    }

//    private void postDataApi(){
//        ApiService.getApiConfig()
//                .postDataGuest("umpar","coba2")
//                .enqueue(new Callback<GuestModel>() {
//                    @Override
//                    public void onResponse(Call<GuestModel> call, Response<GuestModel> response) {
////                        Log.d(TAG, "onResponse: berhasil "+response.body());
////                        Log.d(TAG, "onResponse: "+response.body().get(0).getKeterangan());
//
//                        Toast.makeText(ScanCodeActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<GuestModel> call, Throwable t) {
//                        Log.d(TAG, "onFailure: gagal "+t.getMessage());
//                    }
//                });
//    }

    @Override
    public void onBackPressed() {
        if(tapDuaKali == true){
            super.onBackPressed();
            return;
        }
        this.tapDuaKali = true;
        Toast.makeText(this, "Tekan Sekali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tapDuaKali = false;
            }
        }, 2000);
    }
}