package com.example.foody3.View.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foody3.Controller.PlaceController;
import com.example.foody3.Model.QuanAnModel;
import com.example.foody3.R;


public class PlaceFragment extends Fragment {

    PlaceController placeController;
    RecyclerView recyclerViewPlace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_fragment_places,container,false);
        recyclerViewPlace= view.findViewById(R.id.recyvleviewPlace);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
       placeController = new PlaceController(getActivity());
       placeController.getDanhSachQuanAnController(recyclerViewPlace);
    }
}