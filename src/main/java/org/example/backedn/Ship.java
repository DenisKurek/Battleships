package org.example.backedn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    private int size;
    private List<Cell> cells;
    private boolean isDestroyed = false;

    public Ship( List<Cell> cells) {
        this.size = cells.size();
        this.cells = cells;
        for (Cell cell:this.cells) {
            cell.setShipReff(this);
        }
    }


}
