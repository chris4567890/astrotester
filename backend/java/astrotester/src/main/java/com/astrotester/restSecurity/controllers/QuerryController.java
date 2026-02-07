package com.astrotester.restSecurity.controllers;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import io.javalin.http.Context;

import io.javalin.http.Handler;
import com.astrotester.restSecurity.exceptions.ApiException;

public class QuerryController {

    private static QuerryController instance;

    private QuerryController(){
        //private constructor for singleton
    }

    public static QuerryController getInstance(boolean isTest){
        if (instance == null){
            instance = new QuerryController();
        }
        return instance;
    }

    public static Handler search() {
        return ctx -> {
            try {
                String query = ctx.queryParam("q");
                if (query == null || query.isBlank()) {
                    ctx.status(400);
                    throw new ApiException(400, "missing query parameter");
                }
                
                String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:5000/search?query=" + encodedQuery))
                        .GET()
                        .build();
                
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                ctx.json(response.body());

            } catch (Exception e) {
                ctx.status(500);
                throw new ApiException(500, "error calling python service: " + e.getMessage());
            }
        }; 
    }
}

