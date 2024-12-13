package com.remind.Quicker.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Long pieces;

    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String productImage;

    @Column(nullable = false)
    private String deleteStatus;

    public Product() {
    }

    public Product(Long id, String name, Float price, Long pieces, String description, String productImage, String deleteStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pieces = pieces;
        this.description = description;
        this.productImage = productImage;
        this.deleteStatus = deleteStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getPieces() {
        return pieces;
    }

    public void setPieces(Long pieces) {
        this.pieces = pieces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
