package com.example.b07project;

public class Checkout extends ShoppingCart {
    private int id;
    private int userId;
    private int totalPrice;
    private int totalQuantity;
    private String date;

    public Checkout(int id, int userId, int totalPrice, int totalQuantity, String date) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.date = date;
    }
    //getters and setters for retrieving and setting the values of the private variables
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getTotalPrice() {return totalPrice;}

    public void setTotalPrice(int totalPrice) {this.totalPrice = totalPrice;}

    public int getTotalQuantity() {return totalQuantity;}

    public void setTotalQuantity(int totalQuantity) {this.totalQuantity = totalQuantity;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    @Override
    public String toString() {
        return "Checkout{" + "id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", totalQuantity=" + totalQuantity +
                ", date='" + date + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return id == checkout.id &&
                userId == checkout.userId &&
                totalPrice == checkout.totalPrice &&
                totalQuantity == checkout.totalQuantity &&
                date.equals(checkout.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, totalPrice, totalQuantity, date);
    }
}
