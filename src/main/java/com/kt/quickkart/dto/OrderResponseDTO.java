package com.kt.quickkart.dto;

import java.util.List;

public class OrderResponseDTO {
    private Long orderId;
    private double amount;
    private String status;
    private String orderDate;
    private List<OrderItemSummaryDTO> items;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemSummaryDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemSummaryDTO> items) {
        this.items = items;
    }
}
