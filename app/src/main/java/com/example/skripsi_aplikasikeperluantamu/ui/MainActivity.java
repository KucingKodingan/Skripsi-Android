package com.example.skripsi_aplikasikeperluantamu.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skripsi_aplikasikeperluantamu.R;
import com.example.skripsi_aplikasikeperluantamu.adapter.MasukanAdapter;
import com.example.skripsi_aplikasikeperluantamu.data.api.ApiService;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.MainModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    TextView tv_namaInstansi;
    ImageView iv_icon;
    Button btn_simpan;
    ArrayList<InputTypeModel> inputTypeModelArrayList;
    MasukanAdapter masukanAdapter;
    RecyclerView recyclerView;
    String TAG = "MainActivity";
    String username;
    private Boolean tapDuaKali = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        String nama = bundle.getString("nama");
        String icon = bundle.getString("icon");

        this.username = username;

        tv_namaInstansi = findViewById(R.id.tv_namaInstansi);
        tv_namaInstansi.setText(nama);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataApi();
            }
        });

        iv_icon = findViewById(R.id.iv_icon);

        recyclerView = findViewById(R.id.rv_masukanAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        Picasso.get()
                .load("https://06c7-2001-448a-70ae-2636-31ed-efe2-349-e6ac.ap.ngrok.io/Aplikasi%20keperluan%20tamu/Admin/pages/assets/img/icon%20instanasi/"+icon)
                .error(R.drawable.icon_companyy2)
                .into(iv_icon);

        Toast.makeText(this, "icon "+icon, Toast.LENGTH_SHORT).show();
        ambilDataApi();
    }
    private void ambilDataApi(){
        ApiService.getApiConfig().getDataTypeInstansi(username).enqueue(new Callback<ArrayList<InputTypeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<InputTypeModel>> call, Response<ArrayList<InputTypeModel>> response) {
                ArrayList<InputTypeModel> results = response.body();

                masukanAdapter = new MasukanAdapter(results);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(masukanAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<InputTypeModel>> call, Throwable t) {

            }
        });
    }

    private void postDataApi(){

    }

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