package com.user.homedeco;

import com.user.homedeco.exceptions.IncorrectMailOrPassword;
import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    @FXML
    private Button createButton;
    @FXML
    private Button closeLogin;

    public static String email;

    public InterfaceController() {
    }
    //create account
    public void createAccount(ActionEvent event) throws IOException{
        createAccountForm();
    }
    public void closeLoginOnAction(ActionEvent event){
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void handleLoginButtonAction(ActionEvent event) throws IOException {

        try{
            if((User.loginCheckClient(username.getText(),password.getText()).equals("Client"))){
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeScreenClient.fxml"));
                Stage home_page_scene = new Stage();
                home_page_scene.setScene(new Scene(home_page_parent, 818, 484));
                home_page_scene.show();
                email=username.getText();
            } else if(username.getText().equals("admin@homedecor.com") && password.getText().equals("adminHome")){
                Parent home_admin_parent = FXMLLoader.load(getClass().getResource("HomeScreenAdmin.fxml"));
                Stage home_admin_scene = new Stage();
                home_admin_scene.setScene(new Scene(home_admin_parent, 818, 484));
                home_admin_scene.show();

            }else
            {
                User.checkIncorrect();
            }

            } catch (IncorrectMailOrPassword e) {
               wrongLogIn.setText(e.getMessage());
        }


    }

    public void createAccountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
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

