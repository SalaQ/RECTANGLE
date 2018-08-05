package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: Node 
 * @Description: 节点类
 * @author: CSS
 * @date: 2016-11-2 下午3:14:45
 */
public class UwNode {
	private String nodeId;				//节点id
	private String ip;					//节点ip
	private String parentId;			//父节点id
	private String nodeType;			//节点类型   
	private String testId;				//测试编号
	private boolean onLine;				//是否在线
	private boolean download;			//是否已下载协议
	private boolean burner;              //是否已烧录协议
	private boolean joinNetwork;		//是否参与组网
	
	/**
	 * 
	 * @Title: isBurner 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isBurner() {
		return burner;
	}
	/**
	 * 
	 * @Title: setBurner 
	 * @Description: TODO
	 * @param burner
	 * @return: void
	 */
	public void setBurner(boolean burner) {
		this.burner = burner;
	}
	/**
	 * 
	 * @Title: getTestId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getTestId() {
		return testId;
	}
	/**
	 * 
	 * @Title: setTestId 
	 * @Description: TODO
	 * @param testId
	 * @return: void
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
	/**
	 * 
	 * @Title: getId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * 
	 * @Title: setId 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * 
	 * @Title: getIp 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 
	 * @Title: setIp 
	 * @Description: TODO
	 * @param ip
	 * @return: void
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 
	 * @Title: getParentId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 
	 * @Title: setParentId 
	 * @Description: TODO
	 * @param parentId
	 * @return: void
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 
	 * @Title: getNodeType 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getNodeType() {
		return nodeType;
	}
	/**
	 * 
	 * @Title: setNodeType 
	 * @Description: TODO
	 * @param nodeType
	 * @return: void
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	/**
	 * 
	 * @Title: isOnLine 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isOnLine() {
		return onLine;
	}
	/**
	 * @Title: setOnLine 
	 * @Description: TODO
	 * @param onLine
	 * @return: void
	 */
	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}
	/**
	 * 
	 * @Title: isDownload 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isDownload() {
		return download;
	}
	/**
	 * 
	 * @Title: setDownload 
	 * @Description: TODO
	 * @param download
	 * @return: void
	 */
	public void setDownload(boolean download) {
		this.download = download;
	}
	/**
	 * 
	 * @Title: isJoinNetwork 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isJoinNetwork() {
		return joinNetwork;
	}
	/**
	 * 
	 * @Title: setJoinNetwork 
	 * @Description: TODO
	 * @param joinNetwork
	 * @return: void
	 */
	public void setJoinNetwork(boolean joinNetwork) {
		this.joinNetwork = joinNetwork;
	}
	
}
