package com.example.res_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResProject.db";
    private static final String TABLE_NAME="members";

    static final String MEMBERS_COLUME_ID="m_id";
    static final String MEMBERS_COLUME_NAME="m_name";
    static final String MEMBERS_COLUME_PW="m_pw";
    static final String MEMBERS_COLUME_TEL="m_tel";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    MEMBERS_COLUME_ID + " TEXT PRIMARY KEY," +
                    MEMBERS_COLUME_NAME + " TEXT," +
                    MEMBERS_COLUME_PW + " TEXT,"+
                    MEMBERS_COLUME_TEL+" TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    // 회원가입 시 입력처리
    public boolean insertMember(String id, String name, String pw, String tel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBERS_COLUME_ID, id);
        values.put(MEMBERS_COLUME_NAME, name);
        values.put(MEMBERS_COLUME_PW, pw);
        values.put(MEMBERS_COLUME_TEL, tel);

        db.insert(TABLE_NAME, null, values);

        return true;
    }

    // 특정 정보 읽어오기
    public Cursor getInfo(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME+" where m_id='"+id+"';", null);
        return  cur;
    }

    // 회원정보 수정
    public boolean updateMember(String id, String pw, String tel){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBERS_COLUME_PW, pw);
        values.put(MEMBERS_COLUME_TEL, tel);

        db.update(TABLE_NAME, values, "m_id=?", new String[]{id});

        return true;
    }

    // 모든 회원정보
    public ArrayList getAllMember(){

        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){

            arrayList.add("ID : "+cursor.getString(cursor.getColumnIndex(MEMBERS_COLUME_ID))+"   |   NAME : "+cursor.getString(cursor.getColumnIndex(MEMBERS_COLUME_NAME)));
            cursor.moveToNext();
        }
        return arrayList;

    }


    // 중복 예약 확인
    public int idCheck(String id){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from "+TABLE_NAME+" where m_id='" + id+ "';";
        Cursor cur;
        cur = db.rawQuery(sql, null);
        int checkid = cur.getCount();

        return checkid;

    }

    // 회원 삭제
    public Integer deleteMember(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "m_id=?", new String[]{id});
    }



}
