package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {


    private long productId;
    private String code;
    private String name;
    private String description;
    private double stocks;
    private double price;
    private double unitPrice;
    private String status;
    private Set<User> addedBy;
    private String categories;
    private String provider;
    private String addedOn;
    private boolean isRemoved;
    private String image;

    public String getAddedByUsername(){
        String username = "";
        for(User user : addedBy){
            username = user.getUsername();
            break;
        }
        return username;
    }
}
