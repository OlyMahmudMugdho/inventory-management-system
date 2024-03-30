package com.OlyMahmudMugdho.inventorymanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name")
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
