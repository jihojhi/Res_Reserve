package com.example.res_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Mypage extends Fragment {
    Button backBtn;
    View v;

    LinearLayout layout1, layout2, layout3;

    TextView user_name, user_id, user_tel;

    Button updateBtn, checkReserveBtn;

    EditText userId, userPw, userPwChk, userTel, userName;
    Button update, cancel, verifyB, pwChkB;

    EditText r_date, r_time, r_num, r_ask;
    Button cancelReserve, returnTo;

    String changed_pw=null, changed_tel=null;

    R_DBHelper mydb;
    DBHelper myInfoDb;
    String loginedId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage, container, false);

        mydb = new R_DBHelper(getContext());
        myInfoDb = new DBHelper(getContext());

        Bundle bundle = getArguments();
        loginedId = bundle.getString("id");

        backBtn = v.findViewById(R.id.backBtn_to_afterLoginMain);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_Main_after_login.class);
                intent.putExtra("id", loginedId);
                startActivity(intent);

            }
        });

        // 초기 화면
        layout1 = v.findViewById(R.id.layout1);
        layout1.setVisibility(View.VISIBLE);
        layout2 = v.findViewById(R.id.layout2);
        layout2.setVisibility(View.GONE);
        layout3 = v.findViewById(R.id.layout3);
        layout3.setVisibility(View.GONE);

        // 데이터 베이스에서 가져온 정보(로그인 상태)
        user_name = v.findViewById(R.id.user_name);
        user_id = v.findViewById(R.id.user_id);
        user_tel = v.findViewById(R.id.user_tel);

        if(bundle != null){

            user_name.setText(bundle.getString("name"));
            user_id.setText(bundle.getString("id"));
            user_tel.setText(bundle.getString("tel"));
        }


        //  예약조회 화면에서의 데이터
        r_date = v.findViewById(R.id.r_date);
        r_time = v.findViewById(R.id.r_time);
        r_num = v.findViewById(R.id.r_num);
        r_ask = v.findViewById(R.id.r_ask);

        /*예약조회 버튼*/
        checkReserveBtn = v.findViewById(R.id.checkReserve);

        checkReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 해당 아이디로 예약 데이터가 있을경우
                if(mydb.idCheck(loginedId)!=0){

                    Cursor cursor = mydb.getReserve(loginedId);
                    cursor.moveToFirst();

                    String d = cursor.getString(cursor.getColumnIndex(R_DBHelper.RESERVE_COLUME_DATE));
                    String t = cursor.getString(cursor.getColumnIndex(R_DBHelper.RESERVE_COLUME_TIME));
                    String n = cursor.getString(cursor.getColumnIndex(R_DBHelper.RESERVE_COLUME_NUM));
                    String a = cursor.getString(cursor.getColumnIndex(R_DBHelper.RESERVE_COLUME_ASK));


                    layout1.setVisibility(View.GONE);
                    layout3.setVisibility(View.VISIBLE);

                    r_date.setText(d);
                    r_time.setText(t);
                    r_num.setText(n);
                    r_ask.setText(a);

                }
                // 해당 아이디로 예약 데이터가 없을경우
                else{
                    Toast.makeText(getActivity(), "예약 내역이 없습니다.", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancelReserve = v.findViewById(R.id.cancelReserve);
        cancelReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("정말 예약 취소하시겠습니까?");

                alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                alert.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        layout1.setVisibility(View.VISIBLE);
                        layout3.setVisibility(View.GONE);

                        mydb.deleteReserve(loginedId);
                        Toast.makeText(getActivity(), "예약 취소 되었습니다.", Toast.LENGTH_LONG).show();

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });

        returnTo = v.findViewById(R.id.returnTo);
        returnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
            }
        });


        /*정보수정 버튼*/
        updateBtn = v.findViewById(R.id.updateInformation);

        // 정보수정 화면에서의 데이터
        userName = v.findViewById(R.id.userName);
        userId = v.findViewById(R.id.userId);
        userPw = v.findViewById(R.id.userPw);
        userPwChk = v.findViewById(R.id.userPwChk);
        userTel = v.findViewById(R.id.userTel);

        // 정보수정 화면에서 선택 버튼
        update = v.findViewById(R.id.updateBtn);
        cancel = v.findViewById(R.id.cancelBtn);

        verifyB = v.findViewById(R.id.verifyBtn);
        pwChkB = v.findViewById(R.id.pwChkBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);

                // 데이터 베이스에서 정보 가져오기
                Cursor cursor = myInfoDb.getInfo(loginedId);
                cursor.moveToFirst();

                String n = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
                String p = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));
                String t = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

                if(!cursor.isClosed()){
                    cursor.close();
                }

                //////////////////////////////////
                userName.setText(n);
                userId.setText(loginedId);
                userPw.setText(p);
                userPwChk.setText("");
                userTel.setText(t);
                //////////////////////////////////

            }
        });

        pwChkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userPw.getText().toString().equals(userPwChk.getText().toString())){
                    Toast.makeText(getActivity(), "비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show();

                    userPwChk.setEnabled(false);
                    userPw.setEnabled(false);
                    pwChkB.setEnabled(false);

                    //바뀐 비밀번호 데이터 저장
                    changed_pw = userPw.getText().toString();

                }else{
                    Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    userPw.setText("");
                    userPwChk.setText("");
                }

            }
        });

        verifyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"통신사 인증 기능 추가", Toast.LENGTH_SHORT).show();
                verifyB.setEnabled(false);
                userTel.setEnabled(false);

                //전화번호 데이터 저장
                changed_tel = userTel.getText().toString();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!pwChkB.isEnabled() && !verifyB.isEnabled()){

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("정보를 수정하시겠습니까?");

                    alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            // 데이터 베이스에서 정보 가져오기
                            Cursor cursor = myInfoDb.getInfo(loginedId);
                            cursor.moveToFirst();

                            String n = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
                            String p = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));
                            String t = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

                            if(!cursor.isClosed()){
                                cursor.close();
                            }

                            //////////////////////////////////
                            userName.setText(n);
                            userId.setText(loginedId);
                            userPw.setText(p);
                            userPwChk.setText("");
                            userTel.setText(t);
                            ///////////////////////////////////

                            userPwChk.setEnabled(true);
                            userPw.setEnabled(true);
                            pwChkB.setEnabled(true);
                            verifyB.setEnabled(true);
                            userTel.setEnabled(true);


                            layout1.setVisibility(View.VISIBLE);
                            layout2.setVisibility(View.GONE);

                        }
                    });

                    alert.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                    myInfoDb.updateMember(loginedId, changed_pw, changed_tel);
                    Toast.makeText(getActivity(),"회원정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                    Cursor cursor = myInfoDb.getInfo(loginedId);
                    cursor.moveToFirst();

                    String n = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
                    String p = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));
                    String t = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

                    if(!cursor.isClosed()){
                        cursor.close();
                    }

                    ///////////////////////////////
                    userName.setText(n);
                    userId.setText(loginedId);
                    userPw.setText(p);
                    userPwChk.setText("");
                    userTel.setText(t);
                    ////////////////////////////////

                            userPwChk.setEnabled(true);
                            userPw.setEnabled(true);
                            pwChkB.setEnabled(true);
                            verifyB.setEnabled(true);
                            userTel.setEnabled(true);


                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);

                        }
                    });

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();

                }else{

                    Toast.makeText(getActivity(),"변경된 정보가 없거나, 변경할 수 없습니다.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 데이터 베이스에서 정보 가져오기
                Cursor cursor = myInfoDb.getInfo(loginedId);
                cursor.moveToFirst();

                String n = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_NAME));
                String p = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_PW));
                String t = cursor.getString(cursor.getColumnIndex(DBHelper.MEMBERS_COLUME_TEL));

                if(!cursor.isClosed()){
                    cursor.close();
                }

                //////////////////////////////////
                userName.setText(n);
                userId.setText(loginedId);
                userPw.setText(p);
                userPwChk.setText("");
                userTel.setText(t);
                ///////////////////////////////////

                userPwChk.setEnabled(true);
                userPw.setEnabled(true);
                pwChkB.setEnabled(true);
                verifyB.setEnabled(true);
                userTel.setEnabled(true);

                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);

            }
        });

        return v;
    }
}
