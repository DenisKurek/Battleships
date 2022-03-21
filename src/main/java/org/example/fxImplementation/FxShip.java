package org.example.fxImplementation;

import javafx.scene.shape.Rectangle;
import org.example.backedn.Ship;

public class FxShip {
    Ship ship;
    Rectangle rectangle;
    public FxShip(Ship ship, Rectangle rectangle){
        this.ship = ship;
        this.rectangle = rectangle;
    }
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
