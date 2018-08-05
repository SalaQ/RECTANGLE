package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: PathLength 
 * @Description: 平均路径长度
 * @author: shenyu.css
 * @date: 2016年12月1日 下午4:51:59
 */
public class UwPathLength {
	private String testId;						//测试id
	private int    testNum;						//测试次数
	private String nodeId;                      //测试节点id
	private String nodeType;                    //节点类型
	private int    pathLength;                  //路径长度
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
	 * @Title: getPathLength 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPathLength() {
		return pathLength;
	}
	/**
	 * 
	 * @Title: setPathLength 
	 * @Description: TODO
	 * @param pathLength
	 * @return: void
	 */
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	
	
}
