package org.example.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    UUID id;

    UUID customerId;

    String number;

    String status;

    List<OrderItemDTO> content;
}
