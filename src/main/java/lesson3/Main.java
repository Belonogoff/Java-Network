package lesson3;


import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Server server = new Server();
        Client client = new Client();
        server.runServer();
        client.send();
    }
}