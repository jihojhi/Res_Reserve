package com.example.res_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Reserve_Display extends AppCompatActivity {

    EditText r_id, r_date, r_time, r_num, r_ask;
    R_DBHelper mydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_reserve);

        mydb = new R_DBHelper(this);

        r_id = findViewById(R.id.r_id);
        r_date = findViewById(R.id.r_date);
        r_time = findViewById(R.id.r_time);
        r_num = findViewById(R.id.r_num);
        r_ask = findViewById(R.id.r_ask);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String value = extras.getString("id");

            Cursor rs = mydb.getReserve(value);
            rs.moveToFirst();

            String d = rs.getString(rs.getColumnIndex(R_DBHelper.RESERVE_COLUME_DATE));
            String t = rs.getString(rs.getColumnIndex(R_DBHelper.RESERVE_COLUME_TIME));
            String n = rs.getString(rs.getColumnIndex(R_DBHelper.RESERVE_COLUME_NUM));
            String a = rs.getString(rs.getColumnIndex(R_DBHelper.RESERVE_COLUME_ASK));

            if(!rs.isClosed()){
                rs.close();
            }

            r_id.setText(value);
            r_date.setText(d);
            r_time.setText(t);
            r_num.setText(n);
            r_ask.setText(a);

        }
    }

    public void delete(View view){

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String value = extras.getString("id");

            mydb.deleteReserve(value);
            Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }



    }
}
