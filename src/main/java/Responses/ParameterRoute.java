package Responses;

import Requests.Request;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ParameterRoute implements Route {
    static final String ENCODING = "UTF-8";
    Request request;
    String parameters;

    public ParameterRoute(Request request){
        this.request = request;
        this.parameters = request.path;
    }

    public Response respond() {
        if (request.method.equals("GET"))
            return get();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "");
    }

    public Response get() {
        return new Response(200, "OK", generateHeaders(), generateBody());
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>(){
            {
                put("Content Type", "text/html");
            }
        };
    }

    public byte[] generateBody(){
        String responseBody = "";

        List<String> params = trimAndSplit(parameters);
        for(int index = 0; index < params.size(); index++){
            String item = params.get(index);
            if(item.contains("%"))
                responseBody += decodeParameter(item, ENCODING);
            else
                responseBody += params.get(index);
            if(index%2 == 0){
                responseBody += " = ";
            }
        }

        return responseBody.getBytes();
    }

    public String decodeParameter(String parameter, String encoding) {
        try {
            return URLDecoder.decode(parameter, encoding);
        } catch(Exception e) {
            return "";
        }
    }

    public ArrayList<String> trimAndSplit(String parameters){
        ArrayList<String> split = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(parameters);
        tokenizer.nextToken("?"); //Throw away path.

        do {
            String variable = tokenizer.nextToken("=").substring(1);
            split.add(variable);
            String argument = tokenizer.nextToken("&").substring(1);
            split.add(argument);
        } while(tokenizer.hasMoreTokens());

        return split;
    }
}
