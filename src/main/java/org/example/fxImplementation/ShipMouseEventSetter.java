package org.example.fxImplementation;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.example.backedn.Board;
import org.example.backedn.GameSettings;

public class ShipMouseEventSetter {
    static GameSettings gameSettings = new GameSettings();
    static int x,y;
    public static void SetMouseEvents(FxShip fxship, Board board){
        GameSettings gameSettings;
        Rectangle rectangle = fxship.getRectangle();
        rectangle.setOnMousePressed(mouseEvent -> pressed(mouseEvent,fxship));
        // ustawienie przeciągania
        rectangle.setOnMouseDragged(mouseEvent -> dragged(mouseEvent,fxship));
        // ustawienie znajdowania nablizszej legalnej pozycji
        rectangle.setOnMouseReleased(mouseEvent -> released(mouseEvent,fxship,board));
    }

    private static void pressed(Object mouseEvent, FxShip fxship) {
        x=fxship.getShip().getX();
        y=fxship.getShip().getY();
    }
    private static void dragged(MouseEvent mouseEvent, FxShip fxship) {
        fxship.getRectangle().setX(mouseEvent.getX());
        fxship.getRectangle().setY(mouseEvent.getY());
    }
    private static void released(MouseEvent mouseEvent, FxShip fxship,Board board) {
        int newX =(int)(mouseEvent.getX()/gameSettings.getCellSize());
        int newY =(int)(mouseEvent.getY()/gameSettings.getCellSize());
        System.out.println(newX+" "+newY);
        if(newX<0){newX=0;}
        if(newY<0){newY=0;}
        if(newX>= gameSettings.getBoardSize()){newX= gameSettings.getBoardSize()-1;}
        if(newY>= gameSettings.getBoardSize()){newY= gameSettings.getBoardSize()-1;}
        board.moveShip(newX,newY,fxship.getShip());
        if (fxship.getShip().getX()==newX && fxship.getShip().getY()==newY){
            //System.out.println(newX+" "+newY);
            fxship.getRectangle().setX(newX * gameSettings.getCellSize());
            fxship.getRectangle().setY(newY * gameSettings.getCellSize());
        }
        else{
            //System.out.println(newX+" "+newY+"old = "+fxship.getShip().getX()+" "+fxship.getShip().getY());
            fxship.getRectangle().setX(x * gameSettings.getCellSize());
            fxship.getRectangle().setY(y * gameSettings.getCellSize());
        }
        GameDrawer.DrawBoard();
        GameDrawer.DrawShips();
    }
}
