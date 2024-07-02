module org.example.nightanvil {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.chess to javafx.fxml;
    exports org.example.chess;
}