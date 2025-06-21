package projectinterface;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.json.JSONObject;
import java.time.LocalDate;

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

    private final ApiController apiController = new ApiController();

    @FXML
    public void initialize() {
        // Auto-refresh on load
        onRefresh();
        onShowData();
    }

    @FXML
    protected void onRefresh() {
        communityPoolLabel.setText("Lade...");
        gridPortionLabel.setText("Lade...");

        try {
            JSONObject json = apiController.getCurrentEnergyData();

            if (json.has("communityPoolUsed")) {
                communityPoolLabel.setText(json.getDouble("communityPoolUsed") + "% used");
            } else {
                communityPoolLabel.setText("Keine Daten");
            }

            if (json.has("gridPortion")) {
                gridPortionLabel.setText(json.getDouble("gridPortion") + "%");
            } else {
                gridPortionLabel.setText("Keine Daten");
            }

        } catch (Exception e) {
            communityPoolLabel.setText("Fehler beim Laden");
            gridPortionLabel.setText("Fehler beim Laden");
            e.printStackTrace();
        }
    }

    @FXML
    protected void onShowData() {
        communityProducedLabel.setText("Lade...");
        communityUsedLabel.setText("Lade...");
        gridUsedLabel.setText("Lade...");

        try {
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            if (start == null || end == null) {
                communityProducedLabel.setText("Start- und Enddatum wählen!");
                communityUsedLabel.setText("");
                gridUsedLabel.setText("");
                return;
            }

            String startString = start + "T00:00";
            String endString = end + "T23:59";

            JSONObject json = apiController.getHistoricalEnergyData(startString, endString);

            if (json.has("communityProduced")) {
                communityProducedLabel.setText(json.getDouble("communityProduced") + " kWh");
            } else {
                communityProducedLabel.setText("Keine Daten");
            }

            if (json.has("communityUsed")) {
                communityUsedLabel.setText(json.getDouble("communityUsed") + " kWh");
            } else {
                communityUsedLabel.setText("Keine Daten");
            }

            if (json.has("gridUsed")) {
                gridUsedLabel.setText(json.getDouble("gridUsed") + " kWh");
            } else {
                gridUsedLabel.setText("Keine Daten");
            }

        } catch (Exception e) {
            communityProducedLabel.setText("Fehler beim Laden");
            communityUsedLabel.setText("Fehler beim Laden");
            gridUsedLabel.setText("Fehler beim Laden");
            e.printStackTrace();
        }
    }
}
