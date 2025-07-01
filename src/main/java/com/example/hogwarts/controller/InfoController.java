package com.example.hogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final String port;

    public InfoController(@Value("${server.port}") String port) {
        this.port = port;
    }

    @GetMapping("/port")
    public String getPort() {
        return port;
    }
}
