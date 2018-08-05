package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: TimeDelay 
 * @Description: 时延
 * @author: shenyu.css
 * @date: 2016年12月1日 下午4:36:46
 */
public class UwTimeDelay {
	private String testId;      				//测试id
	private int    testNum;						//测试次数
	private String nodeId;                      //测试节点id
	private String nodeType;                    //节点类型
	private double timeDelay;                   //节点时延（微秒）
	
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
	 * @Title: getTimeDelay 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getTimeDelay() {
		return timeDelay;
	}
	/**
	 * 
	 * @Title: setTimeDelay 
	 * @Description: TODO
	 * @param timeDelay
	 * @return: void
	 */
	public void setTimeDelay(double timeDelay) {
		this.timeDelay = timeDelay;
	}
}
