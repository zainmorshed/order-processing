package com.zain.ordersystem;

import org.springframework.stereotype.Component;

@Component("shippingFulfillment")
public class ShippingFulfillment implements FulfillmentStrategy{
	
	@Override
	public void fulfill(Order order) {
		System.out.println("Shipping order " + order.getOrderId() + " to " +order.getDeliveryAddress());
	}
}
