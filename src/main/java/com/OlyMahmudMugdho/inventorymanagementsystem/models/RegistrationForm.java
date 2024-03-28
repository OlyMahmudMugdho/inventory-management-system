package com.OlyMahmudMugdho.inventorymanagementsystem.models;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import lombok.Data;

@Data
public class RegistrationForm {
    private String name;
    private String username;
    private String password;
    private String email;

    public User toUser(){
        return new User(name,username,password,email);
    }
}
