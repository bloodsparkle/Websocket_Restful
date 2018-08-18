package me.gacl.websocket;

import org.dom4j.DocumentException;
import org.java_websocket.drafts.Draft_17;
import utils.FileUtils;
import utils.connectWebsocket;
import xmlProcess.DatasetProcessor;
import xmlProcess.ModuleProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/model")
public class modelData {
	//将websocket服务端地址定义为常量
//	String uri = "ws://localhost:5001/result";
//	URI revResultUri = URI.create(uri);

	String uri1 = "ws://localhost:5001/init";
	URI revModelUri = URI.create(uri1);

	String uri2 = "ws://localhost:5001/start";
	URI startTrainlUri = URI.create(uri2);

//	String uri2 = "ws:localhost:5001/data";
//	URI revDataUri = URI.create(uri1);

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
		System.out.println("new connection");
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);  //从set中删除
		System.out.println("conn closed" );
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, URISyntaxException, InterruptedException {
//		System.out.println("来自python端的模型数据");
		this.session.getBasicRemote().sendText("this is message from java");

		System.out.println(message);
//		while (message != null){
		switch (message){
//		if (message.equals("init")){
			case ("init"):{
			this.session.getBasicRemote().sendText("initial config：");
			connectWebsocket ws = new connectWebsocket(revModelUri);
			ws.connect();

        FileUtils model_data = new FileUtils();
        File model_file = new File("E:\\API\\data\\train\\initConfig.xml") ;
        try {
            model_data.transportFileToByte(model_file,ws);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        ws.close();
		}

//		if (message.equals("ack")){
//			case ("ack"):{
//				this.session.getBasicRemote().sendText("control message");
//			connectWebsocket start_train = new connectWebsocket(startTrainlUri);
//			start_train.connect();
//			Thread.sleep(30);
////			start_train.send("ack accept：");
//			start_train.send("start");
//			start_train.send("start");
//			start_train.close();
//		}

			//2 xml文件解析
//			ModuleProcessor dataprocess = new ModuleProcessor();
//			File file = new File("E:\\API\\data\\train\\model.xml");
//			try {
//				List dataset = new ArrayList();
//				dataprocess.analyzeXmlFile(file);
//			} catch (DocumentException e) {
//				e.printStackTrace();
//			}
//		}
		//3 相关信息提取

		//4 反馈信息到python端
		}
}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("error occurs");
		error.printStackTrace();
	}

}
