package com.OlyMahmudMugdho.inventorymanagementsystem.controllers.rest;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.KafkaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/new-orders")
    public List<OrderDto> getAllNewOrders() {
        return kafkaService.getOrders();
    }

    @GetMapping("/orders/bulk-add")
    public Map<String,Boolean> bulkAddOrders(){
        Map<String,Boolean> response = new HashMap<>();
        this.kafkaService.addAllOrderToDb();
        response.put("added", true);
        return response;
    }
}
