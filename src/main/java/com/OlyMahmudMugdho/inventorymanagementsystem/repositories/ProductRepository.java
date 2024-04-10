package com.OlyMahmudMugdho.inventorymanagementsystem.repositories;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM products p WHERE "
    + "p.name LIKE CONCAT('%', :keyword,'%')  "
    + "OR p.description LIKE CONCAT('%', :keyword, '%')"
    )
    Page<Product> search(String keyword, Pageable pageable);
}
