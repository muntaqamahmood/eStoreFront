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
        return (int)(name.length() + price* 100);
    }

    @Override
    public String toString(){
        return name + ',' + brand +',' + price;
    }

    /***
    public String getName(){return name;}
    public String getBrand(){return brand;}
    public String getPrice(){return String.valueOf(price);}

    public void setPrice(float price){this.price = price;}
    public void setBrand(String brand){this.brand = brand;}
    public void setPrice(String name){this.name = name;}
     ***/
}
