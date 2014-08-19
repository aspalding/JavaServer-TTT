package Responses;

import Requests.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsRouteTest {
    Request request;
    OptionsRoute route;

    @Before
    public void setUp() throws Exception{
        request = new Request(
                "OPTIONS /method_options HTTP/1.1\n" +
                        "Host: localhost:4000\n" +
                        "Connection: keep-alive\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );

        route = new OptionsRoute(request);
    }

    @Test
    public void testRespond(){
        assert route.respond() != null;
    }

    @Test
    public void testSadRespond(){
        Request request = new Request(
                "DELETE /method_options HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        OptionsRoute route = new OptionsRoute(request);
        assert route.respond().status == 405;
    }

    @Test
    public void testOptions() throws Exception {
        assert route.options().status == 200;
    }

    @Test
    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().containsValue("GET,HEAD,POST,OPTIONS,PUT");
    }
}