package com.gen.fiveinarow.controller;

import com.gen.fiveinarow.game.GameState;
import com.gen.fiveinarow.model.GameModel;
import com.gen.fiveinarow.model.Player;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameControllerUnitTest {

    @InjectMocks
    private GameController controller;

    @Autowired
    private GameState gameState;

    @Before
    public void init() {
        //gameState.makeGame();
        //gameState.printGame();
    }

    @AfterAll
    public void cleanUp() {
        gameState.endGame();
    }

    @Test
    public void a_player1Join(){
        Player player = new Player("Player 1");
        GameModel model = controller.startGame(player);
        Assert.assertThat(model.getMessage(), containsString("Welcome to the game, Player 1"));
    }

    @Test
    public void b_player2Join(){
        Player player = new Player("Player 2");
        GameModel model = controller.startGame(player);
        Assert.assertThat(model.getMessage(), containsString("Welcome to the game, Player 2"));
    }

    @Test
    public void c_player1Move(){
        Player player1 = new Player("Player 1");
        controller.startGame(player1);
        Player player2 = new Player("Player 2");
        controller.startGame(player2);

        player2.setMove(1);

        GameModel model = controller.play(player2);
        Assert.assertThat(model.getMessage(), containsString("Its player 1's turn!"));
    }

    @Test
    public void d_playerInvalidJoin(){
        Player player = new Player("");
        GameModel model = controller.startGame(player);
        Assert.assertThat(model.getMessage(), containsString("Please enter a player name"));
    }

    @Test
    public void e_playerDisconnect(){
        Player player1 = new Player("Player 1");
        controller.startGame(player1);
        Player player2 = new Player("Player 2");
        controller.startGame(player2);

        player2.setMove(1);

        GameModel model = controller.disconnect(player2);
        Assert.assertThat(model.getMessage(), containsString("Player 2 forfeited. You win!"));
    }

}