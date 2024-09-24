package com.nhom2.appbantrasua.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class db_datacontext extends SQLiteOpenHelper {
    private static String DB_NAME = "AppTraSua.db"; // Tên database
    private static String DB_PATH = "";
    private final Context context;
    private SQLiteDatabase database;

    public db_datacontext(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

    }

    private void createDatabase(){
        if(!checkDatabase()){
            this.getReadableDatabase();
            try{
                copyDatabase();
                Log.d("Database_Helper", "Database copied");
            }catch (Exception e){
                Log.e("Database_Helper", "Err copying database!", e);
            }
        }
    }
    private boolean checkDatabase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDatabase() throws Exception{
        InputStream inputStream = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
