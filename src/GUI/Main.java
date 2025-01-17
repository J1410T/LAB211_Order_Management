
package GUI;

import DTO.CustomerList;
import DTO.OrderList;
import DTO.ProductList;
import Service.MenuService;

/**
 *
 * @author Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomerList cusList = new CustomerList();
        ProductList proList = new ProductList();
        OrderList orderList = new OrderList(cusList, proList);
        
        MenuService choiceMenu = new MenuService("TOOLS OF ORDER MANAGEMENT", "Enter your choice: ");
        choiceMenu.addOptions("List all poducts",
                "List all Customers",
                "Search a Customer based on his/her ID",
                "Add a Customer",
                "Update a Customer",
                "Save Customers to the file",
                "List all Orders in ascending order of Customer name",
                "List all Pending Orders",
                "Add an Order",
                "Update an Order",
                "Save Orders to File",
                "Quit");
        int choice;
        do {            
            choiceMenu.display();
            choice = choiceMenu.getUserChoice();
            switch(choice){
                case 1:
                    proList.printAll();
                    break;
                case 2:
                    cusList.printAll();
                    break;
                case 3:
                    cusList.search();
                    break;
                case 4:
                    cusList.addCus();
                    break;
                case 5:
                    cusList.updateCus();
                    break;
                case 6:
                    cusList.writeFile();
                    break;
                case 7:
                    orderList.printAll();
                    break;
                case 8:
                    orderList.printPending();
                    break;
                case 9:
                    orderList.addOrder();
                    break;
                case 10:
                    orderList.updateOrder();
                    break;
                case 11:
                    orderList.writeFile();
                    break;
            }
        } while (choice != 12);
    }
    
}
