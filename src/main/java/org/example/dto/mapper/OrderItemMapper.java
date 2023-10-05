package org.example.dto.mapper;

import org.example.dto.OrderItemDTO;
import org.example.entity.Order;
import org.example.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDTO map(OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setProductId(orderItem.getProductId());

        return orderItemDTO;
    }

    public static OrderItem map(OrderItemDTO dto){
        OrderItem item = new OrderItem();
        item.setQuantity(dto.getQuantity());
        item.setProductId(dto.getProductId());

        return item;
    }
}
