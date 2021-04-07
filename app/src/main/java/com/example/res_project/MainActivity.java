package com.example.res_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button websiteBtn, loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        websiteBtn = findViewById(R.id.websiteBtn);
        loginBtn = findViewById(R.id.loginBtn);

        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 웹사이트 화면으로 넘어가도록 설정

                Intent intent = new Intent(getApplicationContext(), MainActivity_Menu_before_login.class);
                startActivity(intent);
                finish();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로그인 화면으로 넘어가게 설정
                Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
