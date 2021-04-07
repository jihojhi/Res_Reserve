package com.example.res_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Member_Display extends AppCompatActivity {

    EditText u_name, u_id, u_pw, u_tel;
    DBHelper mydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_member);

        mydb = new DBHelper(this);

        u_name = findViewById(R.id.u_name);
        u_id = findViewById(R.id.u_id);
        u_pw = findViewById(R.id.u_pw);
        u_tel = findViewById(R.id.u_tel);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String value = extras.getString("id");

                Cursor rs = mydb.getInfo(value);
                rs.moveToFirst();

                String i = rs.getString(rs.getColumnIndex(DBHelper.MEMBERS_COLUME_ID));
                String n = rs.getString(rs.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
                String p = rs.getString(rs.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));
                String t = rs.getString(rs.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

                if(!rs.isClosed()){
                    rs.close();
                }

                u_id.setText(i);
                u_name.setText(n);
                u_pw.setText(p);
                u_tel.setText(t);

        }
    }

    public void delete(View view){


        Bundle extras = getIntent().getExtras();

        if(extras != null){

                String value = extras.getString("id");

                mydb.deleteMember(value);
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
        }

    }
}
