package com.remind.Quicker.dto.requestRestDto;


public class ProductRequestDto {
    private String name;
    private Float price;
    private Long pieces;
    private String description;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, Float price, Long pieces, String description) {
        this.name = name;
        this.price = price;
        this.pieces = pieces;
        this.description = description;
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
}
