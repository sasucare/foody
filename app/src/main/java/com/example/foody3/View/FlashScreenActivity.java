package com.example.foody3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.foody3.R;

public class FlashScreenActivity extends AppCompatActivity {

    TextView txtPhienBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flash_screen);

        //Cài nút
        txtPhienBan=(TextView) findViewById(R.id.txtPhienBan);


        try {
            //cài phiên bản
            PackageInfo packageInfo =getPackageManager().getPackageInfo(getPackageName(),0);
            txtPhienBan.setText(getString(R.string.phien_ban) +" "+ packageInfo.versionName);

            //chuyển page sang đăng nhập
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent iDangNhap= new Intent(FlashScreenActivity.this,DangNhapActivity.class);
                    startActivity(iDangNhap);
                    finish();
                }
            },2000);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}