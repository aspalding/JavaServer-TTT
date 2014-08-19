import java.util.ArrayList;
import java.util.List;

import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.PathType;

public class Test{
    private final static String jrubyhome = "/src/main/lib/";
    private final static String filename = jrubyhome + "web.rb";

    private static ScriptingContainer container;
    private static Object object;

    public Test(){
        container = new ScriptingContainer();
        List<String> loadPaths = new ArrayList<String>();
        loadPaths.add(jrubyhome);
        container.setLoadPaths(loadPaths);
        object = container.runScriptlet(PathType.RELATIVE, filename);
    }

	private String board() {
        return container.callMethod(object, "show_board", String.class);
	}

    private void move() {
        container.callMethod(object, "human_move", "P", "0");
    }

    public static void main (String cat[]) throws Exception{
        Test x = new Test();
        System.out.print(x.board());
        x.move();
        System.out.print(x.board());
    }
}