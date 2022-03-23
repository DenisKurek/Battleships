package org.example.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backend.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * klasa rysująca grę
 */
public class GameDrawer {
    /**
     * gra rysowana przez klasę
     */
    private Game game = null;
    /**
     * obszar rysowania planszy gracza
     */
    private Pane playerPane;
    /**
     * obszar rysowania planszy przeciwnika
     */
    private Pane enemyPane = null;
    /**
     * tablica dwuwymiarowa reprezentująca planszę gracza
     */
    Rectangle[][] playerRectangles;
    /**
     * tablica dwuwymiarowa reprezentująca planszę przeciwnika
     */
    Rectangle[][] enemyRectangles;
    /**
     * stała przechowująca rozmiar planszy
     */
    final int BOARD_SIZE = GameSettings.boardSize;
    /**
     * stałą przechowująca rozmiar poszczególnych pól
     */
    final double CELL_SIZE = GameSettings.cellSize;

    /**
     * konstruktor klasy game Drawer
     * @param game gra do narysowania
     * @param pane obszar rysowania planszy gracza
     */
    public GameDrawer(Game game,Pane pane){
        this.game = game;
        playerPane=pane;
        playerRectangles = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE * i, CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
                playerRectangles[i][j]=rectangle;
            }
        }
    }

    /**
     *  konstruktor klasy game Drawer
     * @param game gra do narysowania
     * @param playerPane obszar rysowania planszy gracza
     * @param enemyPane obszar rysowania planszy przeciwnika
     */
    public GameDrawer(Game game, Pane playerPane, Pane enemyPane){
        this.game =game;
        this.playerPane =playerPane;
        this.enemyPane = enemyPane;
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

    /**
     * klasa zwracająca reprezentację pola gracza o danych współrzędnych
     * @param x współrzędna x
     * @param y współrzędna y
     * @return prostokąt reprezentujący pole gracza o współrzędnych (x,y)
     */
    public Rectangle getPlayerRectangle(int x, int y) {
        return playerRectangles[x][y];
    }

    /**
     * klasa zwracająca reprezentację pola przeciwnika o danych współrzędnych
     * @param x współrzędna x
     * @param y współrzędna y
     * @return prostokąt reprezentujący pole przeciwnika o współrzędnych (x,y)
     */
    public Rectangle getEnemyRectangle(int x,int y) {
        return enemyRectangles[x][y];
    }

    /**
     * metoda rysująca planszę gracza
     */
    public void DrawPlayerBoard(){
        playerPane.getChildren().clear();
        Board board = game.getPlayerBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                createPlayerRectangleAtIndex(board, i, j);
            }
        }
    }
    /**
     * metoda rysująca planszę przeciwnika
     */
    public void DrawEnemyBoard(){
        enemyPane.getChildren().clear();
        Board board = game.getEnemyBoard();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                createEnemyRectangleAtIndex(board, i, j);
            }
        }
    }

    public void drawRemainingShips(VBox box , Board board){
        box.getChildren().clear();
        ArrayList<Ship> ships = (ArrayList<Ship>) board.getShips();
        Collections.sort(ships);
        for (Ship ship:ships) {
            Rectangle rectangle = new Rectangle(GameSettings.cellSize*ship.getSize(),GameSettings.cellSize,
                    Color.GREEN);
            box.getChildren().add(rectangle);
        }
    }

    /**
     * metoda rysująca statki na planszy gracza
     */
    public void DrawShips(){
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
                        ShipMouseEventSetter.SetMouseEvents(fxShip,board,this);
                        playerPane.getChildren().add(rectangle);
                    }
                }
            }
        }
    }

    /**
     * metoda kolorująca pola na planszy gracza
     * @param board plansza gracza
     * @param x współrzędna x pola
     * @param y współrzędna y pola
     */
    private void createPlayerRectangleAtIndex(Board board, int x, int y) {
        Rectangle rectangle = playerRectangles[x][y];
        rectangle.setStroke(Color.BLACK);
        switch(board.getCell(x, y).getState()) {
            case SEA:
                if(board.getCell(x, y).clicked()){
                    rectangle.setFill(Color.DARKBLUE);
                }
                else{
                    rectangle.setFill(Color.LIGHTBLUE);
                }
                break;
            case SHIP:
                if(board.getCell(x, y).clicked()){
                    rectangle.setFill(Color.RED);
                }
                else {
                    rectangle.setFill(Color.GREEN);
                }
                break;
            case NEAR_SHIP:
                if(board.getCell(x, y).clicked()){
                    rectangle.setFill(Color.DARKBLUE);
                }
                else{
                    rectangle.setFill(Color.LIGHTGREEN);
                }
                break;
        }
        playerPane.getChildren().add(rectangle);
    }

    /**
     * metoda tworząca prostokąt reprezentujący pole o podanych współrzędnych
     * @param board
     * @param x
     * @param y
     */
    private void createEnemyRectangleAtIndex(Board board, int x, int y) {
        Rectangle rectangle = enemyRectangles[x][y];
        rectangle.setStroke(Color.BLACK);
        switch(board.getCell(x, y).getState()) {
            case SHIP:
                if(board.getCell(x, y).clicked()){
                    rectangle.setFill(Color.GREEN);
                }
                else{
                    rectangle.setFill(Color.LIGHTBLUE);
                }
                break;
            case NEAR_SHIP:
            case SEA:
                if(board.getCell(x, y).clicked()){
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
