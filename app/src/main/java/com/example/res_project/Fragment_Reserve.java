package com.example.res_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Reserve extends Fragment {

    /* 전역 변수 */
    View v;
    LinearLayout step1, step2, step3;
    Button backBtn;

    /* step1 변수 */
    CalendarView selectDate;
    int selectYear, selectMonth, selectDay;
    TextView selectedDate;
    Button goToNextStep;

    /* step2 변수 */
    Spinner time, number;
    ArrayAdapter<CharSequence> adapter1, adapter2;
    EditText askFor;
    String selectedTime, selectedNumber, typedAsk;
    Button goToPrevious, goToFinal;

    /* step3 변수 */
    TextView finalD, finalT, finalN, finalA;
    Button goToPrevious2, cancel, confirm;

    String loginedId;
    R_DBHelper mydb;
    String dbSelectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mydb = new R_DBHelper(getContext());

        v= inflater.inflate(R.layout.reserve, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){
            loginedId=bundle.getString("id");
        }

        /* 뒤로 가기 버튼 */
        backBtn = v.findViewById(R.id.backBtn_to_afterLoginMain);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_Main_after_login.class);
                intent.putExtra("id", loginedId);
                startActivity(intent);

            }
        });

        step1 = v.findViewById(R.id.step1);
        step2 = v.findViewById(R.id.step2);
        step3 = v.findViewById(R.id.step3);
        step1.setVisibility(View.VISIBLE);
        step2.setVisibility(View.INVISIBLE);
        step3.setVisibility(View.INVISIBLE);

        /* 예약화면1 : 날짜 선택 */
        goToNextStep = v.findViewById(R.id.goToNextSelect);
        selectDate = v.findViewById(R.id.dateSelect);
        selectedDate = v.findViewById(R.id.selectedDate);

        selectDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                selectYear=year;
                selectMonth=month+1;
                selectDay=dayOfMonth;

                String yy = Integer.toString(selectYear);
                String mm = Integer.toString(selectMonth);
                String dd = Integer.toString(selectDay);

                selectedDate.setText("예약일 : "+yy+"."+mm+"."+dd);
            }
        });

        /* 다음 버튼 */
        goToNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mydb.idCheck(loginedId)!=0){

                    Toast.makeText(getContext(), "해당 아이디로 예약 내역이 있습니다.", Toast.LENGTH_LONG).show();
                    selectedDate.setText("예약일 : ");

                }else{
                    step1.setVisibility(View.INVISIBLE);
                    step2.setVisibility(View.VISIBLE);
                    step3.setVisibility(View.INVISIBLE);
                }
            }
        });


        /* 예약화면 2 : 시간과 인원수 선택과, 요청사항 입력 */
        time = v.findViewById(R.id.timeSelect);
        number = v.findViewById(R.id.numberSelect);
        askFor = v.findViewById(R.id.fillOut);

        adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.timeList, android.R.layout.simple_spinner_dropdown_item);
        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.numberList, android.R.layout.simple_spinner_dropdown_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter1);
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime =  (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTime = null;
            }
        });

        number.setAdapter(adapter2);
        number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNumber = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedNumber = null;
            }
        });

        /* 이전버튼, 다음버튼 */

        goToPrevious = v.findViewById(R.id.goToPrevious);
        goToPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step1.setVisibility(View.VISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.INVISIBLE);

            }
        });

        goToFinal = v.findViewById(R.id.goToFinal);

        finalD = v.findViewById(R.id.finalDate);
        finalT = v.findViewById(R.id.finalTime);
        finalN = v.findViewById(R.id.finalNumber);
        finalA = v.findViewById(R.id.finalAsk);

        goToFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                typedAsk = askFor.getText().toString();
                finalD.setText(selectYear+"."+selectMonth+"."+selectDay);
                finalT.setText(selectedTime);
                finalN.setText(selectedNumber);
                finalA.setText(typedAsk);

                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.INVISIBLE);
                step3.setVisibility(View.VISIBLE);

            }
        });


        /* 예약화면 3 : 예약 최종결과 */

        goToPrevious2 = v.findViewById(R.id.goToPrevious2);
        goToPrevious2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step1.setVisibility(View.INVISIBLE);
                step2.setVisibility(View.VISIBLE);
                step3.setVisibility(View.INVISIBLE);

            }
        });

        cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("진행중인 예약을 취소하시겠습니까?");

                alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                alert.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getContext(), "예약이 취소되었습니다.", Toast.LENGTH_LONG).show();

                        step1.setVisibility(View.VISIBLE);
                        step2.setVisibility(View.INVISIBLE);
                        step3.setVisibility(View.INVISIBLE);

                        selectedDate.setText("예약일 : ");
                        askFor.setText("");

                    }
                });

            }
        });

        confirm = v.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbSelectedDate = selectYear+"."+selectMonth+"."+selectDay;

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("해당 내역으로 예약하시겠습니까?");

                alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getContext(), "예약이 취소되었습니다.", Toast.LENGTH_LONG).show();

                        step1.setVisibility(View.VISIBLE);
                        step2.setVisibility(View.INVISIBLE);
                        step3.setVisibility(View.INVISIBLE);

                        selectedDate.setText("예약일 : ");
                        askFor.setText("");

                    }
                });

                alert.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // 데이터 베이스에 저장
                        mydb.insertReserve(loginedId, dbSelectedDate, selectedTime, selectedNumber, typedAsk);

                        System.out.println(loginedId);
                        System.out.println(dbSelectedDate);
                        System.out.println(selectedTime);
                        System.out.println(selectedNumber);
                        System.out.println(typedAsk);

                        Toast.makeText(getContext(), "예약이 완료되었습니다.", Toast.LENGTH_LONG).show();


                        step1.setVisibility(View.VISIBLE);
                        step2.setVisibility(View.INVISIBLE);
                        step3.setVisibility(View.INVISIBLE);

                        selectedDate.setText("예약일 : ");
                        askFor.setText("");


                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();


            }
        });

        return v;
    }
}
