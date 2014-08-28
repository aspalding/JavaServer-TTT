import Requests.Request;
import Responses.Response;
import Responses.Route;

import java.util.HashMap;

public class GameRoute implements Route {
    Request request;
    TicTacToe game;
    boolean valid;

    public GameRoute(Request request){
        this.request = request;
        this.game = Games.games.get(0);
    }

    public Response respond(){
        switch (request.method) {
            case "GET":
                return get();
            case "POST":
                return post();
            default:
                return new Response(405, "Method Not Allowed", new HashMap<>(), "");
        }
    }

    public Response get(){
        return new Response(200, "", generateHeaders(), generateBody());
    }

    public Response post(){
        valid = game.isValid(isolateLocation(request.body));

        if(!game.isGameOver() && valid)
            game.move(isolateLocation(request.body));
        if(!game.isGameOver() && valid)
            game.aiMove();

        return new Response(200, "", generateHeaders(), generateBody());
    }

    public String generateBody(){
        String content;
        String form = "<form action=\"/game\" method=\"post\">" +
                "<input type=\"text\" name=\"location\">" +
                "<button type=\"submit\">Play!</button>" +
                "</form>";

        if(game.isGameOver()) {
            content = game.board() + game.overMessage();
        } else if(!valid && game.isStarted()) {
            content = game.board() + "\nMove is not valid.\n" + form;
        } else {
            content = game.board() + form;
        }

        return content;
    }

    public HashMap<String, String> generateHeaders(){
        HashMap<String, String> map = new HashMap<String, String>() {
            {
                put("Content-Type", "text/html");
            }
        };

        if(request.method.equals("GET")) {
            map.put("Set-Cookie", "index=" + (Games.games.size() - 1));
            Games.games.add(new TicTacToe());
        }

        return map;
    }

    public String isolateLocation(String parameter){
        return parameter.substring(9);
    }
}
