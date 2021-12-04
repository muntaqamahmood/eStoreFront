package com.example.b07project;

import java.io.Serializable;

public class Product implements Serializable {

    public String name;
    public String brand;
    public float price;

    public Product(){}

    public Product(String name, String brand, float price){
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    @Override
    public int hashCode(){
        return (int)(name.length() + brand.length()* 100);
    }

    @Override
    public String toString(){
        return brand + ' ' + name + ", $"  + price;
    }
}
