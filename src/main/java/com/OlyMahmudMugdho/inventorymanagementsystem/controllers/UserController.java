package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.UserDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String getAllUsers(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ){
        Pageable pagination = PageRequest.of(page,size);
        Page<User> pages = userService.getAllUsers(pagination);
        List<User> users = pages.stream().toList();

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < pages.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("users", users);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPage", page);
        System.out.println("total page = " + pages.getTotalPages());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("lastPage", pages.getTotalPages() - 1);
        model.addAttribute("size",size);
        return "users/all-users-page";
    }

    @GetMapping("/add-user")
    public String addUserPage(Model model) {
        UserDto user = new UserDto();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "users/add-user-form";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") UserDto user) {
        int roleId = Integer.parseInt(user.getRoles());
        Role role = roleService.getRoleById(roleId).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User newUser = user.toUser(passwordEncoder,roles);
        userService.addUser(newUser);
        System.out.println(user);
        return "redirect:/users";
    }

}
