package com.zain.ordersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.zain.ordersystem.Order;
import com.zain.ordersystem.OrderProcessor;
import com.zain.ordersystem.dto.OrderResponse;
import com.zain.ordersystem.enums.Status;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProcessor orderProcessor;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @PostMapping("/process")
    public OrderResponse processOrder(@RequestBody Order order) {
        logger.info("Received order request: {}", order.getOrderId());

        orderProcessor.process(order);

        logger.info("Finished processing order: {}", order.getOrderId());

        // String orderMsg = logger.info("Order processed successfully!");

        return new OrderResponse(
            order.getOrderId(),
            order.getOrderStatus(),
    "Ordered processed successfully!, Thanks " + order.getCustomerName() + "!"
        );
    }

    
}
