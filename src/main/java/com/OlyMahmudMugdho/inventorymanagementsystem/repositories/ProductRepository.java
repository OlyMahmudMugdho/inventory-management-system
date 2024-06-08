package com.OlyMahmudMugdho.inventorymanagementsystem.repositories;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "rest", path = "users")
@RestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM products p WHERE "
    + "p.code = :keyword "
    + "OR p.name LIKE CONCAT('%', :keyword,'%')  "
    + "OR p.description LIKE CONCAT('%', :keyword, '%')"
    )
    Page<Product> search(String keyword, Pageable pageable);
}
