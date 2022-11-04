package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        out(url);
    }

    private static void out(String url) throws IOException {
        URL address = new URL(url);
        URLConnection connection = address.openConnection();
        System.out.println("Расмер в байтах = " + connection.getContentLength());
        System.out.println("Тип: " + connection.getContentType());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}