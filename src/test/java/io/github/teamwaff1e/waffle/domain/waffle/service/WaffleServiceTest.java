package io.github.teamwaff1e.waffle.domain.waffle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WaffleServiceTest {

    @Autowired
    WaffleService waffleService;

    @Test
    void readWaffle() {
        waffleService.readWaffle(1L);
    }


}