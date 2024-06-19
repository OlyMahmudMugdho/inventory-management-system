package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoRest {
    private int productId;
    private String code;
    private String name;
    private String description;
    private int stocks;
    private double price;  // Assuming price should be a double
    private double unitPrice; // Assuming unitPrice should be a double
    private String status;
    private String categories;
    private String image;
}
