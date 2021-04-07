package com.example.res_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_Join extends AppCompatActivity {

    Button backBtn;

    EditText userName, userId, userPw, userPwChk, userTel;
    Button join, cancel, idChkB, pwChkB, verifyB;
    String join_name=null, join_id=null, join_pw=null, join_tel=null;

    DBHelper mydb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        mydb = new DBHelper(this);



        userName = findViewById(R.id.userName);
        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        userPwChk = findViewById(R.id.userPwChk);
        userTel = findViewById(R.id.userTel);

        join = findViewById(R.id.joinBtn);
        cancel = findViewById(R.id.cancelBtn);

        idChkB = findViewById(R.id.idChkBtn);
        pwChkB = findViewById(R.id.pwChkBtn);
        verifyB = findViewById(R.id.verifyBtn);


        backBtn = findViewById(R.id.backBtn_to_login);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                startActivityForResult(intent, 104);
                finish();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity_Join.this);

                    alertDialog.setMessage("회원가입을 취소하시겠습니까?");

                    alertDialog.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });

                    alertDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(getApplicationContext(), "회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();

                            userName.setText("");
                            userId.setText("");
                            userPw.setText("");
                            userPwChk.setText("");
                            userTel.setText("");
                            idChkB.setEnabled(true);
                            pwChkB.setEnabled(true);
                            verifyB.setEnabled(true);

                            Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                    AlertDialog alert = alertDialog.create();
                    alert.show();
            }
        });


        // 회원가입 버튼
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            join_name = userName.getText().toString();

            if(idChkB.isEnabled() || pwChkB.isEnabled() || userTel.isEnabled()) {

                Toast.makeText(getApplicationContext(), "모든 인증을 빠짐없이 해주세요.", Toast.LENGTH_SHORT);

            }else{

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity_Join.this);

                alertDialog.setMessage("회원가입 하시겠습니까?");

                alertDialog.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();

                        userName.setText("");
                        userId.setText("");
                        userPw.setText("");
                        userPwChk.setText("");
                        userTel.setText("");
                        idChkB.setEnabled(true);
                        pwChkB.setEnabled(true);
                        verifyB.setEnabled(true);

                        Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                        startActivity(intent);
                        finish();

                    }
                });

                alertDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mydb.insertMember(join_id, join_name, join_pw, join_tel);
                        Toast.makeText(getApplicationContext(), "회원가입되었습니다.", Toast.LENGTH_SHORT).show();

                        userName.setText("");
                        userId.setText("");
                        userPw.setText("");
                        userPwChk.setText("");
                        userTel.setText("");
                        idChkB.setEnabled(true);
                        pwChkB.setEnabled(true);
                        verifyB.setEnabled(true);

                        Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                        startActivity(intent);
                        finish();

                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.show();

            }

            }
        });


    }

    public void idChk(View view){

        //데이터 베이스에서 조회 한 후, 해당 아이디에 해당하는 행이 없는 경우
        if(mydb.idCheck(userId.getText().toString())==0){
            Toast.makeText(getApplicationContext(),"해당 아이디로 가입 가능합니다.", Toast.LENGTH_SHORT).show();
            idChkB.setEnabled(false);
            userId.setEnabled(false);

            //아이디 데이터 저장
            join_id = userId.getText().toString();

        }else{

            Toast.makeText(getApplicationContext(),"해당 아이디로 가입 불가능합니다.", Toast.LENGTH_SHORT).show();
            userId.setText("");

        }

    }

    public void pwChk(View view){

        if(userPw.getText().toString().equals(userPwChk.getText().toString())){
            Toast.makeText(getApplicationContext(),"비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show();
            pwChkB.setEnabled(false);
            userPw.setEnabled(false);
            userPwChk.setEnabled(false);

            //비밀번호 데이터 저장
            join_pw = userPw.getText().toString();

        }else{
            Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            userPw.setText("");
            userPwChk.setText("");
        }
    }

    public void verifyT(View view){

        Toast.makeText(getApplicationContext(),"통신사 인증 기능 추가", Toast.LENGTH_SHORT).show();
        verifyB.setEnabled(false);
        userTel.setEnabled(false);

        //전화번호 데이터 저장
        join_tel = userTel.getText().toString();

    }
}
