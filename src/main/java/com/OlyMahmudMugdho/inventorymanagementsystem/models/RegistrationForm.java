package com.OlyMahmudMugdho.inventorymanagementsystem.models;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {
    private String name;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    public User toUser(PasswordEncoder passwordEncoder, Set<Role> roles){
        System.out.println(this.username + " matched");
        return new User(name,username,passwordEncoder.encode(password),email, roles);
    }
}
