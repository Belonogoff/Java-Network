package lesson3;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    public void send() throws URISyntaxException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        URI httpURI = new URI("http://localhost:8080/example");
        HttpRequest request = HttpRequest.newBuilder(httpURI)
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("login:java,password:password"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(resp -> {
            System.out.println("Response from: " + resp.uri());
            System.out.println("Response statuscode: " + resp.statusCode());
            System.out.println("Response body: " + resp.body());
        });

    }
}
