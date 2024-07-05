package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String productId;
    private String userId;
    private String cartId;
    private Double quantity;
    private Float price;
}
