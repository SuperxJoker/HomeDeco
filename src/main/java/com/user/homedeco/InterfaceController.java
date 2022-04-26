package com.user.homedeco;

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

    public InterfaceController() {
    }

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }
    public void createAccount(ActionEvent event) throws IOException{
        createAccountForm();
    }

    private void checkLogin() throws IOException{
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            validateLogin();
        } else{
            wrongLogIn.setTextFill(Color.PURPLE);
            wrongLogIn.setText("Please enter username and password");
            new animatefx.animation.Shake(username).play();
            new animatefx.animation.Shake(password).play();

        }
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    wrongLogIn.setTextFill(Color.GREEN);
                    wrongLogIn.setText("Success!");

                }else{
                    wrongLogIn.setTextFill(Color.RED);
                    wrongLogIn.setText("Wrong username or password!");
                    new animatefx.animation.Shake(username).play();
                    new animatefx.animation.Shake(password).play();

                }

            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
           // registerStage.setTitle("Application");
            registerStage.setScene(new Scene(root, 600, 461));
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
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
