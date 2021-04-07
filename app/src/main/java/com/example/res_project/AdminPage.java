package com.example.res_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {

    Button res, mem;

    ListView memberList, reserveList;
    DBHelper mdb;
    R_DBHelper rdb;

    ArrayAdapter mAdapter, rAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpage);

        mdb = new DBHelper(this);
        rdb = new R_DBHelper(this);

        res = findViewById(R.id.res);
        mem = findViewById(R.id.mem);

        memberList = findViewById(R.id.memberList);
        reserveList = findViewById(R.id.reserveList);

        ArrayList arrayListM = mdb.getAllMember();
        ArrayList arrayListR = rdb.getAllReserve();

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListM);
        rAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListR);

        memberList.setAdapter(mAdapter);
        memberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = (String)adapterView.getItemAtPosition(i);
                String[] strArray = item.split(" ");
                String clickedId = strArray[2];

                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", clickedId);

                Intent intent = new Intent(getApplicationContext(), Member_Display.class);
                intent.putExtras(dataBundle);
                startActivity(intent);

            }
        });

        reserveList.setAdapter(rAdapter);
        reserveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = (String)adapterView.getItemAtPosition(i);
                String[] strArray = item.split(" ");
                String clickedId = strArray[2];

                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", clickedId);

                Intent intent = new Intent(getApplicationContext(), Reserve_Display.class);
                intent.putExtras(dataBundle);
                startActivity(intent);


            }
        });


    }

    public void showMember(View view){
        memberList.setVisibility(View.VISIBLE);
        reserveList.setVisibility(View.GONE);
        mem.setBackgroundColor(0xFF152D3F);
        mem.setTextColor(0xefefefef);
        res.setBackgroundColor(0xFFDADADA);
        res.setTextColor(0xFF000000);

    }
    public void showReserve(View view){
        memberList.setVisibility(View.GONE);
        reserveList.setVisibility(View.VISIBLE);

        res.setBackgroundColor(0xFF152D3F);
        res.setTextColor(0xefefefef);
        mem.setBackgroundColor(0xFFDADADA);
        mem.setTextColor(0xFF000000);
    }

    public void goToLoginPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.clear();
        rAdapter.clear();
        mAdapter.addAll(mdb.getAllMember());
        rAdapter.addAll(rdb.getAllReserve());
        mAdapter.notifyDataSetChanged();
        rAdapter.notifyDataSetChanged();
    }
}
