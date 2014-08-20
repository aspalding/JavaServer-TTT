package Responses.TicTacToe;

import Requests.Request;
import Responses.Persistence.Games;
import Responses.Response;
import Responses.Route;

import java.util.HashMap;

public class GameRoute implements Route {
    Request request;

    public GameRoute(Request request){
        this.request = request;
    }

    public Response respond(){
        if(request.method.equals("GET"))
            return get();
        else if(request.method.equals("POST"))
            return post();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "");
    }

    public Response get(){
        return new Response(200, "", generateHeaders(), generateBody());
    }

    public Response post(){
        return new Response(200, "", generateHeaders(), generateBody());
    }

    public String generateBody(){
        if(request.method.equals("POST")){
            Games.games.get(0).move(isolateLocation(request.body));
        }

        String content = Games.games.get(0).board() + "<form action=\"/game\" method=\"post\">" +
                "<input type=\"text\" name=\"location\">" +
                "<button type=\"submit\">Play!</button>" +
                "</form>";

        return content;
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>() {
            {
                put("Content-Type", "text/html");
            }
        };
    }

    public String isolateLocation(String parameter){
        return parameter.substring(9);
    }
}
