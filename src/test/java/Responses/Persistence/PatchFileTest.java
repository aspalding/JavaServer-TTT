package Responses.Persistence;

import Requests.Request;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class PatchFileTest {

    @Test
    public void testWrite() throws Exception {
        Request request = new Request(
                "PATCH /temp HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Cache-Control: max-age=0\r\n\r\n" +
                        "body=notnil"
        );
        File temp = File.createTempFile("temp", "");
        request.path = temp.getPath();

        PatchFile.write(request);

        byte[] writtenContent = Files.readAllBytes(Paths.get(temp.getPath()));
        assertEquals("body=notnil\n", new String(writtenContent));
    }

}