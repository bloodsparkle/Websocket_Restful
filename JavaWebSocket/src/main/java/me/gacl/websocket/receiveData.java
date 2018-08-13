package me.gacl.websocket;

import org.dom4j.DocumentException;
import utils.FileUtils;
import xmlProcess.DatasetProcessor;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/trainData")
public class receiveData extends modelData {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<modelData> webSocketSet = new CopyOnWriteArraySet<modelData>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
//        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！" );
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
//        subOnlineCount();           //在线数减1
        System.out.println("连接关闭！");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    //TODO 完善数据接收的步骤
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自python端的数据");

        //1 接收数据
        FileUtils rev_data= new FileUtils();
        rev_data.transportByteToFile(message,"E:\\API\\data\\train\\trainData.xml");

        //2 xml文件解析
        DatasetProcessor dataprocess = new DatasetProcessor();
        File file = new File("E:\\API\\data\\train\\trainData.xml");
        try {
            List dataset = new ArrayList();
            dataset = dataprocess.analyzeXmlFile(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //3 相关信息提取

        //4 反馈信息到python端
        for(modelData item: webSocketSet) {
            try {
                item.sendMessage("数据接收完毕");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
