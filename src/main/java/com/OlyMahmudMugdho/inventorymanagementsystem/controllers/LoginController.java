package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("")
    public String login(Principal principal) {
        if (principal != null) {
            System.out.println(principal);
            return "redirect:/";
        }
        return "login";
    }
}
