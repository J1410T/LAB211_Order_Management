/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Products {
    private String productId;
    private String productName;
    private String unit;
    private String origin;
    private double price;

    public Products(String productId, String productName, String unit, String origin, double price) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        return hash;
//    }

    @Override
    public boolean equals(Object obj) {
        final Products other = (Products) obj;
        return this.productId.equalsIgnoreCase(other.productId);
    }

    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
//       return "Products{" + "productId=" + productId + ", productName=" + productName + ", unit=" + unit + ", origin=" + origin + ", price=" + price + '}';
        return String.format("%-10s|%-30s|%-30s|%-30s|%-10s", productId, productName, unit, origin, price);
    }
    
    
}
