package Responses;

import Requests.Request;
import Responses.Persistence.Logs;

import java.util.Base64;
import java.util.HashMap;

public class LogsRoute implements Route {
    Request request;
    String validUser;

    public LogsRoute(Request request){
        this.request = request;
        validUser = "admin:hunter2";
    }

    public Response respond(){
        if(request.method.equals("GET"))
            return get();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "");
    }

    public Response get(){
        if(!request.headers.containsKey("Authorization"))
            return new Response(401, "Unauthorized", new HashMap<>(), "Authentication required");
        else if(!isValidUser(decodeString(request.headers.get("Authorization"))))
            return new Response(401, "Unauthorized", new HashMap<>(), "Authentication required");
        else
            return new Response(200, "OK", new HashMap<>(), generateBody());
    }

    public String decodeString(String user){
        user = user.substring(6);
        return new String(Base64.getDecoder().decode(user));
    }

    public boolean isValidUser(String user){
        return user.equals(validUser);
    }

    public byte[] generateBody(){
        String result = "";

        for(String log: Logs.logs)
            result += log + "\n";

        return result.getBytes();
    }
}
