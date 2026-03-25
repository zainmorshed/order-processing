package com.zain.ordersystem;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Map;

import com.zain.ordersystem.enums.FulfillmentType;
import com.zain.ordersystem.enums.PaymentMethod;
import com.zain.ordersystem.enums.Status;

import org.slf4j.Logger;


@Component
public class OrderProcessor {

//methods: process
//dependencies: Order, PaymentMethod, pricingService, Status, PaymentProcessor, FulfillmentStrategy

    private PricingService pricingService;
    // private PaymentProcessor paymentProcessor;

    private Map<String, PaymentProcessor> paymentProcessors;
    private Map<String, FulfillmentStrategy> fulfillmentStrategy;
    private double salesTax;
    private double deliveryFee;
    private double discount;

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

//dependencies injected via constructor
    @Autowired
    public OrderProcessor(PricingService pricingService,
                        // @Qualifier("creditCardProcessor") PaymentProcessor paymentProcessor, //finds the matching candidates for all classes that have the @component annotation - qualifier name must match the component name
                        // @Qualifier("shippingFulfillment") FulfillmentStrategy fulfillmentStrategy,
                        Map<String, PaymentProcessor> paymentProcessors,
                        Map<String, FulfillmentStrategy> fulfillmentStrategy,
                        PricingConfig pricingConfig) {
                            this.pricingService = pricingService;
                            this.paymentProcessors = paymentProcessors;
                            this.fulfillmentStrategy = fulfillmentStrategy;
                            // this.paymentProcessor = paymentProcessor;
                            //load values from YAML
                            this.salesTax = pricingConfig.getSalesTax();
                            this.deliveryFee = pricingConfig.getDeliveryFee();
                            this.discount = pricingConfig.getDiscount();

                        }

                        


    
    public void process(Order order) {
        //polymorphism for PaymentProcessor and FulfillmentStrategy
        //flow: pricing -> payment -> fulfillment -> statusUpdate

        double total = pricingService.finalPrice(order, salesTax, deliveryFee, discount);

        PaymentProcessor processor;

        if(order.getPaymentType() == PaymentMethod.CREDIT) {
            processor = paymentProcessors.get("creditCardProcessor");
            logger.info("processed using credit card");
        } else {
            processor = paymentProcessors.get("debitCardProcessor");
            logger.info("processed using debit card");
        }


        FulfillmentStrategy strategy;

        if(order.getFulfillmentType() == FulfillmentType.SHIPPING) {
            strategy = fulfillmentStrategy.get("shippingFulfillment");
            logger.info("Shipping fulfillment");
        } else {
            strategy = fulfillmentStrategy.get("digitalFulfillment");
            logger.info("Digital fulfillment");
        }

        boolean paymentSuccess = processor.processPayment(order);

        if(!paymentSuccess) {
            logger.error("Payment failed for order {}", order.getOrderId());
            order.setOrderStatus(Status.FAILED);
            return;
        } 

        strategy.fulfill(order);
        order.setOrderStatus(Status.COMPLETED);
        logger.info("Order {} completed successfuly!", order.getOrderId());
        // System.out.println("Order" + order.getOrderId() + " completed.");

    }

}
