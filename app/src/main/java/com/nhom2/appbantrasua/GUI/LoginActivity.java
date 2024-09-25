package com.nhom2.appbantrasua.GUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom2.appbantrasua.DAO.DAO_LoginRegister;
import com.nhom2.appbantrasua.Entity.LoginRegister;
import com.nhom2.appbantrasua.R;

import java.util.concurrent.ExecutorService;


public class LoginActivity extends AppCompatActivity
{
    Button btnNextPageRegister, btnDangNhap;
    EditText userNameAccount, passwordAccout;
    private ExecutorService executorService;
    DAO_LoginRegister daoRegister = new DAO_LoginRegister();
    AccountActivity accountActivity;
    public LoginRegister account;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        daoRegister.InitLogin(this);
        btnNextPageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAccount(userNameAccount.getText().toString().trim(), passwordAccout.getText().toString().trim());
            }
        });
    }

    private void CheckAccount(String userName, String password){

        if(userName.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }
        try{
            if(daoRegister.checkAccount(userName, password) != null){
                account = daoRegister.checkAccount(userName, password);
                AccountActivity.getInstance().account = this.account;
                        goToMainActivity();
            }else{
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }


    }

     private void goToMainActivity (){
         Intent intent = new Intent(this, MainActivity.class);
         startActivity(intent);
    }

    void AnhXa(){
        //Login
        userNameAccount = findViewById(R.id.edittextuser);
        passwordAccout = findViewById(R.id.edittextpass);

        btnNextPageRegister = findViewById(R.id.buttonchuyentrangdangky);
        btnDangNhap = findViewById(R.id.buttondangnhap);
    }
}
