package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private List<ItemDto> items;
    private String userId;
    private String message;
    private String cartId;
    private Date timestamp;
}
