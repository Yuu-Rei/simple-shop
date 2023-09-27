/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qq_activity.service;
import java.util.Scanner;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import qq_activity.model.ProductModel;
import qq_activity.model.OrderModel;

/**
 *
 * @author Rheeey
 */
public class ProductServiceImpl implements ProductService{
    private List<ProductModel> products = new ArrayList<>();  
    private List<OrderModel> orders = new ArrayList<>(); 
    ProductModel product = new ProductModel();
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void checkProducts() {
        
        if(products.isEmpty()){
            System.out.println("\n***********************");
            System.out.println("*      PRODUCTS       *");
            System.out.println("***********************");
            System.out.println("No products found.");
            System.out.println("........................");
            System.out.println("1 - Add New Product");
            System.out.println("2 - Remove Product");
            System.out.println("0 - Back\n");
            
            System.out.print("What do you want to do : ");
        }
        else{
          
            System.out.println("\n***********************");
            System.out.println("*      PRODUCTS       *");
            System.out.println("***********************");
            System.out.println("ID\tName\t\t\tPrice");

            for (ProductModel productItem : products) {
                System.out.printf("%d\t%s\t\t\t%.2f%n", productItem.getProductId(), productItem.getProductName(), productItem.getProductPrice());
            }
            
            System.out.println("........................");
            System.out.println("1 - Add New Product");
            System.out.println("2 - Remove Product");
            System.out.println("3 - Update Product Details");
            System.out.println("0 - Back\n");
            
            System.out.print("What do you want to do : ");
        }
    }

    @Override
    public void addProduct() {
        String productName = null;
        BigDecimal productPrice;
        
        boolean enterName = true;
        while (enterName){
            System.out.print("Name: ");
            productName = scanner.nextLine();

            if (productName.isBlank()){
                System.out.println("ERROR: Invalid input.");
            }
            else {
                if (products.isEmpty()){
                    enterName = false;    
                }
                else {
                    for (ProductModel productItem : products) {
                        if (productItem.getProductName().toLowerCase().equals(productName.toLowerCase())){
                            System.out.println("ERROR: Invalid product name, Product already exists.");
                            break;
                        }
                        else {
                            enterName = false;    
                        }
                    }
                }
            }
        }

        boolean enterPrice = true;
        while (enterPrice){
            System.out.print("Price: ");

            if (scanner.hasNextLine()){
                String priceInput = scanner.nextLine().trim();

                if (!priceInput.isEmpty()) {
                    if (priceInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                        productPrice = new BigDecimal(priceInput);
                        
                        boolean confirmationMessage = true;
                        while (confirmationMessage) {
                            System.out.print("Are you sure you want to add this product (Y/N) : ");
                            String confirmation = scanner.nextLine().toLowerCase();

                            if (confirmation.equals("y")) {
                                Integer newId = products.size() + 1;
                                products.add(new ProductModel(newId, productName, productPrice));
                                
                                System.out.println("Product added successfully!");
                                System.out.print("\nPress \"ENTER\" to continue...");
                                try {
                                    System.in.read();
                                } catch (IOException e) {
                                    // do nothing
                                }

                                enterPrice = false;
                                confirmationMessage = false;
                            } 
                            else if (confirmation.equals("n")) {
                                System.out.println("Action canceled.");
                                System.out.print("\nPress \"ENTER\" to continue...");
                                try {
                                    System.in.read();
                                } catch (IOException e) {
                                    // do nothing
                                }

                                enterPrice = false;
                                confirmationMessage = false;
                            } 
                            else {
                                System.out.println("ERROR: Invalid character.");
                            }
                        }
                    } 
                    else {
                        System.out.println("ERROR: Invalid number.");
                    }
                } 
                else {
                    System.out.println("ERROR: Invalid input.");
                }
            } 
            else {
                System.out.println("ERROR: Invalid input.");
            } 
        }
    }

    @Override
    public void removeProduct() {
        System.out.println("***********************");
        System.out.println("*    REMOVE PRODUCT   *");
        System.out.println("***********************");

        boolean enterId = true;
        while (enterId){
            System.out.print("Product ID : ");

            if (scanner.hasNextLine()){
                String idInput = scanner.nextLine().trim();

                if (!idInput.isEmpty()) { // Check if input is not empty
                    if (idInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                        Integer productId = Integer.valueOf(idInput);
                        product.setProductId(productId);
                        
                        productId = productId - 1;
        
                        // Added ID check feature to check if a product with input ID exists in the list :)
                        if (productId >= 0 && productId < products.size()) {


                            boolean confirmationMessage = true;
                            while (confirmationMessage) {
                                System.out.print("Are you sure you want to remove this product (Y/N) : ");
                                String confirmation = scanner.nextLine().toLowerCase();

                                if (confirmation.equals("y")) {
                                    products.remove(productId.intValue());
                                    System.out.println("Product added successfully!");
                                    System.out.print("\nPress \"ENTER\" to continue...");
                                    try {
                                        System.in.read();
                                    } catch (IOException e) {
                                        // do nothing
                                    }

                                    confirmationMessage = false;
                                } 
                                else if (confirmation.equals("n")) {
                                    System.out.println("Action canceled.");
                                    System.out.print("\nPress \"ENTER\" to continue...");
                                    try {
                                        System.in.read();
                                    } catch (IOException e) {
                                        // do nothing
                                    }

                                    confirmationMessage = false;
                                } 
                                else {
                                    System.out.println("ERROR: Invalid character.");
                                }
                            }
                        }
                        else {
                            System.out.println("Invalid ID to remove.");  
                            System.out.print("\nPress \"ENTER\" to continue...");
                            try {
                                System.in.read();
                            } catch (IOException e) {
                                // do nothing
                            }
                        } 
                        
                        updateProductIds();

                        enterId = false;
                    } 
                    else {
                        System.out.println("ERROR: Invalid number.");
                    }
                } 
                else {
                    System.out.println("ERROR: Invalid input.");
                }
            } 
            else {
                System.out.println("ERROR: Invalid input.");
            } 
        }   
    }
    
    @Override
    public void editProduct() {
        System.out.println("\n***********************");
        System.out.println("*    UPDATE PRODUCT    *");
        System.out.println("***********************");
        
        boolean enterId = true;
        while (enterId){
            System.out.print("Product ID : ");

            if (scanner.hasNextLine()){
                String idInput = scanner.nextLine().trim();

                if (!idInput.isEmpty()) { // Check if input is not empty
                    if (idInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                        Integer productId = Integer.valueOf(idInput);
                        product.setProductId(productId);
                        
                        productId = productId - 1;
        
                        // Added ID check feature to check if a product with input ID exists in the list :)
                        if (productId >= 0 && productId < products.size()) {

                            boolean confirmationMessage = true;
                            while (confirmationMessage) {
                                
                                // NAME AND PRICE INPUT
                                String productName = null;
                                BigDecimal productPrice;

                                boolean enterName = true;
                                while (enterName){
                                    System.out.print("Name: ");
                                    productName = scanner.nextLine();

                                    if (productName.isBlank()){
                                        System.out.println("ERROR: Invalid input.");
                                    }
                                    else {
                                        enterName = false;
                                    }
                                }

                                boolean enterPrice = true;
                                while (enterPrice){
                                    System.out.print("Price: ");

                                    if (scanner.hasNextLine()){
                                        String priceInput = scanner.nextLine().trim();

                                        if (!priceInput.isEmpty()) {
                                            if (priceInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                                                productPrice = new BigDecimal(priceInput);

                                                boolean priceConfirm = true;
                                                while (priceConfirm) {
                                                    System.out.print("Are you sure you want to update this product (Y/N) : ");
                                                    String confirmation = scanner.nextLine().toLowerCase();

                                                    if (confirmation.equals("y")) {
                                                        Integer newId = products.size() - 1;
                                                        
                                                        ProductModel editProductModel = products.get(newId);
                                                        editProductModel.setProductName(productName);
                                                        editProductModel.setProductPrice(productPrice);     

                                                        System.out.println("Product updated successfully!");
                                                        System.out.print("\nPress \"ENTER\" to continue...");
                                                        try {
                                                            System.in.read();
                                                        } catch (IOException e) {
                                                            // do nothing
                                                        }
                                                        
                                                        confirmationMessage = false;
                                                        enterPrice = false;
                                                        priceConfirm = false;
                                                    } 
                                                    else if (confirmation.equals("n")) {
                                                        System.out.println("Action canceled.");
                                                        System.out.print("\nPress \"ENTER\" to continue...");
                                                        try {
                                                            System.in.read();
                                                        } catch (IOException e) {
                                                            // do nothing
                                                        }
                                                        
                                                        confirmationMessage = false;
                                                        enterPrice = false;
                                                        priceConfirm = false;
                                                    } 
                                                    else {
                                                        System.out.println("ERROR: Invalid character.");
                                                    }
                                                }
                                            } 
                                            else {
                                                System.out.println("ERROR: Invalid number.");
                                            }
                                        } 
                                        else {
                                            System.out.println("ERROR: Invalid input.");
                                        }
                                    } 
                                    else {
                                        System.out.println("ERROR: Invalid input.");
                                    } 
                                }
                                
                            }
                        }
                        else {
                            System.out.println("Invalid ID to remove.");  
                            System.out.print("\nPress \"ENTER\" to continue...");
                            try {
                                System.in.read();
                            } catch (IOException e) {
                                // do nothing
                            }
                        } 
                        
                        updateProductIds();

                        enterId = false;
                    } 
                    else {
                        System.out.println("ERROR: Invalid number.");
                    }
                } 
                else {
                    System.out.println("ERROR: Invalid input.");
                }
            } 
            else {
                System.out.println("ERROR: Invalid input.");
            } 
        } 
    }
    
    @Override
    public void showAdminOrder() {
        
        boolean showOrderScreen = true;
        while (showOrderScreen) {
            if(orders.isEmpty()){
            System.out.println("\n***********************");
            System.out.println("*       ORDERS        *");
            System.out.println("***********************");
            System.out.println("No orders found.");
            System.out.println("........................");
            System.out.println("1 - Mark Order As Delivered");
            System.out.println("2 - Filter by Status");
            System.out.println("0 - Back\n");

            boolean choice = true;
            while (choice) {
                System.out.print("What do you want to do : ");
                String userInput = scanner.nextLine();

                if (userInput.equals("0")){
                    choice = false;
                    showOrderScreen = false;
                }
                else if (userInput.equals("1") || userInput.equals("2")){
                    System.out.println("ERROR: Cannot proceed with action. No orders found.");
                } 
                else if (userInput.isBlank()){
                    System.out.println("ERROR: Invalid input.");
                }
                else {
                    System.out.println("ERROR: Invalid input.");
                }
            }
            
            }
            else {
                boolean choice = true;
                while (choice){
                    System.out.println("\n***********************");
                    System.out.println("*       ORDERS        *");
                    System.out.println("***********************");
                    System.out.println("Date\t\t\t\tReference\tName\t\tPrice\t\tQty\tTotal\tStatus");

                    for (OrderModel order : orders) {
                        String dateStr = order.getOrderDate();
                        String reference = order.getReferenceNumber();
                        String name = order.getProductName();
                        BigDecimal price = order.getProductPrice();
                        Integer quantity = order.getOrderQuantity();
                        BigDecimal totalAmount = new BigDecimal(quantity).multiply(price);
                        String status = order.getOrderStatus();

                        // Print each order in the specified format
                        System.out.printf("%s\t%s\t\t%s\t\t%.2f\t\t%d\t%.2f\t%s%n", dateStr, reference, name, price, quantity, totalAmount, status);
                    }
                    System.out.println("........................\n");

                    System.out.println("1 - Mark Order As Delivered");
                    System.out.println("2 - Filter by Status");
                    System.out.println("0 - Back\n");

                    System.out.print("What do you want to do : ");
                    String userInput = scanner.nextLine();

                    if (userInput.equals("0")){
                        choice = false;
                        showOrderScreen = false;
                    }
                    else if (userInput.equals("1")){
                        System.out.println("\n***********************");
                        System.out.println("* UPDATE ORDER STATUS *");
                        System.out.println("***********************");

                        while (true) {
                            System.out.print("Order Reference : ");
                            String referenceToUpdate = scanner.nextLine().trim();

                            if (referenceToUpdate.isEmpty()) {
                                System.out.println("ERROR: Invalid input.");
                            } 
                            else {
                                OrderModel orderToUpdate = null;
                                for (OrderModel order : orders) {
                                    if (order.getReferenceNumber().equals(referenceToUpdate)) {
                                        orderToUpdate = order;
                                        break;
                                    }
                                }

                                if (orderToUpdate != null) {
                                    boolean confirmationMessage = true;
                                    while (confirmationMessage) {
                                        System.out.print("Are you sure you want to mark this order as delivered (Y/N) : ");
                                        String confirmation = scanner.nextLine().toLowerCase();

                                        if (confirmation.equals("y")) {
                                            orderToUpdate.setOrderStatus("DELIVERED");

                                            System.out.println("Order updated successfully.");
                                            System.out.print("\nPress \"ENTER\" to continue...");
                                            try {
                                                System.in.read();
                                            } catch (IOException e) {
                                                // do nothing
                                            }

                                            showOrderScreen = false;
                                            confirmationMessage = false;
                                        } 
                                        else if (confirmation.equals("n")) {
                                            System.out.println("Action canceled.");
                                            System.out.print("\nPress \"ENTER\" to continue...");
                                            try {
                                                System.in.read();
                                            } catch (IOException e) {
                                                // do nothing
                                            }
                                            showOrderScreen = true;
                                            confirmationMessage = false;
                                        } 
                                        else {
                                            System.out.println("ERROR: Invalid character.");
                                        }
                                    }
                                } 
                                else {
                                    System.out.println("ERROR: Order does not exist.");
                                    System.out.print("\nPress \"ENTER\" to continue...");
                                    try {
                                        System.in.read();
                                    } catch (IOException e) {
                                        // do nothing
                                    }
                                }
                                break;
                            }
                        }
                    } 
                    else if (userInput.equals("2")){
         
                        boolean filterByReferenceChoice = true;
                        while (filterByReferenceChoice){
                            System.out.print("Enter Status : ");
                            String statusInput = scanner.nextLine();
                            if (statusInput.toUpperCase().equals("DELIVERED") || statusInput.equals("FOR_DELIVERY")){
                                System.out.println("\n***********************");
                                System.out.println("*       ORDERS        *");
                                System.out.println("***********************");
                                System.out.println("Date\t\t\t\tReference\tName\t\tPrice\t\tQty\tTotal\tStatus");

                                for (OrderModel statusModel : orders) {
                                    String dateStr = statusModel.getOrderDate();
                                    String reference = statusModel.getReferenceNumber();
                                    String name = statusModel.getProductName();
                                    BigDecimal price = statusModel.getProductPrice();
                                    Integer quantity = statusModel.getOrderQuantity();
                                    BigDecimal totalAmount = new BigDecimal(quantity).multiply(price);
                                    String status = statusModel.getOrderStatus();

                                    if (statusModel.getOrderStatus().equals(statusInput.toUpperCase())){
                                        System.out.printf("%s\t%s\t\t%s\t\t%.2f\t\t%d\t%.2f\t%s%n", dateStr, reference, name, price, quantity, totalAmount, status);
                                    }
                                }

                                System.out.print("\nPress \"ENTER\" to continue...");
                                try {
                                    System.in.read();
                                } catch (IOException e) {
                                    // do nothing
                                }
                                
                                filterByReferenceChoice = false;
                            }
                            else {
                                System.out.println("ERROR: Invalid input. Status must be DELIVERED or FOR_DELIVERY.");
                            }
                        }                                               
                    }
                    else if (userInput.isBlank()){
                        System.out.println("ERROR: Invalid input.");
                    }
                    else {
                        System.out.println("ERROR: Invalid input.");
                    }
                }

            }
        }
    }


    @Override
    public void updateProductIds() {
        for (Integer index = 0; index < products.size(); index++) {
            products.get(index).setProductId(index + 1);
        }
    }

    @Override
    public void shopProducts() {
        if(products.isEmpty()){
            System.out.println("\n***********************");
            System.out.println("*         SHOP        *");
            System.out.println("***********************");
            System.out.println("No products found.");
            System.out.println("........................");
            System.out.println("0 - Back\n");

            boolean shopScreenNoProducts = true;
            while(shopScreenNoProducts){
                
                System.out.print("What do you want to do : ");
                String userInput = scanner.nextLine();

                if (userInput.isBlank() || !userInput.equals("0")){
                    System.out.println("ERROR: Cannot proceed with action. No products found.");
                }
                else {
                    shopScreenNoProducts = false;
                }
            }
            
        }
        else{

            System.out.println("\n***********************");
            System.out.println("*         SHOP        *");
            System.out.println("***********************");
            System.out.println("ID\tName\t\tPrice");

            for (ProductModel product : products) {
                System.out.printf("%d\t%s\t\t%.2f%n", product.getProductId(), product.getProductName(), product.getProductPrice());
            }
            
            System.out.println("........................");
            System.out.println("0 - Back\n");
            
            boolean enterId = true;
            while (enterId){
                System.out.print("What do you want to order : ");
                if (scanner.hasNextLine()){
                    String idInput = scanner.nextLine().trim();

                    if (!idInput.isEmpty()) { // Check if input is not empty
                        if (idInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                            Integer productId = Integer.valueOf(idInput);
                            if (productId == 0){
                                enterId = false;
                            }
                            else{
                                if (productId >= 0 && productId <= products.size()){
                                    productId = productId - 1;
                                    ProductModel productModel = products.get(productId);
                                    String productName = productModel.getProductName();
                                    BigDecimal productPrice = productModel.getProductPrice();

                                    System.out.println("\n***********************");
                                    System.out.println("*     PLACE ORDER     *");
                                    System.out.println("***********************");
                                    System.out.println("Name : " + productName);
                                    System.out.println("Price : Php " + productPrice);
                                    System.out.println("........................\n");
                                    
                                    boolean amountInputPrompt = true;
                                    while (amountInputPrompt) {
                                        System.out.print("How many do you want : ");

                                        if (scanner.hasNextLine()){
                                            String userInput = scanner.nextLine().trim();

                                            if (!userInput.isEmpty()) { // Check if input is not empty
                                                if (userInput.matches("\\d+(\\.\\d+)?")) { // Check if valid decimal number
                                                    Integer amountInput = Integer.valueOf(userInput);
                                                    BigDecimal totalAmount = new BigDecimal(amountInput).multiply(productPrice);

                                                    boolean confirmationMessage = true;
                                                    while (confirmationMessage) {
                                                        System.out.println("That would be Php " + totalAmount);
                                                        System.out.print("Proceed with your order (Y/N) : ");
                                                        String confirmation = scanner.nextLine().toLowerCase();

                                                        if (confirmation.equals("y")) {

                                                            // set date
                                                            long millis = System.currentTimeMillis();
                                                            Date date = new Date(millis);

                                                            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                                                            String formattedDate = sdf.format(date);   

                                                            // set reference 
                                                            StringBuilder randomString = new StringBuilder();
                                                            for (int i = 0; i < 5; i++) {
                                                                char randomChar = (char) (random.nextInt(26) + 'A');
                                                                randomString.append(randomChar);
                                                            }

                                                            // set status
                                                            String status = "FOR_DELIVERY";

                                                            orders.add(new OrderModel(formattedDate, randomString.toString(), productName, productPrice, amountInput, totalAmount, status));

                                                            System.out.println("Order placed successfully!");
                                                            System.out.print("\nPress \"ENTER\" to continue...");
                                                            try {
                                                                System.in.read();
                                                            } catch (IOException e) {
                                                                // do nothing
                                                            }
                                                            
                                                            amountInputPrompt = false;
                                                            confirmationMessage = false;
                                                        } 
                                                        else if (confirmation.equals("n")) {
                                                            System.out.println("Action canceled.");
                                                            System.out.print("\nPress \"ENTER\" to continue...");
                                                            try {
                                                                System.in.read();
                                                            } catch (IOException e) {
                                                                // do nothing
                                                            }

                                                            confirmationMessage = false;
                                                        } 
                                                        else {
                                                            System.out.println("ERROR: Invalid character.");
                                                        }
                                                    }

                                                } 
                                                else {
                                                    System.out.println("ERROR: Invalid number.");
                                                }
                                            } 
                                            else {
                                                System.out.println("ERROR: Invalid input.");
                                            }
                                        } 
                                        else {
                                            System.out.println("ERROR: Invalid input.");
                                        } 

                                        enterId = false;
                                    }
                                    
                                }
                                else {
                                    System.out.println("ERROR: Invalid input. Product does not exist.");
                                }
                            }           
                        } 
                        else {
                            System.out.println("ERROR: Invalid number.");
                        }
                    } 
                    else {
                        System.out.println("ERROR: Invalid input.");
                    }
                } 
                else {
                    System.out.println("ERROR: Invalid input.");
                }   
            }
            
        }
    }

    @Override
    public void showCustomerOrder() {
        
        boolean showOrderScreen = true;
        while (showOrderScreen) {
            if(orders.isEmpty()){
            System.out.println("\n***********************");
            System.out.println("*      MY ORDERS      *");
            System.out.println("***********************");
            System.out.println("No orders found.");
            System.out.println("........................\n");

            System.out.print("Press \"ENTER\" to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                // do nothing
            }
            showOrderScreen = false;
            }
            else {
                               
                boolean myOrdersChoice = true;
                while (myOrdersChoice){
                    System.out.println("\n***********************");
                    System.out.println("*      MY ORDERS      *");
                    System.out.println("***********************");
                    System.out.println("Date\t\t\t\tReference\tName\t\tPrice\t\tQty\tTotal\tStatus");

                    for (OrderModel order : orders) {
                        String dateStr = order.getOrderDate();
                        String reference = order.getReferenceNumber();
                        String name = order.getProductName();
                        BigDecimal price = order.getProductPrice();
                        Integer quantity = order.getOrderQuantity();
                        BigDecimal totalAmount = new BigDecimal(quantity).multiply(price);
                        String status = order.getOrderStatus();

                        // Print each order in the specified format
                        System.out.printf("%s\t%s\t\t%s\t\t%.2f\t\t%d\t%.2f\t%s%n", dateStr, reference, name, price, quantity, totalAmount, status);
                    }
                    System.out.println("........................\n");
                    System.out.println("1 - Filter by Reference");
                    System.out.println("0 - Back");
                    System.out.print("\nWhat do you want to do : ");
                    switch (scanner.nextLine()){
                        case "1" -> {

                            boolean filterByReferenceChoice = true;
                            while (filterByReferenceChoice){
                                System.out.print("Enter Status : ");
                                String statusInput = scanner.nextLine();
                                if (statusInput.toUpperCase().equals("DELIVERED") || statusInput.equals("FOR_DELIVERY")){
                                    System.out.println("\n***********************");
                                    System.out.println("*       ORDERS        *");
                                    System.out.println("***********************");
                                    System.out.println("Date\t\t\t\tReference\tName\t\tPrice\t\tQty\tTotal\tStatus");

                                    for (OrderModel statusModel : orders) {
                                        String dateStr = statusModel.getOrderDate();
                                        String reference = statusModel.getReferenceNumber();
                                        String name = statusModel.getProductName();
                                        BigDecimal price = statusModel.getProductPrice();
                                        Integer quantity = statusModel.getOrderQuantity();
                                        BigDecimal totalAmount = new BigDecimal(quantity).multiply(price);
                                        String status = statusModel.getOrderStatus();

                                        if (statusModel.getOrderStatus().equals(statusInput.toUpperCase())){
                                            System.out.printf("%s\t%s\t\t%s\t\t%.2f\t\t%d\t%.2f\t%s%n", dateStr, reference, name, price, quantity, totalAmount, status);
                                        }
                                    }

                                    System.out.print("\nPress \"ENTER\" to continue...");
                                    try {
                                        System.in.read();
                                    } catch (IOException e) {
                                        // do nothing
                                    }
                                    filterByReferenceChoice = false;
                                    myOrdersChoice = false;
                                }
                                else {
                                    System.out.println("ERROR: Invalid input. Status must be DELIVERED or FOR_DELIVERY.");
                                }
                            }
                        }
                        case "0" ->{
                            showOrderScreen = false;
                            myOrdersChoice = false;
                        }
                        default -> { 
                            System.out.println("ERROR: Enter a valid number");
                        }
                    }
                }
            }
        } 
    }
}
