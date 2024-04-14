package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.RegistrationForm;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.IUserService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.backoff.BackOff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private IUserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
    public String register(@ModelAttribute("registrationForm") RegistrationForm registerForm, Model model, RedirectAttributes redirectAttributes){
        System.out.println(registerForm);
        Role role = roleService.getRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        try {
            userService.addUser(registerForm.toUser(passwordEncoder, roles));

        }
        catch (Exception e) {
            redirectAttributes.addAttribute("error",true);

        }
        //userRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
