package org.example.service.impl;

import org.example.dto.OrderCreationDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderUpdateDTO;
import org.example.dto.mapper.OrderItemMapper;
import org.example.dto.mapper.OrderMapper;
import org.example.entity.Order;
import org.example.entity.OrderStatus;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.OrderRepository;
import org.example.repository.util.Page;
import org.example.service.OrderNumberGenerator;
import org.example.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceImpl implements OrderService {
    private final OrderNumberGenerator orderNumberGenerator;
    private final OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(OrderNumberGenerator orderNumberGenerator, OrderRepository orderRepository) {
        this.orderNumberGenerator = orderNumberGenerator;
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderDTO getOrder(UUID orderId) {
        Order order = orderRepository.get(orderId).orElseThrow(() -> notFoundSupplier(orderId));
        return OrderMapper.map(order);
    }

    @Override
    public Page<OrderDTO> getAll(int page, int size) {
        Page<Order> ordersPage = orderRepository.getAll(page, size);
        Page<OrderDTO> orderDTOPage = new Page<>();
        orderDTOPage.setData(ordersPage.getData().stream().map(OrderMapper::map).collect(Collectors.toList()));
        orderDTOPage.setTotalPages(ordersPage.getTotalPages());
        orderDTOPage.setSize(ordersPage.getSize());
        orderDTOPage.setNumber(ordersPage.getNumber());
        orderDTOPage.setTotalCount(ordersPage.getTotalCount());

        return orderDTOPage;
    }

    @Override
    public OrderDTO save(OrderCreationDTO dto) {
        Order order = OrderMapper.map(dto);
        order.setNumber(orderNumberGenerator.generate());
        order.setStatus(OrderStatus.DRAFT);
        order = orderRepository.save(order);
        return OrderMapper.map(order);
    }

    @Override
    public OrderDTO update(UUID id, OrderUpdateDTO dto) {
        Order order = orderRepository.get(id).orElseThrow(() -> notFoundSupplier(id));
        order.setStatus(OrderStatus.decode(dto.getStatus()));
        order.setContent(dto.getContent()
                .stream()
                .map(OrderItemMapper::map)
                .collect(Collectors.toSet()));
        order = orderRepository.update(order);
        return OrderMapper.map(order);
    }

    @Override
    public void delete(UUID id) {
        orderRepository.delete(id);
    }

    private ResourceNotFoundException notFoundSupplier(UUID id){
        return new ResourceNotFoundException(String.format("Order with id %s not found", id));
    }
}
