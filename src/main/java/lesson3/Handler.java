package lesson3;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().equals("POST")) {
            check(exchange);
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP Method: " + exchange.getRequestMethod());
        }
    }

    private void check(HttpExchange exchange) throws IOException {
        String request = null;
        try {
            request = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> input = new HashMap<String, String>();
        Arrays.stream(request.split(",")).forEach(it -> {
            String[] pair = it.split(":", 2);
            input.put(pair[0], pair[1]);
        });
        if ("java".equals(input.get("login"))) {
            try (OutputStream responseBody = exchange.getResponseBody()) {
                String response = "Hello Java";
                exchange.sendResponseHeaders(200, response.length());
                responseBody.write("Hello Java".getBytes(StandardCharsets.UTF_8));
                responseBody.flush();
            }
        } else {
            throw new InvalidLoginException();
        }
    }
}