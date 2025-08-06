package com.example.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class InfoService {

    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);

    public int calculateParallelSum() {
        logger.info("Was invoked method for calculating parallel sum from 1 to 1,000,000");
        return IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}
