package org.example;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.Game;
import org.example.fxImplementation.FxBoard;
import org.example.fxImplementation.FxCell;
import org.example.fxImplementation.GameDrawer;

public class PrimaryController {
    @FXML
    Pane pane;
    @FXML
    Pane shipPane;
    @FXML
    public void initialize() {
        Game game = new Game();
        game.runGame();
        GameDrawer gameDrawer = new GameDrawer(game, pane);
        gameDrawer.DrawBoard();
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
