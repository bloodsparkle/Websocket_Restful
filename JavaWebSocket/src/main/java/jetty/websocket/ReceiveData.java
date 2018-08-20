package jetty.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.net.URI;

public class ReceiveData extends WebSocketAdapter
{
    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("this is receiveData" + sess);
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message: " + message);
        String uri = "ws://localhost:5001/result";
        URI revResultUri = URI.create(uri);

        System.out.println(message);
        //接收反馈训练结果
        if (message.equals("result")) {
            System.out.println("require python result");
//            this.session.getBasicRemote().sendText("require result");
            connWebsocket wss = new connWebsocket( );
            wss.connect(revResultUri);
//            wss.send("haha");
//            this.session.getBasicRemote().sendText("require result");
            FileUtils rev_data = new FileUtils();
            rev_data.transportByteToFile(message, "E:\\API\\java\\data\\result.xml");

        }
    }

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
