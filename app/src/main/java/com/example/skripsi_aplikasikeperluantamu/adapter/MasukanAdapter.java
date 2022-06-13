package com.example.skripsi_aplikasikeperluantamu.adapter;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi_aplikasikeperluantamu.R;
import com.example.skripsi_aplikasikeperluantamu.data.model.InputTypeModel;
import com.example.skripsi_aplikasikeperluantamu.data.model.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MasukanAdapter extends RecyclerView.Adapter<MasukanAdapter.ViewHolder> {

    private ArrayList<InputTypeModel> inputTypeModelArrayList;

    public MasukanAdapter(ArrayList<InputTypeModel> inputTypeModelArrayList) {
        this.inputTypeModelArrayList = inputTypeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_input_type, parent, false);
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
        }


//        private TextView tv_username;
//        private EditText et_listMasukanData;
//        private TextView tv_keteranganMasukan;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_username = itemView.findViewById(R.id.tv_username);
//            tv_keteranganMasukan = itemView.findViewById(R.id.tv_keteranganMasukan);
//            et_listMasukanData = itemView.findViewById(R.id.et_listMasukanData);
//        }
    }
}
