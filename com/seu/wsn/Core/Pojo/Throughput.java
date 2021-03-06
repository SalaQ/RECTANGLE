package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: Throughput 
 * @Description: 网络吞吐量
 * @author: shenyu.css
 * @date: 2016年12月1日 下午4:40:47
 */
public class Throughput {
	private String testId;						//测试id
	private int    testNum;						//测试次数
	private int    cycleNum;                    //周期数
	private String nodeId;                      //测试节点id
	private String nodeType;                    //节点类型
	private String parentId;                    //父节点id
	private double sendSpeed;                   //测试包发送速率（字节／秒）
	private int    sendPacket;	                //发包数
	private int    recievePacketFact;           //实际收包数
	private int    recievePacketTheory;			//理论收包数
	private double  nodePacketLoss;				//节点丢包率
	private int     packetLength;				//发送包长度
	private double    throughput;     			//吞吐量
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
	 * @Title: getRecievePacketFact 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getRecievePacketFact() {
		return recievePacketFact;
	}
	/**
	 * 
	 * @Title: setRecievePacketFact 
	 * @Description: TODO
	 * @param recievePacketFact
	 * @return: void
	 */
	public void setRecievePacketFact(int recievePacketFact) {
		this.recievePacketFact = recievePacketFact;
	}
	/**
	 * 
	 * @Title: getRecievePacketTheory 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getRecievePacketTheory() {
		return recievePacketTheory;
	}
	/**
	 * 
	 * @Title: setRecievePacketTheory 
	 * @Description: TODO
	 * @param recievePacketTheory
	 * @return: void
	 */
	public void setRecievePacketTheory(int recievePacketTheory) {
		this.recievePacketTheory = recievePacketTheory;
	}
	/**
	 * 
	 * @Title: getNodePacketLoss 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getNodePacketLoss() {
		return nodePacketLoss;
	}
	/**
	 * 
	 * @Title: setNodePacketLoss 
	 * @Description: TODO
	 * @param nodePacketLoss
	 * @return: void
	 */
	public void setNodePacketLoss(double nodePacketLoss) {
		this.nodePacketLoss = nodePacketLoss;
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
	 * @Title: getThroughput 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public double getThroughput() {
		return throughput;
	}
	/**
	 * 
	 * @Title: setThroughput 
	 * @Description: TODO
	 * @param throughput
	 * @return: void
	 */
	public void setThroughput(double throughput) {
		this.throughput = throughput;
	}
	
	
	
}
