package com.example.powerflex.dao;

import com.example.powerflex.dto.CustomOrderDTO;
import com.example.powerflex.models.CustomUser;
import com.example.powerflex.models.CustomOrder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class CustomOrderDAO {

    private final CustomOrderRepository customOrderRepository;
    private final UserRepository userRepository;

    public CustomOrderDAO(CustomOrderRepository customOrderRepository, UserRepository userRepository) {
        this.customOrderRepository = customOrderRepository;
        this.userRepository = userRepository;
    }

    public List<CustomOrder> getAllCustomOrders() {
        return (this.customOrderRepository.findAll());
    }

    public CustomOrder getCustomOrderById(long id) {
        Optional<CustomOrder> customOrderOptional = (this.customOrderRepository.findById(id));
        if (customOrderOptional.isPresent()) {
            return customOrderOptional.get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order ID not found"
        );
    }

    public long createCustomOrder(CustomOrderDTO customOrderDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = this.userRepository.findByEmail(authentication.getName());

        CustomOrder customOrder = new CustomOrder(customOrderDTO.datum, customOrderDTO.orderStatus, customUser);

        this.customOrderRepository.save(customOrder);

        return customOrder.getId();
    }
}
