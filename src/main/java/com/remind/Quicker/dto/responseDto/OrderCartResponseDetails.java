package com.remind.Quicker.dto.responseDto;

public class OrderCartResponseDetails {
    private String name;
    private Float price;
    private Long orderedPieces;
    private String productImage;
    private float totalPrice;

    public OrderCartResponseDetails() {
    }

    public OrderCartResponseDetails(String name, Float price, Long orderedPieces, String productImage, float totalPrice) {
        this.name = name;
        this.price = price;
        this.orderedPieces = orderedPieces;
        this.productImage = productImage;
        this.totalPrice = totalPrice;
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

    public Long getOrderedPieces() {
        return orderedPieces;
    }

    public void setOrderedPieces(Long orderedPieces) {
        this.orderedPieces = orderedPieces;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
