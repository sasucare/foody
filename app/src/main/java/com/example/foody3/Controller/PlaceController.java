package com.example.foody3.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody3.Adapter.AdapterRecycleViewPlace;
import com.example.foody3.Controller.Interfaces.PlaceInterface;
import com.example.foody3.Model.QuanAnModel;
import com.example.foody3.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecycleViewPlace adapterRecycleViewPlace;

    public PlaceController(Context context){
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerPlace){

        final List<QuanAnModel> quanAnModelList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerPlace.setLayoutManager(layoutManager);
        recyclerPlace.setHasFixedSize(true);
        adapterRecycleViewPlace=new AdapterRecycleViewPlace(quanAnModelList, R.layout.layout_custom_recycleview_trangchu);
        recyclerPlace.setAdapter(adapterRecycleViewPlace);

        PlaceInterface placeInterface= new PlaceInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                Log.d("KiemTra", quanAnModelList.size()+"");
                adapterRecycleViewPlace.notifyDataSetChanged();
            }
        };

        quanAnModel.getDanhSachQuanAn(placeInterface);
    }
}
