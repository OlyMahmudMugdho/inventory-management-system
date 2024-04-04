package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public String allProductsPage(Model model) {
        List<ProductDto> productDtos = productService.getAllProducts();
        model.addAttribute("products", productDtos);
        return "products/all-products-page";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable long id, Model model) {
        Optional<ProductDto> product = productService.getProductById((id));
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        else {
            model.addAttribute("product", null);
        }
        return "products/product-details";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "products/add-product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductDto productDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Set<User> users = new HashSet<>();
        users.add(user);
        productDto.setAddedBy(users);
        System.out.println(productDto);
        productService.addProduct(productDto);
        return "redirect:/";
    }

}
