import Requests.Request;
import Responses.Response;
import Responses.Router;
import Responses.SimpleRouter;

public class TicTacToeRouter implements Router {
    public Response route(Request request) {
        if(request.path.contains("game"))
            return new GameRoute(request).respond();
        else
            return new SimpleRouter().route(request);
    }
}
