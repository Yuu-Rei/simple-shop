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
public class OrderModel extends ProductModel{
    String orderDate;
    String referenceNumber;
    Integer orderQuantity;
    BigDecimal total;
    String orderStatus;
    
//    constructors
    public OrderModel(){}
    
    public OrderModel(String orderDate, String referenceNumber, String productName, BigDecimal productPrice, Integer orderQuantity, BigDecimal total, String orderStatus){
        this.orderDate = orderDate;
        this.referenceNumber = referenceNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.total = total;
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }  

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    
}
