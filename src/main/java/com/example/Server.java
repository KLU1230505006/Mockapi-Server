import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

public class Server {
    private static final int PORT = 8000; //
    private static final String API_URL = "https://67532bf3f3754fcea7bb0a7d.mockapi.io/WeatherData";

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/data", new DataHandler()); // "/data" endpointi

            System.out.println("Server çalışıyor: http://localhost:" + PORT);
            server.setExecutor(null); // Default executor
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getResponseHeaders();


            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type,Authorization");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {

                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    String jsonResponse = fetchDataFromApi(API_URL);
                    headers.add("Content-Type", "application/json");


                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(jsonResponse.getBytes());
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    String error = "API'den veri çekilirken bir hata oluştu.";
                    exchange.sendResponseHeaders(500, error.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();
                }
            } else {

                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }

        private String fetchDataFromApi(String apiUrl) throws Exception {
            StringBuilder result = new StringBuilder();
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        }
    }
}