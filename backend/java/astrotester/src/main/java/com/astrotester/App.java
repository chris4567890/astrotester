package com.astrotester;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class App 
{
    public static void main( String[] args )
    {
        try{
            String searchQuerry = "m2";

            String encodedQuery = URLEncoder.encode(searchQuerry,StandardCharsets.UTF_8 );

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/hello?query="+ encodedQuery))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, 
                    HttpResponse.BodyHandlers.ofString());
        
            System.out.println("Response from Python: " + response.body());
        } catch(Exception e){
            System.err.println("Error calling python: "+ e.getMessage());    
            e.printStackTrace();
        }    
    }
}
