package com.gen.fiveinarow.controller;

import com.gen.fiveinarow.game.GameState;
import com.gen.fiveinarow.model.GameModel;
import com.gen.fiveinarow.model.Player;
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
public class GameControllerStateLogicTest {

    @InjectMocks
    private GameController controller;

    @Autowired
    private GameState gameState;

    @Test
    public void gameStateLogicTest(){
        Player player1 = new Player("Player 1");
        GameModel model = controller.startGame(player1);
        Assert.assertThat(model.getMessage(), containsString("Welcome to the game, Player 1"));
        Assert.assertThat(model.getMessage(), containsString("Player 1 has joined the game"));

        player1.setMove(1);
        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Waiting for Player 2 to join!"));

        Player player2 = new Player("");
        model = controller.startGame(player2);
        Assert.assertThat(model.getMessage(), containsString("Please enter a player name"));

        player2.setName("");
        model = controller.startGame(player2);
        Assert.assertThat(model.getMessage(), containsString("Please enter a player name"));

        player2.setName("Player 2");
        model = controller.startGame(player2);
        Assert.assertThat(model.getMessage(), containsString("Welcome to the game, Player 2"));
        Assert.assertThat(model.getMessage(), containsString("Player 2 has joined the game"));

        player1.setMove(1);
        player2.setMove(2);

        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Player 2's turn!"));

        model = controller.play(player2);
        Assert.assertThat(model.getMessage(), containsString("Player 1's turn!"));

        player1.setMove(10);
        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Please enter a valid move! (0-9)"));

        player1.setMove(1);
        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Player 2's turn!"));

        player1.setMove(1);
        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Its player 2's turn!"));

        player2.setMove(2);
        model = controller.play(player2);
        Assert.assertThat(model.getMessage(), containsString("Player 1's turn!"));

        player2.setMove(2);
        model = controller.play(player2);
        Assert.assertThat(model.getMessage(), containsString("Its player 1's turn!"));

        player1.setMove(1);
        controller.play(player1);
        player2.setMove(2);
        controller.play(player2);
        player1.setMove(1);
        controller.play(player1);
        player2.setMove(2);
        controller.play(player2);
        player1.setMove(1);
        model = controller.play(player1);
        Assert.assertThat(model.getMessage(), containsString("Player 1: Player 1 has Won!"));

        //Resetting last winning move by player 1
        GameState.board[1][1] = " ";

        player2.setMove(2);
        model = controller.play(player2);
        Assert.assertThat(model.getMessage(), containsString("Player 2: Player 2 has Won!"));

        //Resetting last winning move by player 2
        GameState.board[1][2] = " ";
        printGameBoard("");

        model = controller.disconnect(player2);
        Assert.assertThat(model.getMessage(), containsString(player2.getName() + " forfeited. You win!"));
    }

    public void printGameBoard(String msg) {
        System.out.println(gameState.printGame().replace("<br />", "\n") +  msg);
    }
}

