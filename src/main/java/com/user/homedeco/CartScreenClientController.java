package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotWriteForumException;
import com.user.homedeco.exceptions.EmptyFieldException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static com.user.homedeco.ShopScreenAdminController.shopItems;
import static com.user.homedeco.ShopScreenClientController.cartItems;

public class CartScreenClientController{

    @FXML
    private Button closeButton;

    @FXML
    private Button checkout;

    @FXML
    private TextField addToOrder;
    @FXML
    private TableView<Shop> cartTableClient;
    @FXML
    private TableColumn<Shop,String> nameCart;

    public static String orderLocation =  User.name+".json";
    public static Path orderPath = Paths.get(orderLocation);

    public static String orderAdminLocation = "orders.json";
    public static Path orderAdminPath = Paths.get(orderAdminLocation);

    protected static List<Shop> purchasedItems = new ArrayList<Shop>();
    ObservableList<Shop> cart = FXCollections.observableArrayList();

    public void checkoutButtonOnAction(ActionEvent event){
        try{
            addPurchasedItem(addToOrder.getText());
        }
        catch(EmptyFieldException e) {
            e.printStackTrace();
        }

    }

    public static void loadOrders() throws IOException {

        if (!Files.exists(orderPath) || !Files.exists(orderAdminPath)) {
            //File cartFile = new File(cartLocation);
            //cartFile.createNewFile();
            orderPath.toFile().createNewFile();
            orderAdminPath.toFile().createNewFile();

        }

        //FileUtils.copyURLToFile(Objects.requireNonNull(ShopScreenClientController.class.getClassLoader().getResource("src/main/resources/cart/timeanilgesz.json")), cartPath.toFile());
        ObjectMapper objectMapper = new ObjectMapper();
        purchasedItems = objectMapper.readValue(orderPath.toFile(), new TypeReference<>() {
        });
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public static void addPurchasedItem(String name) throws EmptyFieldException{

        checkIfFieldsAreEmptyClient(name);


        if (cartItems != null) {
            Iterator<Shop> iterator = cartItems.iterator();
            while (iterator.hasNext()) {
                Shop obj2 = iterator.next();
                if(obj2.getName().equals(name))
                    purchasedItems.add(new Shop(name,obj2.getQuantity(),obj2.getColor(),obj2.getMaterial()));



            }

        }




        //System.out.println(cartItems);


        persistForum();
        //System.out.println(arrayQuestion);


    }

    private static void checkIfFieldsAreEmptyClient(String name) throws EmptyFieldException {

        if (name.isEmpty())
            throw new EmptyFieldException();
    }



    public static void persistForum(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(orderPath.toFile(), purchasedItems);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(orderAdminPath.toFile(), purchasedItems);
        } catch (IOException e) {}

    }
    @FXML
    public void initialize()
    {
        nameCart.setCellValueFactory(new PropertyValueFactory<>("name"));


        if (cartItems != null) {
            for (Shop object : cartItems) {
                cart.add(object);

                cartTableClient.setItems(cart);

            }
        }
    }
}
