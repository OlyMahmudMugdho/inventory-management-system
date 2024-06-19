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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long productId;
    private String code;
    private String name;
    private String description;
    private double stocks;
    private double price;
    private double unitPrice;
    private String status;
    private String image;

//    @ManyToOne
//    @JoinTable(
//            name = "product_category_junction",
//            joinColumns = {@JoinColumn(name = "category_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")}
//    )
//    private Category categories;

    private String categories;

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
