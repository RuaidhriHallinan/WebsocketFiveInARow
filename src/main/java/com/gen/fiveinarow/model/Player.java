package com.gen.fiveinarow.model;

/**
 * Model representation of a Five-In-A-Row game player
 *
 */
public class Player {

    private String name;
    private int move;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int move) {
        this.name = name;
        this.move = move;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
