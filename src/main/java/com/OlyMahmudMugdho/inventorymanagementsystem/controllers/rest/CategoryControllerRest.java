package com.OlyMahmudMugdho.inventorymanagementsystem.controllers.rest;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.CategoryMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.CategoryDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Category;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryControllerRest {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public CategoryControllerRest(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories;
    }
}
