package main.java.com.astrotester.config;

import com.astrotester.restSecurity.controllers.QuerryController;
import static io.javalin.apibuilder.ApiBuilder.*;
public class Routes {
    private static QuerryController querryController ;
    
    public static EndpointGroup getRoutes(boolean isTesting){
        querryController = QuerryController.getInstance(isTesting);

        return () -> {
            path("/",() ->{
                get("/search",QuerryController.search());
            });
        };

    }

}
