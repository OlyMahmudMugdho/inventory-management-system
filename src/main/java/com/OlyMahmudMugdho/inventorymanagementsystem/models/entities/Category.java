package com.OlyMahmudMugdho.inventorymanagementsystem.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(unique = true, nullable = false)
    private String categoryCode;
    @Column(name = "category_name")
    private String categoryName;
}
