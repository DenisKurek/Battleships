module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.backend;
    opens org.example.backend to javafx.fxml;
    exports org.example.exceptions;
    opens org.example.exceptions to javafx.fxml;
}
