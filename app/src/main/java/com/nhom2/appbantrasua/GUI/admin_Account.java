package com.nhom2.appbantrasua.GUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nhom2.appbantrasua.R;

public class admin_Account extends Fragment {

    EditText txt_idSanPham, txt_tenSanPham, txt_giaCa, txt_moTa;

    public admin_Account() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin__account, container, false);
    }
}