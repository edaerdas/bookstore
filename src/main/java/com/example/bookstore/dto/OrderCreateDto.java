package com.example.bookstore.dto;

import java.util.List;

public class OrderCreateDto {
    public Long customerId;
    public List<OrderItemDto> orderItemDtoList;
}
