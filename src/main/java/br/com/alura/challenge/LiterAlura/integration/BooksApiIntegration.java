package br.com.alura.challenge.LiterAlura.integration;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class BooksApiIntegration {

    public String getApiBooksData(String endpoint){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();
        HttpResponse<String> response;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}