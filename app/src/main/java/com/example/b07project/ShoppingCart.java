package com.example.b07project;

import java.util.ArrayList;

public class ShoppingCart extends Order{
    private int cartId;
    private int userId;
    private int totalPrice;
    private int totalQuantity;
    ArrayList<Product> added_to_cart = new ArrayList<Product>();
    ArrayList<Product> removed_from_cart = new ArrayList<Product>();
    ArrayList<Product> updated_in_cart = new ArrayList<Product>();
    boolean added;
    boolean removed;
    boolean updated;
    boolean empty;

    public ShoppingCart(){
        System.out.println("Your shopping cart is empty.");
    }

    public ShoppingCart(int cartId, int userId, int totalPrice, int totalQuantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }
    @Override
    public void addItem(Product product) {
        added_to_cart.add(product);
    }

    @Override
    public void removeItem(Product product) {
        removed_from_cart.remove(product);
    }

    public void updateCartadd(Product product) {
        updated_in_cart.add(product);
    }

    public void updateCartremove(Product product) {
        updated_in_cart.remove(product);
    }

    public void clearCart() {
        added_to_cart.clear();
        removed_from_cart.clear();
        updated_in_cart.clear();
    }
}
