package com.nhom2.appbantrasua.DAO;

import android.content.Context;

import com.nhom2.appbantrasua.DAL.DAL_LoginRegister;

import java.io.Serializable;

public class DAO_LoginRegister implements Serializable {
    DAL_LoginRegister dal = new DAL_LoginRegister();

    public void InitLogin(Context context){
        dal.InitLogin(context);
    }

    public void InsertAccount(String username, String password, String name, String otp, String quyen){
        dal.InsertAccount(username, password, name, otp, quyen);
    }

    public boolean checkAccount(String userName, String password){
        return dal.checkAccount(userName, password);
    }
}
