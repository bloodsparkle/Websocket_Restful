package example.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class FileUtils {

     static WebSocketClient client;
    private static byte[] bytes;
    /*定义构造函数，实现websocket连接
     * */
    /**
     * 将File转换为Byte
     * @param file
     * @param client
     * @return
     */
    public static boolean transportFileToByte(File file, WebSocketClient client)  {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 开始传输文件
            byte[] b = new byte[4096];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
                client.send(b);
                Thread.sleep(30);
                bos.flush();
            }
            System.out.println();
            System.out.println("======== 进行连接并传输文件 ========");

            System.out.println("======== 文件传输成功 ========");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



        /**
         * 将Byte 转换为File
         * @return
         */
    public static void transportByteToFile()  {
        try {
            ByteArrayInputStream byt = new ByteArrayInputStream(bytes);
            File directory = new File("D:\\FTCache");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file_r = new File(directory.getAbsolutePath() + File.separatorChar + "data.xml");
            FileOutputStream fos = new FileOutputStream(file_r);

            // 开始接收文件
            byte[] bytes_r = new byte[1024];
            int length = 0;
            while ((length = byt.read(bytes_r, 0, bytes_r.length)) != -1) {
                fos.write(bytes_r, 0, length);
                fos.flush();
            }
            System.out.println("======== 文件接收成功 []  ========");
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}



