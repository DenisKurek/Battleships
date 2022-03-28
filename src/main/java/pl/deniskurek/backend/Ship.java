package pl.deniskurek.backend;

import pl.deniskurek.exceptions.AlreadyDeadException;

/**
 * klasa reprezentująca statek
 */
public class Ship implements Comparable<Ship>{
    /**
     * współrzędna x statku
     */
    protected int x=0;
    /**
     * współrzędna y statku
     */
    protected int y=0;
    /**
     * punkty życia statku
     */
    private  int health;
    /**
     * rozmiar statku
     */
    protected int size;
    /**
     * informacja czy statek jest ustawiony pionowo
     */
    private Boolean vertical=false;
    /**
     * informacja czy statek jest zniszczony
     */
    protected boolean isDestroyed = false;

    /**
     * konstruktor tworzący statek
     * @param x współrzędna x
     * @param y współrzędna y
     * @param size rozmiar
     */
    public Ship(int x,int y,int size){
        this.x=x;
        this.y=y;
        this.size = size;
        this.health = size;
    }

    /**
     * ustawienie współrzędnej x
     * @param Position współrzędna x
     */
    public void setX(int Position) {
        this.x = Position;
    }

    /**
     * ustawienie współrzędnej y
     * @param Position współrzędna y
     */
    public void setY(int Position) {
        this.y = Position;
    }

    /**
     * metoda zwracająca współrzędną x
     * @return  współrzędna x
     */
    public int getX() {
        return x;
    }
    /**
     * metoda zwracająca współrzędną y
     * @return  współrzędna y
     */
    public int getY() {
        return y;
    }
    /**
     * metoda zwracająca rozmiar statku
     * @return  rozmiar statku
     */
    public int getSize(){return size;}
    /**
     * metoda zwracająca informację czy statek jest ustawiony pionowo
     * @return  TRUE, jeżeli jest FALSE, jeżeli nie jest
     */
    public Boolean isVertical() {return vertical;}

    /**
     * metoda ustawiająca współrzędne statku
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * metoda ustawiająca pozycję tatku jako pionowa
     */
    public void setVertical(){
        vertical = true;
    }
    /**
     * metoda ustawiająca pozycję tatku jako poziomom
     */
    public void setHorizontal(){
        vertical = false;
    }

    /**
     * metoda obsługująca strzelenie do statku
     * @return TRUE, jeżeli statek został zniszczony FALSE, jeżeli nie został
     */
    public Boolean shoot(){
        if(isDestroyed) {
            throw new AlreadyDeadException();
        }
        else{
            health--;
            if(health==0){
                isDestroyed=true;
            }
        }
        return isDestroyed;
    }

    /**
     * metoda porównująca dwa statki
     * @param ship statek porównywany z naszym obiektem
     */
    @Override
    public int compareTo(Ship ship) {
        return this.size-ship.size;
    }
}
