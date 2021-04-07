package com.example.res_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Contact extends Fragment {

    View v;
    Button backBtn;
    Bundle bundle;
    String status="", id = "";
    Button dial, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact , container, false);
        bundle = getArguments();

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







        dial = v.findViewById(R.id.dial);
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:021231234"));
                startActivity(intent);

            }
        });

        email = v.findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/Text");
                intent.putExtra(Intent.EXTRA_EMAIL, "RES_KOREA@RES.COM");
                intent.putExtra(Intent.EXTRA_SUBJECT, "문의사항 : [간략히]");
                intent.putExtra(Intent.EXTRA_TEXT, "안녕하세요!");
                startActivity(intent);

            }
        });




        return v;
    }
}
