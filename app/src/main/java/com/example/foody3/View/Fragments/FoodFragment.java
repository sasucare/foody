package com.example.foody3.View.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody3.Controller.PlaceController;
import com.example.foody3.R;


public class FoodFragment extends Fragment {

    PlaceController placeController;
    RecyclerView recyclerViewPlace;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.layout_fragment_food, container, false);
        recyclerViewPlace= view.findViewById(R.id.recyvleviewPlace);
        return view;
    }
}