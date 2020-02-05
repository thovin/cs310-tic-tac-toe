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
        
        Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    }
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    }

    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
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

    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
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
        

    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        return row <= width && col <= width && row >= 0 && col >= 0;

        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        try {
            return board[row][col] != Mark.EMPTY;
        }
        catch (ArrayIndexOutOfBoundsException e){
            return true;
        }

    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col];

    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
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


    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
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

        return yAxis || xAxis || diagonal1 || diagonal2;

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        return !isMarkWin(Mark.X) && !isMarkWin(Mark.O);

    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        boolean isOver = true;
        for (int i = 0; i < width; i++) {
            for (int n = 0; n < width; n++) {
                if (board[i][n] == Mark.EMPTY)
                    isOver = false;
            }
        }
        
        return isOver;

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
