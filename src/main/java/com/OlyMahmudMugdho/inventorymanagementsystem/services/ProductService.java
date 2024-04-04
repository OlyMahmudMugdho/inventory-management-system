package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.ProductMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product addProduct(ProductDto productDto) {
        return productRepository.save(productMapper.mapFrom(productDto));
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        if (!products.isEmpty()) {
            for (Product product : products) {
                productDtos.add(productMapper.mapTo(product));
            }
        }
        return productDtos;
    }

    public Optional<ProductDto> getProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        return Optional.ofNullable(productMapper.mapTo(product));
    }
}
