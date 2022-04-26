module com.user.homedeco {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.sql;


    opens com.user.homedeco to javafx.fxml;
    exports com.user.homedeco;
}