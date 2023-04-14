package com.example.foody3.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.foody3.View.Fragments.FoodFragment;
import com.example.foody3.View.Fragments.PlaceFragment;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    FoodFragment foodFragment;
    PlaceFragment placeFragment;
    public AdapterViewPagerTrangChu(@NonNull FragmentManager fm) {
        super(fm);
         foodFragment=new FoodFragment();
         placeFragment=new PlaceFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return placeFragment;

            case 1:
                return foodFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
