package org.example.service;

import org.example.repository.util.Page;
import org.example.dto.OrderCreationDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderUpdateDTO;

import java.util.UUID;

public interface OrderService {
    OrderDTO getOrder(UUID orderId);

    Page<OrderDTO> getAll(int page, int size);

    OrderDTO save(OrderCreationDTO dto);

    OrderDTO update(UUID id, OrderUpdateDTO dto);

    void delete(UUID id);
}
