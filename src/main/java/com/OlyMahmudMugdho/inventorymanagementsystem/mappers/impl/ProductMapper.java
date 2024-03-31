package com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.Mapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.ProductDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
