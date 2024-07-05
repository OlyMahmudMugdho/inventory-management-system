package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.OrderMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.OrderDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Item;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.Order;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ItemRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private OrderMapper orderMapper;
    private ModelMapper modelMapper;
    private List<Order> orders = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public KafkaService(OrderRepository orderRepository, ItemRepository itemRepository, OrderMapper orderMapper, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderMapper = orderMapper;
        this.modelMapper = modelMapper;
    }


    @KafkaListener(topics = "orders",
            groupId = "listen1",
            containerFactory = "orderListener")

    public OrderDto listenOrder(OrderDto message) {
        System.out.println(message.toString());
        //orders.add(orderMapper.mapFrom(message));
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Order order = modelMapper.map(message, Order.class);
        List<Item> item = message.getItems().stream().map(
                itemDto -> {
                    itemDto.setUserId(order.getUserId());
                    itemDto.setCartId(order.getCartId());
                    return modelMapper.map(itemDto, Item.class);
                }).toList();
        items.addAll(item);
        orders.add(order);
        System.out.println(orders);
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
    public void addAllItemsToDb(){
        itemRepository.saveAll(items);
    }

}
