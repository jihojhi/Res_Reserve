package com.example.res_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_About extends Fragment {

    View v;
    Button backBtn;
    String status="", id = "";
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.about , container, false);

        backBtn = v.findViewById(R.id.backBtn);

        bundle = getArguments();

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
        return v;
    }
}
