package com.OlyMahmudMugdho.inventorymanagementsystem.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "providers")
public class Provider {
    @Id
    @Column(name = "provider_id")
    private long providerId;
    private String name;
    private String type;
    private String address;
    private String email;
    private String phone;
    private String description;
    private String website;
    private boolean isRemoved;

}
