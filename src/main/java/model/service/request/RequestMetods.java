package model.service.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RequestMetods {

    public RequestMetods() {
    }
//TODO БАХНУТЬ КОНСТАНТЫ ПО ТИПУ : /LOCALHOST:8002 , /FIGHT

    public HttpResponse<String> sendRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(url)))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
//TODO ЕБАНУТЬ ИЗ БОЛЬШОГО В МАЛЕНЬКИЕ МЕТОДЫ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public byte[] postRequestToServer(String muUrl, String params) {
        byte[] data = null;
        InputStream is = null;

        try {
            URL url = new URL(muUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            OutputStream os = conn.getOutputStream();
            data = params.getBytes(StandardCharsets.UTF_8);
            os.write(data);
            data = null;

            conn.connect();
            int responseCode = conn.getResponseCode();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            is = conn.getInputStream();

            byte[] buffer = new byte[81922];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            data = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            System.out.println("Catch: " + e.getMessage());
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (Exception ex) {
                System.out.println("Catch: " + ex.getMessage());
            }
        }
        return data;
    }

}
