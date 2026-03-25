package com.zain.ordersystem;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zain.ordersystem.enums.Status;

import org.slf4j.Logger;


@Component
public class OrderProcessor {

//methods: process
//dependencies: Order, PaymentMethod, pricingService, Status, PaymentProcessor, FulfillmentStrategy

    private PricingService pricingService;
    private PaymentProcessor paymentProcessor;
    private FulfillmentStrategy fulfillmentStrategy;
    private double salesTax;
    private double deliveryFee;
    private double discount;

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

//dependencies injected via constructor
    @Autowired
    public OrderProcessor(PricingService pricingService,
                        @Qualifier("creditCardProcessor") PaymentProcessor paymentProcessor, //finds the matching candidates for all classes that have the @component annotation - qualifier name must match the component name
                        @Qualifier("shippingFulfillment") FulfillmentStrategy fulfillmentStrategy,
                        PricingConfig pricingConfig) {
                            this.pricingService = pricingService;
                            this.paymentProcessor = paymentProcessor;
                            this.fulfillmentStrategy = fulfillmentStrategy;
                            //load values from YAML
                            this.salesTax = pricingConfig.getSalesTax();
                            this.deliveryFee = pricingConfig.getDeliveryFee();
                            this.discount = pricingConfig.getDiscount();

                        }

                        


    
    public void process(Order order) {
        //polymorphism for PaymentProcessor and FulfillmentStrategy
        //flow: pricing -> payment -> fulfillment -> statusUpdate

        double total = pricingService.finalPrice(order, salesTax, deliveryFee, discount);

        boolean paymentSuccess = paymentProcessor.processPayment(order);

        if(!paymentSuccess) {
            logger.error("Payment failed for order {}", order.getOrderId());
            order.setOrderStatus(Status.FAILED);
            return;
        } 

        fulfillmentStrategy.fulfill(order);
        order.setOrderStatus(Status.COMPLETED);
        logger.info("Order {} completed successfuly!", order.getOrderId());
        // System.out.println("Order" + order.getOrderId() + " completed.");

    }

}
