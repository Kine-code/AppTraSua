package com.nhom2.appbantrasua.DAO;

import android.content.Context;
import android.util.Log;

import com.nhom2.appbantrasua.DAL.DAL_Product;
import com.nhom2.appbantrasua.Entity.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DAO_Product implements Serializable {
    DAL_Product dal = new DAL_Product();

    public void InitLogin(Context context){
        dal.InitLogin(context);
    }

    public List<Product> ShowListProduct(){


        Log.e("PRODUCTddddddd", String.valueOf(dal.ShowListProduct().size()));
        return dal.ShowListProduct();
    }
}
