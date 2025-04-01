package projectinterface.projectinterface;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label communityPoolLabel;
    @FXML
    private Label gridPortionLabel;

    @FXML
    private Label communityProducedLabel;
    @FXML
    private Label communityUsedLabel;
    @FXML
    private Label gridUsedLabel;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    protected void onRefresh() {
        // Beispielhafte Platzhalterdaten
        communityPoolLabel.setText("78.54% used");
        gridPortionLabel.setText("7.23%");
    }

    @FXML
    protected void onShowData() {
        // Hier könntest du z.B. das ausgewählte Datum verarbeiten
        // startDatePicker.getValue() + evtl. Uhrzeit ergänzen
        // Aber aktuell setzen wir nur feste Werte:

        communityProducedLabel.setText("143.024 kWh");
        communityUsedLabel.setText("130.101 kWh");
        gridUsedLabel.setText("14.75 kWh");
    }
}
