package com.nhom2.appbantrasua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private TextView paymentSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//        paymentSummaryTextView = findViewById(R.id.paymentSummaryTextView);
//
//        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra("totalPrice")) {
//            int totalPrice = intent.getIntExtra("totalPrice", 0);
//
//            // Format total price with thousands separator
//            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
//            String formattedPrice = numberFormat.format(totalPrice);
//            paymentSummaryTextView.setText("Tổng tiền cần thanh toán: " + formattedPrice + " VND");
//        }
    }
}
