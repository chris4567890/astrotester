package main.java.com.astrotester.config;

import com.astrotester.restSecurity.controllers.QuerryController;

public class Routes {
    private static QuerryController querryController ;
    
    public static EndpointGroup getRoutes(boolean isTesting){
        querryController = QuerryController.getInstance(isTesting);
    }

}
