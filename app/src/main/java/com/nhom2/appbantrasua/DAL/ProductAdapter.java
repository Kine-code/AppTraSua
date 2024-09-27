package com.nhom2.appbantrasua.DAL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.GUI.ProductDetailsActivity;
import com.nhom2.appbantrasua.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    Context context;
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        context = parent.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // định dạng số tiền
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String formattedPrice = numberFormat.format(product.getPrice());

        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText(formattedPrice + " VND");

        if (isBase64(product.getImageResource())){
            byte[] decodedBytes = Base64.decode(product.getImageResource(), Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            holder.productImage.setImageBitmap(decodedBitmap);
        }else {
            int imageResId = context.getResources().getIdentifier(product.getImageResource(), "drawable", context.getPackageName());
            holder.productImage.setImageResource(imageResId);
        }

        // Xử lý khi người dùng nhấn vào sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("product", product);
            v.getContext().startActivity(intent);
        });
    }

    public boolean isBase64(String s) {
        try {
            // Sử dụng Base64 của Android để giải mã chuỗi
            Base64.decode(s, Base64.DEFAULT);
            return true;
        } catch (IllegalArgumentException e) {
            // Bắt lỗi nếu chuỗi không phải Base64 hợp lệ
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productDescription, productPrice;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }



}
