package com.gen.fiveinarow.controller;

import com.gen.fiveinarow.game.GameLogic;
import com.gen.fiveinarow.game.GameState;
import com.gen.fiveinarow.model.GameModel;
import com.gen.fiveinarow.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Inspiration for Restful WebSocket from TechPrimers tutorial
 *
 * https://www.youtube.com/watch?v=OK2Fn6k7pwo
 */
@Controller
public class GameController {

    @MessageMapping("/game")
    @SendTo("/topic/fiveinarow")
    public GameModel startGame(Player player) {

        GameModel gameModel = GameLogic.performStartGame(player);
        return gameModel;
    }

    @MessageMapping("/move")
    @SendTo("/topic/fiveinarow")
    public GameModel play(Player player) {

        GameModel gameModel = GameLogic.performMove(player);
        return gameModel;
    }

    @MessageMapping("/disconnect")
    @SendTo("/topic/fiveinarow")
    public GameModel disconnect(Player player) {

        GameModel gameModel = GameLogic.endGame(player);
        return gameModel;
    }

}

