package com.example.productscart.model;

public class Product {

    public String name;
    public int price;
    public int quantity = 0;
    public final int old_price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.old_price = price;

    }
}
