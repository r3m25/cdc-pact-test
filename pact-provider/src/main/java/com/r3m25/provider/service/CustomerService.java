package com.r3m25.provider.service;

import com.r3m25.provider.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer getCustomer() {
        return Customer.builder().build();
    }
}
