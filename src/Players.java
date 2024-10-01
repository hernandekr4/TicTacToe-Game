import cs3331Helper.cs3331TicTacToePlayer;
import cs3331Helper.cs3331TicTacToeSquare;
import cs3331Helper.cs3331TicTacToeBoard;
import cs3331Helper.cs3331TicTacToeController;

public class Players implements cs3331TicTacToePlayer {
    private String symbol;
    private cs3331TicTacToeBoard board;
    private cs3331TicTacToeController controller;
    private myTicTacToe game;

    public Players(String symbol, cs3331TicTacToeBoard board, cs3331TicTacToeController controller, myTicTacToe game) {
        this.symbol = symbol;
        this.board = board;
        this.controller = controller;
        this.game = game;
    }

    // Getter for the player's symbol
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void selectSquare(int row, int col) {
        // Check if the selected square is not already marked
        cs3331TicTacToeSquare square = board.squareAt(row, col);
        if (square != null) {
            try {
                // Mark the square with the player's symbol (X or O)
                square.markSquare(this.symbol);

                // Inform the controller that the player's turn is finished
                controller.finishedTurn();

                // Notify the game to handle the end of the turn (switch player, check win/tie, etc.)
                game.endTurn();
            } catch (Exception e) {
                // Handle any case where marking the square is not allowed
                game.invalidSquareChosen(row, col);
            }
        }


    }
}