package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Controller {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sep2_week3_demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hhdhhdhhd";

    @FXML private ComboBox comboBox;
    @FXML private Label title;




    // function to get data from database
    private ObservableList<String> list;


}
