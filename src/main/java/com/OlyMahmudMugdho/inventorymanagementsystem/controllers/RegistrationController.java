package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.RegistrationForm;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.IUserService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private IUserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("")
    public String register(@ModelAttribute("registrationForm") RegistrationForm registerForm, Model model){
        System.out.println(registerForm);
        userService.addUser(registerForm.toUser());
        return "redirect:/";
    }
}
