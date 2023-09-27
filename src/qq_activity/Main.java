/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package qq_activity;

import java.util.Scanner;
import qq_activity.model.LoginModel;
import qq_activity.service.LoginService;
import qq_activity.service.ProductService;
import qq_activity.service.LoginServiceImpl;
import qq_activity.service.ProductServiceImpl;

/**
 *
 * @author Rheeey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        initializations
        Scanner scanner = new Scanner(System.in);
        LoginModel user = new LoginModel();
        LoginService loginService = new LoginServiceImpl();
        ProductService productService = new ProductServiceImpl();
         
        
//        create admin and customer account
        loginService.createAccount();
        
        boolean mainMenuScreen = true;
        while (mainMenuScreen){
            System.out.println("***********************");
            System.out.println("* Welcome to My Shop! *");
            System.out.println("***********************");
            System.out.println("1 - Login");
            System.out.println("2 - Change Password");
            System.out.println(".......................");
            System.out.println("0 - Exit\n");

            System.out.print("What do you want to do? : ");

            switch (scanner.nextLine()) {
                case "1" -> {
                    
                    boolean loginScreen = true;
                    while (loginScreen){
                        System.out.println("\n***********************");
                        System.out.println("*        LOGIN        *");
                        System.out.println("***********************\n");

                        System.out.print("Username : ");
                        user.setUsername(scanner.nextLine());

                        System.out.print("Password : ");
                        user.setPassword(scanner.nextLine());

                        loginService.login(user);

        //                --------------ADMIN SIDE----------------
                        if (user.getRole() == null){
                            loginScreen = false;
                        }
                        else if (user.getRole().equals("admin")){
                            
                            boolean adminScreen = true;
                            while (adminScreen){
                                System.out.println("\n***********************");
                                System.out.println("*    ADMINISTRATOR    *");
                                System.out.println("***********************");
                                System.out.println("1 - Manage Products");
                                System.out.println("2 - Manage Orders");
                                System.out.println("........................");
                                System.out.println("0 - Logout\n");

                                System.out.print("What do you want to do : ");

                                switch (scanner.nextLine()){
            //                        Manage Products
                                    case "1" -> {

                                        boolean manageProductsScreen = true;
                                        while (manageProductsScreen){
                                            productService.checkProducts();

                //                            Add or Remove Products
                                            switch (scanner.nextLine()){
                //                                Add Product
                                                case "1" -> {
                                                    productService.addProduct();                                           
                                                }
    //                                            Remove Product
                                                case "2" -> {
                                                    productService.removeProduct();
                                                }     
            //                                    Update Product Details
                                                case "3" -> {
                                                    productService.editProduct();
                                                }
    //                                            Back
                                                case "0" -> {
                                                    manageProductsScreen = false;
                                                }
                                                default -> { 
                                                    System.out.println("Enter a valid number");
                                                }
                                            }
                                        }                               
                                    }
            //                        Manage Orders
                                    case "2" -> {
                                        productService.showAdminOrder();
                                    }
            //                        Logout
                                    case "0" -> {
                                        loginScreen = false;
                                        adminScreen = false;
                                    }
                                    default -> { 
                                        System.out.println("Enter a valid number");
                                    }
                                }
                            }
                            
                        }
        //                --------------CUSTOMER SIDE---------------                
                        else{
                            boolean customerScreen = true;
                            while(customerScreen){
                                System.out.println("\n***********************");
                                System.out.println("*      CUSTOMER       *");
                                System.out.println("***********************");
                                System.out.println("1 - Shop");
                                System.out.println("2 - My Orders");
                                System.out.println("........................");
                                System.out.println("0 - Logout\n");

                                System.out.print("What do you want to do : ");


                                switch (scanner.nextLine()){
    //                                Shop
                                    case "1" -> {
                                        productService.shopProducts();
                                        
                                    }
    //                                My Orders
                                    case "2" ->{
                                        productService.showCustomerOrder();
                                    }
    //                                Logout
                                    case "0" -> {
                                        customerScreen = false;
                                        loginScreen = false;
                                    }
                                    default -> { 
                                        System.out.println("Enter a valid number");
                                    }
                                }
                            }
                            
                        }
                    }
                }
                case "2" -> {
                    System.out.println("\n***********************");
                    System.out.println("*   CHANGE PASSWORD    *");
                    System.out.println("***********************\n");
                    
                    System.out.print("Username : ");
                    String username = scanner.nextLine();
                    System.out.print("Password : ");
                    String oldPassword = scanner.nextLine();
                    System.out.print("New Password : ");
                    String newPassword = scanner.nextLine();
                    
                    loginService.changePassword(username, oldPassword, newPassword);
                }
                case "0" -> { 
                    System.out.println("Thank you for using our services!");
                    mainMenuScreen = false;
                }
                default -> { 
                    System.out.println("Enter a valid number");
                }
            }
        }            
    }   
}
