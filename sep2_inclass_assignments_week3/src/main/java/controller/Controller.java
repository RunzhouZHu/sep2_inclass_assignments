package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Controller {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sep2_week3_demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hhdhhdhhd";

    @FXML private ComboBox<String> languageSelector;
    @FXML private ListView<String> employeeList;

    @FXML private Label head;
    @FXML private TextField keyName;
    @FXML private TextField translation;
    @FXML private Button addButton;

    ResourceBundle rb;

    public void initialize() {
        languageSelector.getItems().addAll("English", "Spanish", "French", "简体中文");
        languageSelector.setValue("English");
        fetchLocalizedData("en");
        setLanguage(new Locale.Builder().setLanguage("en").setRegion("US").build());
        languageSelector.setOnAction(event -> changeLanguage());
    }

    private void changeLanguage() {
        String language = languageSelector.getValue();
        String languageCode = getLanguageCode(language);
        fetchLocalizedData(languageCode);
        setLanguage(new Locale.Builder().setLanguage(languageCode).setRegion(getRegionCode(languageCode)).build());
    }


    private String getLanguageCode(String language) {
        return switch (language) {
            case "English" -> "en";
            case "Spanish" -> "es";
            case "French" -> "fr";
            case "简体中文" -> "zh";
            default -> "en";
        };
    }

    private String getRegionCode(String languageCode) {
        return switch (languageCode) {
            case "en" -> "US";
            case "es" -> "ES";
            case "fr" -> "FR";
            case "zh" -> "CN";
            default -> "US";
        };
    }

    private void fetchLocalizedData(String languageCode) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "select Key_name, translation_text from translations where language_code=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, languageCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeList.getItems().clear();

            while (resultSet.next()) {
                String key = resultSet.getString("Key_name");
                String translationText = resultSet.getString("translation_text");
                employeeList.getItems().add(key + ": " + translationText);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addButtonClicked(MouseEvent mouseEvent) {
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO translations (Key_name, language_code, translation_text) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, keyName.getText());
            preparedStatement.setString(2, getLanguageCode(languageSelector.getValue()));
            preparedStatement.setString(3, translation.getText());
            preparedStatement.executeUpdate();
            changeLanguage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // set UI language
    private void setLanguage(Locale locale) {
        keyName.clear();
        translation.clear();
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            head.setText(rb.getString("head"));
            keyName.setPromptText(rb.getString("keyName"));
            translation.setPromptText(rb.getString("translation"));
            addButton.setText(rb.getString("addButton"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

}
