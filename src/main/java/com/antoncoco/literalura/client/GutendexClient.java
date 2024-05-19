package com.antoncoco.literalura.client;

import com.antoncoco.literalura.exceptions.HTTPStatusCode4XXException;
import com.antoncoco.literalura.exceptions.HTTPStatusCode5XXException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.net.http.HttpResponse.*;

public class GutendexClient {
    private final String API_URL;
    private final HttpClient httpClient;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.API_URL = "https://gutendex.com";
    }

    public Optional<String> queryToGutendexAPI(String path) throws HTTPStatusCode5XXException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + path))
                .GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return Optional.ofNullable(response.body());
            }
            if (response.statusCode() >= 400 && response.statusCode() < 500) {
                throw new HTTPStatusCode4XXException("Error("+response.statusCode()+"):" +
                        " la consulta a Gutendex API no ha podido ser realizada.");
            } else {
                throw new HTTPStatusCode5XXException("Error("+response.statusCode()+"):" +
                        " ¡Gutendex API ha fallado! Intente hacer búsquedas más tarde.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Ha ocurrido un error en el proceso de búsqueda: " + e.getMessage());
        }
        return Optional.empty();
    }
}
