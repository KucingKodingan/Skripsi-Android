package com.example.skripsi_aplikasikeperluantamu.adapter;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi_aplikasikeperluantamu.R;
import com.example.skripsi_aplikasikeperluantamu.data.model.EditModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.MainModel;

import java.util.ArrayList;
import java.util.List;

//https://demonuts.com/android-recyclerview-with-edittext/

public class MasukanAdapter extends RecyclerView.Adapter<MasukanAdapter.ViewHolder> {

    private ArrayList<InputTypeModel> inputTypeModelArrayList;
    private ArrayList<EditModel> editModelArrayList;
    String hasil = "";

    public MasukanAdapter(ArrayList<InputTypeModel> inputTypeModelArrayList, ArrayList<EditModel> editModelArrayList) {
        this.inputTypeModelArrayList = inputTypeModelArrayList;
        this.editModelArrayList = editModelArrayList;
    }

    Context context;
    View rootView;
    Button btn_simpan;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_input_type, parent, false);

        context = parent.getContext();
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        btn_simpan = (Button) rootView.findViewById(R.id.btn_simpan);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.til_list.setHint("Masukkan "+inputTypeModelArrayList.get(position).getKeterangan());
        holder.til_list.setCounterMaxLength(inputTypeModelArrayList.get(position).getJumlah_kalimat());

//        holder.tv_username.setText(mainModelList.get(position).getUsername());
//        holder.tv_keteranganMasukan.setText("Masukkan "+mainModelList.get(position).getKeterangan());
//        holder.et_listMasukanData.setFilters(new InputFilter[]{
//                new InputFilter.LengthFilter(mainModelList.get(position).getJumlah_kalimat())
//        });
//        holder.et_listMasukanData.setHint(mainModelList.get(position).getKeterangan());

    }

    @Override
    public int getItemCount() {
        return inputTypeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private com.google.android.material.textfield.TextInputLayout til_list;
        private com.google.android.material.textfield.TextInputEditText et_keterangan;

        public ViewHolder(@NonNull View v) {
            super(v);

            til_list = v.findViewById(R.id.til_list);
            et_keterangan = v.findViewById(R.id.et_keterangan);

            et_keterangan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editModelArrayList.get(getAdapterPosition()).setEditTextValue(et_keterangan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

//            btn_simpan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < editModelArrayList.size(); i++){
//                        tv.setText(tv.getText() + " " + editModelArrayList.get(i).getEditTextValue() +System.getProperty("line.separator"));
//                    }
//                    Toast.makeText(context, "Data berhasil ditampilkan bung "+hasil+", aduh", Toast.LENGTH_SHORT).show();
//                    Log.d("MainActivity", "onClick: "+hasil);
//                    Log.d("MainActivity", "onClick: "+et_keterangan.getText().toString());
//                }
//            });
        }
    }
}