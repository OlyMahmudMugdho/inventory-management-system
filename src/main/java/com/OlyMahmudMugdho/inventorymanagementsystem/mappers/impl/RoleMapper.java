package com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.Mapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleDto> {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto mapTo(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role mapFrom(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }
}
