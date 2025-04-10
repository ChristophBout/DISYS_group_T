package projectinterface;

import javafx.fxml.FXML;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiController {

    private HttpClient client;

    public ApiController(){
        this.client = HttpClient.newHttpClient();
    }

    @FXML
    protected void onRequestButtonClick(){
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://localhost:8080/lectures/count"))
                    .build();

            HttpResponse<String> response
                    = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
