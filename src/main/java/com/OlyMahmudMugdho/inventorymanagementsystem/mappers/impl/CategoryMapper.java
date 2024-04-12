package com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.Mapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.CategoryDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto mapTo(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category mapFrom(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
