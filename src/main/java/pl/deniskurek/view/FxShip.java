package pl.deniskurek.view;

import javafx.scene.shape.Rectangle;
import pl.deniskurek.backend.Ship;

/**
 * klasa powiązująca reprezentację graficzną statku z jego reprezentacją logiczną
 */
public class FxShip {
    /**
     * obiekt klasy ship (reprezentacja logiczna)
     */
    Ship ship;
    /**
     * obiekt klasy rectangle (reprezentacja graficzna)
     */
    Rectangle rectangle;

    /**
     * konstruktor klasy FxShip
     * @param ship reprezentacja logiczna
     * @param rectangle reprezentacja graficzna
     */
    public FxShip(Ship ship, Rectangle rectangle){
        this.ship = ship;
        this.rectangle = rectangle;
    }

    /**
     * metoda zwracająca referencję do statku
     * @return obiekt klasy Ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * metoda zwracająca referencję do graficznej reprezentacji statku
     * @return obiekt klasy rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
}
