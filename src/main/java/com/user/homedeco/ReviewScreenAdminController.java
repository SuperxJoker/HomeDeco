package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.model.Forum;
import com.user.homedeco.model.Review;
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
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ReviewScreenAdminController {
    @FXML
    private Button closeButton;
    @FXML
    private TableView<Review> review;
    @FXML
    private TableColumn<Review,String> clientField;
    @FXML
    private TableColumn<Review,String> reviewField;

    private static List<Review> arrayReview;
    ObservableList<Review> items = FXCollections.observableArrayList();
    public static String reviewLocation = "src/main/resources/reviewClient.json";
    public static Path reviewPath = Paths.get(reviewLocation);


    public void initialize() throws IOException {
        clientField.setCellValueFactory(new PropertyValueFactory<>("emailKey"));
        reviewField.setCellValueFactory(new PropertyValueFactory<>("ReviewKey"));

        if (arrayReview != null) {
            for (Review object : arrayReview) {
                items.add(object);
                review.setItems(items);
            }
        }
    }
    public static void loadForum() throws IOException {
        if (!Files.exists(reviewPath)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(HelpScreenClientController.class.getClassLoader().getResource("src/main/resources/forum.json")), reviewPath.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        arrayReview = objectMapper.readValue(reviewPath.toFile(), new TypeReference<>() {
        });
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



}
