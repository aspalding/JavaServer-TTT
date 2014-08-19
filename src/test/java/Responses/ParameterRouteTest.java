package Responses;

import Requests.Request;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ParameterRouteTest {
    ParameterRoute route;
    Request request;

    @Before
    public void setUp() throws Exception{
        request = new Request(
                "GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );

        route = new ParameterRoute(request);
    }

    @Test
    public void testRespond() throws Exception {
        assert route.get() != null;
    }

    @Test
    public void testSadRespond() throws Exception {
        Request request = new Request(
                "PUT /parameters?variable_1=Operators%20%3C%2C%2%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\r\n" +
                "Host: localhost:4000\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n\r\n"
        );
        ParameterRoute route = new ParameterRoute(request);
        assert route.respond().status == 405;
    }


    @Test
    public void testGet() throws Exception {
        assert route.get().status == 200;
    }

    @Test
    public void testGenerateHeaders() throws Exception {
        assert route.generateHeaders().containsValue("text/html");
    }

    @Test
    public void testGenerateBody() throws Exception {
        String expect = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";
        assertEquals(expect, new String(route.generateBody()));
    }

    @Test
    public void testDecodeParameter() throws Exception {
        String parameter = "Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        String expect = "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
        assertEquals(expect, route.decodeParameter(parameter, "UTF-8"));
    }

    @Test
    public void testSadDecodeParameter() throws Exception {
        String parameter = "Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        assertEquals("", route.decodeParameter(parameter, "jsdafs;dlfja"));
    }

    @Test
    public void testTrimAndSplit() throws Exception {
        ArrayList<String> split = new ArrayList<String>();
        split.add("variable_1");
        split.add("Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20" +
                "%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20" +
                "%5D%3A%20%22is%20that%20all%22%3F");
        split.add("variable_2");
        split.add("stuff");

        assertEquals(split, route.trimAndSplit(request.path));
    }
}