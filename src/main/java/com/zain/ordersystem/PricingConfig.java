package com.zain.ordersystem;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pricing")
public class PricingConfig {
    private double salesTax;
    private double deliveryFee;
    private double discount;

    
    public double getSalesTax() {
        return salesTax;
    }
    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }
    public double getDeliveryFee() {
        return deliveryFee;
    }
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
