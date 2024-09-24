package com.nhom2.appbantrasua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nhom2.appbantrasua.Entity.Product;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "AppTraSua.db";
    public static final int DATABASE_vERSION = 1;
    private ContentValues contentValues;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_vERSION);
        try {
            QueryData("CREATE TABLE \"Account\" ( \"username\" TEXT NOT NULL, \"password\" TEXT, \"name\" TEXT, \"otp\" TEXT UNIQUE, \"quyen\" TEXT, PRIMARY KEY(\"username\") )");
            QueryData("CREATE TABLE \"Product\" ( \"id\" INTEGER NOT NULL, \"nameproduct\" TEXT, \"description\" TEXT, \"price\" REAL, \"imgprd\" TEXT, PRIMARY KEY(\"id\") )");
        } catch (Exception e) {
            Log.e("Error", "database da ton tai");
        }
    }

    //tao ham thuc hien cau lenh truy van
    public void QueryData(String query) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    //truy van co tra ve du lieu
    public Cursor GetData(String query) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query, null);
    }

//region Account
    public void INSERT_ACCOUNT(String username, String password, String name, String otp, String quyen) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "INSERT INTO Account VALUES(?,?,?,?,?)";
        SQLiteStatement stm = database.compileStatement(query);
        //clear du lieu cu neu co
//        stm.clearBindings();
        stm.bindString(1,username);
        stm.bindString(2,password);
        stm.bindString(3,name);
        stm.bindString(4,otp);
        stm.bindString(5,quyen);
        stm.executeInsert();
    }


//endregion


//region Produc



//endregion


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
