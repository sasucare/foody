package com.example.foody3.Adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody3.Model.QuanAnModel;
import com.example.foody3.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class AdapterRecycleViewPlace extends RecyclerView.Adapter<AdapterRecycleViewPlace.ViewHolder> {

    List<QuanAnModel> quanAnModelList;
    int resource;

    public AdapterRecycleViewPlace(List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenQuanAnPlace;
        Button btnDatMon;
        ImageView imgQuanAn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAnPlace= itemView.findViewById(R.id.txtTenQuanAnPlace);
            btnDatMon= itemView.findViewById(R.id.btnDatMon);
            imgQuanAn=itemView.findViewById(R.id.imgQuanAn);
        }
    }
    @NonNull
    @Override
    public AdapterRecycleViewPlace.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleViewPlace.ViewHolder holder, int position) {
        QuanAnModel quanAnModel= quanAnModelList.get(position);
        holder.txtTenQuanAnPlace.setText(quanAnModel.getTenQuanAn());
       if(quanAnModel.isGiaoHang()){
           holder.btnDatMon.setVisibility(View.VISIBLE);
       }

       if(quanAnModel.getHinhAnhQuanAn().size()>0){
           StorageReference storageHinhAnh= FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhAnhQuanAn().get(0));
           long ONE_MEGABYTE=1024*1024;
           storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
               Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
               holder.imgQuanAn.setImageBitmap(bitmap);
           });
       }
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}
