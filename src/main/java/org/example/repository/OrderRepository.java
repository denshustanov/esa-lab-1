package org.example.repository;

import org.example.entity.Order;
import org.example.repository.util.Page;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    void delete(UUID id);

    Order update(Order order);

    Optional<Order> get(UUID id);

    Page<Order> getAll(int page, int size);
}
