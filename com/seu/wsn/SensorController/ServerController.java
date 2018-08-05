package com.seu.wsn.SensorController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


import com.seu.wsn.Core.Pojo.ClientSocket;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.OrderEnum;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.SystemSettingServiceImpl;

/**
 * 
 * @ClassName: ServerController 
 * @Description:节点控制器
 * @author: CSS
 * @date: 2016-11-3 下午8:01:18
 */
public class ServerController implements Runnable{
	private String testId;
	private NodeService nodeService;
	private ServerSocket serverSocket ;
	public volatile static Map<String,ClientSocket> socketList = new HashMap<String,ClientSocket>();
	public ServerController(String testId,NodeService nodeService,ServerSocket serverSocket ){
		this.testId = testId;
		this.nodeService = nodeService;
		this.serverSocket = serverSocket;
	}
	
	@Override
	public void run() {
		try {
			while(Order.startConnection){
				Socket socket = serverSocket.accept();
				String ip = socket.getInetAddress().toString().substring(1); //节点ip
				if(!"127.0.0.1".equals(ip)){
					Node node = new Node();
					node.setIp(ip);
					node.setTestId(testId);
					String nodeId = ip.substring(8); 
					//注入节点初始信息
					node.setNodeId(nodeId);
					Node nodeTmp = nodeService.select(nodeId, testId);
					ClientSocket cSocket = new ClientSocket();
					if(nodeTmp==null){
						nodeService.insert(node);
					}else{
						nodeTmp.setOnLine(true);
						nodeTmp.setBurner(false);
						nodeTmp.setDownload(false);
						nodeTmp.setParentId("null");
						nodeService.update(nodeTmp);
						cSocket.setNodeType(nodeTmp.getNodeType());
					}
					cSocket.setSocket(socket);
					cSocket.setNodeId(nodeId);
					socketList.put(nodeId,cSocket);
					SystemSettingServiceImpl.executor.execute(new NodeTask(OrderEnum.CONNECT_SUCCESS,cSocket));
				}
			}
			serverSocket = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
