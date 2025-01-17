/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DAO.Customers;
import Service.InputService;
import Service.MenuService;
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
public class CustomerList extends ArrayList<Customers> {

    private String fName = "src\\Data\\Customers";

    public void readFile() {
        File file = new File(fName);
        if (!file.exists()) {
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
                String cusID = stk.nextToken().trim().toUpperCase();
                if (!cusID.matches("[cC][0-9]{3}")) continue;
                String cusName = stk.nextToken().trim().toUpperCase();
                String cusAdd = stk.nextToken().trim().toUpperCase();
                String cusPhone = stk.nextToken().trim().toUpperCase();
                this.add(new Customers(cusID, cusName, cusAdd, cusPhone));
            }
            System.out.println("Read Customer File Success!!");
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Cannot Find File");
        }
    }

    public CustomerList() {
        this.readFile();
    }

    public void writeFile(Customers cus) {
        try {
            FileWriter fw = new FileWriter(fName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(cus.toString());
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("None");
        }
    }

    public void writeFile() {
        try {
            FileWriter fw = new FileWriter(fName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Customers cus : this) {
                bw.write(cus.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("None");
        }
    }

    public void printAll() {
        for (Customers c : this) {
            System.out.println(c);
        }
    }

    public Customers search(String cusId) {
        for (Customers cus : this) {
            if (cusId.equalsIgnoreCase(cus.getCusID())) {
                return cus;
            }
        }
        return null;
    }

    public void search() {
        String cusId = InputService.inputString("Enter Customer ID: ");
        Customers cus = search(cusId);
        if (cus == null) {
            System.out.println("Customer " + cusId + " does not exist");
        } else {
            System.out.println(cus);
        }
    }

    public void addCus() {
        String cusID;
        String cusName;
        String cusAdd;
        String cusPhone;
        do {
            cusID = InputService.inputPattern("Enter Customer ID: ", "[cC][0-9]{3}", "Format is Cxxx (x is digit)!").trim().toUpperCase();
        } while (search(cusID) != null);
        cusName = InputService.inputString("Enter Customer name: ").trim().toUpperCase();
        cusAdd = InputService.inputString("Enter Customer address: ").trim().toUpperCase();
        cusPhone = InputService.inputPattern("Enter Customer phone: ", "[0-9]{10,12}", "Phone contains from 10 to 12 digits").trim().toUpperCase();
        Customers cus = new Customers(cusID, cusName, cusAdd, cusPhone);
        this.add(cus);
        this.writeFile(cus);
        System.out.println("ADD SUCCESSFUL!!");
    }

    public void updateCus() {
        System.out.print("Enter Customer ID: ");
        String tmp;
        String cusId = InputService.SC.nextLine();
        Customers cus = search(cusId);
        if (cus == null) {
            System.out.println("Customer " + cusId + " does not exist!!");
            return;
        }
        MenuService updateChoice = new MenuService("Element you want to change: ", "Enter your choice: ");
            updateChoice.addOptions("Name", "Address", "Phone", "Quit");
            int choice;
            do {
                updateChoice.display();
                choice = updateChoice.getUserChoice();
                switch (choice){
                    case 1:
                        System.out.println("Old name: " + cus.getCusName());
                        System.out.print("Enter new name: ");
                        tmp = InputService.SC.nextLine().toUpperCase();
                        if (tmp != "") {
                            cus.setCusName(tmp);
                            if (cus.getCusName().equals(tmp)){
                                System.out.println("UPDATE SUCCESSFUL!!");
                            }
                            else {
                                System.out.println("UPDATE FAILED!!");
                            }
                        } else {
                            System.out.println("UPDATE FAILED!!");
                        }
                        break;
                    case 2:
                        System.out.println("Old Address: " + cus.getCusAdd());
                        System.out.print("Enter new Address: ");
                        tmp = InputService.SC.nextLine().toUpperCase();
                        if (tmp != "") {
                            cus.setCusAdd(tmp);
                            if (cus.getCusAdd().equals(tmp)){
                                System.out.println("UPDATE SUCCESSFUL!!");
                            }
                            else {
                                System.out.println("UPDATE FAILED!!");
                            }
                        } else {
                            System.out.println("UPDATE FAILED!!");
                        }
                        break;
                    case 3:
                        System.out.println("Old Phone: " + cus.getCusPhone());
                        System.out.print("Enter new phone: ");
                        tmp = InputService.SC.nextLine().toUpperCase();
                        if (!"".equals(tmp)) {
                            cus.setCusPhone(tmp);
                            if (cus.getCusPhone().equals(tmp)){
                                System.out.println("UPDATE SUCCESSFUL!!");
                            }
                            else {
                                System.out.println("UPDATE FAILED!!");
                            }
                        } else {
                            System.out.println("UPDATE FAILED!!");
                        }
                        break;
                }
            } while (choice < 4);
    }
}
