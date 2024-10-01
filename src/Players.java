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

    // Getter method to retrieve player's symbol
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void selectSquare(int row, int col) {
        // Check if the selected square byt the user is not already taken or marked. 
        cs3331TicTacToeSquare square = board.squareAt(row, col);
        if (square != null) {
            try {
                // Assign player's symbol to the selected sqaure spot
                square.markSquare(this.symbol);
                //Letting the controller know that the  player is finished with their turn.
                controller.finishedTurn();
                // Notify the game to handle the end of the turn (switch player, check win/tie, etc.)
                game.endTurn();
            } catch (Exception e) {
                //handle invalid seletions by the user 
                game.invalidSquareChosen(row, col);
            }
        }


    }
}