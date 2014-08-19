package Responses.Persistence;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormTest {

    @Test
    public void testDeleteContent() throws Exception {
        Form.content = "hello";
        assert Form.content == "hello";
        Form.deleteContent();
        assert Form.content == "";
    }
}