package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.CategoryMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.ProductMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.CategoryDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Category;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.CategoryService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, CategoryService categoryService, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }


    @GetMapping("/old")
    public String allProductsPage(Model model) {
        List<ProductDto> productDtos = productService.getAllProducts();

        model.addAttribute("products", productDtos);
        return "products/all-products-page";
    }

    @GetMapping("")
    public String paginatedProductsPage(Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pagination = PageRequest.of(page, size);
        Page<Product> products = productService.getPaginatedProducts(pagination);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDtos.add(productMapper.mapTo(product));
        }
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < products.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("products", productDtos);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPage", page);
        System.out.println("total page = " + products.getTotalPages());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("lastPage", products.getTotalPages() - 1);
        model.addAttribute("size",size);
        return "products/all-products-page";
    }


    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable long id, Model model) {
        Optional<ProductDto> product = productService.getProductById((id));
        if (product.isPresent()) {
            System.out.println(product.get());
            ProductDto productDto = product.get();
            long catId = Long.parseLong(productDto.getCategories());
            String categoryName = categoryService.getCategoryById(catId).get().getCategoryName();
            productDto.setCategories(categoryName);
            model.addAttribute("product", productDto);
        }
        else {
            model.addAttribute("product", null);
        }
        return "products/product-details";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = categories.stream().map(c -> categoryMapper.mapTo(c)).collect(Collectors.toList());
        System.out.println(categoryDtos);
        model.addAttribute("categories", categories);
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
        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    public String serveEditProductPage(Model model, @PathVariable long id) {
        Optional<ProductDto> product = productService.getProductById((id));

        if (product.isPresent()) {
            List<Category> categories = categoryService.getAllCategories();

            ProductDto productDto = product.get();
            long catId = Long.parseLong(productDto.getCategories());
            String categoryName = categoryService.getCategoryById(catId).get().getCategoryName();
            productDto.setCategories(categoryName);
            model.addAttribute("product", productDto);

            model.addAttribute("categories", categories);

            model.addAttribute("defaultCategory", catId);
            model.addAttribute("productId", id);
            return "products/edit-product-page";
        }
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("product") ProductDto productDto, Model model, RedirectAttributes redirectAttributes) {
        productService.editProduct(productDto);
        redirectAttributes.addAttribute("updated", true);
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Optional<ProductDto> product = productService.getProductById((id));
        if (product.isPresent()) {
            System.out.println(product.get());
            productService.deleteProduct(product.get());
            redirectAttributes.addAttribute("deleted", true);
        }
        return "redirect:/products";
    }

    @PostMapping("/remove-attributes")
    public String removeAttribute(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("updated",false);
        return "redirect:/products";
    }


    @GetMapping("/search")
    public String search(
            @RequestParam(defaultValue = " ") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ) {
        Pageable pagination = PageRequest.of(page, size);
        Page<Product> products = productService.getSearchedProducts(keyword, pagination);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDtos.add(productMapper.mapTo(product));
        }
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < products.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("products", productDtos);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPage", page);
        System.out.println("total page = " + products.getTotalPages());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("lastPage", products.getTotalPages() - 1);
        model.addAttribute("size",size);
        return "products/searched-products-page";
    }

}
