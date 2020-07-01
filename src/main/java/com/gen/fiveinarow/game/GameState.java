package com.gen.fiveinarow.game;

/**
 * Genesys Five-In-A-Row assigment
 *
 * Inspiration for Connect 5 algorithms:
 *
 * https://github.com/Samin12/Connect4/blob/master/src/Recursive4.java
 * https://github.com/AWScode/NewBananas/blob/master/Game/game.java
 *
 */
public class GameState {

    final static int NUM_COLUMNS = 9;
    final static int NUM_ROWS = 6;
    final static int BOTTOM_ROW = NUM_ROWS - 1;

    // instance variables - replace the example below with your own
    public static String[][] board = new String[NUM_ROWS][NUM_COLUMNS];
    public static String message;
    public static boolean gameCreated = false;
    public static String playerName1 = "";
    public static String playerName2 = "";
    public static boolean player1Turn = true;

    //creates the board
    public static void makeGame() {
        for (int r = 0; NUM_ROWS > r; r += 1) {
            for (int c = 0; NUM_COLUMNS > c; c += 1) {
                board[r][c] = " ";
            }
        }
        gameCreated = true;
    }

    //prints the board
    public static String printGame() {
        //prints blank board after setup
        String result = "<br />";
        // prints the board
        for (int r = 0; NUM_ROWS > r; r += 1) {
            for (int c = 0; NUM_COLUMNS > c; c += 1) {
                result += "[" + board[r][c] + "]";
            }
            result += "<br />";
        }
        result += "<br />";
        for (int c = 0; NUM_COLUMNS > c; c += 1) {
            result += "[" + c + "]";
        }
        result += "<br />";
        return result;
    }

    //CHECKING FOR WIN

    //Checks if a player 1 has won horizontally
    public static boolean checkXHorizontal(){
        // creates boolean to act as flag
        boolean win = false;

        // creates counter
        int counter = 0;
        while (!win) {

            // goes through board horizontally
            for (int r = 0; NUM_ROWS > r; r += 1) {
                for (int c = 0; NUM_COLUMNS > c; c += 1) {
                    if (board[r][c].equals("X")) { // if it finds an X, add 1 to counter
                        counter += 1;
                    } else {
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if (counter >= 5) {
                        message = "Player 2 has won!"; // if counter is greater or equal to 5, player wins
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    //Checks if player 1 has won vertically
    public static boolean checkXVertical(){
        // creates boolean to act as flag
        boolean win = false;

        // creates counter
        int counter = 0;
        while (!win) {

            // goes through board vertically
            for (int c = 0; NUM_COLUMNS > c; c += 1) {
                for (int r = 0; NUM_ROWS > r; r += 1) {
                    if (board[r][c].equals("X")) { // if it finds an X, add 1 to counter
                        counter += 1;
                    } else {
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if (counter >= 5) {
                        message = "Player 2 has won!"; // if counter is greater or equal to 5, player wins
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    //Checks if player 2 has won vertically
    public static boolean checkOVertical(){
        // creates boolean to act as flag
        boolean win = false;

        // creates counter
        int counter = 0;
        while (!win) {

            // goes through board vertically
            for (int c = 0; NUM_COLUMNS > c; c += 1) {
                for (int r = 0; NUM_ROWS > r; r += 1) {
                    if (board[r][c].equals("O")) { // if it finds an O, add 1 to counter
                        counter += 1;
                    } else {
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if (counter >= 5) {
                        message = "Player 1 has won!"; // if counter is greater or equal to 5, player wins
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    //checks if player 2 has won horizontally
    public static boolean checkOHorizontal(){
        // creates boolean to act as flag
        boolean win = false;

        // creates counter
        int counter = 0;
        while (!win) {
            // goes through board vertically
            for (int r = 0; NUM_ROWS > r; r += 1) {
                for (int c = 0; NUM_COLUMNS > c; c += 1) {
                    if (board[r][c].equals("O")) { // if it finds an O, add 1 to counter
                        counter += 1;
                    } else {
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if (counter >= 5) {
                        message = "Player 1 has won!"; // if counter is greater or equal to 5, player wins
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    public static boolean checkXDiagonalForward() {
        // Ascending diagonal
        for (int c = 5; c > 3; c--) {
            for (int r = 0; r < 5; r++) {
                if (board[r][c].equals("X") && board[r][c] == board[r + 1][c - 1] && board[r][c] == board[r + 2][c - 2]
                        && board[r][c] == board[r + 3][c - 3] && board[r][c] == board[r + 4][c - 4]) {
                    message = "Player 2 has won!";
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckODiagonalForward() {
        // Ascending diagonal
        for (int c = 5; c > 3; c--) {
            for (int r = 0; r < 5; r++) {
                if (board[r][c].equals("O") && board[r][c] == board[r + 1][c - 1] && board[r][c] == board[r + 2][c - 2]
                        && board[r][c] == board[r + 3][c - 3] && board[r][c] == board[r + 4][c - 4]) {
                    message = "Player O has won!";
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkXDiagonalBack() {
        // Descending diagonal
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 2; i++) {
                if (board[j][i].equals("X") && board[j][i] == board[j + 1][i + 1] && board[j][i] == board[j + 2][i + 2]
                        && board[j][i] == board[j + 3][i + 3] && board[j][i] == board[j + 4][i + 4]) {
                    System.out.println("Player 2 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckODiagonalBack() {
        // Descending diagonal
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 2; c++) {
                if (board[r][c].equals("O") && board[r][c] == board[r + 1][c + 1] && board[r][c] == board[r + 2][c + 2]
                        && board[r][c] == board[r + 3][c + 3] && board[r][c] == board[r + 4][c + 4]) {
                    System.out.println("Player 1 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void DropX(int column) {
        // creates a counter
        int counter = 1;

        // shows whos turn
        message = "Player 1's turn!";

        while (true) {
            if (column > NUM_COLUMNS) {
                message = "That's not a valid column";
                break;
            }

            if (board[BOTTOM_ROW][column].equals(" ")) { // checks to see if space is blank, puts X there if it is
                board[BOTTOM_ROW][column] = "X";
                break; // breaks loop after placing
            } else if (board[BOTTOM_ROW][column].equals("X") || board[BOTTOM_ROW][column].equals("O")) { // if space isn't blank,
                // checks to see if one
                // above is
                if (board[BOTTOM_ROW - counter][column].equals(" ")) { // puts X if blank
                    board[BOTTOM_ROW - counter][column] = "X";
                    break; // breaks loop after placing
                }
            }
            counter += 1; // adds one to counter if the space wasn't blank, then loops again
            if (counter == NUM_ROWS) { // checks to see if at end of column
                message = "That column is full";
                break;
            }
        }
    }

    public static void DropO(int column){
        //creates a counter
        int counter = 1;

        //shows whos turn
        message = "Player 2's turn!";

        while(true){
            if(column > NUM_COLUMNS){
                message = "That's not a valid column";
                break;
            }

            if (board[BOTTOM_ROW][column].equals(" ")) { //checks to see if space is blank, puts O there if it is
                board[BOTTOM_ROW][column] = "O";
                break; //breaks loop after placing
            }else if(board[BOTTOM_ROW][column].equals("X") || board[BOTTOM_ROW][column].equals("O")){ //if space isn't blank, checks to see if one above is
                if(board[BOTTOM_ROW - counter][column].equals(" ")){ //puts O if blank
                    board[BOTTOM_ROW - counter][column] = "O";
                    break; //breaks loop after placing
                }
            }
            counter += 1; //adds one to counter if the space wasn't blank, then loops again
            if(counter == NUM_ROWS){ //checks to see if at end of column
                message = "That column is full";
                break;
            }
        }
    }

    public static boolean CheckX() {
        // creates flag
        boolean flag = false;

        // checks all Xs at once, for cleaner main loop
        if (checkXVertical() || checkXHorizontal() || checkXDiagonalBack() || checkXDiagonalForward()) {
            flag = true;
        }
        return flag;
    }

    public static boolean CheckO() {
        // creates flag
        boolean flag = false;

        // checks all Os at once, for cleaner main loop
        if (checkOVertical() || checkOHorizontal() || CheckODiagonalBack() || CheckODiagonalForward()) {
            flag = true;
        }
        return flag;
    }

    public static boolean endGame() {
        board = new String[NUM_ROWS][NUM_COLUMNS];
        GameState.message = "";
        GameState.makeGame();
        GameState.printGame();

        GameState.player1Turn = true;
        GameState.gameCreated = false;
        return true;
    }
}
