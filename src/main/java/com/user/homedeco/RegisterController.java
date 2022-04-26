package com.user.homedeco;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.File;


public class RegisterController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextfield;
    @FXML
    private TextField lastnameTextfield;
    @FXML
    private TextField usernameTextfield;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("");

        }else{
            confirmPasswordLabel.setText("Password does not match");
            new animatefx.animation.Shake(confirmPasswordField).play();
            new animatefx.animation.Shake(setPasswordField).play();
        }

    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextfield.getText();
        String lastname = lastnameTextfield.getText();
        String username = usernameTextfield.getText();
        String password = setPasswordField.getText();

        String insertFields = "INSERT INTO user_account(firstname, lastname, username, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try{
        Statement statement = connectDB.createStatement();
        statement.executeUpdate(insertToRegister);
        registrationMessageLabel.setText("User registered successfully!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
