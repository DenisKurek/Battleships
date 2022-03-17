package org.example.fxImplementation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.backedn.Board;
import org.example.backedn.Game;

public class GameDrawer {
    private Game game = null;
    private Pane pane;
    public GameDrawer(Game game,Pane pane){
        this.game=game;
        this.pane=pane;
    }
    public void DrawBoard(){
        int BOARD_SIZE = game.getBoardSize();
        Board board = game.getBoard();
        double CELL_SIZE = game.getCellSize();
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE*i,CELL_SIZE*j,CELL_SIZE,CELL_SIZE);
                rectangle.setStroke(Color.BLACK);

                if(board.getCell(i, j).get_ship()!=null){
                    rectangle.setFill(Color.GREEN);
                }
                else{
                    rectangle.setFill(Color.GRAY);
                }
                pane.getChildren().add(rectangle);
            }
        }
    }
}
