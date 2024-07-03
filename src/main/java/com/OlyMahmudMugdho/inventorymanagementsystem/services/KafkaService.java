package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Order;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaService {
    private List<Order> orders = new ArrayList<>();
    @KafkaListener(topics = "orders",
            groupId = "listen1",
            containerFactory = "orderListener")
    public void listenOrder(OrderDto message) {
        System.out.println(message.toString());
    }
}
