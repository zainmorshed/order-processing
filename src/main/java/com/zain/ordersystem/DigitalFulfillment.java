package com.zain.ordersystem;

import org.springframework.stereotype.Component;

@Component("digitalFulfillment")
public class DigitalFulfillment implements FulfillmentStrategy{

    @Override
    public void fulfill(Order order) {

        System.out.println("Sending digital download link to " +  order.getCustomerName() + "at " + order.getEmail());
    }
}
