package org.example.backend;
/**
 * interfejs przechowujący ustawienia gry
 */
public interface GameSettings {
    /**
     * rozmiar planszy
     */
    int boardSize =10;
    /**
     * minimalna długość statku
     */
    int minShipSize = 2;
    /**
     * maksymalna długość statku
     */
    int maxShipSize = 4;
    /**
     * ilość generowanych statków
     */
    int numberOfShips = 9;
    /**
     * rozmiar pola na planszy
     */
    double cellSize = 40;
}
