package com.example.res_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity_menu_grid extends AppCompatActivity {

    MyData [] myData = {
      new MyData(R.drawable.dish, "메뉴명1"),
      new MyData(R.drawable.dish1, "메뉴명2"),
      new MyData(R.drawable.dessert, "메뉴명3"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        GridView mGrid = findViewById(R.id.grid);
        MyAdapter adapter = new MyAdapter(this, myData);
        mGrid.setAdapter(adapter);

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i %= myData.length;
                Toast.makeText(getBaseContext(), myData[i].name, Toast.LENGTH_LONG).show();

            }
        });


    }
}
