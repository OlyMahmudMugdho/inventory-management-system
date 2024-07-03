package com.OlyMahmudMugdho.inventorymanagementsystem.controllers.rest;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.KafkaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/new-orders")
    public List<OrderDto> getAllNewOrders() {
        return kafkaService.getOrders();
    }

}
