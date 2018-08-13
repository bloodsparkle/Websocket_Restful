package utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

// TODO 按照不同的URI进行websocket连接
public abstract class connectWebsocket extends WebSocketClient {
    static WebSocketClient client;
    private static final URI sendDataUri1 = URI.create("https:localhost:5001/websocket");
    private static final URI sendDataUri2 = URI.create("https:localhost:5001/data");

    public connectWebsocket(URI serverURI) throws URISyntaxException, InterruptedException{
        super(serverURI);
    }
        WebSocketClient ws= new WebSocketClient(new URI("ws://https:localhost:5001/websocket"), new Draft_17()){
        @Override
        public void onOpen(ServerHandshake serverHandshake) {

        }

        @Override
        public void onMessage(String s) {

        }

        @Override
        public void onClose(int i, String s, boolean b) {

        }

        @Override
        public void onError(Exception e) {

        }
    };


}
