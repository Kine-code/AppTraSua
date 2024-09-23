package com.nhom2.appbantrasua.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom2.appbantrasua.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUserName,fullName,  email, password;
    Button btnRegister;
    private ExecutorService executorService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();


        executorService = Executors.newSingleThreadExecutor();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _username = txtUserName.getText().toString();
                String _pass = password.getText().toString();
                String _email = email.getText().toString();
                sendEmail(_username, _pass, _email);
            }
        });
    }


    public void sendEmail(String _username, String _pass, String _email){
        if (_username.isEmpty() || _pass.isEmpty() || _email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            // Sử dụng ExecutorService để thực hiện việc gửi email trên background thread
            executorService.execute(() -> {
                try {
                    GmailSender sender = new GmailSender("nhompro88@gmail.com", "picz syny ykix mkmh");
                    String verificationCode = generateVerificationCode();
                    sender.sendMail(_email, "Mã xác thực", "Mã xác thực của bạn là: " + verificationCode);

                    // Cập nhật giao diện trên Main Thread sau khi gửi email thành công

                    //sửa lai Mainactivity thành OTP
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("Phone Number", verificationCode);
                    runOnUiThread(() ->
                            Toast.makeText(RegisterActivity.this, "Đã gửi mã xác thực đến email!", Toast.LENGTH_SHORT).show()
                    );
                } catch (MessagingException e) {
                    e.printStackTrace();
                    runOnUiThread(() ->
                            Toast.makeText(RegisterActivity.this, "Gửi email thất bại!", Toast.LENGTH_SHORT).show()
                    );
                }
            });
        }
    }


    private String generateVerificationCode() {
        int randomCode = (int) (Math.random() * 90000) + 1000;
        return String.valueOf(randomCode);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Đảm bảo tắt ExecutorService khi không còn cần thiết
//        if (!executorService.isShutdown()) {
//            executorService.shutdown();
//        }
//    }


    void AnhXa(){

        //Register
        txtUserName = findViewById(R.id.edittextuser);
        fullName = findViewById(R.id.edittextfullname);
        email = findViewById(R.id.edittextemail);
        password = findViewById(R.id.edittextpass);
        btnRegister = findViewById(R.id.buttondangky);

    }
}
