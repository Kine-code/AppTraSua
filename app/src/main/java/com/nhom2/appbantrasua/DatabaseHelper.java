package com.nhom2.appbantrasua;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper DATA;

    private Context context;
    public static final String DATABASE_NAME = "BanTraSua.Sqlite";
    public static final int DATABASE_vERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_vERSION);
    }

    //tao ham thuc hien cau lenh truy van
    public void QueryData(String query){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    //truy van co tra ve du lieu
    public Cursor GetData(String query){
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(query,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
