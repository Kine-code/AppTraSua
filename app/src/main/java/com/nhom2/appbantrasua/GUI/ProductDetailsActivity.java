package com.nhom2.appbantrasua.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.appbantrasua.CartManager;
import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView;
    private Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize UI components
        productImageView = findViewById(R.id.productImageView);
        productNameTextView = findViewById(R.id.productNameTextView);
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        addToCartButton = findViewById(R.id.addToCartButton);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product")) {
            Product product = (Product) intent.getSerializableExtra("product");

            // Đảm bảo product không null
            if (product != null) {
                // Hiển thị thông tin sản phẩm
                productImageView.setImageResource(product.getImageResource());
                productNameTextView.setText(product.getName());
                productDescriptionTextView.setText(product.getDescription());

                // Định dạng giá tiền với dấu chấm
                NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
                String formattedPrice = numberFormat.format(product.getPrice());
                productPriceTextView.setText(formattedPrice + " VND");
            }
            // Xử lý nút Thêm vào giỏ hàng
            addToCartButton.setOnClickListener(v -> {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(this, product.getName() + " đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                // mở CartActivity khi ấn thêm sản phẩm
//                Intent cartIntent = new Intent(ProductDetailsActivity.this, CartActivity.class);
//                startActivity(cartIntent);
                finish();
            });
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
