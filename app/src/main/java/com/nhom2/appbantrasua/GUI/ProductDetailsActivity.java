package com.nhom2.appbantrasua.GUI;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.appbantrasua.CartManager;
import com.nhom2.appbantrasua.DAL.CartAdapter;
import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView, quantityTextView;
    private Button addToCartButton,increaseQuatityButtonProduct,
            decreaseQuatityButtonProduct,buttontoppingitem, buttonConfirmTopping;
    int quatity = 1;
    CartAdapter cartAdapter = new CartAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        AnhXa();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product")) {
            Product product = (Product) intent.getSerializableExtra("product");

            // Đảm bảo product không null
            if (product != null) {
                // Hiển thị thông tin sản phẩm
                int imageResId = this.getResources().getIdentifier(product.getImageResource(), "drawable", this.getPackageName());
                productImageView.setImageResource(imageResId);
                productNameTextView.setText(product.getName());
                productDescriptionTextView.setText(product.getDescription());

                // Định dạng giá tiền với dấu chấm
                NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
                String formattedPrice = numberFormat.format(product.getPrice());
                productPriceTextView.setText(formattedPrice + " VND");
            }

// ButtonQuatityProduct

            buttontoppingitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //DialogLogin();
                }
            });


            increaseQuatityButtonProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quatity = Integer.parseInt(quantityTextView.getText().toString());
                    quatity++;
                    quantityTextView.setText(String.valueOf(quatity));
                }
            });


            decreaseQuatityButtonProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quatity = Integer.parseInt(quantityTextView.getText().toString());
                    if(quatity > 0)
                        quatity--;
                    else
                        quatity = 0;
                    quantityTextView.setText(String.valueOf(quatity));
                }
            });


            // Xử lý nút Thêm vào giỏ hàng
            addToCartButton.setOnClickListener(v -> {
                product.setQuality(quatity);
                if(quatity != 0){
                    CartManager.getInstance().LoadAndSaveData(product, getBaseContext());
                    Toast.makeText(this, product.getName() + " đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                    // mở CartActivity khi ấn thêm sản phẩm
//                Intent cartIntent = new Intent(ProductDetailsActivity.this, CartActivity.class);
//                startActivity(cartIntent);
                    finish();
                }else{
                    Toast.makeText(this, "Số lượng phaỉ lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    private void DialogLogin(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_topping);
        buttonConfirmTopping = dialog.findViewById(R.id.confirmButtonTopping);
        buttonConfirmTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailsActivity.this, "OKOKOKKOK", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    void AnhXa(){
        // Initialize UI components
        productImageView = findViewById(R.id.productImageView);
        productNameTextView = findViewById(R.id.productNameTextView);
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        addToCartButton = findViewById(R.id.addToCartButton);

        //Button Increase and decrease
        quantityTextView = findViewById(R.id.quantityTextView);
        increaseQuatityButtonProduct = findViewById(R.id.increaseQuantityButtonProduct);
        decreaseQuatityButtonProduct = findViewById(R.id.decreaseQuantityButton);
        buttontoppingitem = findViewById(R.id.buttontoppingitem);
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
