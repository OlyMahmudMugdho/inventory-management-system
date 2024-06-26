package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.ProductMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::mapTo);
    }

    public ProductDto editProduct(ProductDto productDto) {
            Product product = productMapper.mapFrom(productDto);
            System.out.println(product);
            productRepository.save(product);
            return productMapper.mapTo(product);
    }

    public void deleteProduct(ProductDto productDto) {
        Product product = productMapper.mapFrom(productDto);
        productRepository.delete(product);
    }

    public Page<Product> getPaginatedProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public Page<Product> getSearchedProducts(String keyword, Pageable pageable){
        Page<Product> products = productRepository.search(keyword, pageable);
        System.out.println(products);
        return products;
    }



}
