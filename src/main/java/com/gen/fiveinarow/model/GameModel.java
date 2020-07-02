package com.gen.fiveinarow.model;

/**
 * Model representation of the game state messages
 */
public class GameModel {

    public GameModel() {
    }

    private String message;

    public GameModel(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}