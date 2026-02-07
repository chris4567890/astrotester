package com.astrotester.config;

import io.javalin.*;
import io.javalin.apibuilder.EndpointGroup;

import com.astrotester.utils.Logger;

import io.javalin.http.HttpStatus;

import com.astrotester.restSecurity.exceptions.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
        app = Javalin.create(config -> {
            config.http.defaultContentType="application/json";
            config.routing.contextPath="/api";
            config.plugins.enableDevLogging();

        }); 
        return appConfig; 
    };

    public ApplicationConfig setRoutes(EndpointGroup routes){
        app.routes(routes);
        return appConfig;
    }

    public ApplicationConfig startServer(int port){
        app.start(port);
        return appConfig;
    }

    public ApplicationConfig setExceptionHandling(){
        app.exception(ApiException.class,(e,ctx) ->{
            ObjectNode exception = Logger.log(e.getStatus(),e.getMessage(),ctx.ip());
            ctx.json(exception).status(e.getStatus());
        });
        app.exception(NumberFormatException.class, (e,ctx)->{
            ObjectNode exception = Logger.log(HttpStatus.forStatus(400),e.getMessage(),ctx.ip());
            ctx.json(exception).status(HttpStatus.forStatus(400));
        });
        app.exception(Exception.class,(e,ctx)->{
            ObjectNode exception = Logger.log(HttpStatus.forStatus(500),e.getMessage(),ctx.ip());
            ctx.json(exception).status(HttpStatus.forStatus(500));
        });
        return appConfig;
    }        

    public ApplicationConfig closeServer(){
        app.close();
        return appConfig;
    }    

    public ApplicationConfig configureCors(){
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type");
        });

        app.options("/*", ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type");
        });
        return appConfig;
    }

}
