package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.example.backend.*;
import org.example.view.GameDrawer;

/**
 * klasa obsługująca rozgrywkę przeciwko sztucznej inteligencji
 */
public class SecondaryController {
    /**
     * obszar rysowania planszy gracza
     */
    @FXML
    Pane playerPane;
    /**
     * obszar rysowania planszy przeciwnika
     */
    @FXML
    Pane enemyPane;
    /**
     * obiekt przechowujący obszary służące do rysowania gry
     */
    @FXML
    VBox box;
    /**
     * kontener przechowujący pozostałe statki przeciwnika
     */
    @FXML
    VBox remainingEnemyShips;
    /**
     * kontener przechowujący pozostałe statki gracza
     */
    @FXML
    VBox remainingPlayerShips;
    /**
     * przeciwnik gracza
     */
    EnemyPlayer enemyPlayer = new EnemyPlayer();
    /**
     * egzemplarz klasy rysującej grę
     */
    GameDrawer gameDrawer;
    /**
     * egzemplarz klasy reprezentującej grę
     */
    Game game;

    /**
     * metoda rozpoczynająca grę
     */
    @FXML
    public void beginGame() {
        this.gameDrawer = new GameDrawer(game,playerPane,enemyPane);
        refreshGameState();
        for(int i=0;i<GameSettings.boardSize;i++){
            for(int j=0;j<GameSettings.boardSize;j++){
                // kopie zmiennych wysyłane do funkcji
                final int fJ=j,fI=i;
                gameDrawer.getEnemyRectangle(i,j).setOnMouseClicked(mouseEvent->onclick(mouseEvent,fI,fJ));
            }
        }
    }

    /**
     * metoda obsługująca strzelanie do pól na planszy
     * @param mouseEvent miejsce kliknięcia myszy
     * @param x współrzędna x klikniętego pola
     * @param y współrzędna y klikniętego pola
     */
    private void onclick(MouseEvent mouseEvent, int x, int y) {
        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        rectangle.setOnMouseClicked(null);
        Board playerBoard=game.getPlayerBoard();
        Board enemyBoard=game.getEnemyBoard();
        boolean failedShoot = !game.getEnemyBoard().shoot(x,y);
        while(failedShoot){
            if(!enemyPlayer.makeMove(playerBoard)){
                break;
            }
            refreshGameState();
        }
        for(int ii=0;ii<GameSettings.boardSize;ii++) {
            for (int jj = 0; jj < GameSettings.boardSize; jj++) {
                if(game.getEnemyBoard().getCell(ii,jj).clicked()){
                    gameDrawer.getEnemyRectangle(ii,jj).setOnMouseClicked(mouseEvent1 -> {});
                }
            }
        }
        refreshGameState();
        if(playerBoard.getShips().isEmpty()){
            endGame("Przegrałeś");
        }
        if(enemyBoard.getShips().isEmpty()){
            endGame("Wygrałeś");
        }
    }

    /**
     * metoda przypinająca referencję do egzemplarza klasy Game
     * @param game  podpinany egzemplarz klasy Game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * metoda wyświetlająca informacje o wyniku rozgrywki
     * @param massage   wiadomość wyświetlana pod koniec gry
     */
    public void endGame(String massage){
        box.getChildren().clear();
        box.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setText(massage);
        label.setFont(new Font("Arial", 50));
        if(massage == "Wygrałeś"){
            label.setTextFill(Color.GREEN);
        }
        else{
            label.setTextFill(Color.RED);
        }
        box.getChildren().add(label);
    }

    /**
     * metoda refreshable scenę gry
     */
    private void refreshGameState() {
        gameDrawer.DrawPlayerBoard();
        gameDrawer.drawRemainingShips(remainingPlayerShips,game.getPlayerBoard());
        gameDrawer.DrawEnemyBoard();
        gameDrawer.drawRemainingShips(remainingEnemyShips,game.getEnemyBoard());
    }
}