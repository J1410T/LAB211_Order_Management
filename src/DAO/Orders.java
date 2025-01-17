/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Orders {
    private String orderID;
    private String cusID;
    private String productID;
    private int orderQuantity;
    private String orderDate;
    private boolean status;

    public Orders(String orderID, String cusID, String productID, int orderQuantity, String orderDate, boolean status) {
        this.orderID = orderID;
        this.cusID = cusID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        return hash;
//    }

    @Override
    public boolean equals(Object obj) {
        Orders other = (Orders) obj;
        return this.cusID.equalsIgnoreCase(other.cusID);
    }

    
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-10s|%-10s|%-10s|%-5s|%-15s|%-10s", orderID, cusID, productID, orderQuantity, orderDate, status);
    }
    
    
}
