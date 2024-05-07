package com.antoncoco.literalura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static java.net.http.HttpResponse.*;

public class GutendexClient {
    private final String API_URL;
    private final HttpClient httpClient;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.API_URL = "https://gutendex.com";
    }

    public String queryToGutendexAPI(String path) {
        System.out.println(API_URL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + path))
                .GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            return response.statusCode() == 200 ? response.body() : null;
        } catch (IOException | InterruptedException e) {
            System.out.println("Error:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
