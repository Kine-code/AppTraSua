package com.nhom2.appbantrasua;


import com.nhom2.appbantrasua.Entity.Product;
import com.nhom2.appbantrasua.GUI.CartActivity;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    public CartActivity CartActvity = new CartActivity();
    private List<Product> cart;

    private CartManager() {
        cart = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }


    public void addToCart(Product product) {
        cart.add(product);
    }

    public List<Product> getCartItems() {
        return cart;
    }
}
