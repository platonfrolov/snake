module example {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens example.view to javafx.fxml;
    exports example.view;
//    exports example.controller;
}