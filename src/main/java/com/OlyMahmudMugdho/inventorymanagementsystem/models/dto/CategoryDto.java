package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDto {
    private Long categoryId;
    private String categoryCode;
    private String categoryName;
}
