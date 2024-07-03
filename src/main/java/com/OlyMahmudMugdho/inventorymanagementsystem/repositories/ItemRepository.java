package com.OlyMahmudMugdho.inventorymanagementsystem.repositories;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
