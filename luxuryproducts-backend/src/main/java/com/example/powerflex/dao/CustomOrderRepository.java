package com.example.powerflex.dao;

import com.example.powerflex.models.CustomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomOrderRepository extends JpaRepository<CustomOrder, Long> {
}
