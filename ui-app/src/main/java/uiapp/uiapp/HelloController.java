package uiapp.uiapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.incubator.vector.VectorOperators;

public class HelloController {

   private int counter = 0;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField nameInput;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}