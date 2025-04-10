package projectinterface;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiController {

    private final HttpClient client;

    public ApiController() {
        this.client = HttpClient.newHttpClient();
    }

    public JSONObject getCurrentEnergyData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/energy/current"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return new JSONObject(response.body());
            } else {
                System.err.println("Fehler beim Abrufen der aktuellen Energiedaten: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Exception bei getCurrentEnergyData:");
            e.printStackTrace();
        }

        return null; // Rückgabe null bei Fehler
    }

    public JSONObject getHistoricalEnergyData(String start, String end) {
        try {
            String uri = "http://localhost:8080/energy/historical?start=" + start + "&end=" + end;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return new JSONObject(response.body());
            } else {
                System.err.println("Fehler beim Abrufen der historischen Daten: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Exception bei getHistoricalEnergyData:");
            e.printStackTrace();
        }

        return null; // Rückgabe null bei Fehler
    }
}
