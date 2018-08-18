package utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class connectWebsocket extends WebSocketClient {

//    public connectWebsocket(URI serverUri , Draft draft ) {
//        super( serverUri, draft );
//    }

    public connectWebsocket(URI serverURI ) {
        super( serverURI );
    }

//    public connectWebsocket() {
//        super(serverUri,  httpHeaders);
//    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
//        send("connection success");
        System.out.println( "opened connection" );
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
//        System.out.println( "received: " + message );

    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

//    public static void main( String[] args ) throws URISyntaxException, IOException, InterruptedException {
//        connectWebsocket c = new connectWebsocket( new URI( "ws://localhost:5001/init" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
//        c.connect();
//        FileUtils model_data = new FileUtils();
//        File model_file = new File("E:\\API\\data\\train\\initConfig.xml") ;
//        try {
//            model_data.transportFileToByte(model_file,c);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

}