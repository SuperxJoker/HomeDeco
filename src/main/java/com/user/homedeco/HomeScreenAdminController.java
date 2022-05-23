package com.user.homedeco;

import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenAdminController implements Initializable {

    @FXML
    private Label adminLabel;
    @FXML
    private Button closeButton;
    @FXML
    private Button homePageButton;
    @FXML
    private AnchorPane home;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button clientServiceButton;
    @FXML
    private Button ordersButton;



    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



    public void homeScreenButtonOnAction(ActionEvent event) throws IOException {

        getPage("HomeScreenAdmin");
    }

    public void shopButtonOnAction(ActionEvent event) throws IOException{

        getPage("ShopScreenAdmin");

    }
    public void ordersButtonOnAction(ActionEvent event) throws IOException{
        getPage("ViewOrdersAdmin");

    }

    public void clientServiceButtonOnAction(ActionEvent event) throws IOException{
        getPage("TestAdminForum");

    }



    public void getPage(String filename) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource(filename + ".fxml"));
        home.getChildren().setAll(node);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adminLabel.setText("admin");

    }
}
