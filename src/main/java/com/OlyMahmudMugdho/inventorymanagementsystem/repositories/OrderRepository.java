package com.OlyMahmudMugdho.inventorymanagementsystem.repositories;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
