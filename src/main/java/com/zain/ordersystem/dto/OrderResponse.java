package com.zain.ordersystem.dto;

import com.zain.ordersystem.*;
import com.zain.ordersystem.enums.Status;

public class OrderResponse {
    private String orderId;
    private Status orderStatus;
    private String message;

    public OrderResponse(String orderId, Status orderStatus, String message) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    


}
