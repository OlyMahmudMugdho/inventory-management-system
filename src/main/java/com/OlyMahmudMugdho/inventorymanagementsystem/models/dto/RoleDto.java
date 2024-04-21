package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private int roleId;
    private String authority;
}
