package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity2 extends AppCompatActivity {
    Button btnC, btnU, btnR, btnD, btnMap, btnDangXuat;
    GoogleSignInClient mGoogleSignInClient;
    ImageView img, avatar;
    TextView txtName, txtEmail, txt_noti;
    Noti notifind;

//    private MyService mMyService;
//    private Boolean isConnect = false;
//    private ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.Mybinder binder = (MyService.Mybinder) service;
//            mMyService = binder.getService();
//            isConnect = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isConnect = false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Intent intent = new Intent(MainActivity2.this, MyService.class);
//        bindService(intent, mConnection, BIND_AUTO_CREATE);

        avatar = findViewById(R.id.avatar);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

        btnC = findViewById(R.id.btnC);
        btnR = findViewById(R.id.btnR);
        btnU = findViewById(R.id.btnU);
        btnD = findViewById(R.id.btnD);
        img= findViewById(R.id.imageView);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnMap = findViewById(R.id.btnMap);
        txt_noti = findViewById(R.id.txt_noti);

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.btnDangXuat:
                        signOut();
                        break;
                    // ...
                }
            }
        });

        Animation xoay = AnimationUtils.loadAnimation(this, R.anim.xoay);
        img.startAnimation(xoay);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            txtName.setText(personName);
            txtEmail.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(avatar);
        }



        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "Thêm thành công";
                txt_noti.setText(a);
                notifind = new Noti(txt_noti.getText().toString());
                Toast.makeText(MainActivity2.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                clickStartService();
            }
        });

//        btnR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMyService.RHao();
//            }
//        });
//
//        btnU.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMyService.UHao();
//            }
//        });
//
//        btnD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMyService.DHao();
//            }
//        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MapActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }


    private void clickStartService() {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("object_song",notifind);
        intent.putExtras(bundle);

        startService(intent);
    }
}