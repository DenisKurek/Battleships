package org.example.backedn;
/**
 * klasa przechowująca ustawienia gry
 */
public class GameSettings {
    /**
     * rozmiar planszy
     */
    static public final int boardSize =10;
    /**
     * minimalna długość statku
     */
    static public final int MinShipSize = 2;
    /**
     * maksymalna długość statku
     */
    static public final int MaxShipSize = 5;
    /**
     * ilość generowanych statków
     */
    static public final int NumberOfShips = 8;
    /**
     * rozmiar pola na planszy
     */
    static public final double CellSize = 40;
    /**
     *pobranie rozmiaru plansz
     * @return rozmiar ztatku
     */
    static public int getBoardSize() {
        return boardSize;
    }
    /**
     *pobranie ilości statków u gracz
     * @return liczbę statków
     */
    static public int getNumberOfShips() {
        return NumberOfShips;
    }
    /**
     *pobranie maksymalnej długości statku
     * @return maksymalny rozmiar statku
     */
    static public int getMaxShipSize() {
        return MaxShipSize;
    }
    static public int getMinShipSize() {
        return MinShipSize;
    }

    /**
     *pobranie rozmiaru komurki
     * @return rozmiar komórki
     */
    static public double getCellSize() {
        return CellSize;
    }
}
