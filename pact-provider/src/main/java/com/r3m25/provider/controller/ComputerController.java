package com.r3m25.provider.controller;

import com.r3m25.provider.domain.Computer;
import com.r3m25.provider.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping("/computer")
    public ResponseEntity<Computer> getComputer() {
        return ResponseEntity.ok().body(computerService.getComputer());
    }

}
