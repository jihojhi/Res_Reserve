package com.example.res_project;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Announce extends Fragment {


    RelativeLayout list1, list2, list3;
    TextView content1, content2, content3;
    Button backBtn;
    View v;
    String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        id = bundle.getString("id");



        v = inflater.inflate(R.layout.announce, container, false);
        backBtn = v.findViewById(R.id.backBtn_to_afterLoginMain);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_Main_after_login.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

        list1 = v.findViewById(R.id.list1);
        list2 = v.findViewById(R.id.list2);
        list3 = v.findViewById(R.id.list3);

        content1 = v.findViewById(R.id.content1);
        content2 = v.findViewById(R.id.content2);
        content3 = v.findViewById(R.id.content3);

        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(content1.getVisibility()== View.GONE){

                    list1.setBackgroundColor(0xffffffff);
                    list2.setBackgroundColor(0xFFDFDFDF);
                    list3.setBackgroundColor(0xFFDFDFDF);

                    content1.setVisibility(View.VISIBLE);
                    content2.setVisibility(View.GONE);
                    content3.setVisibility(View.GONE);

                }else{

                    list1.setBackgroundColor(0xFFDFDFDF);
                    content1.setVisibility(View.GONE);

                }
            }
        });

        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(content2.getVisibility()== View.GONE){

                    list2.setBackgroundColor(0xffffffff);
                    list1.setBackgroundColor(0xFFDFDFDF);
                    list3.setBackgroundColor(0xFFDFDFDF);

                    content1.setVisibility(View.GONE);
                    content2.setVisibility(View.VISIBLE);
                    content3.setVisibility(View.GONE);

                }else{

                    list2.setBackgroundColor(0xFFDFDFDF);
                    content2.setVisibility(View.GONE);

                }
            }
        });

        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(content3.getVisibility()== View.GONE){

                    list3.setBackgroundColor(0xffffffff);
                    list2.setBackgroundColor(0xFFDFDFDF);
                    list1.setBackgroundColor(0xFFDFDFDF);

                    content1.setVisibility(View.GONE);
                    content2.setVisibility(View.GONE);
                    content3.setVisibility(View.VISIBLE);

                }else{

                    list3.setBackgroundColor(0xFFDFDFDF);
                    content3.setVisibility(View.GONE);

                }
            }
        });



























        return v;
    }
}
