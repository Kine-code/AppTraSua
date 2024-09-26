package com.nhom2.appbantrasua.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.appbantrasua.DAL.CartAdapter;
import com.nhom2.appbantrasua.R;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {
    TextView fullname, price;
    EditText address, phoneNumber;

    Button paymentCancel, buttonPaymentConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Init();
        fullname.setText(AccountActivity.getInstance().account.getName());
        String _price = getIntent().getStringExtra("totalPrice");
        price.setText(_price + " VND");

        buttonPaymentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(address.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()){
                    Toast.makeText(PaymentActivity.this, "Phải điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PaymentActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    CartAdapter cart = new CartAdapter();
                    cart.cartItems.clear();
                    Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        paymentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    void Init(){
        fullname = findViewById(R.id.edittextfullnamepayment);
        price = findViewById(R.id.textpricepayment);
        address = findViewById(R.id.textaddresspayment);
        phoneNumber = findViewById(R.id.textphonepayment);
        buttonPaymentConfirm = findViewById(R.id.buttonconfirmpayment);
        paymentCancel = findViewById(R.id.buttoncancelpayment);
    }

}
