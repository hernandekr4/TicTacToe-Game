import cs3331Helper.cs3331TicTacToeGame;
import cs3331Helper.cs3331TicTacToePlayer;
import cs3331Helper.cs3331TicTacToeBoard;
import cs3331Helper.cs3331TicTacToeController;
import javafx.scene.paint.Color;

public class myTicTacToe implements cs3331TicTacToeGame {
    private cs3331TicTacToeBoard board;
    private cs3331TicTacToeController controller;
    private Players playerX;
    private Players playerO;
    private Players currentPlayer;



    public myTicTacToe() {
        // Initialize the board and controller
        this.board = new cs3331TicTacToeBoard();
        this.controller = new cs3331TicTacToeController();
        

        //Create two instances of player class
        // Initialize players with symbols "X" and "O"
        this.playerX = new Players("X", board, controller);
        this.playerO = new Players("O", board, controller);
        // add both instances to the contorller 
        // Start game with Player X's turn
        currentPlayer = playerX; // Start with player X

        controller.addPlayer(playerX);
        controller.addPlayer(playerO);


            // Start game message
    controller.setControllerMessage("Select a square to start playing!");
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
    if (winner instanceof Players) {  // Check if the instance can be cast to Players
        Players winningPlayer = (Players) winner;  // Safe casting
        controller.setControllerMessage("Player " + winningPlayer.getSymbol() + " wins");
        board.highlightWinningSquares(Color.GREEN);
    } else {
        controller.setControllerMessage("Error: Winning player data is not available.");
    }
}

    @Override
    public void restartGame() {
        board.clearSymbols();
        board.clearHighlights();
            currentPlayer = playerX; // Reset to start with Player X

        controller.playAgain();
        controller.setControllerMessage("Select a square to start playing");
    }

    

    
}
