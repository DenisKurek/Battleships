package org.example.fxImplementation;

import javafx.scene.shape.Rectangle;
import org.example.backedn.Cell;
import org.example.backedn.Ship;

import java.util.List;

public class FxShip extends Ship {

    Rectangle rectangle;

    public FxShip(int x, int y, int size) {
        super(x, y, size);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
