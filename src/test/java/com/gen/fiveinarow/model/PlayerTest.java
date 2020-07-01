package com.gen.fiveinarow.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayerTest {

    private static String playerName = "Genesys";
    private static int move = 1;

    @Test
    public void Player() {
        Player player = new Player(playerName);
        assertEquals(playerName, player.getName());
    }

    @Test
    public void PlayerMove() {
        Player player = new Player(playerName, move);
        assertEquals(move, player.getMove());
    }

    @Test
    public void getMove() {
        Player player = new Player();
        player.setName(playerName);
        player.setMove(1);
        assertEquals(1, player.getMove());
        assertEquals(playerName, player.getName());
    }
}
