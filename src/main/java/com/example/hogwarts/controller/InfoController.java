package com.example.hogwarts.controller;

import com.example.hogwarts.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/parallel-sum")
    public int getParallelSum() {
        return infoService.calculateParallelSum();
    }
}
