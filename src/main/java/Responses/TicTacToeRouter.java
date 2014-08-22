package Responses;

import Requests.Request;
import Responses.TicTacToe.GameRoute;

public class TicTacToeRouter implements Router {
    public Response route(Request request) {
        if(request.path.contains("game"))
            return new GameRoute(request).respond();
        else
            return new CobRouter().route(request);
    }
}
