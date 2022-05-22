package com.user.homedeco;

import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.model.Forum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelpScreenClientController {

    @FXML
    private Button closeButton;
    @FXML
    private Button submit;
    @FXML
    private TableView<Forum> forum;
    @FXML
    private TableColumn<Forum,String> titleKey;
    @FXML
    private TableColumn<Forum,String> questionKey;
    @FXML
    private TableColumn<Forum,String> answerKey;
    @FXML
    private TextField titlePost;
    @FXML
    private TextField questionPost;
    @FXML
    private Label wrongLabel;

    private static JSONArray arrayQuestion;


    @FXML
    public void initialize() {
        titleKey.setCellValueFactory(new PropertyValueFactory<>("titleKey"));
        questionKey.setCellValueFactory(new PropertyValueFactory<>("questionKey"));
        answerKey.setCellValueFactory(new PropertyValueFactory<>("answerKey"));

    }


    public static void addForumQuestion(String title,String question) throws TitleNotAvailable, EmptyFieldException {

        checkIfFieldsAreEmptyClient(title,question);

        JSONObject obj = new JSONObject();
        arrayQuestion = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/forum.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayQuestion = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayQuestion.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Title:").equals(title))
            {
                throw new TitleNotAvailable();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("Title:", title);
        obj.put("Question:", question);
        obj.put("Answer:", null);


        arrayQuestion.add(obj);

        try {
            File file = new File("src/main/resources/forum.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayQuestion.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static void checkIfFieldsAreEmptyClient(String title, String question) throws EmptyFieldException {

        if (title.isEmpty() | question.isEmpty())
            throw new EmptyFieldException();
    }

    public void submitButtonOnAction(ActionEvent event) throws IOException{

        try{
            addForumQuestion(titlePost.getText(),questionPost.getText());
           // forum.getColumns().addAll(titleKey,questionKey);
          //  titleKey.setCellValueFactory(c -> c.getValue().getTitleKey());
           // new Forum(titlePost.getText(),questionPost.getText(),null);


            ObservableList<Forum> items = FXCollections.observableArrayList();

            for (Object forum_entry : arrayQuestion) {
                items.add((Forum) forum_entry);
                forum.setItems(items);
            }
        }
        //error if not all fields are completed
        catch(EmptyFieldException | TitleNotAvailable e){
            wrongLabel.setText(e.getMessage());
        }

    }




    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
