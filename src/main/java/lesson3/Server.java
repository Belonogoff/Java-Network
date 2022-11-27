package lesson3;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    public void runServer() throws IOException {
        final var server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.setExecutor(Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors()));
        server.createContext("/example", new Handler());
        server.start();
    }
}