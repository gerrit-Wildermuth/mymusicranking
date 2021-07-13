package com.acn.gamechangers.mymusicranking;

import com.acn.gamechangers.mymusicranking.controller.SongController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MymusicrankingApplicationTests {

    @Autowired
    private SongController songController;

    @Test
    void contextLoads() {
        assertThat(songController);
    }

}
