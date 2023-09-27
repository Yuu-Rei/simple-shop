/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qq_activity.service;

import java.math.BigDecimal;

/**
 *
 * @author Rheeey
 */
    public interface ProductService {
        // admin check if there are products
        void checkProducts();

        void addProduct(); // decline product when product with same name is present

        void removeProduct();
        
        void editProduct();

        // Added update product ID to change product IDs when removing a product in the front or middle of the list
        void updateProductIds();
        
        // show customer orders
        void showAdminOrder();

        // customer shop buy
        void shopProducts();
        
        // customer show order
        void showCustomerOrder();
    }
