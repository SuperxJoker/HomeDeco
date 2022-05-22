module com.user.homedeco {
    requires java.sql;
    requires json.simple;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.user.homedeco to javafx.fxml;
    exports com.user.homedeco;
    exports com.user.homedeco.services;
    opens com.user.homedeco.services to javafx.fxml;
}