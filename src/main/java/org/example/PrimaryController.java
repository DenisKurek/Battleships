package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.Cell;
import org.example.backedn.DraggableMaker;
import org.example.fxImplementation.FxBoard;
import org.example.fxImplementation.FxCell;
import org.example.fxImplementation.FxShip;

public class PrimaryController {
    private final int BOARD_SIZE = 10;
    private final int RECTANGLE_SIZE = 40;
    @FXML
    Pane pane;
    @FXML
    Pane shipPane;
    @FXML
    public void initialize(){
        FxBoard board = new FxBoard(BOARD_SIZE,RECTANGLE_SIZE);
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                FxCell cell = board.getCell(i,j);
                Rectangle rectangle = cell.getRectangle();

                rectangle.setFill(Color.GREEN);
                rectangle.setStroke(Color.BLACK);
                //rectangle.addEventFilter(MouseEvent.ANY,e->System.out.println(e));
                /*rectangle.setOnMouseClicked(MouseEvent -> pressed(MouseEvent,cell));
                rectangle.setOnMouseDragged(MouseEvent -> dragged(MouseEvent));*/
                pane.getChildren().add(rectangle);
            }
        }
        for(int i=1;i<=BOARD_SIZE/2;i++){
            FxShip ship = new FxShip(i*RECTANGLE_SIZE,i*(RECTANGLE_SIZE*2)-RECTANGLE_SIZE,RECTANGLE_SIZE*i,RECTANGLE_SIZE);
            Rectangle rectangle =ship.getRectangle();
            DraggableMaker draggableMaker = new DraggableMaker();
            draggableMaker.makeDraggable(rectangle);
            rectangle.setOnMouseReleased(MouseEvent->{
                int x =(int)(rectangle.getX()/RECTANGLE_SIZE);
                int y =(int)(rectangle.getY()/RECTANGLE_SIZE);
                rectangle.setX(x*RECTANGLE_SIZE);
                rectangle.setY(y*RECTANGLE_SIZE);
            });
            pane.getChildren().add(rectangle);
        }


    }


    private void pressed(MouseEvent e , FxCell cell){
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
    }

}
