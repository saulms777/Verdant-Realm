module com.saulms.verdantrealm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;

    opens com.saulms.verdantrealm to javafx.fxml;
    opens com.saulms.verdantrealm.controllers to javafx.fxml;
    opens com.saulms.verdantrealm.data to javafx.fxml;
    opens com.saulms.verdantrealm.entities to javafx.fxml;
    opens com.saulms.verdantrealm.world to javafx.fxml;
    exports com.saulms.verdantrealm;
    exports com.saulms.verdantrealm.controllers;
    exports com.saulms.verdantrealm.data;
    exports com.saulms.verdantrealm.entities;
    exports com.saulms.verdantrealm.world;
}