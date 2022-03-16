package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PrimaryController {
    private final int BOARD_SIZE = 10;
    private final int RECTANGLE_SIZE = 40;
    private Rectangle[][] board = new Rectangle[BOARD_SIZE][BOARD_SIZE];
    @FXML
    Pane pane;
    @FXML
    public void initialize(){
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                Rectangle rectangle = new Rectangle(i*RECTANGLE_SIZE,j*RECTANGLE_SIZE,RECTANGLE_SIZE,RECTANGLE_SIZE);
                rectangle.setFill(Color.GREEN);
                rectangle.setStroke(Color.BLACK);
                //rectangle.addEventFilter(MouseEvent.ANY,e->System.out.println(e));
                rectangle.setOnMouseClicked(MouseEvent -> pressed(MouseEvent,rectangle));
                rectangle.setOnMouseDragged(MouseEvent -> dragged(MouseEvent));
                pane.getChildren().add(rectangle);
                board[i][j]=rectangle;
            }
        }
    }
    private void pressed(MouseEvent e , Rectangle r){
        r.setFill(Color.GREEN);
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
    }

}
