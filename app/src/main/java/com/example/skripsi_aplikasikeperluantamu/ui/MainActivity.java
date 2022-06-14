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
import com.example.skripsi_aplikasikeperluantamu.data.model.EditModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.GuestModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.MainModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    TextView tv_namaInstansi, tv;
    ImageView iv_icon;
    Button btn_simpan;
    ArrayList<InputTypeModel> inputTypeModelArrayList;
    MasukanAdapter masukanAdapter;
    RecyclerView recyclerView;

    String username,nama,icon;
    int jumlahInputan;
    String TAG = "MainActivity";
    String hasilInput = "";
    private Boolean tapDuaKali = false;

    ArrayList<InputTypeModel> results;

    public ArrayList<EditModel> editModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        nama = bundle.getString("nama");
        icon = bundle.getString("icon");
        jumlahInputan = Integer.parseInt(bundle.getString("jumlahInputan"));

        this.username = username;

        tv = findViewById(R.id.tv);

        ambilDataTypeInputInstansi();

        tv_namaInstansi = findViewById(R.id.tv_namaInstansi);
        tv_namaInstansi.setText(nama);

        btn_simpan = findViewById(R.id.btn_simpan);

        iv_icon = findViewById(R.id.iv_icon);

        recyclerView = findViewById(R.id.rv_masukanAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        Picasso.get()
                .load("https://06c7-2001-448a-70ae-2636-31ed-efe2-349-e6ac.ap.ngrok.io/Aplikasi%20keperluan%20tamu/Admin/pages/assets/img/icon%20instanasi/"+icon)
                .error(R.drawable.icon_companyy2)
                .into(iv_icon);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < editModelArrayList.size(); i++){
                    tv.setText(tv.getText().toString()+results.get(i).getKeterangan()+":"+ editModelArrayList.get(i).getEditTextValue()+",- ");
                }
                hasilInput = tv.getText().toString();
                Log.d(TAG, "onClick: "+hasilInput);
                tv.setText("");
                postGuestData();
            }
        });

    }
    private void ambilDataTypeInputInstansi(){
        editModelArrayList = populateList();

        ApiService.getApiConfig().getDataTypeInstansi(username).enqueue(new Callback<ArrayList<InputTypeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<InputTypeModel>> call, Response<ArrayList<InputTypeModel>> response) {
                results = response.body();


                masukanAdapter = new MasukanAdapter(results, editModelArrayList);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(masukanAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<InputTypeModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void postGuestData(){
        try{
            ApiService.getApiConfig()
                    .postDataGuest(username,hasilInput)
                    .enqueue(new Callback<GuestModel>() {
                        @Override
                        public void onResponse(Call<GuestModel> call, Response<GuestModel> response) {
                            Toast.makeText(MainActivity.this, "berhasil simpan data", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<GuestModel> call, Throwable t) {
                            Log.d(TAG, "onFailure: gagal "+t.getMessage());
                        }
                    });
            Toast.makeText(MainActivity.this, "Berhasil Mengirim data", Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this, "Gagal mengirim data", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<EditModel> populateList(){

        ArrayList<EditModel> list = new ArrayList<>();

        for(int i = 0; i < jumlahInputan; i++){
            EditModel editModel = new EditModel();
            editModel.setEditTextValue(String.valueOf(i));
            list.add(editModel);
        }
        return list;
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
        }, 2500);
    }

}