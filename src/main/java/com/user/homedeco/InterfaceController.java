package com.user.homedeco;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public InterfaceController() {
    }

    public void userLogin(ActionEvent event) throws IOException {
        this.checkLogin();
    }

    private void checkLogin() throws IOException {

        if (this.username.getText().toString().equals("admin") && this.password.getText().toString().equals("admin")) {
            this.wrongLogIn.setText("Success!");

        } else if (this.username.getText().isEmpty() && this.password.getText().isEmpty()) {
            this.wrongLogIn.setText("Please enter your data.");
        } else {
            this.wrongLogIn.setText("Wrong username or password!");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    userLogin(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
