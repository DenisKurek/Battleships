package org.example.fxImplementation;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.*;

public class GameDrawer {
    static private Game game = null;
    static private Pane pane;
    static Rectangle[][] rectangles;
    static GameSettings gameSettings=new GameSettings();
    static int BOARD_SIZE = gameSettings.getBoardSize();
    static double CELL_SIZE = gameSettings.getCellSize();

    public GameDrawer(Game game,Pane pane){
        this.game=game;
        this.pane=pane;
        this.pane.setOnScroll(MouseEvent -> ShipMouseEventSetter.rotate(MouseEvent));
        Board board = game.getBoard();
        rectangles = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
                rectangles[i][j]=rectangle;
            }
        }

    }

    static public void DrawBoard(){
        pane.getChildren().clear();
        Board board = game.getBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = rectangles[i][j];
                rectangle.setStroke(Color.BLACK);
                if(board.getCell(i,j).getState() == Cell.State.SEA){
                    rectangle.setFill(Color.LIGHTBLUE);
                }
                else if(board.getCell(i,j).getState() == Cell.State.SHIP){
                    rectangle.setFill(Color.VIOLET);
                }
                else{
                    rectangle.setFill(Color.LIGHTGREEN);
                }
                pane.getChildren().add(rectangle);
            }
        }
    }
    static public void DrawShips(){

        Board board = game.getBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Cell cell = board.getCell(i,j);
                if(cell.getState() == Cell.State.SHIP){
                    Ship ship = cell.get_ship();
                    if(ship.getX()==i && ship.getY()==j){
                        Rectangle rectangle;
                        if (ship.isVertical()) {
                            rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j,
                                    CELL_SIZE, CELL_SIZE * ship.getSize());
                        }
                        else{
                            rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j,
                                    CELL_SIZE * ship.getSize(), CELL_SIZE);
                        }
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setFill(Color.GREEN);
                        FxShip fxShip = new FxShip(ship,rectangle);
                        ShipMouseEventSetter.SetMouseEvents(fxShip,board);
                        pane.getChildren().add(rectangle);
                    }
                }
            }
        }
    }

}
