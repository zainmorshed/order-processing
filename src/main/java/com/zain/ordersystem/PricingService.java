package com.zain.ordersystem;

import org.springframework.stereotype.Service;

@Service
public class PricingService {


    public double finalPrice(Order order, double salesTax, double deliveryFee, double discount) {
        double subTotal = order.subTotal();
        double total = (subTotal + salesTax + deliveryFee) - discount;
        return total;
    }
}