package org.example.backedn;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class DraggableMaker {
    private double mouseStartingX;
    private double mouseStartingy;
    public void makeDraggable(Rectangle node){
        node.setOnMousePressed(mouseEvent -> {
            mouseStartingX = mouseEvent.getX();
            mouseStartingy = mouseEvent.getY();
        });
        node.setOnMouseDragged(mouseEvent -> {
            node.setX(mouseEvent.getX());
            node.setY(mouseEvent.getY());
        });
    }
}
