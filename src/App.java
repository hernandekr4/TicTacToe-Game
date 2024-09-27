import cs3331Helper.cs3331TicTacToeFrame;
import cs3331Helper.cs3331TicTacToeGame;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    public void start(Stage stage){

        cs3331TicTacToeFrame frame = new cs3331TicTacToeFrame(stage);
     
        // Add the game here to the frame
        myTicTacToe game = new myTicTacToe();
        
        frame.addGame(game);

    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
