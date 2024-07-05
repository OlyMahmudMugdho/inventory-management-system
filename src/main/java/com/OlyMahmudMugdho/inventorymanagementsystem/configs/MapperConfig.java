package com.OlyMahmudMugdho.inventorymanagementsystem.configs;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.OrderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper(modelMapper());
    }

}
