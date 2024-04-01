package com.OlyMahmudMugdho.inventorymanagementsystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    private long productId;
    private String name;
    private String description;
    private double stocks;
    private double price;
    private double unitPrice;
    private String status;

//    @ManyToMany
//    @JoinTable(
//            name = "product_vategory_junction",
//            joinColumns = {@JoinColumn(name = "product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
//    @Column(name = "product_category")
    //private Set<Category> categories;
    private String categories;

//    @ManyToMany
//    @JoinTable(
//            name = "product_category_junction",
//            joinColumns = {@JoinColumn(name = "product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "provider_id")}
//    )
//    private Set<Provider> providers;
    private String provider;

    @ManyToMany
    @JoinTable(
            name = "product_added_by_junction",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> addedBy;

    public String getAddedByUsername(){
        String username = "";
        for(User user : addedBy){
            username = user.getUsername();
            break;
        }
        return username;
    }

    private String addedOn;
    private boolean isRemoved;
}
