package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ViewController {

    @FXML private Label lblWeight;
    @FXML private Label lblHeight;
    @FXML private Label lblResult;
    @FXML private Button btnCalculate;
    @FXML private TextField tfWeight;
    @FXML private TextField tfHeight;

    ResourceBundle rb;


    public void initialize() {
        setLanguage(new Locale.Builder().setLanguage("en").setRegion("US").build());
    }

    public void onCalculateClick(ActionEvent event) {
        if (tfWeight == null || tfHeight == null) {
            System.err.println("TextFileds are empty");
            return;
        }

        try {
            // Parse weight and height values
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText());

            // Convert height from centimeters to meters
            // Convert height from centimeters to meters
            height = height / 100.0;  // Convert to meters

            System.out.println("Weight: " + weight + " kg, Height: " + height + " m");

            // Calculate BMI using the formula: BMI = weight / (height * height)
            double bmi = weight / (height * height);
            System.out.println("Raw BMI: " + bmi);

            // Format the BMI value to 2 decimal places
            DecimalFormat df = new DecimalFormat("#0.00");
            String bmiString = df.format(bmi);
            System.out.println("Formatted BMI: " + bmiString);

            // Directly concatenate the BMI value to the result message
            lblResult.setText(rb.getString("result") + " " + bmiString);

        } catch (NumberFormatException e) {
            // Handle invalid input
            lblResult.setText(rb.getString("invalid"));
        }
    }

    public void onENClick(ActionEvent event) {
        setLanguage(new Locale("enw", "US"));
    }

    public void onFRClick(ActionEvent event) {
        setLanguage(new Locale("fr", "FR"));
    }

    public void onPAClick(ActionEvent event) {
        setLanguage(new Locale("ur", "PA"));
    }

    public void onVIClick(ActionEvent event) {
        setLanguage(new Locale("vi", "VI"));
    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            lblWeight.setText(rb.getString("weight"));
            lblHeight.setText(rb.getString("height"));
            btnCalculate.setText(rb.getString("calculate"));
            System.out.println(rb.getString("result"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resource error");
        }
    }
}
