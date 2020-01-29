package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {//DONE?
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                board[i][j] = Mark.EMPTY;
            }
        }
        // INSERT YOUR CODE HERE
        
    }
	
    public boolean makeMark(int row, int col) {//DONE?
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        boolean canMark = true;
        
        if (!isValidSquare(row, col)) {canMark = false;}
        else if (isSquareMarked(row, col)) {canMark = false;}
        
        if (canMark) {
            if (xTurn) {
                board[row][col] = Mark.X;
                xTurn = false;
            }
            
            else {
                board[row][col] = Mark.O;
                xTurn = true;
            }
        }
        
        return canMark;
        
        //return false; // remove this line later!
        
    }
	
    private boolean isValidSquare(int row, int col) {//DONE?
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        // INSERT YOUR CODE HERE
        return (row > width || col > width || row < 0 || col < 0)? false : true;
        //return !(row > size || col > size || row < 0 || col < 0);


        //return false; // remove this line later!
        
    }
	
    private boolean isSquareMarked(int row, int col) {//DONE?
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE
        try {
            return board[row][col] != Mark.EMPTY;
        }
        catch (ArrayIndexOutOfBoundsException e){
            return true;
        }

        //return false; // remove this line later!
            
    }
	
    public Mark getMark(int row, int col) {//DONE?
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];

        //return null; // remove this line later!
            
    }
	
    public Result getResult() {//DONE?
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if (isMarkWin(Mark.X)){
            return Result.X;
        }
        
        else if (isMarkWin(Mark.O)){
            return Result.O;
        }
        
        else if (!isGameover()) {
            return Result.NONE;
        }
        
        else if (isTie()) {
            return Result.TIE;
        }
        
        else
            return null;

        //return null; // remove this line later!
        
    }
	
    private boolean isMarkWin(Mark mark) {//DONE?
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        boolean markWins = false;
        boolean xAxis = true;
        boolean yAxis = true;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        
        for (int i = 0; i < width; i++){
            xAxis = true;
            yAxis = true;
            for (int n = 0; n < width; n++){
                if (board[i][n] != mark){
                    yAxis = false;
                }
                
                if (board[n][i] != mark){
                    xAxis = false;
                }
            }
            
            if (yAxis || xAxis){
                break;
            }
            
            if (board[i][i] != mark){
                diagonal1 = false;
            }

            if (board[i][width - i - 1] != mark){
                diagonal2 = false;
            }
        }
        
        if (yAxis || xAxis || diagonal1 || diagonal2)
            return true;
        
        else
            return false;

        //return false; // remove this line later!

    }
	
    private boolean isTie() {//DONE?
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE
        if (!isMarkWin(Mark.X) && !isMarkWin(Mark.O))
            return true;
        
        else
            return false;

        //return false; // remove this line later!
        
    }

    public boolean isGameover() {//Adjusted for my code
        
        /* Return TRUE if the game is over */
        
        boolean isOver = true;
        for (int i = 0; i < width; i++) {
            for (int n = 0; n < width; n++) {
                if (board[i][n] == Mark.EMPTY)
                    isOver = false;
            }
        }
        
        return isOver;
        //return true;
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {//DONE?
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE
        //System.out.print("\n\n  ");
        /*
        System.out.print("  ");
        for (int i = 0; i < width; i++){
            System.out.print(i);
        }
        System.out.print("\n\n");

        for (int i = 0; i < width; i++){
            System.out.print(i + " ");
            
            for (int j = 0; j < width; j++){
                System.out.print(board[i][j].toString());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        */

        //output.append("  ");
        for (int i = 0; i < width; i++){
            output.append(i);
        }
        output.append("\n");

        for (int i = 0; i < width; i++){
            output.append(i + " ");
            
            for (int j = 0; j < width; j++){
                output.append(board[i][j].toString());
            }
            output.append("\n");
        }
        output.append("\n");

        return output.toString();
        
    }
    
}
