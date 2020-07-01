package com.gen.fiveinarow.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameModelTest {

    private static String msg = "Invalid move!";

    @Test
    public void GameModel() {
        GameModel gameModel = new GameModel(msg);
        assertEquals(msg, gameModel.getMessage());
    }

}
