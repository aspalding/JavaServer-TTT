package Responses;

import Requests.Request;

import java.util.HashMap;

public class RedirectRoute implements Route {
    Request request;

    public RedirectRoute(Request request) {
        this.request = request;
    }

    public Response respond() {
        if (request.method.equals("GET"))
            return get();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "");
    }

    public Response get(){
        return new Response(302, "Found", generateHeaders(), generateBody());
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>(){
            {
                put("Location", "http://" + request.headers.get("Host") + "/");
            }
        };
    }

    public byte[] generateBody() {
        return "This page has moved.".getBytes();
    }
}
