package com.example.res_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_Menu_after_login extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment_Announce fragmentAnnounce = new Fragment_Announce();
    private Fragment_Reserve fragmentReserve = new Fragment_Reserve();
    private Fragment_Mypage fragmentMypage = new Fragment_Mypage();

    String loginedId;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_after_login);

        Intent secondIntent = getIntent();
        loginedId = secondIntent.getStringExtra("id");

        mydb = new DBHelper(this);

        Cursor cursor = mydb.getInfo(loginedId);
        cursor.moveToFirst();
        String savedName = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
        String savedTel = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

        if(!cursor.isClosed()){
            cursor.close();
        }

        Bundle bundle = new Bundle();
        bundle.putString("id", loginedId);
        bundle.putString("name", savedName);
        bundle.putString("tel", savedTel);

        fragmentAnnounce.setArguments(bundle);
        fragmentReserve.setArguments(bundle);
        fragmentMypage.setArguments(bundle);


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentAnnounce).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()){

                case R.id.announce_res :
                    transaction.replace(R.id.frameLayout, fragmentAnnounce).commitAllowingStateLoss(); break;

                case R.id.reserve_res :
                    transaction.replace(R.id.frameLayout, fragmentReserve).commitAllowingStateLoss(); break;

                case R.id.mypage_res :
                    transaction.replace(R.id.frameLayout, fragmentMypage).commitAllowingStateLoss(); break;

            }
            return true;
        }
    }
}
