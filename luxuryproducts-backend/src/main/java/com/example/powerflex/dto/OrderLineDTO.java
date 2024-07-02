package com.example.powerflex.dto;

//import com.example.powerflex.models.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Entity;

public class OrderLineDTO {

    public String name;
    public String description;
    public double unit_price;
    public long quantity;
    public long order_id;

}
