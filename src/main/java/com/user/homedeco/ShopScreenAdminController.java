package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotWriteForumException;
import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.model.Shop;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ShopScreenAdminController{
    @FXML
    private Button closeButton;
    @FXML
    private Button addProduct;

    @FXML
    private TextField addName;
    @FXML
    private TextField addQuantity;
    @FXML
    private TextField addColor;
    @FXML
    private TextField addMaterial;

    @FXML
    private TableView<Shop> shopTable;
    @FXML
    private TableColumn<Shop,String> product;
    @FXML
    private TableColumn<Shop,String> quantity;
    @FXML
    private TableColumn<Shop,String> color;
    @FXML
    private TableColumn<Shop,String> material;


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



    protected static List<Shop> shopItems= new ArrayList<>();
    public static String shopLocation = "shop.json";
    public static Path shopPath = Paths.get(shopLocation);
    ObservableList<Shop> shop = FXCollections.observableArrayList();



    @FXML
    public void initialize() {


        product.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));

        if (shopItems != null) {
            for (Shop object : shopItems) {
                shop.add(object);
                shopTable.setItems(shop);
            }
        }
    }


    public static void loadShop() throws IOException {

        if (!Files.exists(shopPath)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(ShopScreenAdminController.class.getClassLoader().getResource("shop.json")), shopPath.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        shopItems = objectMapper.readValue(shopPath.toFile(), new TypeReference<>() {
        });
    }
    public static void addShopItem(String name,String quantity, String color, String material) throws TitleNotAvailable, EmptyFieldException, CouldNotWriteForumException {

        checkIfFieldsAreEmptyClient(name,quantity,color,material);


        if (shopItems != null) {
            Iterator<Shop> iterator = shopItems.iterator();
            while (iterator.hasNext()) {
                Shop obj2 = iterator.next();
                if (Objects.equals(obj2.getName(), name)) {
                    throw new TitleNotAvailable();
                }
            }
        }

        shopItems.add(new Shop(name,quantity,color,material));
        persistForum();
        //System.out.println(arrayQuestion);


    }


    private static void checkIfFieldsAreEmptyClient(String name, String quantity,String color,String material) throws EmptyFieldException {

        if (name.isEmpty() | quantity.isEmpty() | color.isEmpty() | material.isEmpty())
            throw new EmptyFieldException();
    }


    public void addButtonOnAction(ActionEvent event)
    {
        try{
            addShopItem(addName.getText(),addQuantity.getText(),addColor.getText(),addMaterial.getText());
            ObservableList<Shop> readShop = FXCollections.observableArrayList();

            for (Shop shop_entry : shopItems) {
                readShop.add(shop_entry);
                shopTable.setItems(readShop);
            }
        } catch(EmptyFieldException e) {
        } catch (TitleNotAvailable e) {
            e.printStackTrace();
        } catch (CouldNotWriteForumException e) {
            e.printStackTrace();
        }
    }
    public static void persistForum() throws CouldNotWriteForumException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(shopPath.toFile(), shopItems);
        } catch (IOException e) {
            throw new CouldNotWriteForumException();
        }
    }


}
