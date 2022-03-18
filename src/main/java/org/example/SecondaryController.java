package org.example;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.Cell;
import org.example.backedn.Game;
import org.example.backedn.GameSettings;
import org.example.fxImplementation.GameDrawer;

public class SecondaryController {
    @FXML
    Pane playerPane;
    @FXML
    Pane enemyPane;
    @FXML
    public void beginGame() {
        GameDrawer gameDrawer = new GameDrawer(game,playerPane,enemyPane);
        GameDrawer.DrawPlayerBoard();
        GameDrawer.DrawEnemyBoard();
        for(int i=0;i<GameSettings.getBoardSize();i++){
            for(int j=0;j<GameSettings.getBoardSize();j++){
                final int fJ=j,fI=i;
                GameDrawer.getEnemyRectangle(i,j).setOnMouseClicked(mouseEvent->onclick(mouseEvent,fI,fJ));
            }
        }
    }

    private void onclick(MouseEvent mouseEvent, int i, int j) {
        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        rectangle.setFill(Color.GREEN);
        rectangle.setOnMouseClicked(null);
        Random random = new Random();
        Cell cell;
        while(true) {
            cell=game.getPlayerBoard().getCell(random.nextInt(GameSettings.getBoardSize()),
                    random.nextInt(GameSettings.getBoardSize()));
            if(!cell.ifClicked()){
                cell.click();
                GameDrawer.getPlayerRectangle((int)(cell.getX()/GameSettings.getCellSize()),
                        (int)(cell.getY()/GameSettings.getCellSize())).setFill(Color.RED);
                break;
            }
        }
    }

    Game game;
    public void setGame(Game game) {
        this.game = game;
    }
}