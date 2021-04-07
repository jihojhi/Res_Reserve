package com.example.res_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class R_DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "ResProjectR.db";
    private static final String TABLE_NAME="reserves";

    static final String RESERVE_COLUME_ID="r_id";
    static final String RESERVE_COLUME_DATE="r_date";
    static final String RESERVE_COLUME_TIME="r_time";
    static final String RESERVE_COLUME_NUM="r_num";
    static final String RESERVE_COLUME_ASK="r_ask";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    RESERVE_COLUME_ID + " TEXT PRIMARY KEY," +
                    RESERVE_COLUME_DATE + " TEXT," +
                    RESERVE_COLUME_TIME + " TEXT,"+
                    RESERVE_COLUME_NUM+" TEXT,"+
                    RESERVE_COLUME_ASK+" TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    R_DBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // 예약 시 입력처리
    public boolean insertReserve(String id, String date, String time, String num, String ask){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESERVE_COLUME_ID, id);
        values.put(RESERVE_COLUME_DATE, date);
        values.put(RESERVE_COLUME_TIME, time);
        values.put(RESERVE_COLUME_NUM, num);
        values.put(RESERVE_COLUME_ASK, ask);

        db.insert(TABLE_NAME, null, values);

        return true;
    }

    //특정 예약정보 읽어오기
    public Cursor getReserve(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME+" where r_id='"+id+"';", null);
        return  cur;
    }

    //모든 예약정보
    public ArrayList getAllReserve(){

        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){

            arrayList.add("ID : "+cursor.getString(cursor.getColumnIndex(RESERVE_COLUME_ID))+"   |   "
                    +"DATE : "+cursor.getString(cursor.getColumnIndex(RESERVE_COLUME_DATE)));
            cursor.moveToNext();
        }
        return arrayList;

    }

    // 예약 취소
    public Integer deleteReserve(String id){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_NAME, "r_id=?", new String[]{id});
    }

    // 아이디 중복 검사
    public int idCheck(String id){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from "+TABLE_NAME+" where r_id='" + id+ "';";
        Cursor cur;

        cur = db.rawQuery(sql, null);
        int checkid = cur.getCount();

        return checkid;

    }


}
