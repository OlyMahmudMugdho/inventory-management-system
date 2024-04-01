package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {


    private long productId;
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

    public String getAddedByUsername(){
        String username = "";
        for(User user : addedBy){
            username = user.getUsername();
            break;
        }
        return username;
    }
}
