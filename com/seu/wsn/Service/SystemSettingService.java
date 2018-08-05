package com.seu.wsn.Service;
/**
 * 
 * @ClassName: OrderSettingService 
 * @Description: 业务逻辑层系统指令设置接口
 * @author: CSS
 * @date: 2016-11-19 上午10:11:33
 */
public interface SystemSettingService {
	/**
	 * 
	 * @Title: setOrder 
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @return: void
	 */
	public void setOrder(String orderType,NodeService nodeService);
	/**
	 * 
	 * @Title: setOrder 
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @param type
	 * @param systemSettingService
	 * @return: void
	 */
	public void setOrder(String orderType,String type,SystemSettingService systemSettingService,TestingService testingService);
	/**
	 * 
	 * @Title: setOrder 
	 * @Description: 根据orderType设置相应的指令类型
	 * @param orderType
	 * @param type
	 * @param nodeService
	 * @return: void
	 */
	public void setOrder(String orderType, String type,NodeService nodeService,TestingService testingService,SystemSettingService systemSettingService);
	/**
	 * 
	 * @Title: setOrder 
	 * @Description: 协议下载指令设置
	 * @param orderType
	 * @param path
	 * @param fileName
	 * @param fileType
	 * @return: void
	 */
	public void setOrder(String orderType,String path,String fileName,String nodeType,NodeService nodeService);
//	public void setOrder(String orderType,String path,String fileName,String nodeType,UwNodeService uwNodeService);
	/**
	 * 
	 * @Title: setClientSocket 
	 * @Description: 设置客服端socket相关信息
	 * @param nodeId
	 * @param nodeType
	 * @param joinNetwork
	 * @return: void
	 */
	public void setClientSocket(String nodeId,String nodeType,String joinNetwork);
	/**
	 * 
	 * @Title: closeStream 
	 * @Description: 关闭socket的InputStream
	 * @return: void
	 */
	public void closeStream();
}
