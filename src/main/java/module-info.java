module com.user.homedeco {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.user.homedeco to javafx.fxml;
    exports com.user.homedeco;
}