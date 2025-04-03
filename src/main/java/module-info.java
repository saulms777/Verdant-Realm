module com.saulms.cavernwarrior {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.saulms.cavernwarrior to javafx.fxml;
    opens com.saulms.cavernwarrior.controllers to javafx.fxml;
    exports com.saulms.cavernwarrior;
}