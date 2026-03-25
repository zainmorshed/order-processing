package com.zain.ordersystem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("creditCardProcessor")
public class CreditCardProcessor implements PaymentProcessor{

    @Override
    public boolean processPayment(Order order){
        System.out.println("Processing credit card payment for order " + order.getOrderId());
        return true;
    }

}
