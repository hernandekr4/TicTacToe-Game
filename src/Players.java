import cs3331Helper.cs3331TicTacToePlayer;
import cs3331Helper.cs3331TicTacToeSquare;
import cs3331Helper.cs3331TicTacToeBoard;
import cs3331Helper.cs3331TicTacToeController;

public class Players implements cs3331TicTacToePlayer {
    private String symbol;
    private cs3331TicTacToeBoard board;
    private cs3331TicTacToeController controller;

    public Players(String symbol, cs3331TicTacToeBoard board, cs3331TicTacToeController controller) {
        this.symbol = symbol;
        this.board = board;
        this.controller = controller;
    }

    // Getter for the player's symbol
    public String getSymbol() {
        return this.symbol;
    }
    /* */
    @Override
    public void selectSquare(int row, int col) {
        cs3331TicTacToeSquare square = board.squareAt(row, col);
        if (square != null) { // Checking if the square is not null
            square.markSquare(symbol);
            controller.finishedTurn();
        }
    }
}
