package com.nhom2.appbantrasua.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nhom2.appbantrasua.DAO.DAO_Product;
import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.ImageSliderAdapter;
import com.nhom2.appbantrasua.DAL.ProductAdapter;
import com.nhom2.appbantrasua.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private ImageSliderAdapter imageSliderAdapter;

// region DAO
    DAO_Product daoProduct = new DAO_Product();


// endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Setup ViewPager for Image Slider
        viewPager = findViewById(R.id.viewPager);
        List<Integer> imageList = Arrays.asList(R.drawable.slider1, R.drawable.slider2, R.drawable.slider3);
        imageSliderAdapter = new ImageSliderAdapter(this, imageList);
        viewPager.setAdapter(imageSliderAdapter);

        daoProduct.InitLogin(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = daoProduct.ShowListProduct();
////        // data tĩnh
        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_cart) {
                    Intent intent = new Intent(MainActivity.this, CartActivity.class);
                    Toast.makeText(MainActivity.this, "Giỏ hàng được chọn", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (id == R.id.nav_home) {
                    // Xử lý mục Đồ uống
                    Toast.makeText(MainActivity.this, "Trở về trang chủ", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_contact) {
                    // Xử lý mục Liên hệ
                    Toast.makeText(MainActivity.this, "Liên hệ được chọn", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_account) {
                    // Xử lý mục Lịch sử
                    Toast.makeText(MainActivity.this, "Tài khoản được chọn", Toast.LENGTH_SHORT).show();
                }
                return ;
            }

        });
    }
    }

