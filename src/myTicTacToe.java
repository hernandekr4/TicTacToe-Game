import cs3331Helper.cs3331TicTacToeGame;
import cs3331Helper.cs3331TicTacToePlayer;
import cs3331Helper.cs3331TicTacToeBoard;
import cs3331Helper.cs3331TicTacToeController;
import cs3331Helper.cs3331TicTacToeFrame;
import javafx.scene.paint.Color;

public class myTicTacToe implements cs3331TicTacToeGame {



    //Instance variables
    private cs3331TicTacToeBoard board;
    private cs3331TicTacToeController controller;
    private Players playerX;
    private Players playerO;
    private Players currentPlayer;
    private int stepCount;
    private cs3331TicTacToePlayer winner;  // Global variable for tracking winner

        //Constants for minimum moves required for win and maximum for a tie 
        private static final int MOVES_TO_TRIGGER_WIN_CHECK = 5;
        private static final int MAX_TURNS_LIMIT = 9;


    // Consturctor to initialize the board 
    public myTicTacToe() {
        // Set up the board and controller
        this.board = new cs3331TicTacToeBoard();
        this.controller = new cs3331TicTacToeController();
        this.winner = null;

        //Create two players  which are two instances, with their respective symbols
        
        this.playerX = new Players("X", board, controller, this);
        this.playerO = new Players("O", board, controller, this);

        // Start game with Player X's turn
        this.currentPlayer = playerX; 

        //Start move count with 0
        this.stepCount = 0;

        // Add both instances to the contorller 
        controller.addPlayer(playerX);
        controller.addPlayer(playerO);


        // Start game message
        controller.setControllerMessage("Select a square to start playing");
    }



    //Method to execute after the end of each turn.
    public void endTurn() {
        // Add a move after each attempt
        stepCount++;

        // Check for winner once the minimum amount of winning moves is reached
        if (stepCount >= MOVES_TO_TRIGGER_WIN_CHECK) {
            winner = controller.getWinningPlayer();

            //Check for winner 
            if (winner != null) {
                playerWins(); 
                //Stop game if there is a winner 
                return; 
            }
        }

        // Check for a tie after 9 moves if no winner
        if (stepCount == MAX_TURNS_LIMIT) {
            // All squares filled and no winner, declare a tie
            noWinner();
            //Halt game 
            return;
        }

        // Switch players if no winner or tie is detected 
        switchPlayer();
    }


    
    private void switchPlayer() {
        // Toggle between Player X and Player O
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        controller.setControllerMessage("Player " + currentPlayer.getSymbol() + "'s turn");
    }

    

    @Override
    public void invalidSquareChosen(int row, int col) {
        controller.setControllerMessage("Square chosen has already been taken. Try again.");
        board.squareAt(row, col).flashColor(Color.RED);
    }
    @Override
    public void noWinner() {
        controller.setControllerMessage("it's a tie");
    }


    @Override
    public void playerWins() {
        winner = controller.getWinningPlayer();
        if (winner instanceof Players) { // Check if the instance can be cast to Players
            Players winningPlayer = (Players) winner; // Safe casting
            controller.setControllerMessage("Player " + winningPlayer.getSymbol() + " wins");
            board.highlightWinningSquares(Color.GREEN);
        } else {
            controller.setControllerMessage("Error: Winning player data not found.");
        }
    }
 

    
    @Override
    public void restartGame() {

        // Clear the board and reset everything, by resetting symbols, and highlights
        board.clearSymbols(); 
        board.clearHighlights(); 

        
        // Resets game settings in order to restart the game.
        controller.playAgain(); 
        
        // Reset move count
        stepCount = 0; 

        // Make sure to start with Player X
        currentPlayer = playerX; 

        // reset the controller message for new game
        controller.setControllerMessage("Select a square to start playing!"); // Reset the message
    }
}