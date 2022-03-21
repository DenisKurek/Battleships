package org.example.backedn;
/**
 * klasa prezentujaca ustawienie gry(rozmiar planszy, ilość statków, itd...)
 */
public class GameSettings {
    /**
     *ustawienie rozmiaru plansz(10x10)
     */
    static private final int boardSize =10;
    /**
     *ustawienie ilości statków u gracz
     */
    static private final int NumberOfShips = 8;
    /**
     *ustawienie maksymalnej długości statku
     */
    static private final int MaxShipSize = 5;
    /**
     *ustawienie rozmiaru komurki
     */
    static private final double CellSize = 40;
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
    /**
     *pobranie rozmiaru komurki
     * @return rozmiar komórki
     */
    static public double getCellSize() {
        return CellSize;
    }
}
