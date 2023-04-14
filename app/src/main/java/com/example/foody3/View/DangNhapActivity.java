package com.example.foody3.View;



import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foody3.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;


public class DangNhapActivity extends AppCompatActivity
                                implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener
                                            ,FirebaseAuth.AuthStateListener{

    Button btnDangNhapGoogle;
    TextView txtDangKy,txtQuenMatKhau;
    GoogleApiClient apiClient;
    LoginButton btnDangNhapFaceBook;
    EditText edEmailDangNhap,edPasswordDangNhap;
    Button btnDangNhap;

    ProgressDialog progressDialog;
    public  static int REQUESTCODE_DANGNHAP_GOOGLE=99;
    public  static int KIEMTRA_PROVIDER_DANGNHAP=0;
    FirebaseAuth firebaseAuth;
    CallbackManager mCallbackFacebook;
    LoginManager loginManager;
    List<String> permissionFacebook= Arrays.asList("email","public_profile");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_dang_nhap);

        mCallbackFacebook=CallbackManager.Factory.create();
        loginManager=LoginManager.getInstance();


        firebaseAuth=FirebaseAuth.getInstance();

        btnDangNhapGoogle=(Button) findViewById(R.id.btnDangNhapGoogle);
        btnDangNhapGoogle.setOnClickListener(this);

        btnDangNhapFaceBook=(LoginButton) findViewById(R.id.btnDangNhapFaceBook);
        btnDangNhapFaceBook.setOnClickListener(this);

        txtDangKy=(TextView) findViewById(R.id.txtDangKy);
        txtDangKy.setOnClickListener(this);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(this);
        edEmailDangNhap=findViewById(R.id.edEmailDangNhap);
        edPasswordDangNhap=findViewById(R.id.edPasswordDangNhap);

        txtQuenMatKhau=findViewById(R.id.txtQuenMatKhau);
        txtQuenMatKhau.setOnClickListener(this);

        TaoClientDangNhapGoogle();

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private void ChungThucDangNhapFireBase(String tokenID){
        if(KIEMTRA_PROVIDER_DANGNHAP==1){
            AuthCredential authCredential=GoogleAuthProvider.getCredential(tokenID,null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if(KIEMTRA_PROVIDER_DANGNHAP==2){
            AuthCredential authCredential= FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE_DANGNHAP_GOOGLE){
            if(resultCode==RESULT_OK){
                GoogleSignInResult signInResult=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account=signInResult.getSignInAccount();
                String tokenID=account.getIdToken();
                ChungThucDangNhapFireBase(tokenID);
            }else{
                mCallbackFacebook.onActivityResult(requestCode,resultCode,data);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    //Lắng nghe user có click button đăng nhập, google, facebook
    @Override
   public void onClick(View view){
        int id = view.getId();
        switch (id){
            case  R.id.btnDangNhapGoogle:
                DangNhapGoogle(apiClient);
                break;
            case R.id.btnDangNhapFaceBook:
                DangNhapFacebook();
                break;
            case R.id.txtDangKy:
                startActivity(new Intent(DangNhapActivity.this,DangKyActivity.class));
                break;
            case R.id.btnDangNhap:
                DangNhap();
                break;
            case R.id.txtQuenMatKhau:
                startActivity(new Intent(DangNhapActivity.this,QuenMatKhauActivity.class));
                break;
        }
    }
    // Đang nhập bằng Email,password
    private void DangNhap() {
        String email=edEmailDangNhap.getText().toString();
        String password=edPasswordDangNhap.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(DangNhapActivity.this,"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void DangNhapFacebook(){
        loginManager.logInWithReadPermissions(this,permissionFacebook);
        loginManager.registerCallback(mCallbackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP=2;
                String tokenID = loginResult.getAccessToken().getToken();
                ChungThucDangNhapFireBase(tokenID);
            }
            @Override
            public void onCancel() {}
            @Override
            public void onError(@NonNull FacebookException e) {}
        });
    }
    private void TaoClientDangNhapGoogle(){
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        GoogleApiClient apiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }
    private void DangNhapGoogle(GoogleApiClient apiClient){
        KIEMTRA_PROVIDER_DANGNHAP=1;
        Intent iDangNhapGoogle=Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iDangNhapGoogle,REQUESTCODE_DANGNHAP_GOOGLE);
    }

    //Sự kiện kiểm tra đã đăng nhập hay đăng xuất
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(this,TrangChuActivity.class));

        }else {

        }
    }
}
