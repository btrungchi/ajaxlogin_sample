package app;

import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;
import java.nio.file.Paths;

/**
 * Main class
 *
 */
public class App 
{
    public static final String STATIC_PATH = "/public";
    public static final String ROUTE_LOGIN = "/login";

    public static Route loginPostAction = (Request request, Response response) -> {
        Map<String, String> loginResult = new HashMap<String, String>();
        
        // Get query data
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        if (email == null || password == null) {
            return Utils.sendJsonContent(request, response, loginResult);
        }
        
        loginResult.put("email", email);

        // Check valid account
        if (email.equals("test@abc.com") && password.equals("test")) {
            loginResult.put("loginSuccess", "Yes");
        } else {
            loginResult.put("loginSuccess", "No");
        }

        return Utils.sendJsonContent(request, response, loginResult);
    };
    public static void main( String[] args )
    {   
        String currDirectory = Paths.get(".").toAbsolutePath().normalize().toString();

        externalStaticFileLocation(currDirectory + App.STATIC_PATH);
        setIpAddress("localhost");
        setPort(8080);        

        post(App.ROUTE_LOGIN, App.loginPostAction);
    }    
}
