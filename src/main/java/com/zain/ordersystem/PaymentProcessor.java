package com.zain.ordersystem;

public interface PaymentProcessor {

    boolean processPayment(Order order);
}
