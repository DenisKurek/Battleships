package org.example.backend;

import org.example.exceptions.InvalidPositionException;
import java.util.Random;
/**
 * klasa reprezentująca grę
 */
public class Game {
    /**
     * plansza gracza
     */
    private Board playerBoard;
    /**
     * plansza przeciwnika
     */
    private Board enemyBoard;
    /**
     * metoda zwracająca planszę gracza
     * @return plansza gracza
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }
    /**
     * metoda zwracająca planszę przeciwnika
     * @return plansza przeciwnika
     */
    public Board getEnemyBoard() {
        return enemyBoard;
    }

    /**
     * metoda uruchamiająca grę
     */
    public void runGame(){
        playerBoard = new Board();
        enemyBoard = new Board();
        generatePlayerBoard();
        generateEnemyBoard();
    }

    /**
     * metoda generująca planszę przeciwnika
     */
    private void generateEnemyBoard() {
        Random random = new Random();
        for(int i=0;i< GameSettings.numberOfShips;i++) {
            try {
                Ship ship = new Ship(random.nextInt(GameSettings.boardSize),
                        random.nextInt(GameSettings.boardSize),
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

    /**
     * metoda generująca planszę gracza
     */
    private void generatePlayerBoard() {
        Random random = new Random();
        for(int i=0;i< GameSettings.numberOfShips;i++) {
            try {
                Ship ship = new Ship(random.nextInt(GameSettings.boardSize),
                        random.nextInt(GameSettings.boardSize),
                        GameSettings.minShipSize + random.nextInt(GameSettings.maxShipSize));
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
    }
}
