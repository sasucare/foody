package com.example.foody3.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class QuenMatKhauActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtChuyenDangKy;
    Button btnGuiMailKhoiPhuc;
    EditText edEmailKhoiPhuc;

    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quen_mat_khau);

        btnGuiMailKhoiPhuc=findViewById(R.id.btnGuiMailKhoiPhuc);
        btnGuiMailKhoiPhuc.setOnClickListener(this);

        edEmailKhoiPhuc=findViewById(R.id.edEmailKhoiPhuc);

        txtChuyenDangKy=findViewById(R.id.txtChuyenDangKy);
        txtChuyenDangKy.setOnClickListener(this);

        firebaseAuth= FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btnGuiMailKhoiPhuc:
                String email=edEmailKhoiPhuc.getText().toString();
                if(KiemTraEmail(email)){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(QuenMatKhauActivity.this,"Gửi email thành công",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(QuenMatKhauActivity.this,"Email không hợp lệ",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtChuyenDangKy:
                startActivity(new Intent(QuenMatKhauActivity.this,DangKyActivity.class));
                break;
          }
    }
    private boolean KiemTraEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}