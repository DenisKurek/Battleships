package org.example.backend;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * interfejs przechowujący ustawienia gry
 */
public interface GameSettings {
    /**
     * rozmiar planszy
     */
    int boardSize =10;
    /**
     * lista rozstawianych statków
     */
    ArrayList<Integer> sizesOfShips = new ArrayList<Integer>(Arrays.asList(4,3,3,2,2,2,1,1,1,1));
    /**
     * ilość generowanych statków
     */
    int numberOfShips = sizesOfShips.size();
    /**
     * rozmiar pola na planszy
     */
    double cellSize = 40;
}
