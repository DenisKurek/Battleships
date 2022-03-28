package pl.deniskurek.backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * interfejs przechowujący ustawienia gry
 */
public interface GameSettings {
    /**
     * rozmiar planszy
     */
    int BOARD_SIZE =10;
    /**
     * lista rozstawianych statków
     */
    ArrayList<Integer> SIZES_0F_SHIPS = new ArrayList<Integer>(Arrays.asList(4,3,3,2,2,2,1,1,1,1));
    /**
     * ilość generowanych statków
     */
    int NUMBER_OF_SHIPS = SIZES_0F_SHIPS.size();
    /**
     * rozmiar pola na planszy
     */
    double CELL_SIZE = 40;
}
