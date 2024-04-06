package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page) {
        Pageable testPage = PageRequest.of(page, size);
        Page<Product> pagedProducts = productRepository.findAll(testPage);
        List<Product> products = pagedProducts.getContent();
        System.out.println(pagedProducts.getTotalPages());
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        //return "requested " + size + " page = " + page;
    }
}
