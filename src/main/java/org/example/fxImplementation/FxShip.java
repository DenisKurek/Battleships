package org.example.fxImplementation;

import javafx.scene.shape.Rectangle;
import org.example.backedn.Cell;
import org.example.backedn.Ship;

import java.util.List;

public class FxShip extends Ship {

    Rectangle rectangle;
    public FxShip(double x,double y,double width, double length ) {
        super(x,y,width,length);
        rectangle = new Rectangle(xPosition,yPosition,width,length);

    }
    public Rectangle getRectangle(){
        return rectangle;
    }
}
