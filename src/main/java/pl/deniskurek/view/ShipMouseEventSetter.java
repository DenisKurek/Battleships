package pl.deniskurek.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import pl.deniskurek.backend.Board;
import pl.deniskurek.backend.GameSettings;

/**
 * klasa konfigurująca przesuwanie obiektów
 */
public class ShipMouseEventSetter {
    /**
     * początkowa współrzędna x przeciąganego obiektu
     */
    private static int oldX;
    /**
     * początkowa współrzędna y przeciąganego obiektu
     */
    private static int oldY;
    /**
     * informacja o tym, czy obiekt został obrócony
     */
    private static Boolean positionChanged = false;
    /**
     * referencja do aktualnie przeciąganego obiektu
     */
    private static Rectangle draggedRectangle=null;

    /**
     * metoda zmieniająca pozycję przesuwanego obiektu (w sęnsie wartości logicznej atrybutu "positionChanged")
     */
    public static void changePosition(){
        if (!positionChanged){
            positionChanged = true;
        }
        else {
            positionChanged = false;
        }
    }

    /**
     * metoda inicjalizująca eventy połączone z obiektem
     * @param fxShip    obiekt to którego zostają dopięte metody
     * @param board     plansza której częścią jest obiekt fxShip
     * @param gameDrawer    referencja do klasy rysującej planszę
     */
    public static void SetMouseEvents(FxShip fxShip, Board board,GameDrawer gameDrawer){
        Rectangle rectangle = fxShip.getRectangle();
        rectangle.setOnMousePressed(mouseEvent -> pressed(fxShip));
        // ustawienie przeciągania
        rectangle.setOnMouseDragged(mouseEvent -> dragged(mouseEvent,fxShip));
        // ustawienie znajdowania najbliższej legalnej pozycji
        rectangle.setOnMouseReleased(mouseEvent -> released(mouseEvent,fxShip,board,gameDrawer));
    }

    /**
     * metoda wywoływana w przypadku naciśnięcia obiektu, zachowująca jego startowe współrzędne
     * @param fxship    klasa zawierająca naciśnięty obiekt
     */
    private static void pressed( FxShip fxship) {
        oldX =fxship.getShip().getX();
        oldY =fxship.getShip().getY();
        draggedRectangle = fxship.getRectangle();
    }

    /**
     * metoda wywoływana przy przeciąganiu obiektu aktualizująca jego pozycję na ekranie
     * @param mouseEvent informacje o pozycji myszki
     * @param fxShip    klasa zawierająca przeciągany obiekt
     */
    private static void dragged(MouseEvent mouseEvent, FxShip fxShip) {
        fxShip.getRectangle().setX(mouseEvent.getX());
        fxShip.getRectangle().setY(mouseEvent.getY());
    }

    /**
     * metoda wywoływana przy opuszczeniu przycisku (szuka najbliższej legalnej pozycji do umieszczenia)
     * @param mouseEvent pozycja myszy w czasie upuszczania statku
     * @param fxShip klasa zawierająca opuszczany obiekt
     * @param board plansza, na którą został upuszczony statek
     * @param gameDrawer   klasa rysująca planszę, po aktualizacji jej stanu
     */
    private static void released(MouseEvent mouseEvent, FxShip fxShip,Board board,GameDrawer gameDrawer) {
        draggedRectangle=null;
        int newX =(int)(mouseEvent.getX()/ GameSettings.cellSize);
        int newY =(int)(mouseEvent.getY()/GameSettings.cellSize);
        // sprawdzenie, czy pozycja myszy jest legalna
        if(newX<0){newX=0;}
        if(newY<0){newY=0;}
        if(newX>= GameSettings.boardSize){newX= GameSettings.boardSize-1;}
        if(newY>= GameSettings.boardSize){newY= GameSettings.boardSize-1;}


        if (board.moveShip(newX,newY,fxShip.getShip(), positionChanged)){
            //System.out.println(newX+" "+newY);
            fxShip.getRectangle().setX(newX * GameSettings.cellSize);
            fxShip.getRectangle().setY(newY * GameSettings.cellSize);
        }
        else{
            //System.out.println(newX+" "+newY+"old = "+fxship.getShip().getX()+" "+fxship.getShip().getY());
            fxShip.getRectangle().setX(oldX * GameSettings.cellSize);
            fxShip.getRectangle().setY(oldY * GameSettings.cellSize);
        }
        positionChanged =false;
        gameDrawer.DrawPlayerBoard();
        gameDrawer.DrawShips();
    }

    /**
     * metoda wywoływana w trakcie kliknięcia przycisku
     * @param event kliknięty przycisk
     */
    public static void rotate(KeyEvent event) {
        if(event.getCode()==KeyCode.SPACE){
            rotate();
        }
    }

    /**
     * metoda obradzająca przeciągany prostokąt
     */
    private static void rotate() {
        Rectangle rectangle=draggedRectangle;
        if (rectangle!=null){
            double width, height;
            width = rectangle.getWidth();
            height = rectangle.getHeight();
            rectangle.setWidth(height);
            rectangle.setHeight(width);
            ShipMouseEventSetter.changePosition();
        }
    }
}
