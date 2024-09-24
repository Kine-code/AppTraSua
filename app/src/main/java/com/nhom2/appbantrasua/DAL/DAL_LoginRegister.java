package com.nhom2.appbantrasua.DAL;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom2.appbantrasua.DatabaseHelper;
import com.nhom2.appbantrasua.Entity.LoginRegister;

import java.io.Serializable;
import java.util.ArrayList;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DAL_LoginRegister implements Serializable {

    public static DatabaseHelper database;
    public Context context;

    public void InitLogin(Context context) {
        database = new DatabaseHelper(context);
        this.context = context;
    }

    public void InsertAccount(String username, String password, String name, String otp, String quyen) {
        database.INSERT_ACCOUNT(username, password, name, otp, quyen);
    }

    public boolean checkAccount(String userName, String password) {

        Cursor cursor = database.GetData( String.format("SELECT * FROM Account WHERE userName = '%s' AND password = '%s'" , userName,  password));

        Log.e("Account", String.valueOf( cursor.getCount()));

        if( cursor.getCount() > 0){
            Log.e("Account", "11111111");
            return true;
        }
        return false;
    }

}
