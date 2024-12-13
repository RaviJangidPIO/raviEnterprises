package com.remind.Quicker.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomUser customer;

    @Column(nullable = false)
    private Long categoryOrder;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime orderPlacedAt;

    public OrderStatus() {
    }

    public OrderStatus(Long id, CustomUser customer, Long categoryOrder, String status, LocalDateTime orderPlacedAt) {
        this.id = id;
        this.customer = customer;
        this.categoryOrder = categoryOrder;
        this.status = status;
        this.orderPlacedAt = orderPlacedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomUser getCustomer() {
        return customer;
    }

    public void setCustomer(CustomUser customer) {
        this.customer = customer;
    }

    public Long getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Long categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderPlacedAt() {
        return orderPlacedAt;
    }

    public void setOrderPlacedAt(LocalDateTime orderPlacedAt) {
        this.orderPlacedAt = orderPlacedAt;
    }
}
