package com.OlyMahmudMugdho.inventorymanagementsystem.models.dto;


import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String username;
    private String roles;
    private String address;
    private String password;
    private String phone;
    private String email;
    private String joined_on;
    private String description;

    public User toUser(PasswordEncoder passwordEncoder, Set<Role> roles){
        System.out.println(this.username + " matched");
        return new User(name,username,passwordEncoder.encode(password),email, roles);
    }

}
