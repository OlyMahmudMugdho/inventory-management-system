package com.OlyMahmudMugdho.inventorymanagementsystem.controllers.rest;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.ProductMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.rest.ProductDtoRest;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductControllerRest {
    private final ProductMapper productMapper;
    private ProductService productService;


    public ProductControllerRest(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(path = {"/products/all","/products/all/"})
    public List<ProductDtoRest> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        List<ProductDtoRest> products = new ArrayList<>();
        productDtos.stream().map(p -> productMapper.mapToRest(p)).forEach(products::add);
        return products;
    }


    @GetMapping(path = {"/products","/products/"})
    public List<ProductDtoRest> paginatedProductsPage(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size
    ) {
        if (size <= 0) {
            return new ArrayList<>();
        }
        Pageable pagination = PageRequest.of(page, size);
        Page<Product> products = productService.getPaginatedProducts(pagination);
        List<ProductDtoRest> productDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDtos.add(productMapper.mapToRest(product));
        }
        return productDtos;
    }

    @GetMapping(path = {"/products/{id}","/products/{id}/"})
    public Optional<ProductDtoRest> getProduct(@PathVariable Long id){
        Optional<ProductDto> productDto = productService.getProductById(id);
        if(productDto.isPresent()){
            ProductDtoRest productDtoRest = productMapper.mapToRest(productDto.get());
            return Optional.of(productDtoRest);
        }
        return Optional.empty();
    }

}
