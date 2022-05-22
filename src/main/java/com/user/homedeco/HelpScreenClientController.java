package com.user.homedeco;

import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.TitleNotAvailable;
import com.user.homedeco.exceptions.UsernameNotAvailable;
import com.user.homedeco.services.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HelpScreenClientController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button submit;
    @FXML
    private TableView<String> forum;
    @FXML
    private TextField titlePost;
    @FXML
    private TextField questionPost;
    @FXML
    private Label wrongLabel;



    public static void addForumQuestion(String title,String question) throws TitleNotAvailable, EmptyFieldException {

        checkIfFieldsAreEmptyClient(title,question);

        JSONObject obj = new JSONObject();
        JSONArray arrayQuestion = new JSONArray();
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
            //Load the Home Page for client


        }
        catch(EmptyFieldException | TitleNotAvailable e){
            //error if not all fields are completed
            wrongLabel.setText(e.getMessage());
        }

    }


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
