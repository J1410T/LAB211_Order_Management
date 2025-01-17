/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DTO.ProductList;
import DAO.Orders;
import Service.InputService;
import Service.MenuService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author Asus
 */
public final class OrderList extends ArrayList<Orders> {

    private final String fName = "src\\Data\\Orders";
    private final CustomerList cusList;
    private final ProductList proList;

    public OrderList(CustomerList cusList, ProductList proList) {
        this.cusList = cusList;
        this.proList = proList;
        this.readFile();
    }

    public void readFile() {
        File file = new File(fName);
        if (!file.exists()) {
            System.out.println("File is not Existed!!");
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
                String orderID = stk.nextToken().trim().toUpperCase();
                if (!orderID.matches("[dD][0-9]{3}")) {
                    continue;
                }
                String cusID = stk.nextToken().trim().toUpperCase();
                if (cusList.search(cusID) == null) {
                    continue;
                }
                String productID = stk.nextToken().trim().toUpperCase();
                if (proList.search(productID) == null) {
                    continue;
                }
                int orderQuantity = Integer.parseInt(stk.nextToken().trim());
                String orderDate = stk.nextToken().trim().toUpperCase();
                boolean status = Boolean.parseBoolean(stk.nextToken().trim());
                this.add(new Orders(orderID, cusID, productID, orderQuantity, orderDate, status));
            }
            System.out.println("Read Order File Success!!");
            fr.close();
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Cannot Find File");
        }
    }

    public void writeFile(Orders order) {
        try {
            FileWriter fw = new FileWriter(fName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(order.toString());
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
            for (Orders o : this) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("SAVE SUCCESSFUL!!");
            System.out.println("");
        } catch (IOException ex) {
            System.out.println("SAVE FAILED!!");
            System.out.println("");
        }
    }

    public void printAll() {
        Comparator c = new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return cusList.search(o1.getCusID()).getCusName().compareToIgnoreCase(cusList.search(o2.getCusID()).getCusName());
            }

        };
        Collections.sort(this, c);
        System.out.println("");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+LIST OF ORDER-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(String.format("%-10s|%-10s|%-10s|%-5s|%-15s|%-10s", "Order ID", "Cus ID", "Product ID", "Quan", "Date", "Status"));
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        for (Orders o : this) {
            System.out.println(o);
        }
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public void printPending() {
        System.out.println("");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+LIST OF PENDING ORDER-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(String.format("%-10s|%-10s|%-10s|%-5s|%-15s|%-10s", "Order ID", "Cus ID", "Product ID", "Quan", "Date", "Status"));
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        for (Orders o : this) {
            if (o.isStatus() == false) {
                System.out.println(o);
            }
        }
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public Orders search(String orderID) {
        for (Orders o : this) {
            if (o.getOrderID().equalsIgnoreCase(orderID)) {
                return o;
            }
        }
        return null;
    }

    public void addOrder() {
        String orderID;
        String cusID;
        String productID;
        boolean check = true;
        do {
            do {
                orderID = InputService.inputPattern("Enter Order ID: ", "[dD][0-9]{3}", "Format is Dxxx (x is digit)!").trim().toUpperCase();
            } while (search(orderID) != null);
            do {
                cusList.printAll();
                cusID = InputService.inputPattern("Enter Customer ID: ", "[cC][0-9]{3}", "Format is Cxxx (x is digit)!").trim().toUpperCase();
            } while (cusList.search(cusID) == null);
            do {
                proList.printAll();
                productID = InputService.inputPattern("Enter Product ID: ", "[pP][0-9]{3}", "Format is Cxxx (x is digit)!").trim().toUpperCase();
            } while (proList.search(productID) == null);
            int orderQuantity = InputService.inputInt("Enter Quantity: ", 0, Integer.MAX_VALUE);
            String orderDate = InputService.inputDate("Enter Date: ");
            boolean status = InputService.inputBoolean("Enter Status (T/F): ");
            Orders order = new Orders(orderID, cusID, productID, orderQuantity, orderDate, status);
            this.add(order);
            this.writeFile(order);
            System.out.println("ADD SUCESSFULL");
            System.out.println("");
            check = InputService.inputBoolean("Do you wanna continue? (Y/N)");
        }  while(check);

    }

    public void updateOrder() {
        System.out.print("Enter Order ID: ");
        String orderId = InputService.SC.nextLine();
        Orders order = search(orderId);
        if (order == null) {
            System.out.println("Order does not exist. FAILED!!");
            return;
        }
        System.out.println("Old information: ");
        System.out.println(order);
        MenuService updateService = new MenuService("Update: ", "Enter your choice: ");
        updateService.addOptions("Update Order information", "Delete Order");
        updateService.display();
        int choice = updateService.getUserChoice();
        switch (choice) {
            case 1:
                String tmp;
                System.out.print("Enter customer ID: ");
                String cusID = InputService.SC.nextLine().trim().toUpperCase();
                if (cusList.search(cusID) != null) {
                    order.setCusID(cusID);
                    System.out.println("UPDATE SUCCESSFULL!!");
                } else {
                    System.out.println("UPDATE FAILED!!");
                }

                System.out.print("Enter Product ID: ");
                String productID = InputService.SC.nextLine().trim().toUpperCase();
                if (proList.search(productID) != null) {
                    order.setProductID(productID);
                    System.out.println("UPDATE SUCCESSFULL!!");
                } else {
                    System.out.println("UPDATE FAILED!!");
                }

                System.out.print("Enter Quantity: ");
                tmp = InputService.SC.nextLine().trim();
                if (InputService.isNumberic(tmp) && Integer.parseInt(tmp) >= 0) {
                    order.setOrderQuantity(Integer.parseInt(tmp));
                    System.out.println("UPDATE SUCCESSFULL!!");
                } else {
                    System.out.println("UPDATE FAILED!!");
                }

                System.out.print("Enter order date: ");
                tmp = InputService.SC.nextLine().trim();
                if (InputService.isDate(tmp)) {
                    order.setOrderDate(tmp);
                    System.out.println("UPDATE SUCCESSFULL!!");
                } else {
                    System.out.println("UPDATE FAILED!!");
                }

                boolean status;
                tmp = InputService.inputPattern("Enter status of order: ", "[TtFf\n]", "");
                if (tmp != "") {
                    status = (tmp.equalsIgnoreCase("T"));
                    order.setStatus(status);
                    System.out.println("UPDATE SUCCESSFULL!!");
                } else {
                    System.out.println("UPDATE FAILED!!");
                }
                break;
            case 2:
                this.remove(order);
                System.out.println("REMOVE SUCCESSFULL!!");
                break;

        }
    }

}
