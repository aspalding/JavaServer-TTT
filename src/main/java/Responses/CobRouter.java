package Responses;

import Requests.Request;
import Responses.Persistence.Logs;
import Responses.Cob.*;

import java.io.File;
import java.util.HashMap;

public class CobRouter implements Router{

    public Response route(Request request){
        Response response;

        Logs.logs.add(Logs.formatLog(request));

        if(request.path.contains("parameters")){
           response = new ParameterRoute(request).respond();
        } else if(request.path.contains("redirect")) {
            response = new RedirectRoute(request).respond();
        } else if(request.path.contains("method_options")){
            response = new OptionsRoute(request).respond();
        } else if(request.path.contains("form")) {
            response = new FormRoute(request).respond();
        } else if(request.path.contains("logs")){
            response = new LogsRoute(request).respond();
        } else if(isFileDirectory(request.path)){
            response = new FileDirectoryRoute(request).respond();
        } else {
                response = generateFourOFour();
        }

        return response;
    }

    public static boolean isFileDirectory(String path){
        return new File(path).exists();
    }

    public static Response generateFourOFour(){
        return new Response(404, "Not Found", new HashMap<>(), "Page Not Found.");
    }

}
