package org.example.backedn;

import org.example.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    GameSettings gameSettings = new GameSettings();
    public Board getPlayerBoard() {
        return playerBoard;
    }
    public Board getEnemyBoard() {
        return enemyBoard;
    }

    private Board playerBoard;
    private Board enemyBoard;
    public Game(){
    }
    public void runGame(){
        Random random = new Random();
        int seed = random.nextInt();
        playerBoard = new Board();
        enemyBoard = new Board();
        for(int i=0;i< gameSettings.getNumberOfShips();i++) {
            try {
                Ship ship = new Ship(random.nextInt(gameSettings.getBoardSize()),
                        random.nextInt(gameSettings.getBoardSize()),
                        1 + random.nextInt(gameSettings.getMaxShipSize() - 1));
                if (random.nextBoolean()) {
                    ship.setVertical();
                } else {
                    ship.setHorizontal();
                }
                playerBoard.addShip(ship);
            } catch (InvalidPositionException exception) {
                i--;
            }
        }
        for(int i=0;i< gameSettings.getNumberOfShips();i++) {
            try {
                Ship ship = new Ship(random.nextInt(gameSettings.getBoardSize()),
                        random.nextInt(gameSettings.getBoardSize()),
                        playerBoard.getShips().get(i).size);
                if (random.nextBoolean()) {
                    ship.setVertical();
                } else {
                    ship.setHorizontal();
                }
                enemyBoard.addShip(ship);
            } catch (InvalidPositionException exception) {
                i--;
            }
        }
    }
}
