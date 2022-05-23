package com.user.homedeco;

import com.user.homedeco.model.Forum;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class TestAdminForum {

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






}
