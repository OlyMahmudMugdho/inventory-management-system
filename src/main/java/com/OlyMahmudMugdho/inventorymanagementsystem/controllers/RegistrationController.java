package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @GetMapping("")
    public String registerForm(Model model) {
        return "registration";
    }
}
