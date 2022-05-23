package com.user.homedeco;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserLogin extends Application {
    @Override
            public void start(Stage primaryStage) throws Exception{
             HelpScreenClientController.loadForum();
            ShopScreenAdminController.loadShop();
             Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
             primaryStage.initStyle(StageStyle.UNDECORATED);
             //primaryStage.setTitle("Application");
             primaryStage.setScene(new Scene(root, 818, 484));
             primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
