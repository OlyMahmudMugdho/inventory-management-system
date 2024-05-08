package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}
