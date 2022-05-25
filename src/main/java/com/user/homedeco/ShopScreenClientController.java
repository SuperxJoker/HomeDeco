package com.user.homedeco;

import com.user.homedeco.model.Shop;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopScreenClientController{

    @FXML
    private Button closeButton;

    @FXML
    private TableView shopTableClient;
    @FXML
    private TableColumn productClient;
    @FXML
    private TableColumn quantityClient;
    @FXML
    private TableColumn colorClient;
    @FXML
    private TableColumn materialClient;
    @FXML
    private TextField  addNameClient;
    @FXML
    private TextField  addQuantityClient;
    @FXML
    private TextField  addColorClient;
    @FXML
    private TextField  addMaterialClient;

    @FXML
    private Button addToCart;


    ObservableList<Shop> shop = FXCollections.observableArrayList();



    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void addToCartOnAction(ActionEvent event){

    }

    @FXML
    public void initialize() {


        productClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityClient.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colorClient.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialClient.setCellValueFactory(new PropertyValueFactory<>("material"));

        if (ShopScreenAdminController.shopItems != null) {
            for (Shop object : ShopScreenAdminController.shopItems) {
                shop.add(object);
                shopTableClient.setItems(shop);
            }
        }
    }
}
