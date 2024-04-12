package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.CategoryMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.CategoryDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Category;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/add-category")
    public String addCategoryPage(Model model) {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("category", categoryDto);
        return "categories/add-category-page";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryDto categoryDto, RedirectAttributes redirectAttributes) {
        System.out.println(categoryDto);
        Category recievedCategory = categoryMapper.mapFrom(categoryDto);
        categoryService.createCategory(recievedCategory);
        redirectAttributes.addAttribute("added", true);
        return "redirect:/";
    }

}
