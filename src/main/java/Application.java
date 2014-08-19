import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    public static void main (String cat[]) throws Exception{
        runLoop(cat);
    }

    public static void runLoop(String[] cat) throws Exception{
        ArgsParser.parseArguments(Arrays.asList(cat));

        System.setProperty("user.dir", ArgsParser.root);
        ServerSocket s = new ServerSocket(ArgsParser.port, 8192);

        ExecutorService exe = Executors.newFixedThreadPool(16);

        while(!s.isClosed()){
            exe.submit(new ServerWorker(s.accept()));
        }
    }
}
