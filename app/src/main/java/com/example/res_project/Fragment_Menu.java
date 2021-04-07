package com.example.res_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Menu extends Fragment {
    MyData [] myData = {
            new MyData(R.drawable.dish, "메뉴명1"),
            new MyData(R.drawable.dish1, "메뉴명2"),
            new MyData(R.drawable.dish2, "메뉴명3"),
    };

    MyData [] myData1 = {
            new MyData(R.drawable.main, "메뉴명1"),
            new MyData(R.drawable.main1, "메뉴명2"),
            new MyData(R.drawable.main2, "메뉴명3"),
    };

    MyData [] myData2 = {
            new MyData(R.drawable.dessert, "메뉴명1"),
            new MyData(R.drawable.dessert1, "메뉴명2"),
            new MyData(R.drawable.dessert2, "메뉴명3"),
    };

    MyData [] myData3 = {
            new MyData(R.drawable.bever, "메뉴명1"),
            new MyData(R.drawable.bever1, "메뉴명2"),
            new MyData(R.drawable.bever2, "메뉴명3"),
    };

    View v;
    Button backBtn, entree, main, dessert, beverage;
    Bundle bundle;
    GridView mGrid;
    String status="", id = "";
    MyAdapter adapter, adapter1, adapter2, adapter3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.menu, container, false);

        bundle = getArguments();

        mGrid = v.findViewById(R.id.grid);

        adapter = new MyAdapter(v.getContext(), myData);
        adapter1 = new MyAdapter(v.getContext(), myData1);
        adapter2 = new MyAdapter(v.getContext(), myData2);
        adapter3 = new MyAdapter(v.getContext(), myData3);

        //초기화면
        mGrid.setAdapter(adapter);

        entree = v.findViewById(R.id.entreeImage);
        main = v.findViewById(R.id.mainImage);
        dessert = v.findViewById(R.id.dessertImage);
        beverage = v.findViewById(R.id.beverageImage);

        entree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGrid.setAdapter(adapter);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGrid.setAdapter(adapter1);
            }
        });
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGrid.setAdapter(adapter2);
            }
        });
        beverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGrid.setAdapter(adapter3);
            }
        });

        backBtn = v.findViewById(R.id.backBtn);

        if(bundle != null){

            status = bundle.getString("status");
            id = bundle.getString("id");

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(status==null){

                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);

                    }else{

                        Intent intent = new Intent(v.getContext(), MainActivity_Main_after_login.class);
                        intent.putExtra("status", status);
                        intent.putExtra("id", id);
                        startActivity(intent);

                    }

                }
            });

        }else{

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

        }


        // 사진 클릭 시 메뉴명 띄우기
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i %= myData.length;
                Toast.makeText(v.getContext(), myData[i].name, Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

}
