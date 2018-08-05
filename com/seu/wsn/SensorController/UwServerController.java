package com.seu.wsn.SensorController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


import com.seu.wsn.Core.Pojo.ClientSocket;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.OrderEnum;
import com.seu.wsn.Service.UwDataService;
import com.seu.wsn.Service.UwTestingService;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.SystemSettingServiceImpl;
import com.seu.wsn.Service.TestingService;

/**
 * 
 * @ClassName: ServerController 
 * @Description:节点控制器
 * @author: CSS
 * @date: 2016-11-3 下午8:01:18
 */
public class UwServerController implements Runnable{
	private String testId;
	private UwDataService uwDataService;
	private NodeService nodeService;
	private UwTestingService uwTestingService;
//	private TestingService testingService;
	private ServerSocket serverSocket ;
	public UwServerController(String testId,UwDataService uwDataService,NodeService nodeService,ServerSocket serverSocket, UwTestingService uwTestingService){
		this.testId = testId;
		this.uwDataService = uwDataService;
		this.nodeService = nodeService;
		this.serverSocket = serverSocket;
		this.uwTestingService = uwTestingService;
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
					String nodeId = ip.substring(12);
					//注入节点初始信息
					node.setNodeId("null");
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
					Order.uwSocketList.put(ip, socket);
					Thread t = new Thread(new SocketThread(socket, uwDataService, nodeService, testId, uwTestingService));  
		            t.start();
					//SystemSettingServiceImpl.executor.execute(new NodeTask(OrderEnum.CONNECT_SUCCESS,cSocket));
				}
			}
			serverSocket = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
