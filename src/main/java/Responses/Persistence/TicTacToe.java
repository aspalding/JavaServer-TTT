package Responses.Persistence;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private final static String jrubyhome = "/src/main/lib/";
    private final static String filename = jrubyhome + "web.rb";

    public ScriptingContainer container;
    public Object object;

    public TicTacToe(){
        container = new ScriptingContainer();
        List<String> loadPaths = new ArrayList<String>();
        loadPaths.add(jrubyhome);
        container.setLoadPaths(loadPaths);
        object = container.runScriptlet(PathType.RELATIVE, filename);
    }

    public String board() {
        return container.callMethod(object, "show_board", String.class);
    }

    public void move(String location) {
        container.callMethod(object, "human_move", "x", location);
    }
}
