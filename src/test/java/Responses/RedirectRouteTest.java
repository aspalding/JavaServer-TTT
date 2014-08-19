package Responses;

import Requests.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectRouteTest {
    Request request;
    RedirectRoute route;

    @Before
    public void setUp() throws Exception {
        request = new Request(
                "GET /redirect HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        route = new RedirectRoute(request);
    }

    @Test
    public void testRespond() throws Exception {
        assert route.respond().status == 302;
    }

    @Test
    public void testSadRespond() throws Exception {
        Request request = new Request(
                "POST /redirect HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        RedirectRoute route = new RedirectRoute(request);
        assert route.respond().status == 405;
    }

    @Test
    public void testGet() throws Exception {
        assert route.get() != null;
    }

    @Test
    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().containsValue("http://localhost:4000/");
    }

    @Test
    public void testGenerateBody() throws Exception {
        assert new String(route.generateBody()).equals("This page has moved.");
    }

}