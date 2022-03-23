package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.backend.Game;
import org.example.view.GameDrawer;

import java.io.IOException;

/**
 * klasa obsługująca rozstawienie statków na planszy gracza
 */
public class PrimaryController {
    /**
     * obiekt reprezentujący instancję gry
     */
    private Game game;
    /**
     * miejsce, na którym zostaje narysowana plansza
     */
    @FXML
    Pane pane;
    /**
     * przycisk przechodzący do kolejnej sceny
     */
    @FXML
    Button beginButton;

    /**
     * metoda inicjalizująca wygląd sceny
     */
    @FXML
    public void initialize() {
        game = new Game();
        game.runGame();
        GameDrawer gameDrawer = new GameDrawer(game, pane);
        beginButton.setFocusTraversable(false);
        gameDrawer.DrawPlayerBoard();
        gameDrawer.DrawShips();
    }

    /**
     * metoda rozpoczynająca właściwą grę w statki
     * @param event miejsce kliknięcia przycisku button
     * @throws IOException  rzucany, jeżeli nie uda się utworzyć obiektu fxlLoader
     */
    @FXML
    public void beginGame(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary" + ".fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            SecondaryController secondaryController = fxmlLoader.getController();
            secondaryController.setGame(game);
            secondaryController.beginGame();
            stage.show();
    }
}
