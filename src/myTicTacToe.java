import cs3331Helper.cs3331TicTacToeGame;
import cs3331Helper.cs3331TicTacToePlayer;
import cs3331Helper.cs3331TicTacToeBoard;
import cs3331Helper.cs3331TicTacToeController;
import javafx.scene.paint.Color;

public class myTicTacToe implements cs3331TicTacToeGame {


    private static final int MIN_MOVES_FOR_WIN = 5;
    private static final int MAX_MOVES = 9;

    private cs3331TicTacToeBoard board;
    private cs3331TicTacToeController controller;
    private Players playerX;
    private Players playerO;
    private Players currentPlayer;
    private int moveCount;


    public myTicTacToe() {
        // Initialize the board and controller
        this.board = new cs3331TicTacToeBoard();
        this.controller = new cs3331TicTacToeController();


        //Create two instances of player class
        // Initialize players with symbols "X" and "O"
        this.playerX = new Players("X", board, controller, this);
        this.playerO = new Players("O", board, controller, this);
        // add both instances to the contorller 
        // Start game with Player X's turn
        this.currentPlayer = playerX; // Start with player X
        this.moveCount = 0;

        controller.addPlayer(playerX);
        controller.addPlayer(playerO);


        // Start game message
        controller.setControllerMessage("Select a square to start playing!");
    }

    public void endTurn() {
        // Increment move count after each move
        moveCount++;

        // Only check for a winner if there have been at least 5 moves (because a win cannot occur before that)
        if (moveCount >= MIN_MOVES_FOR_WIN) {
            cs3331TicTacToePlayer winner = controller.getWinningPlayer();
            if (winner != null) {
                playerWins(); // Announce the winner if there is one
                return; // Stop the game if there is a winner
            }
        }

        // Check for a tie after 9 moves if no winner
        if (moveCount == MAX_MOVES) {
            // All squares filled and no winner, declare a tie
            noWinner();
            return; // Stop the game if it's a tie
        }

        // Switch players if no winner or tie
        switchPlayer();
    }



    private void switchPlayer() {
        // Toggle between Player X and Player O
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        controller.setControllerMessage("Player " + currentPlayer.getSymbol() + "'s turn");
    }

    private void clearPreviousWinner() {

        // Clear any residual winner by resetting the controller's message
        //controller.setControllerMessage("");   Clear the message first

        // The playAgain() method should reset all internal controller states, including winner state
        controller.playAgain(); // Reset the controller state, including any winner reference
        controller.setControllerMessage("Select a square to start playing!");

        // We also want to explicitly check the winning state and ensure it’s null
        if (controller.getWinningPlayer() != null) {
            // Force reset by setting the winning player to null in some way (assuming the controller has the method)
            System.out.println("Warning: Old winner still present after reset!");

        }
    }

    @Override
    public void invalidSquareChosen(int row, int col) {
        controller.setControllerMessage("Square chosen has already been taken. Try again.");
        board.squareAt(row, col).flashColor(Color.RED);
    }
    @Override
    public void noWinner() {
        controller.setControllerMessage("It's a tie");
    }


    @Override
    public void playerWins() {
        cs3331TicTacToePlayer winner = controller.getWinningPlayer();
        if (winner instanceof Players) { // Check if the instance can be cast to Players
            Players winningPlayer = (Players) winner; // Safe casting
            controller.setControllerMessage("Player " + winningPlayer.getSymbol() + " wins");
            board.highlightWinningSquares(Color.GREEN);
        } else {
            controller.setControllerMessage("Error: Winning player data is not available.");
        }
    }

    @Override
    public void restartGame() {


        // Clear the board and reset everything
        board.clearSymbols(); // Reset the visual symbols
        board.clearHighlights(); // Reset any highlight of the winning squares
        controller.playAgain(); // Reset the controller's state to handle a fresh game



        moveCount = 0; // Reset move count

        currentPlayer = playerX; // Start with Player X

        clearPreviousWinner(); // NEW METHOD to manually clear the winner


        // Ensure that the winning player is set to null to prevent message carryover
        controller.setControllerMessage("Select a square to start playing!"); // Reset the message
    }

}