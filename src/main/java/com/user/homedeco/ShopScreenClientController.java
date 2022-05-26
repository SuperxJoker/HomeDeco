package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotWriteForumException;
import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.NonExistentProduct;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.model.Shop;
import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.user.homedeco.ShopScreenAdminController.shopItems;

public class ShopScreenClientController{

    @FXML
    private Button closeButton;

    @FXML
    private TableView<Shop> shopTableClient;
    @FXML
    private TableColumn<Shop,String> productClient;
    @FXML
    private TableColumn<Shop,String> quantityClient;
    @FXML
    private TableColumn<Shop,String> colorClient;
    @FXML
    private TableColumn<Shop,String> materialClient;
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

    @FXML
    private Label wrongProduct;


    public static String cartLocation = "shop.json";
    public static Path cartPath = Paths.get(cartLocation);
    public static Path shopPath = Paths.get("shop.json");


    protected static List<Shop> cartItems= new ArrayList<Shop>();

    ObservableList<Shop> shop = FXCollections.observableArrayList();


    public static void loadCart() throws IOException {

        if (!Files.exists(cartPath)) {
            //File cartFile = new File(cartLocation);
            //cartFile.createNewFile();
            cartPath.toFile().createNewFile();

        }

        //FileUtils.copyURLToFile(Objects.requireNonNull(ShopScreenClientController.class.getClassLoader().getResource("src/main/resources/cart/timeanilgesz.json")), cartPath.toFile());
        ObjectMapper objectMapper = new ObjectMapper();
        cartItems = objectMapper.readValue(cartPath.toFile(), new TypeReference<>() {
        });
        Object objectMapperShop = new ObjectMapper();
        shopItems= objectMapper.readValue(shopPath.toFile(), new TypeReference<>() {
        });
    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public static void addCartItem(String name,String quantity, String color, String material) throws  EmptyFieldException, CouldNotWriteForumException{

        checkIfFieldsAreEmptyClient(name,quantity,color,material);






        cartItems.add(new Shop(name,quantity,color,material));
        //System.out.println(cartItems);


        persistForum();
        //System.out.println(arrayQuestion);


    }


    private static void checkIfFieldsAreEmptyClient(String name, String quantity,String color,String material) throws EmptyFieldException {

        if (name.isEmpty() | quantity.isEmpty() | color.isEmpty() | material.isEmpty())
            throw new EmptyFieldException();
    }



    public static void persistForum() throws CouldNotWriteForumException {
        ObjectMapper objectMapper = new ObjectMapper();
    }
    public void addToCartOnAction(ActionEvent event){
        wrongProduct.setText(" ");
        try{
            addCartItem(addNameClient.getText(),addQuantityClient.getText(),addColorClient.getText(),addMaterialClient.getText());
        }
         catch(EmptyFieldException e) {
            wrongProduct.setText(e.getMessage());
         }catch (CouldNotWriteForumException e) {
            wrongProduct.setText(e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {


        productClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityClient.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colorClient.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialClient.setCellValueFactory(new PropertyValueFactory<>("material"));

        if (shopItems != null) {
            for (Shop object : shopItems) {
                shop.add(object);
                shopTableClient.setItems(shop);
            }
        }
    }
}
