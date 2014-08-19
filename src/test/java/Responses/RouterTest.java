package Responses;

import Requests.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouterTest {

    @Test
    public void testRoute() throws Exception {
        Request parameterRequest = new Request(
                "GET /parameters?variable1=dsn HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        assert Router.route(parameterRequest).status != 404;


        Request redirectRequest = new Request(
                "GET /redirect HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Range: bytes=0-4\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        assert Router.route(redirectRequest).status != 404;

        Request optionsRequest = new Request(
                "OPTIONS /method_options HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        assert Router.route(optionsRequest).status != 404;

        Request formRequest = new Request(
                "POST /form HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "param = hi"
        );
        assert Router.route(formRequest).status != 404;

        Request logsRequest = new Request(
                "GET /logs HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        assert Router.route(logsRequest).status != 404;

        Request fileDirectoryRequest = new Request(
                "GET / HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        assert Router.route(fileDirectoryRequest).status != 404;

        Request fourOFour = new Request(
                "GET /C: HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        assert Router.route(fourOFour) != null;
    }

    @Test
    public void testIsFileDirectory() throws Exception {
        assert Router.isFileDirectory(System.getProperty("user.dir") + "/");
    }

    @Test
    public void testGenerateFourOFour() throws Exception {
        assert Router.generateFourOFour().status == 404;
    }
}