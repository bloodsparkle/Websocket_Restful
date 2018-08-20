package jetty.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class connWebsocket extends WebSocketClient {

    private Session session;
    private CountDownLatch closeLatch;

    public void connect(URI serverURI) {
        WebSocketClient client = new WebSocketClient();
        try
        {
            try
            {
                client.start();
                // The socket that receives events
                EventSocket socket = new EventSocket();
                // Attempt Connect
                Future<Session> fut = client.connect(socket,serverURI);
                // Wait for Connect
                Session session = fut.get();
                // Send a message
                session.getRemote().sendString("connect success");
                // Close session
//                session.close();
            }
            finally
            {
                client.stop();
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }


}

