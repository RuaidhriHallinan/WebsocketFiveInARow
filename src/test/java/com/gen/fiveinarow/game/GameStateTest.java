package com.gen.fiveinarow.game;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class GameStateTest {

    public GameState gameState;
    private static final Logger LOG = LoggerFactory.getLogger(GameStateTest.class);

    @BeforeAll
    public void setUp() {
        gameState = new GameState();
        gameState.makeGame();
        printGameBoard();
    }

    @After
    public void cleanUp() {
        gameState.endGame();
    }

    @Test
    public void checkXVerticalWin() {
        LOG.info("\ncheckXVerticalWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][0] = "X";
        gameState.board[2][0] = "X";
        gameState.board[3][0] = "X";
        gameState.board[4][0] = "X";
        gameState.board[5][0] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "X";
        gameState.board[2][0] = "X";
        gameState.board[3][0] = "X";
        gameState.board[4][0] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertFalse(gameWon);
    }

    @Test
    public void checkXDiagonalBackWin() {
        LOG.info("\ncheckXDiagonalBackWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][0] = "X";
        gameState.board[2][1] = "X";
        gameState.board[3][2] = "X";
        gameState.board[4][3] = "X";
        gameState.board[5][4] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "X";
        gameState.board[2][1] = "X";
        gameState.board[3][2] = "X";
        gameState.board[4][3] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertFalse(gameWon);
    }

    @Test
    public void checkXDiagonalForwardWin() {
        LOG.info("\ncheckXDiagonalForwardWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][4] = "X";
        gameState.board[2][3] = "X";
        gameState.board[3][2] = "X";
        gameState.board[4][1] = "X";
        gameState.board[5][0] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "X";
        gameState.board[2][1] = "X";
        gameState.board[3][2] = "X";
        gameState.board[4][3] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertFalse(gameWon);
    }

    @Test
    public void checkXHorizontalWin() {
        LOG.info("\ncheckXHorizontalWin");

        boolean gameWon = false;
        gameState.makeGame();

        LOG.info("\ntestHorizontalWin");
        gameState.board[5][0] = "X";
        gameState.board[5][1] = "X";
        gameState.board[5][2] = "X";
        gameState.board[5][3] = "X";
        gameState.board[5][4] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertTrue(gameWon);

        gameState.makeGame();
        gameState.board[5][0] = "X";
        gameState.board[5][1] = "X";
        gameState.board[5][2] = "X";
        gameState.board[5][3] = "X";
        printGameBoard();
        gameWon = gameState.CheckX();
        assertFalse(gameWon);
    }


    @Test
    public void checkOVerticalWin() {
        LOG.info("\ncheckOVerticalWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][0] = "O";
        gameState.board[2][0] = "O";
        gameState.board[3][0] = "O";
        gameState.board[4][0] = "O";
        gameState.board[5][0] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "O";
        gameState.board[2][0] = "O";
        gameState.board[3][0] = "O";
        gameState.board[4][0] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertFalse(gameWon);
    }

    @Test
    public void checkODiagonalBackWin() {
        LOG.info("\ncheckODiagonalBackWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][0] = "O";
        gameState.board[2][1] = "O";
        gameState.board[3][2] = "O";
        gameState.board[4][3] = "O";
        gameState.board[5][4] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "O";
        gameState.board[2][1] = "O";
        gameState.board[3][2] = "O";
        gameState.board[4][3] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertFalse(gameWon);
    }

    @Test
    public void checkODiagonalForwardWin() {
        LOG.info("\ncheckODiagonalForwardWin");

        boolean gameWon = false;
        gameState.makeGame();

        gameState.board[1][4] = "O";
        gameState.board[2][3] = "O";
        gameState.board[3][2] = "O";
        gameState.board[4][1] = "O";
        gameState.board[5][0] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertTrue(gameWon);

        gameState.makeGame();

        gameState.board[1][0] = "O";
        gameState.board[2][1] = "O";
        gameState.board[3][2] = "O";
        gameState.board[4][3] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertFalse(gameWon);
    }

    @Test
    public void checkOHorizontalWin() {
        LOG.info("\ncheckOHorizontalWin");

        boolean gameWon = false;
        gameState.makeGame();

        LOG.info("\ntestHorizontalWin");
        gameState.board[5][0] = "O";
        gameState.board[5][1] = "O";
        gameState.board[5][2] = "O";
        gameState.board[5][3] = "O";
        gameState.board[5][4] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertTrue(gameWon);

        gameState.makeGame();
        gameState.board[5][0] = "O";
        gameState.board[5][1] = "O";
        gameState.board[5][2] = "O";
        gameState.board[5][3] = "O";
        printGameBoard();
        gameWon = gameState.CheckO();
        assertFalse(gameWon);
    }

    @Test
    public void dropOInvalidTest() {
        LOG.info("\ndropOInvalidTest");
        gameState.makeGame();
        gameState.DropO(10);
        assertEquals(gameState.message, "That's not a valid column");
        printGameBoard();
    }

    @Test
    public void dropOValidTest() {
        LOG.info("\ndropOValidTest");
        gameState.makeGame();
        gameState.DropO(1);
        assertEquals(gameState.message, "Player 2's turn!");
        printGameBoard();
    }

    @Test
    public void dropXInvalidTest() {
        LOG.info("\ndropXInvalidTest");
        gameState.makeGame();
        gameState.DropX(10);
        assertEquals(gameState.message, "That's not a valid column");
        printGameBoard();
    }

    @Test
    public void dropXValidTest() {
        LOG.info("\ndropXValidTest");
        gameState.makeGame();
        gameState.DropX(1);
        assertEquals(gameState.message, "Player 1's turn!");
        printGameBoard();
    }

    @Test
    public void dropXDropOTest() {
        LOG.info("\ndropXDropOTest");
        gameState.makeGame();
        gameState.DropX(1);
        gameState.DropO(1);
        assertEquals(gameState.message, "Player 2's turn!");
        printGameBoard();
    }

    @Test
    public void dropODropXTest() {
        LOG.info("\ndropODropXTest");
        gameState.makeGame();
        gameState.DropO(1);
        gameState.DropX(1);
        assertEquals(gameState.message, "Player 1's turn!");
        printGameBoard();
    }

    @Test
    public void dropODropXFullTest() {
        LOG.info("\ndropODropXFullTest");
        gameState.makeGame();
        gameState.DropO(1);
        gameState.DropX(1);
        gameState.DropO(1);
        gameState.DropX(1);
        gameState.DropO(1);
        gameState.DropX(1);
        gameState.DropO(1);
        assertEquals(gameState.message, "That column is full");
        printGameBoard();
    }

    @Test
    public void dropXDropOFullTest() {
        LOG.info("\ndropODropXFullTest");
        gameState.makeGame();
        gameState.DropX(1);
        gameState.DropO(1);
        gameState.DropX(1);
        gameState.DropO(1);
        gameState.DropX(1);
        gameState.DropO(1);
        gameState.DropX(1);
        assertEquals(gameState.message, "That column is full");
        printGameBoard();
    }

    public void printGameBoard() {
        LOG.info(gameState.printGame().replace("<br />", "\n"));
    }
}
