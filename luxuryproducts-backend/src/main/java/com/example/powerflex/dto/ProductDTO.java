package com.example.powerflex.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductDTO {
    public String name;
    public String description;
    public double price;

    @JsonAlias("category_id")
    public long categoryId;
    public String imageUrl;

    public ProductDTO(String name, String description, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }
}
