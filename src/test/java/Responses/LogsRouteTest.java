package Responses;

import Requests.Request;
import Responses.Persistence.Logs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogsRouteTest {
    Request authorizedRequest, invalidRequest, unauthorizedRequest, notAllowedRequest;
    LogsRoute authorizedRoute, invalidRoute, unauthorizedRoute, notAllowedRoute;

    @Before
    public void setUp() throws Exception {
        authorizedRequest = new Request(
                "GET /logs HTTP/1.1\n" +
                        "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                        "Connection: close\n" +
                        "Host: localhost:5000"
        );
        authorizedRoute = new LogsRoute(authorizedRequest);

        invalidRequest = new Request(
                "GET /logs HTTP/1.1\n" +
                        "Authorization: Basic YWRtaW46cGFzc3dvcmQ=\n" +
                        "Connection: close\n" +
                        "Host: localhost:5000"
        );
        invalidRoute = new LogsRoute(invalidRequest);

        unauthorizedRequest = new Request(
                "GET /logs HTTP/1.1\n" +
                        "Connection: close\n" +
                        "Host: localhost:5000"
        );

        unauthorizedRoute = new LogsRoute(unauthorizedRequest);

        notAllowedRequest = new Request(
                "POST /logs HTTP/1.1\n" +
                        "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                        "Connection: close\n" +
                        "Host: localhost:5000"
        );

        notAllowedRoute = new LogsRoute(notAllowedRequest);
    }

    @After
    public void tearDown() throws Exception {
        Logs.deleteLogs();
    }

    @Test
    public void testRespond() throws Exception {
        assert notAllowedRoute.respond().status == 405;
        assert authorizedRoute.respond().status == 200;
        assert invalidRoute.respond().status == 401;
        assert unauthorizedRoute.respond().status == 401;
    }

    @Test
    public void testGet() throws Exception {
        assert unauthorizedRoute.get() != null;
        assert authorizedRoute.get() != null;
    }

    @Test
    public void testGenerateBody() throws Exception {
        Router.route(unauthorizedRequest);
        assert authorizedRoute.generateBody().length > 0;
    }

    @Test
    public void testDecodeString() throws Exception{
        assertEquals("admin:hunter2", authorizedRoute.decodeString("Basic YWRtaW46aHVudGVyMg=="));
    }

    @Test
    public void testIsValidUser() throws Exception{
        assert authorizedRoute.isValidUser("admin:hunter2");
    }
}