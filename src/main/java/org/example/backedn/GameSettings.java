package org.example.backedn;

public class GameSettings {
    static private final int boardSize =10;
    static private final int NumberOfShips = 8;
    static private final int MaxShipSize = 5;
    static private final double CellSize = 40;

    static public int getBoardSize() {
        return boardSize;
    }

    static public int getNumberOfShips() {
        return NumberOfShips;
    }

    static public int getMaxShipSize() {
        return MaxShipSize;
    }

    static public double getCellSize() {
        return CellSize;
    }
}
