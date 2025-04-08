module com.saulms.cavernwarrior {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.saulms.cavernwarrior to javafx.fxml;
    opens com.saulms.cavernwarrior.controllers to javafx.fxml;
    opens com.saulms.cavernwarrior.entities to javafx.fxml;
    exports com.saulms.cavernwarrior;
    exports com.saulms.cavernwarrior.controllers;
    exports com.saulms.cavernwarrior.entities;
}