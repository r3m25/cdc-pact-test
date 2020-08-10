package com.r3m25.consumer.client;

import com.r3m25.consumer.client.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-client", url = "/customer")
public interface CustomerClient {

    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable("id") String id);

}
