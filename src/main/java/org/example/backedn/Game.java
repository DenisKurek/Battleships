package org.example.backedn;

import org.example.exceptions.InvalidPositionException;

import java.util.ArrayList;
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
        Random random = new Random();
        int seed = random.nextInt();
        playerBoard = new Board();
        enemyBoard = new Board();
        //generowanie statków i rozmieszczanie na planszy gracza
        for(int i=0;i< GameSettings.getNumberOfShips();i++) {
            try {
                Ship ship = new Ship(random.nextInt(GameSettings.getBoardSize()),
                        random.nextInt(GameSettings.getBoardSize()),
                        GameSettings.getMinShipSize() + random.nextInt(GameSettings.getMaxShipSize()));
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
        //generowanie statków i rozmieszczanie na planszy przeciwnika
        for(int i=0;i< GameSettings.getNumberOfShips();i++) {
            try {
                Ship ship = new Ship(random.nextInt(GameSettings.getBoardSize()),
                        random.nextInt(GameSettings.getBoardSize()),
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
