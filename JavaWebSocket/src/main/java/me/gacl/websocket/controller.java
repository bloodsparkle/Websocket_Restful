package me.gacl.websocket;


import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 *
 */
@ServerEndpoint("/control")
public class controller extends WebSocketServer {

    private static byte[] bytes;
    String uri1 = "ws://localhost:5001/init";
    URI revModelUri = URI.create(uri1);

    public controller(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public controller(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("端口" + port);
    }

    /**
     * 触发连接事件
     * @param conn
     * @param handshake
     */
    @Override
    public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {
        System.out.println("websocket连接成功:" + conn);
    }

    /**
     * 触发关闭事件
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
     * @param conn
     * @param message
     */
    @Override
    public void onMessage(org.java_websocket.WebSocket conn, String message) {
        //TODO 实现对python端的控制


        conn.send("hahahah");

    }

    /**
     * 触发异常事件
     * @param conn
     * @param message
     */
    @Override
    public void onError(org.java_websocket.WebSocket conn, Exception message) {
        System.out.println("Socket异常:" + message.toString());
    }
    public static void main(String[] args) throws InterruptedException{
        System.out.println("开始启动webSocket");
        int port = 8080; // 端口随便设置，只要不跟现有端口重复就可以了
        controller s =null;
        try {
            s = new controller(port);
            s.start();
        } catch (UnknownHostException e1) {
            System.out.println("启动webSocket失败！");
            e1.printStackTrace();
        }
        System.out.println("启动webSocket成功！");
    }






}

