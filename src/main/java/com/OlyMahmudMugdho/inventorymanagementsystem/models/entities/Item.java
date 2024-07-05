package com.OlyMahmudMugdho.inventorymanagementsystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cartId;
    private String productId;
    private String userId;
    private Double quantity;
    private Float price;
}
