package com.gen.fiveinarow.game;

import com.gen.fiveinarow.model.GameModel;
import com.gen.fiveinarow.model.Player;

public class GameLogic {

    private static final String HTML_BREAK = "<p><br /></p>";

    public static GameModel performStartGame(Player player) {
        String response = "";

        //Checking Player name length
        if (player.getName().trim().isEmpty()) {
            response = "Please enter a player name";
            return new GameModel(response);

        //Checking if game is created
        } else if (!GameState.gameCreated) {
            GameState.makeGame();
            GameState.playerName1 = player.getName();
            response = HTML_BREAK + "Player 1: " + player.getName() + " has joined the game";
        } else {
            GameState.playerName2 = player.getName();
            response = HTML_BREAK + "Player 2: " + player.getName() + " has joined the game";
        }
        return new GameModel("Welcome to the game, " + player.getName() + HTML_BREAK + GameState.printGame() + response);
    }

    public static GameModel performMove(Player player) {

        String response = validateRules(player);

        if(!response.equals("")) {
            return new GameModel(response);
        } else if (GameState.playerName1.equals(player.getName())) {
            if (GameState.player1Turn) {
                GameState.DropO(player.getMove());

                if(!GameState.message.equals("That column is full")) {
                    GameState.player1Turn = false;
                }

            } else {
                response = "Its player 2's turn! " + HTML_BREAK + GameState.printGame();
                return new GameModel(response);
            }
        } else {
            if (!GameState.player1Turn) {
                GameState.DropX(player.getMove());

                if(!GameState.message.equals("That column is full")) {
                    GameState.player1Turn = true;
                }

            } else {
                response = "Its player 1's turn! " + HTML_BREAK + GameState.printGame();
                return new GameModel(response);
            }
        }

        if(GameState.CheckX()) {
            response = "Player 2: " + GameState.playerName2 + " has Won!" + HTML_BREAK;
            return new GameModel(response);
        } else if(GameState.CheckO()) {
            response = "Player 1: " + GameState.playerName1 + " has Won!" + HTML_BREAK;
            return new GameModel(response);
        } else {
            response = GameState.message + HTML_BREAK + GameState.printGame();
            return new GameModel(response);
        }
    }

    public static GameModel endGame(Player player) {
        String response = "";

        response = player.getName() + " forfeited. You win!" + HTML_BREAK;
        GameState.endGame();
        return new GameModel(response);
    }

    private static String validateRules(Player player) {
        String response = "";

        if (GameState.playerName2.isEmpty()) {
            response = "Waiting for Player 2 to join!" + HTML_BREAK + GameState.printGame();
        } else if (player.getMove() < 0 || player.getMove() >= 10) {
            response = "Please enter a valid move! (0-9)" + HTML_BREAK + GameState.printGame();
        }

        return response;
    }
}
