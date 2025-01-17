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
public class Customers {
    private String cusID;
    private String cusName;
    private String cusAdd;
    private String cusPhone;

    
    public Customers(String cusID, String cusName, String cusAdd, String cusPhone) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusAdd = cusAdd;
        this.cusPhone = cusPhone;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        return hash;
//    }

    @Override
    public boolean equals(Object obj) {
        Customers other = (Customers) obj;
        return this.cusID.equalsIgnoreCase(other.cusID);
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = (cusName.length() > 0)? cusName: this.cusName;
    }

    public String getCusAdd() {
        return cusAdd;
    }

    public void setCusAdd(String cusAdd) {
        this.cusAdd = (cusAdd.length() > 0)? cusAdd: this.cusAdd;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        
        this.cusPhone = (cusPhone.matches("[0-9]{10,12}") && cusPhone.length() > 0)? cusPhone: this.cusPhone;
    }

    @Override
    public String toString() {
//        return "Customers{" + "cusID=" + cusID + ", cusName=" + cusName + ", cusAdd=" + cusAdd + ", cusPhone=" + cusPhone + '}';
        return String.format("%-10s|%-30s|%-20s|%-10s", cusID, cusName, cusAdd, cusPhone);
    }
    
    
}
