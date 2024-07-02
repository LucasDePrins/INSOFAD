package com.example.powerflex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity (name = "orderlines")
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private double unit_price;
    private long quantity;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomOrder customOrder;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id") // Assuming a foreign key constraint in the database
    private Product product;

    public OrderLine() {
    }

    public OrderLine(String name, String description, double unit_price, long quantity, CustomOrder customOrder) {
        this.name = name;
        this.description = description;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.customOrder = customOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CustomOrder getCustomOrder() {
        return customOrder;
    }

    public void setCustomOrder(CustomOrder customOrder) {
        this.customOrder = customOrder;
    }
}
