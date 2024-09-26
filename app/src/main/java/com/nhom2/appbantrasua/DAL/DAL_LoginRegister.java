package com.nhom2.appbantrasua.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nhom2.appbantrasua.DatabaseHelper;
import com.nhom2.appbantrasua.Entity.LoginRegister;

import java.io.Serializable;

import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DAL_LoginRegister implements Serializable {

    public static DatabaseHelper databaseHelper;
    private Context context;
    private ContentValues contentValues;

    public void InitLogin(Context context) {
        databaseHelper = new DatabaseHelper(context);
        this.context = context;
    }

    //region Account
    public void InsertAccount(String username, String password, String name, String otp, String quyen) {
        String query = "INSERT INTO Account VALUES(?,?,?,?,?)";
        SQLiteStatement stm = databaseHelper.openDB().compileStatement(query);
        //clear du lieu cu neu co
//        stm.clearBindings();
        stm.bindString(1,username);
        stm.bindString(2,password);
        stm.bindString(3,name);
        stm.bindString(4,otp);
        stm.bindString(5,quyen);
        stm.executeInsert();
    }

    public LoginRegister checkAccount(String userName, String password) {

        Cursor cursor = databaseHelper.GetData( String.format("SELECT * FROM Account WHERE userName = '%s' AND password = '%s'" , userName,  password));

        Log.e("Account", String.valueOf( cursor.getCount()));

        if( cursor.getCount() > 0){
            try {
                while (cursor.moveToNext()){
                    return new LoginRegister(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                }
            } finally {
                cursor.close(); // Đảm bảo đóng con trỏ
            }
        }
        return null;
    }

    public boolean ChangePasswordAccount(String userName, String newPassword){
        contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        int check = databaseHelper.openDB().update("Account", contentValues, "userName = ?", new String[] {userName});
        if(check == 0){
            return false;
        }else
        {
            return true;
        }
    }


}
