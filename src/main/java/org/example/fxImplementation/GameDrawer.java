package org.example.fxImplementation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.*;

public class GameDrawer {
    static private Game game = null;
    static private Pane playerPane;
    static private Pane enemyPane = null;
    static Rectangle[][] playerRectangles,enemyRectangles;
    static GameSettings gameSettings=new GameSettings();
    static int BOARD_SIZE = gameSettings.getBoardSize();
    static double CELL_SIZE = gameSettings.getCellSize();

    public GameDrawer(Game game,Pane pane){
        this.game=game;
        this.playerPane=pane;
        this.playerPane.setOnScroll(MouseEvent -> ShipMouseEventSetter.rotate(MouseEvent));
        Board board = game.getPlayerBoard();
        playerRectangles = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
                playerRectangles[i][j]=rectangle;
            }
        }

    }

    public static Rectangle getPlayerRectangle(int i, int j) {
        return playerRectangles[i][j];
    }

    public static Rectangle getEnemyRectangle(int i,int j) {
        return enemyRectangles[i][j];
    }

    public GameDrawer(Game game, Pane playerPane, Pane enemyPane){
        this.game=game;
        this.playerPane=playerPane;
        this.enemyPane = enemyPane;
        Board playerBoard = game.getPlayerBoard();
        Board enemyBoard  = game.getEnemyBoard();
        playerRectangles = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        enemyRectangles = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
                playerRectangles[i][j]=rectangle;
                rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
                enemyRectangles[i][j]=rectangle;
            }
        }

    }

    static public void DrawPlayerBoard(){
        playerPane.getChildren().clear();
        Board board = game.getPlayerBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = playerRectangles[i][j];
                rectangle.setStroke(Color.BLACK);
                switch(board.getCell(i,j).getState()) {
                    case SEA:
                        if(board.getCell(i,j).clicked()){
                            rectangle.setFill(Color.DARKBLUE);
                        }
                        else{
                            rectangle.setFill(Color.LIGHTBLUE);
                        }
                        break;
                    case SHIP:
                        if(board.getCell(i,j).clicked()){
                            rectangle.setFill(Color.RED);
                        }
                        else {
                            rectangle.setFill(Color.GREEN);
                        }
                        break;
                    case NEAR_SHIP:
                        if(board.getCell(i,j).clicked()){
                            rectangle.setFill(Color.DARKBLUE);
                        }
                        else{
                            rectangle.setFill(Color.LIGHTGREEN);
                        }
                        break;
                }
                playerPane.getChildren().add(rectangle);
            }
        }
    }
    static public void DrawEnemyBoard(){
        enemyPane.getChildren().clear();
        Board board = game.getEnemyBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = enemyRectangles[i][j];
                rectangle.setStroke(Color.BLACK);
                switch(board.getCell(i,j).getState()) {
                    case SHIP:
                        if(board.getCell(i,j).clicked()){
                            rectangle.setFill(Color.GREEN);
                        }
                        else{
                            rectangle.setFill(Color.LIGHTBLUE);
                        }
                        break;
                    case NEAR_SHIP:
                    case SEA:
                        if(board.getCell(i,j).clicked()){
                            rectangle.setFill(Color.DARKBLUE);
                        }
                        else{
                            rectangle.setFill(Color.LIGHTBLUE);
                        }
                        break;
                }
                enemyPane.getChildren().add(rectangle);
            }
        }
    }
    static public void DrawShips(){

        Board board = game.getPlayerBoard();
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
                        playerPane.getChildren().add(rectangle);
                    }
                }
            }
        }
    }

}
