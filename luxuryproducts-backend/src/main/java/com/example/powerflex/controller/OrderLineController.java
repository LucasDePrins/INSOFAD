package com.example.powerflex.controller;

import com.example.powerflex.dao.OrderLineDAO;
import com.example.powerflex.dto.OrderLineDTO;
import com.example.powerflex.models.OrderLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:19574", "http://s1149574.student.inf-hsleiden.nl:19574"})
@RequestMapping("/orderline")
public class OrderLineController {

    private final OrderLineDAO orderLineDAO;

    public OrderLineController(OrderLineDAO orderLineDAO) {
        this.orderLineDAO = orderLineDAO;
    }

    @GetMapping
    public ResponseEntity<List<OrderLine>> getAllOrderLines(){
        return ResponseEntity.ok(this.orderLineDAO.getAllOrderLines());
    }

    @PostMapping
    public ResponseEntity<String> createOrderLine(@RequestBody OrderLineDTO orderLineDTO){
        System.out.println("Testing Orderline 1");
        this.orderLineDAO.createOrderLine(orderLineDTO);
        return ResponseEntity.ok("Created a new order item named ");
    }
}
