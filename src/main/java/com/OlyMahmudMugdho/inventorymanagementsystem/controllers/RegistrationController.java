package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.RegistrationForm;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.IUserService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.backoff.BackOff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private IUserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String registerForm(Model model, @ModelAttribute("registrationForm") RegistrationForm registerForm, Principal principal) {
//        model.addAttribute("registrationForm", new RegistrationForm());

        if(principal != null) {
            return "redirect:/";
        }

        return "registration";
    }

    @PostMapping("")
    public String register(@ModelAttribute("registrationForm") RegistrationForm registerForm, Model model){
        System.out.println(registerForm);
        userService.addUser(registerForm.toUser(passwordEncoder));
        //userRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
