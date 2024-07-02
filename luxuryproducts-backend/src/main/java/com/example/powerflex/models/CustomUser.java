package com.example.powerflex.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Users")
public class CustomUser {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "customUser")
    @JsonManagedReference
    @OrderBy("id DESC")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "customUser")
    @JsonManagedReference
    @OrderBy("id DESC")
    private Set<CustomOrder> customOrders;

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "shoppingcart_id", referencedColumnName = "id")
//    private ShoppingCart shoppingCart;

    public CustomUser() {
    }

    public CustomUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


    public Set<CustomOrder> getCustomOrders() {
        return customOrders;
    }

    public void setCustomOrders(Set<CustomOrder> customOrders) {
        this.customOrders = customOrders;
    }

    //    public ShoppingCart getShoppingCart() {
//        return shoppingCart;
//    }
//
//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }

}
