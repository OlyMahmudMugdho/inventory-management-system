package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.OrderMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Order;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ItemRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private OrderMapper orderMapper = new OrderMapper();
    private List<Order> orders = new ArrayList<>();

    public KafkaService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }


    @KafkaListener(topics = "orders",
            groupId = "listen1",
            containerFactory = "orderListener")

    public OrderDto listenOrder(OrderDto message) {
        System.out.println(message.toString());
        orders.add(orderMapper.mapFrom(message));
        return message;
    }

    public List<OrderDto> getOrders() {
        return orders.stream().map(
                o -> orderMapper.mapTo(o)
        ).collect(Collectors.toList());
    }

    public void addAllOrderToDb() {
        orderRepository.saveAll(orders);
    }

}
