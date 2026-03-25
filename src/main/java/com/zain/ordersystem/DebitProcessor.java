package com.zain.ordersystem;

import org.springframework.stereotype.Component;

@Component("debitCardProcessor")
public class DebitProcessor implements PaymentProcessor{

    @Override
    public boolean processPayment(Order order){
        System.out.println("Processing debit card payment for order " + order.getOrderId());
        return true;
    }

}
