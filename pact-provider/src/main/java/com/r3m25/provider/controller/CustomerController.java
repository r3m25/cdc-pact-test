package com.r3m25.provider.controller;

import com.r3m25.provider.domain.Customer;
import com.r3m25.provider.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer() {
        return ResponseEntity.ok().body(customerService.getCustomer());
    }


}
