package com.zain.ordersystem;

import java.math.BigDecimal;
import java.security.cert.PKIXParameters;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.zain.ordersystem.enums.*;


public class Order {

    private String orderId;
    private LocalDateTime createdAt;
    private ArrayList<Item> items;
    private PaymentMethod paymentType;
    private String customerName;
    private Address deliveryAddress;
    private Status orderStatus;
    private String email;
    private FulfillmentType fulfillmentType;

    public Order(String orderId, LocalDateTime createdAt, ArrayList<Item> items, PaymentMethod paymentType, String customerName, Address deliveryAddress, Status orderStatus, String email, FulfillmentType fulfillmentType) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.items = items;
        this.paymentType = paymentType;
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
        this.email = email;
        this.fulfillmentType = fulfillmentType;
    }




    public FulfillmentType getFulfillmentType() {
        return fulfillmentType;
    }

    public void setFulfillmentType(FulfillmentType fulfillmentType) {
        this.fulfillmentType = fulfillmentType;
    }


    public String getEmail() {
        return email;
    }




    public void setEmail(String email) {
        this.email = email;
    }




    public double subTotal() {
        
        double subTotal = 0.00;

        for (Item item : items) {
            subTotal += item.getItemQuantity() * item.getItemPrice();
        }

        return subTotal;

    }




    // getters & setters:

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentMethod getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethod paymentType) {
        this.paymentType = paymentType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDateTime() {
        return createdAt;
    }

    public void setDate(LocalDateTime orderDateTime) {
        this.createdAt = orderDateTime;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItem(ArrayList<Item> orderItems) {
        this.items = orderItems;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address orderDeliveryAddress) {
        this.deliveryAddress = orderDeliveryAddress;
    }


    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatusStage){
        this.orderStatus = orderStatusStage;
    }


}