/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qq_activity.model;

import java.math.BigDecimal;


/**
 *
 * @author Rheeey
 */
public class ProductModel {
    Integer productId;
    String productName;
    BigDecimal productPrice;
    
//    constructors
    public ProductModel(){}
    
//    For user adding products
    public ProductModel(String productName, BigDecimal productPrice){
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
//    For adding product to list
    public ProductModel(Integer productId, String productName, BigDecimal productPrice){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
//    For removing product in list
    public ProductModel(Integer productId){
        this.productId = productId;
    }
    
    
    
//    getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    
    
}
