package com.tistory.dayglo.sqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        // word_success : 정답 / word_failed : 오답
        db.execSQL("CREATE TABLE WORDRESULT (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "word_failed TEXT, word_success TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String word_success, String word_failed) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO WORDRESULT VALUES(null, '" + word_success + "', '" + word_failed + "');");
        db.close();
    }

    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM WORDRESULT", null);

        while(cursor.moveToNext()) {
            result += cursor.getString(0) + " : "
                    + "successed word : " + cursor.getString(1)
                    + " / " + "failed word : " + cursor.getString(2)
                    + "\n";
        }

        return result;
    }


}
