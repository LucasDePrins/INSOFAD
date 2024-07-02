package com.example.powerflex.dao;

import com.example.powerflex.dto.OrderLineDTO;
import com.example.powerflex.models.CustomOrder;
import com.example.powerflex.models.OrderLine;
import com.example.powerflex.models.Product;
//import com.example.powerflex.models.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class OrderLineDAO {
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;
    private final CustomOrderRepository customOrderRepository;

    public OrderLineDAO(OrderLineRepository orderLineRepository, ProductRepository productRepository, CustomOrderRepository customOrderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepository;
        this.customOrderRepository = customOrderRepository;
    }

    public List<OrderLine> getAllOrderLines() {
        return this.orderLineRepository.findAll();
    }

    public void createOrderLine(OrderLineDTO orderLineDTO) {
        Optional<CustomOrder> customOrderOptional = this.customOrderRepository.findById(orderLineDTO.order_id);
        if (customOrderOptional.isPresent()) {
            CustomOrder customOrder = customOrderOptional.get();
            this.orderLineRepository.save(new OrderLine(orderLineDTO.name, orderLineDTO.description, orderLineDTO.unit_price, orderLineDTO.quantity, customOrder));
            return;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order ID not found"
        );
    }


}
