package com.example.res_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_Menu_before_login extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private Fragment_About fragmentAbout = new Fragment_About();
    private Fragment_Menu fragmentMenu = new Fragment_Menu();
    private Fragment_Location fragmentLocation = new Fragment_Location();
    private Fragment_Contact fragmentContact = new Fragment_Contact();

    Bundle bundle;
    String isLoginStatus = null, isLoginedID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_before_login);


        // 초기화면 설정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentAbout).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


        // 전 화면에서 받아온 값 : intent -> intent
        Intent secondIntent = getIntent();
        isLoginStatus = secondIntent.getStringExtra("loginStatus"); /* "Success */
        isLoginedID = secondIntent.getStringExtra("id");

        Bundle bundle = new Bundle();
        bundle.putString("status", isLoginStatus);
        bundle.putString("id", isLoginedID);

        fragmentAbout.setArguments(bundle);
        fragmentMenu.setArguments(bundle);
        fragmentLocation.setArguments(bundle);
        fragmentContact.setArguments(bundle);

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();


            switch (menuItem.getItemId()){

                case R.id.about_res :
                    transaction.replace(R.id.frameLayout, fragmentAbout).commitAllowingStateLoss();
                    break;

                case R.id.menu_res :
                    transaction.replace(R.id.frameLayout, fragmentMenu).commitAllowingStateLoss(); break;

                case R.id.location_res :
                    transaction.replace(R.id.frameLayout, fragmentLocation).commitAllowingStateLoss(); break;

                case R.id.contact_res :
                    transaction.replace(R.id.frameLayout, fragmentContact).commitAllowingStateLoss(); break;

            }
            return true;
        }
    }
}
