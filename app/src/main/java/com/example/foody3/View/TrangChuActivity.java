package com.example.foody3.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foody3.Adapter.AdapterViewPagerTrangChu;
import com.example.foody3.R;

public class TrangChuActivity extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
ViewPager viewPagerTrangChu;
RadioButton rdFood,rdPlace;
RadioGroup groupFoodPlace;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu);

        viewPagerTrangChu=findViewById(R.id.viewpager_trangchu);
        rdFood=findViewById(R.id.rdFood);
        rdPlace=findViewById(R.id.rdPlace);
        groupFoodPlace=findViewById(R.id.groupPlaceFood);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu=new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        groupFoodPlace.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rdPlace.setChecked(true);
                break;
            case 1:
                rdFood.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rdFood:
                viewPagerTrangChu.setCurrentItem(1);
                break;
            case R.id.rdPlace:
                viewPagerTrangChu.setCurrentItem(0);
                break;
        }
    }
}