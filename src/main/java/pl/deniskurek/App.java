package pl.deniskurek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.deniskurek.view.ShipMouseEventSetter;

import java.io.IOException;

/**
 * Aplikacja Battleships
 * @author Denis Kurek
 */
public class App extends Application {
    @Override
    /**
     * metoda startowa aplikacji
     */
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(keyEvent -> ShipMouseEventSetter.rotate(keyEvent));
    }

    /**
     * metoda ładująca rodzica z pliku fxml
     * @param fxml string będący nazwą pliku (bez rozszerzenia)
     * @return Rodzica pobranego z pliku fxml
     * @throws IOException  W przypadku nieudanego pobranie FxmlLoader
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * funkcja main
     * @param args argumenty konsoli
     */
    public static void main(String[] args) {
        launch();
    }

}