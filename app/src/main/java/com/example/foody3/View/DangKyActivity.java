package com.example.foody3.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foody3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity

         extends AppCompatActivity implements View.OnClickListener {

    Button btnDangKy;
    EditText edEmailDangKy,edPassword,edNhapLaiPassword;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        edEmailDangKy=(EditText) findViewById(R.id.edEmailDangKy);
        edPassword=(EditText) findViewById(R.id.edPassword);
        edNhapLaiPassword=(EditText) findViewById(R.id.edNhapLaiPassword);

        btnDangKy=(Button) findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        progressDialog.setMessage("Đang xử lý");
        progressDialog.show();
        String email= edEmailDangKy.getText().toString();
        String matKhau=edPassword.getText().toString();
        String nhapLaiMatKhau=edNhapLaiPassword.getText().toString();


        if(email.trim().length()==0){
            Toast.makeText(this,"Email chưa hợp lệ",Toast.LENGTH_SHORT).show();
        }else if(matKhau.trim().length()==0){
            Toast.makeText(this,"Mật khẩu chưa hợp lệ",Toast.LENGTH_SHORT).show();
        }else if(!nhapLaiMatKhau.equals(matKhau)){
            Toast.makeText(this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,matKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(DangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                       firebaseAuth.signInWithEmailAndPassword(email,matKhau);
                       startActivity(new Intent(DangKyActivity.this,TrangChuActivity.class));

                    }
                }
            });
        }

    }
}