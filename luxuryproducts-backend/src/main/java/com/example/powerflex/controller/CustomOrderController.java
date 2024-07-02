package com.example.powerflex.controller;

import com.example.powerflex.dao.CustomOrderDAO;
import com.example.powerflex.dto.CustomOrderDTO;
import com.example.powerflex.models.CustomOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:19574", "http://s1149574.student.inf-hsleiden.nl:19574"})
@RequestMapping("/orders")
public class CustomOrderController {

    private final CustomOrderDAO customOrderDAO;

    public CustomOrderController(CustomOrderDAO customOrderDAO) {
        this.customOrderDAO = customOrderDAO;
    }

    @GetMapping
    public ResponseEntity<List<CustomOrder>> getAllCustomOrders(){
        return ResponseEntity.ok(this.customOrderDAO.getAllCustomOrders());
    }

    @GetMapping(params = "id")
    public ResponseEntity<CustomOrder> getCustomOrderById(@RequestParam long id) {
        return ResponseEntity.ok(this.customOrderDAO.getCustomOrderById(id));
    }

    @PostMapping
    public ResponseEntity<String> createFinishedOrder(@RequestBody CustomOrderDTO customOrderDTO) {
        try {
            Long orderId = this.customOrderDAO.createCustomOrder(customOrderDTO);
            return ResponseEntity.ok(orderId.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create order: " + e.getMessage());
        }
    }
}
