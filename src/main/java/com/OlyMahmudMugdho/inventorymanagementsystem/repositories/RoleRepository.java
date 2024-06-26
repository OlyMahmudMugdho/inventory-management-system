package com.OlyMahmudMugdho.inventorymanagementsystem.repositories;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByAuthority(String authority);
}