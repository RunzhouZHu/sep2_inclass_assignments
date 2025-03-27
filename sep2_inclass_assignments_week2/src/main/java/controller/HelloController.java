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

public class HelloController {

    @FXML private Label lblDistance;
    @FXML private Label lblFuel;
    @FXML private Label lblResult;
    @FXML private Button btnCalculate;
    @FXML private TextField txtDistance;
    @FXML private TextField txtFuel;

    ResourceBundle rb;

    public void initialize() {
        setLanguage(new Locale.Builder().setLanguage("en").setRegion("US").build());
    }


    public void onCalculateClick(ActionEvent event) {
        if (txtDistance == null || txtFuel == null) {
            System.err.println("TextFiled empty");
            return;
        }

        try {
            // Parse weight and height values
            double distance = Double.parseDouble(txtDistance.getText());
            double fuel = Double.parseDouble(txtFuel.getText());

            // Convert distance from km to 100km
            distance = distance / 100.0;  // Convert to 100km

            System.out.println("Distance: " + distance + " 100km, Fuel: " + fuel + " L");

            // Calculate consumption using the formula: consumption = fuel / distance
            double consumption = fuel / distance;
            System.out.println("Raw consumption: " + consumption);

            // Format the BMI value to 2 decimal places
            DecimalFormat df = new DecimalFormat("#0.00");
            String consumptionString = df.format(consumption);
            System.out.println("Formatted BMI: " + consumptionString);

            // Directly concatenate the BMI value to the result message
            lblResult.setText(rb.getString("result") + " " + consumptionString + " L/100km");

        } catch (NumberFormatException e) {
            // Handle invalid input
            lblResult.setText(rb.getString("invalid"));
        }
    }

    public void onENClick(ActionEvent event) {
        setLanguage(new Locale.Builder().setLanguage("en").setRegion("US").build());
    }

    public void onFRClick(ActionEvent event) {
        setLanguage(new Locale.Builder().setLanguage("fr").setRegion("FR").build());
    }

    public void onJPClick(ActionEvent event) {
        setLanguage(new Locale.Builder().setLanguage("ja").setRegion("JP").build());
    }

    public void onIRClick(ActionEvent event) {
        setLanguage(new Locale.Builder().setLanguage("fa").setRegion("IR").build());
    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            lblDistance.setText(rb.getString("distance"));
            lblFuel.setText(rb.getString("fuel"));
            btnCalculate.setText(rb.getString("calculate"));
            System.out.println(rb.getString("result"));
        } catch (MissingResourceException e) {
            lblResult.setText("Error loading resource error");
        }
    }
}
