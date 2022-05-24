module com.user.homedeco {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.io;


    opens com.user.homedeco to javafx.fxml;
    exports com.user.homedeco;
    exports com.user.homedeco.services;
    opens com.user.homedeco.services to javafx.fxml;
    exports com.user.homedeco.model;
    opens com.user.homedeco.model to javafx.fxml;
}