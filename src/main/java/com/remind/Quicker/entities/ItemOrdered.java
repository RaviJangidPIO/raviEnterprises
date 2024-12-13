package com.remind.Quicker.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ItemOrdered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    private Long piecesOrdered;
    private float price;

    public ItemOrdered() {
    }

    public ItemOrdered(Long id, OrderStatus orderStatus, Product product, Long piecesOrdered, float price) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.product = product;
        this.piecesOrdered = piecesOrdered;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getPiecesOrdered() {
        return piecesOrdered;
    }

    public void setPiecesOrdered(Long piecesOrdered) {
        this.piecesOrdered = piecesOrdered;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
