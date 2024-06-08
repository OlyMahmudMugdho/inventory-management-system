package com.OlyMahmudMugdho.inventorymanagementsystem.controllers.rest;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.ProductMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.rest.ProductDtoRest;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = {"/products","/products/"})
    public List<ProductDtoRest> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        List<ProductDtoRest> products = new ArrayList<>();
        productDtos.stream().map(p -> productMapper.mapToRest(p)).forEach(products::add);
        return products;
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
