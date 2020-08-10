package com.r3m25.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String address;
    private String phone;
}
