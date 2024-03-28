package com.OlyMahmudMugdho.inventorymanagementsystem.models;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String name;
    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder){
        System.out.println(this.username + " matched");
        return new User(name,username,passwordEncoder.encode(password),email);
    }
}
