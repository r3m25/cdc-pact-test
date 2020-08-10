package com.r3m25.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PactConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PactConsumerApplication.class, args);
    }

}
