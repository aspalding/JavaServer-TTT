package Responses.Persistence;

import Requests.Request;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogsTest {

    @After
    public void tearDown() throws Exception {
        Logs.deleteLogs();
    }

    @Test
    public void testFormatLog() throws Exception {
        Request request = new Request(
                "GET /temp HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );

        assertEquals("GET /temp HTTP/1.1", Logs.formatLog(request));
    }

    @Test
    public void testLogs(){
        Request request = new Request(
                "GET /temp HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        Request request1 = new Request(
                "GET /temp HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );
        Request request2 = new Request(
                "GET /temp HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n"
        );

        Logs.logs.add(Logs.formatLog(request));
        Logs.logs.add(Logs.formatLog(request1));
        Logs.logs.add(Logs.formatLog(request2));

        assertEquals(3, Logs.logs.size());
        assert Logs.logs.get(0).equals("GET /temp HTTP/1.1");
    }

    @Test
    public void testDeleteLogs() {
        Logs.deleteLogs();
        assert Logs.logs.size() == 0;
    }
}