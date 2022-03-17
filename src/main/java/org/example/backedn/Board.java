package org.example.backedn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    protected int BOARD_SIZE =10;
    private final int NUMBER_OF_SHIPS = 8;
    private final int MAX_SHIP_SIZE = 5;
    protected double CELL_SIZE = 40;

    public Cell getCell(int i,int j){
        return cells[i][j];
    }

    private Cell[][] cells;
    private List<Ship> ships;
    public Board(){
        initialize();
    }
    public Board(int boardSize , double cellSize){
        this.BOARD_SIZE = boardSize;
        this.CELL_SIZE = cellSize;
        initialize();
    }
    private void initialize(){
        ships = new ArrayList<Ship>();
        initializeCells();
    }
    public void addShip(Ship ship) {
        ships.add(ship);
        if(checkIfShipPositionValid(ship,ship.isVertical())){
            int x = ship.getX();
            int y = ship.getY();
            if(ship.isVertical()){
                for (int i=0;i<ship.getSize();i++,y++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbState( x,y);
                }
            }
            else{
                for (int i=0;i<ship.getSize();i++,x++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbState( x,y);
                }
            }
        }
        else{
            throw new InvalidPositionException();
        }
    }

    private void changeNeighbState(int x ,int y) {
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(checkPosition(x+i,y+j)){
                    if(cells[x + i][y + j].getState()== Cell.State.SEA) {
                        cells[x + i][y + j].setState(Cell.State.NEAR_SHIP);
                    }
                }
            }
        }
    }

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

    private boolean checkPosition(int x, int y) {
        if(x<0 || x>= BOARD_SIZE){
            return false;
        }
        if(y<0 || y>= BOARD_SIZE){
            return false;
        }
        return true;
    }

    private void initializeCells() {
        this.cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0;i <BOARD_SIZE;i++){
            for (int j = 0;j <BOARD_SIZE;j++){
                this.cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,CELL_SIZE);
            }
        }
    }
}
