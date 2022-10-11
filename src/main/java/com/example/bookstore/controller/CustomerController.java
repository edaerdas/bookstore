package com.example.bookstore.controller;

import com.example.bookstore.dto.CustomerDto;
import com.example.bookstore.dto.base.BaseListDto;
import com.example.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto) {

        Long customerId = customerService.createCustomer(customerDto);
        if (customerId != 0L) {
            String message = String.format("Customer saved successfully, id : %s", customerId);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>("Customer already existing with same email on database", HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<BaseListDto> getCustomers(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        BaseListDto customers = customerService.getCustomers(pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
