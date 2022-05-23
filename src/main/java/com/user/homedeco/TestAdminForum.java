package com.user.homedeco;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.homedeco.exceptions.CouldNotAnswerException;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TestAdminForum {

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
    private TextField answerPost;
    @FXML
    private Label wrongLabel;

    private static List<Forum> arrayQuestion;
    public static String forumLocation = "src/main/resources/forum.json";
    public static Path forumPath = Paths.get(forumLocation);
    ObservableList<Forum> items = FXCollections.observableArrayList();




    @FXML
    public void initialize() throws IOException {
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
            FileUtils.copyURLToFile(Objects.requireNonNull(TestAdminForum.class.getClassLoader().getResource("src/main/resources/forum.json")), forumPath.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        arrayQuestion = objectMapper.readValue(forumPath.toFile(), new TypeReference<>() {
        });
    }

    public static void addForumAnswer(String title, String answer) throws IOException, EmptyFieldException, CouldNotWriteForumException, CouldNotAnswerException {
        checkIfFieldsAreEmptyClient(title,answer);

        if (arrayQuestion != null) {
            Iterator<Forum> iterator = arrayQuestion.iterator();
            while (iterator.hasNext()) {
                Forum obj2 = iterator.next();
                if (Objects.equals(obj2.getTitleKey(), title) && Objects.equals(obj2.getAnswerKey(),"Not answered")) {
                    obj2.setAnswerKey(answer);

                }
                else{
                    throw new CouldNotAnswerException();
                }
            }
        }

        //arrayQuestion.add(new Forum(title, question, "null"));
        persistForum();


    }
    public void submitButtonOnAction(ActionEvent event) throws IOException{

        try{
            addForumAnswer(titlePost.getText(),answerPost.getText());
            ObservableList<Forum> items = FXCollections.observableArrayList();

            for (Forum forum_entry : arrayQuestion) {
                items.add(forum_entry);
                forum.setItems(items);
            }
        } catch(EmptyFieldException e){
            wrongLabel.setText(e.getMessage());
        } catch(CouldNotWriteForumException | CouldNotAnswerException e) {
            wrongLabel.setText(e.getMessage());
        }
    }


        public void closeButtonOnAction(ActionEvent event){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            Platform.exit();
        }
        private static void checkIfFieldsAreEmptyClient(String title, String answer) throws EmptyFieldException {

            if (title.isEmpty() | answer.isEmpty())
                throw new EmptyFieldException();
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

