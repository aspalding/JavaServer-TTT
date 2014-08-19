import Requests.*;
import Responses.*;

import java.net.Socket;
import java.util.NoSuchElementException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerWorker implements Runnable {
    private static final Logger reqRespLog = Logger.getLogger( ServerWorker.class.getName() );
    public Request clientRequest;
    public Socket connection;
    public String request;

    public ServerWorker(Socket connection) throws Exception{
        this.connection = connection;

        try {
            this.request = SocketIO.readFullRequest(connection.getInputStream());
            this.clientRequest = new Request(request);
        } catch(NoSuchElementException e) {
            this.clientRequest = null;
        }
    }

    public void run() {
        try {
            while (!connection.isClosed()) {
                if(clientRequest == null)
                    connection.close();
                else {
                    Response response = Router.route(clientRequest);

                    SocketIO.writeResponse(
                            response.statusAndHeadersToString(),
                            response.body,
                            connection.getOutputStream()
                    );

                    //reqRespLog.log(Level.INFO, request);
                    //reqRespLog.log(Level.INFO, response.toString());


                    connection.close();
                }
            }
        } catch(Exception e){
            //Intentionally empty.
        }
    }
}
