package Responses.Persistence;

import Requests.Request;

import java.util.LinkedList;

public class Logs {
    public static LinkedList<String> logs = new LinkedList<>();

    public static String formatLog(Request request){
        String[] relative = request.path.split("/");
        return request.method + " /" + relative[relative.length - 1] + " HTTP/1.1";
    }

    public static void deleteLogs() {
        logs = new LinkedList<>();
    }
}
