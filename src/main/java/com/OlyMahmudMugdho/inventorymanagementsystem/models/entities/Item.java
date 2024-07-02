package com.OlyMahmudMugdho.inventorymanagementsystem.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Long id;
    @ManyToOne
    private Order orderId;
    private String productId;
    private Double quantity;
    private Float price;
}
