module com.example.rail_cookery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.rail_cookery to javafx.fxml;
    exports com.example.rail_cookery;
}