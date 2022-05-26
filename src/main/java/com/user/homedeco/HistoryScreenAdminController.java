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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.user.homedeco.CartScreenClientController.purchasedItems;

public class HistoryScreenAdminController {

    @FXML
    private Button closeButton;

    @FXML
    private TableView<Shop> orderTableAdmin;
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


    public static String orderLocation = "orders.json";
    public static Path orderPath = Paths.get(orderLocation);

    private static List<Shop> orderedItems = new ArrayList<>();
    ObservableList<Shop> order = FXCollections.observableArrayList();


    public static void loadOrdersAdmin() throws IOException {

        if (!Files.exists(orderPath) || !Files.exists(orderPath)) {
            //File cartFile = new File(cartLocation);
            //cartFile.createNewFile();
            orderPath.toFile().createNewFile();


        }

        //FileUtils.copyURLToFile(Objects.requireNonNull(ShopScreenClientController.class.getClassLoader().getResource("src/main/resources/cart/timeanilgesz.json")), cartPath.toFile());
        ObjectMapper objectMapper = new ObjectMapper();
        orderedItems = objectMapper.readValue(orderPath.toFile(), new TypeReference<>() {
        });
        System.out.println(orderedItems);
    }
    @FXML
    public void initialize() {
        nameOrder.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityOrder.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colorOrder.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialOrder.setCellValueFactory(new PropertyValueFactory<>("material"));
        if (orderedItems != null) {
            for (Shop object : orderedItems) {
                order.add(object);

                orderTableAdmin.setItems(order);

            }
        }
    }


}
