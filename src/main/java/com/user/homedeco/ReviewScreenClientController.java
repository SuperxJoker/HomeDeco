package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotWriteForumException;
import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.exceptions.UsernameNotAvailable;
import com.user.homedeco.model.Forum;
import com.user.homedeco.model.Review;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewScreenClientController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea reviewField;
    @FXML
    private Button submitButton;
    @FXML
    private Label wrongLabel;

    private static List<Review> arrayReview;
    public static String reviewLocation = "reviewClient.json";
    public static Path reviewPath = Paths.get(reviewLocation);


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    public static void addReview(String email,String review) throws TitleNotAvailable, EmptyFieldException, CouldNotWriteForumException, UsernameNotAvailable {

        checkIfFieldsAreEmptyClient(email,review);


        if (arrayReview != null) {
            Iterator<Review> iterator = arrayReview.iterator();
            while (iterator.hasNext()) {
                Review obj2 = iterator.next();
                if (Objects.equals(obj2.getEmailKey(), email)) {
                    throw new UsernameNotAvailable();
                }
            }
        }

        arrayReview.add(new Review(email, review));
        persistForum();
        //System.out.println(arrayQuestion);


    }

    public void submitButtonOnAction(ActionEvent event) throws IOException{

        try{
            addReview(emailField.getText(),reviewField.getText());
            wrongLabel.setText("Thank you for your review");

        } catch(EmptyFieldException | TitleNotAvailable | CouldNotWriteForumException | UsernameNotAvailable e) {
            wrongLabel.setText(e.getMessage());
        }
    }

    private static void checkIfFieldsAreEmptyClient(String email, String review) throws EmptyFieldException {

        if (email.isEmpty() | review.isEmpty())
            throw new EmptyFieldException();
    }

    public static void loadForum() throws IOException {
        if (!Files.exists(reviewPath)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(HelpScreenClientController.class.getClassLoader().getResource("reviewClient.json")), reviewPath.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        arrayReview = objectMapper.readValue(reviewPath.toFile(), new TypeReference<>() {
        });
    }
    public static void persistForum() throws CouldNotWriteForumException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(reviewPath.toFile(), arrayReview);
        } catch (IOException e) {
            throw new CouldNotWriteForumException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
