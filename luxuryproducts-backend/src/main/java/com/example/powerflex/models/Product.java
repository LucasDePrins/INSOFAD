package com.example.powerflex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /*
       Maps the many-to-one relationship between product and category, jsonbackreference so that we do not get an
       infinite dependency loop in the request. Cascasdetype merge so the product is able to create a category if we
       seed the data to the database. Without the merge you get a persistence race condition.
       */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderLine> orderLines;

    //needed by JPA to create the entity must be present no arg constructor
    public Product() {
    }

    public Product(String name, String description, double price, Category category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    //getters and setters are needed to map all the properties to the database by JPA, could
    //also be solved by making the properties public but gives less control over the properties.
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}