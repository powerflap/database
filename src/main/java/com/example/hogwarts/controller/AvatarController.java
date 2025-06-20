package com.example.hogwarts.controller;

import com.example.hogwarts.service.AvatarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @GetMapping
    public List<Avatar> getAvatars(@RequestParam int page,
                                   @RequestParam int size) {
        return service.getAvatars(page, size);
    }
}
