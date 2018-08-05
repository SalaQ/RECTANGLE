package com.seu.wsn.Core.Pojo;

import java.net.Socket;

import com.seu.wsn.Core.StaticConst.WebConst;

/**
 * 
 * @ClassName: ClientSocket 
 * @Description: 开发板连接服务器socket
 * @author: CSS
 * @date: 2016-11-23 上午9:04:07
 */
public class ClientSocket {
	private Socket socket;									//客户端socket
	private String nodeId;									//节点id
	private String nodeType = WebConst.COMMON_NODE;			//节点类型,默认为普通节点
	private boolean download = false;						//是否已下载协议
	private boolean burner = false;             			//是否已烧录协议
	private boolean joinNetwork = true;					    //是否参与组网
	/**
	 * 
	 * @Title: getSocket 
	 * @Description: TODO
	 * @return
	 * @return: Socket
	 */
	public Socket getSocket() {
		return socket;
	}
	/**
	 * 
	 * @Title: setSocket 
	 * @Description: TODO
	 * @param socket
	 * @return: void
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	/**
	 * 
	 * @Title: getNodeId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * 
	 * @Title: setNodeId 
	 * @Description: TODO
	 * @param nodeId
	 * @return: void
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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
