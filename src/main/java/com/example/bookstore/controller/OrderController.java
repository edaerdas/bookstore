package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderCreateDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.exception.EntityNotFound;
import com.example.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getById")
    public ResponseEntity<OrderDto> getOrderById(@RequestParam("id") Long id) {

        try {
            OrderDto order = orderService.getOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (EntityNotFound entityNotFound) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderCreateDto orderCreateDto) {

        try {
            Long orderId = orderService.createOrder(orderCreateDto);
            return new ResponseEntity<String>("Order created successfully with id " + orderId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/getByDateInterval")
    public ResponseEntity<List<OrderDto>> getOrdersByDateInterval(@RequestParam Long startTime, @RequestParam Long endTime) {

        List<OrderDto> ordersByDateInterval = orderService.getOrdersByDateInterval(startTime, endTime);
        return new ResponseEntity<>(ordersByDateInterval, HttpStatus.OK);
    }
}
