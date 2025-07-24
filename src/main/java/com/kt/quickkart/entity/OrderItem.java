package com.kt.quickkart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderitem")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    private int quantity;
    private double price;

    @ManyToOne
    @MapsId("order")
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem() {}

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
