package net;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;

public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Listening...");
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        server.createContext("/hello", SimpleHttpServer::handleHelloRequest);
        server.createContext("/people", SimpleHttpServer::handlePeopleRequest);
        server.start();
    }

    private static void handleHelloRequest(HttpExchange exchange) throws IOException {
        String response = "Hello, world!";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handlePeopleRequest(HttpExchange exchange) throws IOException {
        String response;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5435/localdb",
                    "fajri",
                    "12345678"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name")).append("\n");
            }
            response = stringBuilder.toString();
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
