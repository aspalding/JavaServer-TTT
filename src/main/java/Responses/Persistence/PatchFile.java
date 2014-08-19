package Responses.Persistence;

import Requests.Request;

import java.io.FileWriter;

public class PatchFile {
    public static final boolean APPEND = true;

    public static void write(Request request){
        try {
            FileWriter fw = new FileWriter(request.path, APPEND);
            fw.write(request.body + "\n");
            fw.close();
        } catch(Exception e) {
            //Intentionally blank.
        }
    }
}
