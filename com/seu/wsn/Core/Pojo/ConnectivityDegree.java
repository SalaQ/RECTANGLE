package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: ConnectivityDegree 
 * @Description: 网络联通度
 * @author: shenyu.css
 * @date: 2016年12月1日 下午4:55:50
 */
public class ConnectivityDegree {
	private String testId;						//测试id
	private int    testNum;						//测试次数
	private String nodeId;                      //测试节点id
	private boolean recieveFlag;                //是否收到该节点信息
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
	 * @Title: isRecieveFlag 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isRecieveFlag() {
		return recieveFlag;
	}
	/**
	 * 
	 * @Title: setRecieveFlag 
	 * @Description: TODO
	 * @param recieveFlag
	 * @return: void
	 */
	public void setRecieveFlag(boolean recieveFlag) {
		this.recieveFlag = recieveFlag;
	}
	
	
}
