package com.r3m25.provider.service;

import com.r3m25.provider.domain.Computer;
import org.springframework.stereotype.Service;

@Service
public class ComputerService {

    public Computer getComputer() {
        return Computer.builder().build();
    }
}
