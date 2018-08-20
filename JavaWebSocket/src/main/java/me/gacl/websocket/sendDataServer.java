package me.gacl.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import utils.FileUtils;
import utils.WebSocketPool;
import utils.connectWebsocket;

import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 *
 */

public class sendDataServer extends WebSocketServer {

    private static byte[] bytes;
    String uri1 = "ws://localhost:5001/init";
    URI revModelUri = URI.create(uri1);

    public sendDataServer(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public sendDataServer(int port) throws UnknownHostException {
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
//        this.session.getBasicRemote().sendText("this is message from java");

        System.out.println(message);
//		while (message != null){
        switch (message) {
//		if (message.equals("init")){
            case ("init"): {
//                this.session.getBasicRemote().sendText("initial config：");
                connectWebsocket ws = new connectWebsocket(revModelUri);
                ws.connect();

                FileUtils model_data = new FileUtils();
                    File model_file = new File("E:\\API\\data\\train\\initConfig.xml");
                    try {
                    model_data.transportFileToByte(model_file, ws);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}
    }


            /**
             * 触发异常事件
             *
             * @param conn
             * @param message
             */
            @Override
            public void onError (org.java_websocket.WebSocket conn, Exception message){
                System.out.println("Socket异常:" + message.toString());
            }
            public static void main (String[]args) throws InterruptedException {
                System.out.println("开始启动webSocket");
                int port = 8080; // 端口随便设置，只要不跟现有端口重复就可以了
                sendDataServer s = null;
                int port1 = 8081; // 端口随便设置，只要不跟现有端口重复就可以了
                receiveDataServer ws = null;
                try {
                    s = new sendDataServer(port);
                    s.start();
                    ws = new receiveDataServer(port1);
                    ws.start();
                } catch (UnknownHostException e1) {
                    System.out.println("启动webSocket失败！");
                    e1.printStackTrace();
                }
                System.out.println("启动webSocket成功！");
            }
        }
