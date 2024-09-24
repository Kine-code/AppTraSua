package com.nhom2.appbantrasua.DAL;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.appbantrasua.DatabaseHelper;
import com.nhom2.appbantrasua.Entity.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DAL_Product implements Serializable {

    private static DatabaseHelper database;
    private Context context;

    public void InitLogin(Context context) {
        database = new DatabaseHelper(context);
        this.context = context;
    }

    public List<Product> ShowListProduct(){
        List<Product> list = new ArrayList<>();
        Cursor cursor = database.GetData("SELECT * FROM Product");
        try {
            if(cursor.getCount() > 0){

                while (cursor.moveToNext()){
                    list.add(
                            new Product(cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getDouble(3),
                                    cursor.getString(4))
                    );
                }

            }
        } finally {
            cursor.close(); // Đảm bảo đóng con trỏ
        }

        Log.e("LISTTTTTTTT",  String.valueOf(list.size()));

        return list;
    }
}
