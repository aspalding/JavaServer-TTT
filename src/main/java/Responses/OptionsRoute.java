package Responses;

import Requests.Request;

import java.util.HashMap;

public class OptionsRoute implements Route {
    Request request;

    public OptionsRoute(Request request) {
        this.request = request;
    }

    public Response respond(){
        if (!request.method.equals("DELETE"))
            return options();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "");
    }

    public Response options(){
        return new Response(200, "OK", generateHeaders(), "");
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>() {
            {
                put("Content-Type", "text/html");
                put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
            }
        };
    }

}
