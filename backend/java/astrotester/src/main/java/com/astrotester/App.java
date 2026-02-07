package com.astrotester;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.astrotester.config.ApplicationConfig;

import com.astrotester.config.Routes;

public class App 
{
    public static void main( String[] args )
    {
        boolean isTesting = false;

        ApplicationConfig app = ApplicationConfig.getInstance()
            .initiateServer()
            .configureCors()
            .setRoutes(Routes.getRoutes(isTesting))
            .setExceptionHandling()
            .startServer(7070);
            
            
    }    
}
