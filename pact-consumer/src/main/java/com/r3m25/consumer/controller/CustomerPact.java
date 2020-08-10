package com.r3m25.consumer.controller;

import com.r3m25.consumer.client.CustomerClient;
import com.r3m25.consumer.client.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerPact {

    private final CustomerClient customerClient;

    @GetMapping("/client")
    public ResponseEntity<Customer> test() {
        return ResponseEntity.ok(customerClient.getCustomerById("1"));
    }

}
