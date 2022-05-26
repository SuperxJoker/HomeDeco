package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.model.Shop;
import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static com.user.homedeco.CartScreenClientController.purchasedItems;
import static com.user.homedeco.ShopScreenClientController.cartItems;

public class HistoryScreenClientController{

    @FXML
    private Button closeButton;

    @FXML
    private TableView<Shop> orderTableClient;
    @FXML
    private TableColumn<Shop,String> nameOrder;
    @FXML
    private TableColumn<Shop,String> quantityOrder;
    @FXML
    private TableColumn<Shop,String> colorOrder;
    @FXML
    private TableColumn<Shop,String> materialOrder;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public static String orderLocation = "src/main/resources/orders/"+ User.name+".json";
    public static Path orderPath = Paths.get(orderLocation);

    ObservableList<Shop> order = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        nameOrder.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityOrder.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colorOrder.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialOrder.setCellValueFactory(new PropertyValueFactory<>("material"));
        if (purchasedItems != null) {
            for (Shop object : purchasedItems) {
                order.add(object);

                orderTableClient.setItems(order);

            }
        }
    }


}
