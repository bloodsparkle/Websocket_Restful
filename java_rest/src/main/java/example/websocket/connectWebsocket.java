package example.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class connectWebsocket extends WebSocketClient {
    static WebSocketClient client;
    private static final URI sendDataUri1 = URI.create("https:localhost:5001/websocket");
    private static final URI sendDataUri2 = URI.create("https:localhost:5001/data");

//    public connectWebsocket(String sendDataUri1) throws URISyntaxException {
//        client = new WebSocketClient(new URI(sendDataUri1), new Draft_17()) {
//            @Override
//            public void onOpen(ServerHandshake serverHandshake) {
//                client.send("WebSockets 连接成功");
//            }
//
//            @Override
//            public void onMessage(String s) {
//                System.out.println("接收到消息：" + s);
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//
//            }
//
//            @Override
//            public void onError(Exception ex) {
//
//            }
//        };
//
//    }



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


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        client.send("WebSockets 连接成功");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息：" + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
