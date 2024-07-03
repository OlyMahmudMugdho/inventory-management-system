package com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.Mapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Order;
import org.modelmapper.ModelMapper;

public class OrderMapper implements Mapper<Order, OrderDto> {

    private ModelMapper modelMapper;


    public OrderMapper() {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto mapTo(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
