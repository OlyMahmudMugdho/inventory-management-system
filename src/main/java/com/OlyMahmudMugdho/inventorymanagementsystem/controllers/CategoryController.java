package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.CategoryMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.CategoryDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Category;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("")
    public String getAllCategories(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pagination = PageRequest.of(page, size);
        Page<Category> categories = categoryService.getPaginatedCategories(pagination);
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories.getContent()) {
            categoryDtos.add(categoryMapper.mapTo(category));
        }
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < categories.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPage", page);
        System.out.println("total page = " + categories.getTotalPages());
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("lastPage", categories.getTotalPages() - 1);
        model.addAttribute("size",size);
        return "categories/all-category-page";
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
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String getCategoryDetails(Model model, @PathVariable() long id){
        Optional<Category> categoryFetched = categoryService.getCategoryById(id);
        if (categoryFetched.isEmpty()) {
            return "redirect:/categories";
        }
        CategoryDto categoryDto = categoryMapper.mapTo(categoryFetched.get());
        model.addAttribute("category", categoryDto);
        return "categories/category-details";
    }

    @GetMapping("/edit-product")
    public String editProductPage(Model model, @PathVariable long id) {
        Optional<Category> categoryFetched = categoryService.getCategoryById(id);
        if (categoryFetched.isEmpty()) {
            return "redirect:/categories";
        }
        CategoryDto categoryDto = categoryMapper.mapTo(categoryFetched.get());
        model.addAttribute("category", categoryDto);
        return "categories/edit-category-page";
    }

}
