package org.example.backedn;

import java.util.Random;

public class Game {


    private final int BOARD_SIZE =10;
    private final int NUMBER_OF_SHIPS = 8;
    private final int MAX_SHIP_SIZE = 5;
    private final double CELL_SIZE = 40;

    public Board getBoard() {
        return board;
    }

    private Board board;
    public Game(){
    }
    public void runGame(){
        board = new Board();
        Random random = new Random();
        for(int i=0;i<NUMBER_OF_SHIPS;i++){
            try {
                Ship ship = new Ship(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE), random.nextInt(MAX_SHIP_SIZE));
                if(random.nextBoolean()){
                    ship.setVertical();
                }
                else{
                    ship.setHorizontal();
                }
                board.addShip(ship);
            }
            catch (InvalidPositionException exception){
                i--;
            }
        }

    }
    public int getBoardSize() {
        return BOARD_SIZE;
    }
    public double getCellSize() {
        return CELL_SIZE;
    }
    public int getNumberOfShips() {
        return NUMBER_OF_SHIPS;
    }
    public int getMaxShipSize() {
        return MAX_SHIP_SIZE;
    }
}
