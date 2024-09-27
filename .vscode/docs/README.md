# JavaFX Tic TacToe Game

## Project Description
This JavaFX application implements a classic Tic TacToe game with a graphical user interface. The game is designed for two players and provides visual feedback for moves, victories, and ties.

## Technologies Used
- Java
- JavaFX
- cs3331Helper (custom helper package for managing game state and UI interactions)

## Setup
To run this project, you will need:
- Java JDK 11 or later
- JavaFX SDK (compatible with your JDK version)
- Any IDE that supports Java and JavaFX (e.g., IntelliJ IDEA, Eclipse)

### Configuration
Ensure your JavaFX SDK path is correctly configured in your project settings:
- For IntelliJ IDEA:
  1. Go to `File` > `Project Structure` > `Libraries`
  2. Add your JavaFX SDK as a library by pointing to its `lib` folder.
  3. Ensure that VM options for your run configuration include:
     ```
     --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
     ```

## Running the Application
To run the game, follow these steps:
1. Clone this repository to your local machine.
2. Open the project in your chosen IDE.
3. Ensure that the main class is set to `App.java`.
4. Run `App.java` to start the game.

## How to Play
- The game starts immediately upon launching the application.
- Players take turns clicking on the TicTacToe grid to place their symbol (X or O).
- The first player to align three symbols vertically, horizontally, or diagonally wins.
- If all squares are filled and no player has three aligned symbols, the game is a tie.
- Press the "Restart" button to clear the board and start a new game.

## Contributing
Contributions to this project are welcome. Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
