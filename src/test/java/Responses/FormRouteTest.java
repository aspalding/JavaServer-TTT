package Responses;

import Requests.Request;
import Responses.Persistence.Form;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormRouteTest {
    Request request;
    FormRoute route;

    @Before
    public void setUp() throws Exception {
        request = new Request(
                "POST /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        route = new FormRoute(request);
    }

    @After
    public void tearDown() throws Exception{
        Form.deleteContent();
    }

    @Test
    public void testRespond() throws Exception {
        assert this.route.respond().status == 200;

        Request request = new Request(
                "PUT /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        FormRoute route = new FormRoute(request);
        assert route.respond().status == 200;

        request = new Request(
                "GET /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        route = new FormRoute(request);
        assert route.respond().status == 200;

        request = new Request(
                "DELETE /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        route = new FormRoute(request);
        assert route.respond().status == 200;
    }

    @Test
    public void testSadRespond() throws Exception {
        Request request = new Request(
                "OPTIONS /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        FormRoute route = new FormRoute(request);
        assert route.respond().status == 405;
    }

    @Test
    public void testGet() throws Exception {
        assert route.get() != null;
    }

    @Test
    public void testPut() throws Exception {
        assert route.put() != null;
        assertEquals("body = notnil", Form.content);
    }

    @Test
    public void testPost() throws Exception {
        assert route.post() != null;
    }

    @Test
    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().containsValue("text/html");
    }

    @Test
    public void testAddSpaces(){
        String parameter = "p=x";
        assertEquals("p = x", route.addSpaces(parameter));
    }
}