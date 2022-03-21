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
import org.example.backedn.Game;
import org.example.fxImplementation.GameDrawer;

import java.io.IOException;

public class PrimaryController {
    private Game game;
    @FXML
    Pane pane;
    @FXML
    Button beginButton;
    @FXML
    public void initialize() {
        game = new Game();
        game.runGame();
        GameDrawer gameDrawer = new GameDrawer(game, pane);
        beginButton.setFocusTraversable(false);
        gameDrawer.DrawPlayerBoard();
        gameDrawer.DrawShips();
    }
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

        /*secondaryController.setPlayerPane(Pane);
        secondaryController.setEnemyPane();
        secondaryController.();*/

    }

    /*private void pressed(MouseEvent e , FxCell cell){
        cell.getRectangle().setFill(Color.GREEN);
    }

    private void dragged(MouseEvent e ){
       Node node = e.getPickResult().getIntersectedNode();
       try {
           Rectangle rectangle = (Rectangle) node;
           rectangle.setFill(Color.YELLOW);
       }
       catch (Exception ignored){

       }

    }
    private void dropped(MouseEvent e , Rectangle r){
        r.setFill(Color.YELLOW);
    }*/

}
