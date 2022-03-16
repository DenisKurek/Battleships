package org.example.fxImplementation;

import javafx.scene.shape.Rectangle;
import org.example.backedn.Cell;

public class FxCell extends Cell {
    private Rectangle rectangle;
    public FxCell(double x, double y, double size) {
        super(x, y, size);
        rectangle = new Rectangle(x,y,size,size);
    }
    public Rectangle getRectangle(){
        return rectangle;
    }
}
