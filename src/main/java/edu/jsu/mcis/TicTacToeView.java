package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {
    
    private final Scanner keyboard;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView() {
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);
        
    }
	
    public TicTacToeMove getNextMove(boolean isXTurn) { //DONE?
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */
        System.out.println("Please enter the row and column of your move (R C)");
        Scanner in = new Scanner(System.in);
        int userRow = in.nextInt();
        int userCol = in.nextInt();

        return new TicTacToeMove(userRow, userCol);


        // INSERT YOUR CODE HERE

       // return null; // remove this line later!

    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }
    
    public void showBoard(String board) {
        
        System.out.println("\n" + board);
        
    }
	
}
