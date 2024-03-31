package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "products/add-product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductDto productDto, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Set<User> users = new HashSet<>();
        users.add(user);
        productDto.setAddedBy(users);
        System.out.println(productDto);
        productService.addProduct(productDto);
        return "redirect:/";
    }

}
