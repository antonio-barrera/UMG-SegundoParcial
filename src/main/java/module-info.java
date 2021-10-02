module org.openjfx.comisiones {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens org.openjfx.comisiones to javafx.fxml;
    opens modelo to javafx.fxml;
    exports org.openjfx.comisiones;
    exports modelo;
}
