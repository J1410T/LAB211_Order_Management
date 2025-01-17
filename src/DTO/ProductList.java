/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DAO.Products;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ProductList extends ArrayList<Products>{
    private String fName = "src\\Data\\Products";
    public void readFile(){
        File file = new File(fName);
        if (!file.exists()){
            System.out.println("File is not existed!!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                StringTokenizer stk = new StringTokenizer(line, "[|]");
                String productID = stk.nextToken().trim().toUpperCase();
                if (!productID.matches("[pP][0-9]{3}")) continue;
                String productName = stk.nextToken().trim().toUpperCase();
                String unit = stk.nextToken().trim().toUpperCase();
                String orgin = stk.nextToken().trim().toUpperCase();
                double price = Double.parseDouble(stk.nextToken().trim());
                this.add(new Products(productID, productName, unit, orgin, price));
            }
            System.out.println("Read Product File Success!!");
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Cannot Find File");
        }
    }

    public ProductList() {
        this.readFile();
    }

    private void writeFile(){
        try {
            FileWriter fw = new FileWriter(fName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Products p : this) {
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void printAll(){
        for (Products p : this) {
            System.out.println(p);
        }
    }
    
    public Products search(String productId){
        for (Products p : this) {
            if(p.getProductId().equalsIgnoreCase(productId)) return p;
        }
        return null;
    }
    
//    public static void main(String[] args) {
//        ProductList proList = new ProductList();
//        proList.readFile();
//        proList.printAll();
//        proList.writeFile();
//    }
}
