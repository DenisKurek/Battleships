package org.example.backedn;

import org.example.exceptions.InvalidPositionException;

import java.util.Random;

public class Game {
    GameSettings gameSettings = new GameSettings();
    public Board getBoard() {
        return board;
    }

    private Board board;
    public Game(){
    }
    public void runGame(){
        board = new Board();
        Random random = new Random();
        for(int i=0;i< gameSettings.getNumberOfShips();i++){
            try {
                Ship ship = new Ship(random.nextInt(gameSettings.getBoardSize()),
                        random.nextInt(gameSettings.getBoardSize()),
                        1+random.nextInt(gameSettings.getMaxShipSize()-1));
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
}
