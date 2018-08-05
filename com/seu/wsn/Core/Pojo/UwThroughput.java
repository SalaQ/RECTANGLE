package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: Throughput 
 * @Description: 网络吞吐量
 * @author: shenyu.css
 * @date: 2016年12月1日 下午4:40:47
 */
public class UwThroughput {
	private String testId;						//测试id
	private int    testNum;						//测试次数
	private String nodeId;                      //测试节点id
	private String nodeType;                    //节点类型
	private String parentId;                    //父节点id
	private int    sendPacket;	                //发包数
	private int    successPacket;				//成功发包数
	private int    receivePacket;         	    //收包数
	private double throughput;     				//吞吐量
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
	 * @Title: getReceivePacketFact 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getReceivePacket() {
		return receivePacket;
	}
	/**
	 * 
	 * @Title: setReceivePacketFact 
	 * @Description: TODO
	 * @param receivePacketFact
	 * @return: void
	 */
	public void setReceivePacket(int receivePacket) {
		this.receivePacket = receivePacket;
	}
	/**
	 * 
	 * @Title: getReceivePacketTheory 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getSuccessPacket() {
		return successPacket;
	}
	/**
	 * 
	 * @Title: setReceivePacketTheory 
	 * @Description: TODO
	 * @param receivePacketTheory
	 * @return: void
	 */
	public void setSuccessPacket(int successPacket) {
		this.successPacket = successPacket;
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