package com.seu.wsn.Core.Pojo;

public class UwNodePacketLoss {
	private String 	testId;						//本次测试ID
	private int    	testNum;					//测试次数
	private String 	nodeId;						//节点ID
	private String  parentId;					//父节点ID
	private String  nodeType;					//节点类型
	private int     sendPacket;					//发包数
	private int     successPacket;				//成功发包数
	private int     receivePacket;				//收包数
	private double  nodePacketLoss;				//节点丢包率
	private int     packetInterval;				//数据包发送间隔
	
	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
	
	public int getTestNum() {
		return testNum;
	}

	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	public int getSendPacket() {
		return sendPacket;
	}

	public void setSendPacket(int sendPacket) {
		this.sendPacket = sendPacket;
	}
	
	public int getSuccessPacket() {
		return successPacket;
	}

	public void setSuccessPacket(int successPacket) {
		this.successPacket = successPacket;
	}
	
	public int getreceivePacket() {
		return receivePacket;
	}

	public void setreceivePacket(int receivePacket) {
		this.receivePacket = receivePacket;
	}
	
	public double getNodePacketLoss() {
		return nodePacketLoss;
	}

	public void setNodePacketLoss(double nodePacketLoss) {
		this.nodePacketLoss = nodePacketLoss;
	}
	
	public int getPacketInterval() {
		return packetInterval;
	}

	public void setPacketInterval(int packetInterval) {
		this.packetInterval = packetInterval;
	}
}
