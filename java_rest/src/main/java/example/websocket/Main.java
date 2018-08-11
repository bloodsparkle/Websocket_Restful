package example.websocket;

import com.sun.xml.xsom.impl.util.Uri;
import org.java_websocket.WebSocketImpl;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class Main {
    private static final URI sendDataUri1 = URI.create("https:localhost:5001/websocket");
    private static final URI sendDataUri2 = URI.create("https:localhost:5001/data");

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        System.out.println("开始启动webSocket");
        WebSocketImpl.DEBUG = false;
        int port = 8080; // 端口随便设置，只要不跟现有端口重复就可以了
        WebSocket s =null;
        try {
            s = new WebSocket(port);
            s.start();
        } catch (UnknownHostException e1) {
            System.out.println("启动webSocket失败！");
            e1.printStackTrace();
        }
        System.out.println("启动webSocket成功！");

        connectWebsocket ws = new connectWebsocket(sendDataUri1);
        File file = new File("E:\\API\\java\\data\\megadata.xml");
        FileUtils transFile=new FileUtils();
        transFile.transportFileToByte(file,ws);

    }
}
