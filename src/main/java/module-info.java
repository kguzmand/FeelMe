module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens GUI to javafx.fxml;
    exports GUI;
    exports logic;
    opens logic to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}