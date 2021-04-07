package com.example.res_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_Main_after_login extends AppCompatActivity {

    Button goToWebsite, goToReserve;
    String loginedId;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_after_login);

        // 넘겨받은 아이디로 회원이름 불러오기
        Intent secondIntent = getIntent();
        loginedId = secondIntent.getStringExtra("id");


        mydb = new DBHelper(this);
        Cursor cursor = mydb.getInfo(loginedId);
        cursor.moveToFirst();
        String savedName = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
        if(!cursor.isClosed()){
            cursor.close();
        }


        Toast.makeText(getApplicationContext(), savedName+"님 안녕하세요:)", Toast.LENGTH_SHORT).show();



        goToWebsite = findViewById(R.id.goToWebsite);
        goToReserve = findViewById(R.id.goToReserve);

        goToWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity_Menu_before_login.class);
                intent.putExtra("loginStatus", "Success");
                intent.putExtra("id",loginedId);
                startActivity(intent);
                finish();

            }
        });

        goToReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Menu_after_login.class);
                intent.putExtra("id", loginedId);
                startActivity(intent);
                finish();

            }
        });

    }

}
