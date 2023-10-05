package org.example.dto.mapper;

import org.example.dto.OrderCreationDTO;
import org.example.dto.OrderDTO;
import org.example.entity.Order;
import org.example.entity.OrderStatus;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO map(Order order) {
        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setContent(order.getContent()
                .stream()
                .map(OrderItemMapper::map)
                .collect(Collectors.toList()));
        dto.setStatus(order.getStatus().getCode());
        dto.setNumber(order.getNumber());

        return dto;
    }

    public static Order map(OrderCreationDTO dto) {
        Order order = new Order();
        order.setCustomerId(dto.getCustomerId());
        order.setContent(dto.getContent()
                .stream()
                .map(OrderItemMapper::map)
                .collect(Collectors.toSet()));

        order.getContent().forEach(e -> e.setOrder1(order));
        return order;
    }
}
