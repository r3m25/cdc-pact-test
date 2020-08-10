package com.r3m25.provider.service;

import com.r3m25.provider.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    public Customer getCustomer() {
        return Customer.builder()
                .name("ruben")
                .lastName("morales")
                .age(31)
                .email("ruben.morales@globant.com")
                .address("las violetas")
                .phone("965827070")
                .build();
    }

}
