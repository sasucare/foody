package com.example.foody3.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foody3.Controller.Interfaces.PlaceInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    private boolean giaoHang;
    private String gioMoCua;
    private String gioDongCua;
    private String tenQuanAn;
    private String videoGioiThieu;
    private String maQuanAn;
    private long luotThich;
    private List<String> tienIch;


    private List<String> hinhAnhQuanAn;

    public List<String> getHinhAnhQuanAn() {
        return hinhAnhQuanAn;
    }

    public void setHinhAnhQuanAn(List<String> hinhAnhQuanAn) {
        this.hinhAnhQuanAn = hinhAnhQuanAn;
    }

    DatabaseReference databaseReference;

    public QuanAnModel(){
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public boolean isGiaoHang() {
        return giaoHang;
    }

    public void setGiaoHang(boolean giaoHang) {
        this.giaoHang = giaoHang;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public String getGioDongCua() {
        return gioDongCua;
    }

    public void setGioDongCua(String gioDongCua) {
        this.gioDongCua = gioDongCua;
    }

    public String getTenQuanAn() {
        return tenQuanAn;
    }

    public void setTenQuanAn(String tenQuanAn) {
        this.tenQuanAn = tenQuanAn;
    }

    public String getVideoGioiThieu() {
        return videoGioiThieu;
    }

    public void setVideoGioiThieu(String videoGioiThieu) {
        this.videoGioiThieu = videoGioiThieu;
    }

    public String getMaQuanAn() {
        return maQuanAn;
    }

    public void setMaQuanAn(String maQuanAn) {
        this.maQuanAn = maQuanAn;
    }

    public long getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(long luotThich) {
        this.luotThich = luotThich;
    }

    public List<String> getTienIch() {
        return tienIch;
    }

    public void setTienIch(List<String> tienIch) {
        this.tienIch = tienIch;
    }

    public void getDanhSachQuanAn(PlaceInterface placeInterface){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot snapshotQuanAn= snapshot.child("quanans");
                for (DataSnapshot valueQuanAn: snapshotQuanAn.getChildren()) {
                    QuanAnModel quanAnModel= valueQuanAn.getValue(QuanAnModel.class);
                    quanAnModel.setMaQuanAn(valueQuanAn.getKey());

                    DataSnapshot snapshotHinhAnhQuanAn= snapshot.child("hinhanhquanans").child(valueQuanAn.getKey());

                    List<String> hinhAnhList= new ArrayList<>();

                    for (DataSnapshot valueHinhAnhQuanAn:snapshotHinhAnhQuanAn.getChildren()){
                        hinhAnhList.add(valueHinhAnhQuanAn.getValue(String.class));
                    }
                    quanAnModel.setHinhAnhQuanAn(hinhAnhList);
                    placeInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }





}
