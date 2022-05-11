package com.user.homedeco;

import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.UsernameNotAvailable;
import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

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
    private TextField emailTextfield;
    @FXML
    private Button returnSignin;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException{

        try{
            User.addUserClient(firstnameTextfield.getText(),lastnameTextfield.getText(),emailTextfield.getText(),setPasswordField.getText());


        }
        catch(EmptyFieldException | UsernameNotAvailable e){
            registrationMessageLabel.setText(e.getMessage());
        }

    }

    public void returnSigninOnAction(ActionEvent event) throws IOException{
        signinForm();

    }

    public void signinForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            // registerStage.setTitle("Application");
            registerStage.setScene(new Scene(root, 818, 484));
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
