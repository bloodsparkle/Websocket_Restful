package jetty.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SendData extends WebSocketAdapter
{
    String uri1 = "ws://localhost:5001/init";
    URI revModelUri = URI.create(uri1);

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("this is SendData" + sess);
    }

    @Override
public void onWebSocketText(String message)
{
    super.onWebSocketText(message);
    System.out.println("Received TEXT message: " + message);
    System.out.println(message);


		if (message.equals("init")){

//                this.session.getBasicRemote().sendText("initial config：");
            connWebsocket ws = new connWebsocket();
            ws.connect(revModelUri);
            Session wss = this.getSession();

            FileUtils model_data = new FileUtils();
            File model_file = new File("E:\\API\\data\\train\\initConfig.xml");
            try {
                model_data.transportFileToByte(model_file,wss);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }}


    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}
