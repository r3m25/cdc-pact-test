package com.r3m25.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    private Integer id;
    private String type;
    private String brand;
    private String model;
    private Card card;
    private Processor processor;
    private Ram ram;
    private String[] decorators;
    private List<Component> component;
}
