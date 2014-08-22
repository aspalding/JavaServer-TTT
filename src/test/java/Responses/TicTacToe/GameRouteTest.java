package Responses.TicTacToe;

import Requests.Request;
import Responses.Persistence.Games;
import Responses.Persistence.TicTacToe;
import junit.framework.TestCase;
import org.junit.Before;

public class GameRouteTest extends TestCase {
    Request request;
    GameRoute route;

    @Before
    public void setUp() throws Exception {
        request = new Request(
                "GET /game HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "location=1"
        );
        Games.games.add(new TicTacToe());
        route = new GameRoute(request);
    }

    public void testRespond() throws Exception {
        assert route.respond() != null;
    }

    public void testGet() throws Exception {
        assert route.get().status == 200;
    }

    public void testPost() throws Exception {
        assert route.post().status == 200;
    }

    public void testGenerateBody() throws Exception {
        assert route.generateBody().length() > 1;
    }

    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().get("Content-Type").equals("text/html");
    }

    public void testIsolateLocation() {
        assert route.isolateLocation("location=1").equals("1");
    }
}