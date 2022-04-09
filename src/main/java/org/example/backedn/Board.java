package org.example.backedn;

import org.example.exceptions.InvalidPositionException;
import org.example.exceptions.ShipNotExistexception;

import java.util.ArrayList;
import java.util.List;
/**
 * klasa prezentujaca Board
 */
public class Board {
    GameSettings gameSettings = new GameSettings();
    /**
     * tablica dwuwymiarowa prezentująca rozmiar planszy
     */
    private Cell[][] cells;
    /**
     * lista z klasy ship
     * @return info na temat statku
     */

    public List<Ship> getShips() {
        return ships;
    }
    /**
     * lista z klasy ship
     */
    private List<Ship> ships;
    /**
     *
     */
    public Board(){
        initialize();
    }
    /**
     *
     */
    private void initialize(){
        ships = new ArrayList<Ship>();
        initializeCells();
    }
    /**
     * funkcja prezentująca dodanie statku
     */
    public void addShip(Ship ship) {
        ships.add(ship);
        if(checkIfShipPositionValid(ship,ship.isVertical())){
            int x = ship.getX();
            int y = ship.getY();
            if(ship.isVertical()){
                for (int i=0;i<ship.getSize();i++,y++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbState( x,y, Cell.State.NEAR_SHIP);
                }
            }
            else{
                for (int i=0;i<ship.getSize();i++,x++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbState(x,y, Cell.State.NEAR_SHIP);
                }
            }
        }
        else{
            throw new InvalidPositionException();
        }
    }
    /**
     * funkcja prezentująca ruch statku
     */
    public void moveShip(int x,int y,Ship ship,boolean changePosition){
        removeShip(ship);
        int oldX = ship.getX();
        int oldY = ship.getY();
        ship.setPosition(x,y);
        ships.remove(ship);
        try{
            if(changePosition){
                if(ship.isVertical()){
                    ship.setHorizontal();
                }
                else{
                    ship.setVertical();
                }
            }
            addShip(ship);
        }catch (InvalidPositionException exception){
            ship.setPosition(oldX,oldY);
            if(changePosition){
                if(ship.isVertical()){
                    ship.setHorizontal();
                }
                else{
                    ship.setVertical();
                }
            }
            addShip(ship);
        }
    }
    /**
     * funkcja prezentująca ruch statku
     */
    private void removeShip(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        if(ship.isVertical()){
            for (int i=0;i<ship.getSize();i++,y++){
                cells[x][y].setShipReff(null);
                cells[x][y].setState(Cell.State.SEA);
                changeNeighbState( x,y, Cell.State.SEA);
            }
        }
        else{
            for (int i=0;i<ship.getSize();i++,x++){
                cells[x][y].setShipReff(ship);
                cells[x][y].setState(Cell.State.SEA);
                changeNeighbState(x,y, Cell.State.SEA);
            }
        }

    }
    /**
     *
     */
    private void changeNeighbState(int x , int y, Cell.State state) {
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(checkPosition(x+i,y+j)){
                    if(cells[x + i][y + j].getState() != Cell.State.SHIP && state == Cell.State.NEAR_SHIP) {
                        cells[x + i][y + j].setState(state);
                    }
                    else if (state == Cell.State.SEA){
                        cells[x + i][y + j].setState(state);
                    }
                }
            }
        }
    }
    /**
     * sprawdzanie czy pozycja statku nie koliduje i innymi
     */
    private boolean checkIfShipPositionValid(Ship ship,boolean isVertical) {
        int x=ship.getX();
        int y=ship.getY();
        if(isVertical){
            for (int i=0;i<ship.getSize();i++,y++){
                if(!checkPosition(x,y) || cells[x][y].getState()!= Cell.State.SEA){
                    return false;
                }
            }
        }
        else{
            for (int i=0;i<ship.getSize();i++,x++){
                if(!checkPosition(x,y) || cells[x][y].getState()!= Cell.State.SEA){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     *
     */
    private boolean checkPosition(int x, int y) {
        if(x<0 || x>= gameSettings.getBoardSize()){
            return false;
        }
        if(y<0 || y>= gameSettings.getBoardSize()){
            return false;
        }
        return true;
    }
    /**
     *
     */
    private void initializeCells() {
        this.cells = new Cell[gameSettings.getBoardSize()][gameSettings.getBoardSize()];
        for (int i = 0;i <gameSettings.getBoardSize();i++){
            for (int j = 0;j <gameSettings.getBoardSize();j++){
                this.cells[i][j] = new Cell(i*gameSettings.getCellSize(), j*gameSettings.getCellSize(),gameSettings.getCellSize());
                cells[i][j].setState(Cell.State.SEA);
            }
        }
    }
    public Cell getCell(int i,int j){
        return cells[i][j];
    }
}
