package com.astrotester.config;

import io.javalin.*;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ApplicationConfig {
    
    private static ObjectMapper jsonMapper = new ObjectMapper ();
    private static ApplicationConfig appConfig;
    
    private Javalin app;

    private ApplicationConfig (){}

    public static ApplicationConfig getInstance(){
        if(appConfig == null){
            appConfig = new ApplicationConfig();        
        }
        return appConfig;
    }

    public ApplicationConfig initiateServer(){
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        String separator = System.getProperty("file.separator");
        app =  
    };

}
