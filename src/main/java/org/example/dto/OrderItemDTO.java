package org.example.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    long quantity;
    UUID productId;
}
