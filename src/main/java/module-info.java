module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.deniskurek to javafx.fxml;
    exports pl.deniskurek;
    exports pl.deniskurek.backend;
    opens pl.deniskurek.backend to javafx.fxml;
    exports pl.deniskurek.exceptions;
    opens pl.deniskurek.exceptions to javafx.fxml;
}
