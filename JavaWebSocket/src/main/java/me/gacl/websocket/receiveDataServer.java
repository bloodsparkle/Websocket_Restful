package me.gacl.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import utils.FileUtils;
import utils.WebSocketPool;
import utils.connectWebsocket;

import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 *
 */

public class receiveDataServer extends WebSocketServer {

    private static byte[] bytes;
    String uri1 = "ws://localhost:5001/init";
    URI revModelUri = URI.create(uri1);

    public receiveDataServer(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public receiveDataServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("端口" + port);
    }

    /**
     * 触发连接事件
     *
     * @param conn
     * @param handshake
     */
    @Override
    public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {
        System.out.println("websocket连接成功:" + conn);
    }

    /**
     * 触发关闭事件
     *
     * @param conn
     * @param message
     * @param reason
     * @param remote
     */
    @Override
    public void onClose(org.java_websocket.WebSocket conn, int message, String reason, boolean remote) {
        System.out.println("连接关闭！");
    }

    /**
     * 客户端发送消息到服务器是触发事件
     *
     * @param conn
     * @param message
     */
    @Override
    public void onMessage(org.java_websocket.WebSocket conn, String message) {
        //TODO 实现对python端的控制
        conn.send("hahahah");
        String uri = "ws://localhost:5001/result";
        URI revResultUri = URI.create(uri);

        System.out.println(message);
        //接收反馈训练结果
        if (message.equals("result")) {
            System.out.println("require python result");
//            this.session.getBasicRemote().sendText("require result");
            connectWebsocket wss = new connectWebsocket( revResultUri);
            wss.connect();
//            wss.send("haha");
//            this.session.getBasicRemote().sendText("require result");
            FileUtils rev_data = new FileUtils();
            rev_data.transportByteToFile(message, "E:\\API\\java\\data\\result.xml");

        }

    }

    /**
     * 触发异常事件
     *
     * @param conn
     * @param message
     */
    @Override
    public void onError(org.java_websocket.WebSocket conn, Exception message) {
        System.out.println("Socket异常:" + message.toString());
    }
}