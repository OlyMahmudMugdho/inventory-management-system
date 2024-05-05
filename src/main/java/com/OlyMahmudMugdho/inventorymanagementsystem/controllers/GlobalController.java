package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.services.CategoryService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.GlobalService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {
    private GlobalService globalService;

    public GlobalController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("users", globalService.userCount());
        return "index";
    }
}
