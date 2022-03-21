package org.example;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.example.backedn.*;
import org.example.fxImplementation.GameDrawer;

public class SecondaryController {
    @FXML
    Pane playerPane;
    @FXML
    Pane enemyPane;
    @FXML
    VBox box;
    EnemyPlayer enemyPlayer = new EnemyPlayer();
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
        rectangle.setOnMouseClicked(null);
        Random random = new Random();
        Board playerboard=game.getPlayerBoard();
        Board enemyboard=game.getEnemyBoard();
        Boolean faliure = !game.getEnemyBoard().Shoot(i,j);
        while(faliure){
            if(!enemyPlayer.make_move(playerboard)){
                break;
            }
            GameDrawer.DrawPlayerBoard();
        }
        GameDrawer.DrawEnemyBoard();
        GameDrawer.DrawPlayerBoard();
        if(playerboard.getNumberOfShips()<=0){
            endGame("Przegrałeś");
        }
        if(enemyboard.getNumberOfShips()<=0){
            endGame("Wygrałeś");
        }
    }
    Game game;
    public void setGame(Game game) {
        this.game = game;
    }
    public void endGame(String massage){
        box.getChildren().clear();
        Label label = new Label();
        label.setText(massage);
        label.setFont(new Font("Arial", 50));
        if(massage == "Wygrałeś"){
            label.setTextFill(Color.GREEN);
        }
        else{
            label.setTextFill(Color.RED);
        }
        box.getChildren().add(label);
    };
}