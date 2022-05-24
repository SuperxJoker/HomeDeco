package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotWriteForumException;
import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.model.Forum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HelpScreenClientController {

    @FXML
    private Button closeButton;
    @FXML
    private Button submit;
    @FXML
    private TableView<Forum> forum;
    @FXML
    private TableColumn<Forum,String> titleField;
    @FXML
    private TableColumn<Forum,String> questionField;
    @FXML
    private TableColumn<Forum,String> answerField;
    @FXML
    private TextField titlePost;
    @FXML
    private TextField questionPost;
    @FXML
    private Label wrongLabel;

    private static List<Forum> arrayQuestion;
    public static String forumLocation = "src/main/resources/forum.json";
    public static Path forumPath = Paths.get(forumLocation);
    ObservableList<Forum> items = FXCollections.observableArrayList();



    @FXML
    public void initialize() {
        titleField.setCellValueFactory(new PropertyValueFactory<>("TitleKey"));
        questionField.setCellValueFactory(new PropertyValueFactory<>("QuestionKey"));
        answerField.setCellValueFactory(new PropertyValueFactory<>("AnswerKey"));

        if (arrayQuestion != null) {
            for (Forum object : arrayQuestion) {
                items.add(object);
                forum.setItems(items);
            }
        }

    }

    public static void loadForum() throws IOException {
        if (!Files.exists(forumPath)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(HelpScreenClientController.class.getClassLoader().getResource("src/main/resources/forum.json")), forumPath.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        arrayQuestion = objectMapper.readValue(forumPath.toFile(), new TypeReference<>() {
        });
    }

    public static void addForumQuestion(String title,String question) throws TitleNotAvailable, EmptyFieldException, CouldNotWriteForumException {

        checkIfFieldsAreEmptyClient(title,question);


        if (arrayQuestion != null) {
            Iterator<Forum> iterator = arrayQuestion.iterator();
            while (iterator.hasNext()) {
                Forum obj2 = iterator.next();
                if (Objects.equals(obj2.getTitleKey(), title)) {
                    throw new TitleNotAvailable();
                }
            }
        }

        arrayQuestion.add(new Forum(title, question, "Not answered"));
        persistForum();
        //System.out.println(arrayQuestion);


    }


    private static void checkIfFieldsAreEmptyClient(String title, String question) throws EmptyFieldException {

        if (title.isEmpty() | question.isEmpty())
            throw new EmptyFieldException();
    }

    public void submitButtonOnAction(ActionEvent event) throws IOException{

        try{
            addForumQuestion(titlePost.getText(),questionPost.getText());
            ObservableList<Forum> items = FXCollections.observableArrayList();

            for (Forum forum_entry : arrayQuestion) {
                items.add(forum_entry);
                forum.setItems(items);
            }
        } catch(EmptyFieldException e){
            wrongLabel.setText(e.getMessage());
        } catch (TitleNotAvailable e) {
            wrongLabel.setText(e.getMessage());
        } catch(CouldNotWriteForumException e) {
            wrongLabel.setText(e.getMessage());
        }
    }


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public static void persistForum() throws CouldNotWriteForumException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(forumPath.toFile(), arrayQuestion);
        } catch (IOException e) {
            throw new CouldNotWriteForumException();
        }
    }

}
