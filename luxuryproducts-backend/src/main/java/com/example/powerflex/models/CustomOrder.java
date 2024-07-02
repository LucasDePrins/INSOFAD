package com.example.powerflex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity (name = "orders")
public class CustomOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String datum;
    private String orderStatus;

    @OneToMany(mappedBy = "customOrder")
    @JsonManagedReference
    private Set<OrderLine> orderLines;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomUser customUser;

    public CustomOrder(String datum, String orderStatus, CustomUser customUser) {
        this.datum = datum;
        this.orderStatus = orderStatus;
        this.customUser = customUser;
    }

    public CustomOrder() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }
}