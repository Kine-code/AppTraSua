package com.nhom2.appbantrasua.DAL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.appbantrasua.CartManager;
import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.GUI.CartActivity;
import com.nhom2.appbantrasua.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItems;
    int quatity = 1;

    CartActivity _cartActivity;

    public CartAdapter(Context context, List<Product> cartItems, CartActivity cartActivity) {
        this.context = context;
        _cartActivity = cartActivity;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        int imageResId = context.getResources().getIdentifier(product.getImageResource(), "drawable", context.getPackageName());
        holder.productImageView.setImageResource(imageResId);
        holder.productNameTextView.setText(product.getName());
        holder.productPriceTextView.setText(product.getPrice() + " VND");
        holder.productQualityTextView.setText(String.valueOf(product.getQuality()));



//Start Button increase and decrase Cart
        holder.increaseQuantityButtonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quatity = Integer.parseInt(holder.productQualityTextView.getText().toString());
                quatity++;
                holder.productQualityTextView.setText(String.valueOf(quatity));
                product.setQuality(quatity);
                _cartActivity.TotalAmount();
            }
        });


        holder.decreaseQuantityButtonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quatity = Integer.parseInt(holder.productQualityTextView.getText().toString());
                if(quatity > 0)
                    quatity--;

                if(quatity == 0){
                    Toast.makeText(context, "Sản phẩm này đã được xóa", Toast.LENGTH_SHORT).show();
                    cartItems.remove(holder.getAdapterPosition());
                    _cartActivity.ReLoadRycyclerView(cartItems);
                }
                holder.productQualityTextView.setText(String.valueOf(quatity));
                product.setQuality(quatity);
                _cartActivity.TotalAmount();
            }
        });
    }
//End

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;
        TextView productPriceTextView;
        TextView productQualityTextView;
        Button decreaseQuantityButtonCart, increaseQuantityButtonCart;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.cartProductImageView);
            productNameTextView = itemView.findViewById(R.id.cartProductNameTextView);
            productPriceTextView = itemView.findViewById(R.id.cartProductPriceTextView);
            productQualityTextView = itemView.findViewById(R.id.quantityTextView);
            decreaseQuantityButtonCart = itemView.findViewById(R.id.decreaseQuantityButtonCart);
            increaseQuantityButtonCart = itemView.findViewById(R.id.increaseQuantityButtonCart);
        }
    }
}
