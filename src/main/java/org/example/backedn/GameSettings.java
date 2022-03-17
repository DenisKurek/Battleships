package org.example.backedn;

public class GameSettings {
    private final int boardSize =10;
    private final int NumberOfShips = 8;
    private final int MaxShipSize = 5;
    private final double CellSize = 40;

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumberOfShips() {
        return NumberOfShips;
    }

    public int getMaxShipSize() {
        return MaxShipSize;
    }

    public double getCellSize() {
        return CellSize;
    }
}
