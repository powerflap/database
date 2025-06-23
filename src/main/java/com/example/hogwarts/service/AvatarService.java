package com.example.hogwarts.service;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.repository.AvatarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarService {

    private final AvatarRepository repository;

    public AvatarService(AvatarRepository repository) {
        this.repository = repository;
    }

    public List<Avatar> getAvatars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }
}
