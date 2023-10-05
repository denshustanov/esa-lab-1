package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDTO {
    List<OrderItemDTO> content;

    String status;
}
