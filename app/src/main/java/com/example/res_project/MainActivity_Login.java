package com.example.res_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.R)
public class MainActivity_Login extends AppCompatActivity{

    Button backBtn, joinBtn, loginCheckBtn, kakao;
    EditText insertedId, insertedPw;
    DBHelper mydb;
    KakaoLoginAPI kakaoAPI = new KakaoLoginAPI();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mydb = new DBHelper(this);

        // 카카오톡 로그인 버튼
        kakao = findViewById(R.id.kakao);

        // 카카오톡 로그인 버튼 이벤트
        kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kakaoAPI.kakaoLogin(getApplicationContext());
                kakaoAPI.tokenCheck();
                kakaoAPI.userInfoRequest();
            }
        });


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        joinBtn = findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity_Join.class);
                startActivityForResult(intent, 105);
                finish();

            }
        });


        insertedId = findViewById(R.id.adminId);
        insertedPw = findViewById(R.id.adminPw);

        loginCheckBtn = findViewById(R.id.loginCheckBtn);
        loginCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //관리자 로그인
                if(insertedId.getText().toString().equals("admin") && insertedPw.getText().toString().equals("0000")) {

                    Toast.makeText(getApplicationContext(), "관리자 로그인 성공", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                    startActivity(intent);
                    finish();

                }

                // 회원 정보가 존재하는 경우
                else if(mydb.idCheck(insertedId.getText().toString())!=0){

                    String memberId = insertedId.getText().toString();
                    String memberPw = insertedPw.getText().toString();
                    Cursor cursor = mydb.getInfo(memberId);

                    cursor.moveToFirst();
                    String savedPw = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));

                    if(!cursor.isClosed()){
                        cursor.close();
                    }
                    // 아이디와 비밀번호가 일치할 때
                    if(memberPw.equals(savedPw)){

                        Intent intent = new Intent(getApplicationContext(), MainActivity_Main_after_login.class);
                        //아이디랑 같이 넘겨주기
                        intent.putExtra("id", memberId);
                        startActivity(intent);
                        finish();

                    }else{
                        insertedPw.setText("");
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                // 회원 정보가 없을 경우
                else{
                    insertedId.setText("");
                    insertedPw.setText("");
                    Toast.makeText(getApplicationContext(), "가입된 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
