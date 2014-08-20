package Responses;

import Requests.Request;
import junit.framework.TestCase;

public class TicTacToeRouterTest extends TestCase {

    public void testRoute() throws Exception {
        Request parameterRequest = new Request(
                "GET /game HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        assert new TicTacToeRouter().route(parameterRequest).status != 404;
    }
}