package com.seu.wsn.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Log4j.LoggerUtil;
import com.seu.wsn.Core.Pojo.ClientSocket;
import com.seu.wsn.Core.Pojo.UwClientSocket;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.OrderEnum;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.SensorController.NodeTask;
import com.seu.wsn.SensorController.ServerController;
import com.seu.wsn.SensorController.UwServerController;

/**
 * 
 * @ClassName: OrderSettingServiceImpl 
 * @Description: 业务逻辑层实现系统设置相关的操作
 * @author: CSS
 * @date: 2016-11-22 上午10:32:21
 */
@Service("systemSettingService")
public class SystemSettingServiceImpl implements SystemSettingService{
	public static ExecutorService executor = Executors.newCachedThreadPool();  //线程池
	/*
	 * (non Javadoc) 
	 * @Title: setOrder
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @param nodeService 
	 * @see com.seu.wsn.Service.SystemSettingService#setOrder(java.lang.String, com.seu.wsn.Service.NodeService)
	 */
	@Override
	public void setOrder(String orderType,NodeService nodeService) {
		OrderEnum order = OrderEnum.getOrderByType(orderType);
		Map<String,ClientSocket> map = ServerController.socketList;
		Iterator<Map.Entry<String, ClientSocket>> it = map.entrySet().iterator();
		ClientSocket cSocket;
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			if(cSocket.isJoinNetwork()){
				NodeTask task = new NodeTask(order,cSocket);
				task.setNodeService(nodeService);
				executor.execute(task);
			}
		}
	}
	
	/*
	 * (non Javadoc) 
	 * @Title: setClientSocket
	 * @Description: 修改客户端socket相关信息
	 * @param nodeId
	 * @param nodeType
	 * @param joinNetwork 
	 * @see com.seu.wsn.Service.SystemSettingService#setClientSocket(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setClientSocket(String nodeId,String nodeType,String joinNetwork) {
		boolean join;
		ClientSocket cSocket = ServerController.socketList.get(nodeId);
		cSocket.setNodeType(nodeType);
		cSocket.setNodeType(nodeType);
		if("true".equals(joinNetwork)){
			 join = true;
		 }else{
			 join = false;
		 }
		cSocket.setJoinNetwork(join);
		ServerController.socketList.put(nodeId,cSocket);
	}
	/*
	 * (non Javadoc) 
	 * @Title: setOrder
	 * @Description: 协议下载指令设置
	 * @param orderType
	 * @param path
	 * @param fileName
	 * @param nodeType
	 * @param nodeService 
	 * @see com.seu.wsn.Service.SystemSettingService#setOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.seu.wsn.Service.NodeService)
	 */
	@Override
	public void setOrder(String orderType, String path, String fileName,
			String nodeType,NodeService nodeService) {
		OrderEnum order = OrderEnum.getOrderByType(orderType);
		Map<String,ClientSocket> map = ServerController.socketList;
		Iterator<Map.Entry<String, ClientSocket>> it = map.entrySet().iterator();
		ClientSocket cSocket;
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			if(cSocket.getNodeType().equals(nodeType) && cSocket.isJoinNetwork()){
				NodeTask task = new NodeTask(order,cSocket,path,fileName);
				task.setNodeService(nodeService);
				executor.execute(task);
			}
		}
	}
//	@Override
//	public void setOrder(String orderType, String path, String fileName,
//			String nodeType,UwNodeService uwNodeService) {
//		OrderEnum order = OrderEnum.getOrderByType(orderType);
//		Map<String,UwClientSocket> map = UwServerController.uwSocketList;
//		Iterator<Map.Entry<String, UwClientSocket>> it = map.entrySet().iterator();
//		UwClientSocket uwcSocket;
//		while(it.hasNext()){
//			Map.Entry<String, UwClientSocket> entry = (Map.Entry<String, UwClientSocket>)it.next();
//			uwcSocket =(UwClientSocket)entry.getValue();
//			if(uwcSocket.getNodeType().equals(nodeType) && uwcSocket.isJoinNetwork()){
//				NodeTask task = new NodeTask(order,uwcSocket,path,fileName);
//				task.setUwNodeService(uwNodeService);
//				executor.execute(task);
//			}
//		}
//	}
	/*
	 * (non Javadoc) 
	 * @Title: setOrder
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @param type
	 * @param nodeService
	 * @param testingService
	 * @param systemSettingService 
	 * @see com.seu.wsn.Service.SystemSettingService#setOrder(java.lang.String, java.lang.String, com.seu.wsn.Service.NodeService, com.seu.wsn.Service.TestingService, com.seu.wsn.Service.SystemSettingService)
	 */
	@Override
	public void setOrder(String orderType, String type, NodeService nodeService,TestingService testingService,SystemSettingService systemSettingService) {
		OrderEnum order = OrderEnum.getOrderByType(orderType);
		if("connectivityDegree".equals(type)){
			NodeTask.commonSet.clear();
			NodeTask.gatwaySet.clear();
		}
		
		Map<String,ClientSocket> map = ServerController.socketList;
		Iterator<Map.Entry<String, ClientSocket>> it = map.entrySet().iterator();
		ClientSocket cSocket;
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			if((WebConst.CLUSTER_HEAD_NODE.equals(cSocket.getNodeType()) || WebConst.GATEWAY_NODE.equals(cSocket.getNodeType())) && cSocket.isJoinNetwork()){
				NodeTask task = new NodeTask(order,cSocket);
				task.setNodeService(nodeService);
				task.setTestingService(testingService);
				task.setSystemSettingService(systemSettingService);
				executor.execute(task);
			}
		}
		it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			if(WebConst.COMMON_NODE.equals(cSocket.getNodeType()) && cSocket.isJoinNetwork()){
				NodeTask task = new NodeTask(order,cSocket);
				task.setNodeService(nodeService);
				task.setTestingService(testingService);
				task.setSystemSettingService(systemSettingService);
				executor.execute(task);
				try {
					Thread.sleep(Order.packetInterval*500/(map.size()));
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：OrderSettingServiceImpl;方法：setOrder(String orderType, String type, NodeService nodeService)");
				}
			}
		}
	}
	/*
	 * (non Javadoc) 
	 * @Title: closeStream
	 * @Description: 关闭socket的InputStream 
	 * @see com.seu.wsn.Service.SystemSettingService#closeStream()
	 */
	@Override
	public void closeStream() {
		Map<String,ClientSocket> map = ServerController.socketList;
		Iterator<Map.Entry<String, ClientSocket>> it = map.entrySet().iterator();
		ClientSocket cSocket;
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			try {
				if(cSocket.getSocket().getInputStream()!=null){
					cSocket.getSocket().getInputStream().close();
				}
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：SystemSettingServiceImpl;方法：closeStream()");
			}
		}
		
	}
	/*
	 * (non Javadoc) 
	 * @Title: setOrder
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @param type
	 * @param systemSettingService
	 * @param testingService 
	 * @see com.seu.wsn.Service.SystemSettingService#setOrder(java.lang.String, java.lang.String, com.seu.wsn.Service.SystemSettingService, com.seu.wsn.Service.TestingService)
	 */
	@Override
	public void setOrder(String orderType, String type,
			SystemSettingService systemSettingService,
			TestingService testingService) {
		OrderEnum order = OrderEnum.getOrderByType(orderType);
		Map<String,ClientSocket> map = ServerController.socketList;
		Iterator<Map.Entry<String, ClientSocket>> it = map.entrySet().iterator();
		ClientSocket cSocket;
		while(it.hasNext()){
			Map.Entry<String, ClientSocket> entry = (Map.Entry<String, ClientSocket>)it.next();
			cSocket =(ClientSocket)entry.getValue();
			if(cSocket.isJoinNetwork()){
				NodeTask task = new NodeTask(order,cSocket);
				task.setSystemSettingService(systemSettingService);
				task.setTestingService(testingService);
				executor.execute(task);
			}
		}
		
	}

}
