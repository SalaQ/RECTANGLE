package com.seu.wsn.Core.Pojo;
/**
 * 
 * @ClassName: NodePacketLoss 
 * @Description: 节点丢包率存储数据
 * @author: CSS
 * @date: 2016-11-29 下午1:03:49
 */
public class NodePacketLoss {
	private String 	testId;						//本次测试ID
	private int    	testNum;					//测试次数
	private int    	cycleNum;					//测试周期数
	private String 	nodeId;						//节点ID
	private String  parentId;					//父节点ID
	private String  nodeType;					//节点类型
	private double  sendSpeed;					//测试包发送速率
	private int     recievePacket;				//收包数
	private int     sendPacket;					//发包数
	private double  nodePacketLoss;				//节点丢包率
	private int     packetLength;				//发送包长度
	private int     packetInterval;				//数据包发送间隔
	/**
	 * 
	 * @Title: getPacketInterval 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPacketInterval() {
		return packetInterval;
	}
	/**
	 * 
	 * @Title: setPacketInterval 
	 * @Description: TODO
	 * @param packetInterval
	 * @return: void
	 */
	public void setPacketInterval(int packetInterval) {
		this.packetInterval = packetInterval;
	}
	/**
	 * 
	 * @Title: getPacketLength 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPacketLength() {
		return packetLength;
	}
	/**
	 * 
	 * @Title: setPacketLength 
	 * @Description: TODO
	 * @param packetLength
	 * @return: void
	 */
	public void setPacketLength(int packetLength) {
		this.packetLength = packetLength;
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
	 * @Title: getTestNum 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getTestNum() {
		return testNum;
	}
	/**
	 * 
	 * @Title: setTestNum 
	 * @Description: TODO
	 * @param testNum
	 * @return: void
	 */
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getCycleNum() {
		return cycleNum;
	}
	/**
	 * 
	 * @Title: setCycleNum 
	 * @Description: TODO
	 * @param cycleNum
	 * @return: void
	 */
	public void setCycleNum(int cycleNum) {
		this.cycleNum = cycleNum;
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
	 * @Title: getSendSpeed 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getSendSpeed() {
		return sendSpeed;
	}
	/**
	 * 
	 * @Title: setSendSpeed 
	 * @Description: TODO
	 * @param sendSpeed
	 * @return: void
	 */
	public void setSendSpeed(double sendSpeed) {
		this.sendSpeed = sendSpeed;
	}
	/**
	 * 
	 * @Title: getRecievePacket 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getRecievePacket() {
		return recievePacket;
	}
	/**
	 * 
	 * @Title: setRecievePacket 
	 * @Description: TODO
	 * @param recievePacket
	 * @return: void
	 */
	public void setRecievePacket(int recievePacket) {
		this.recievePacket = recievePacket;
	}
	/**
	 * 
	 * @Title: getSendPacket 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getSendPacket() {
		return sendPacket;
	}
	/**
	 * 
	 * @Title: setSendPacket 
	 * @Description: TODO
	 * @param sendPacket
	 * @return: void
	 */
	public void setSendPacket(int sendPacket) {
		this.sendPacket = sendPacket;
	}
	/**
	 * 
	 * @Title: getNodePakcetLoss 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getNodePacketLoss() {
		return nodePacketLoss;
	}
	/**
	 * 
	 * @Title: setNodePakcetLoss 
	 * @Description: TODO
	 * @param nodePakcetLoss
	 * @return: void
	 */
	public void setNodePacketLoss(double nodePacketLoss) {
		this.nodePacketLoss = nodePacketLoss;
	}
	
}
