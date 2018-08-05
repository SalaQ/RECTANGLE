package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: DelayT 
 * @Description: 进行时延测试时定义的临时类，用于存储节点的收发包时间
 * @author: CSS
 * @date: 2016-12-3 上午9:54:49
 */
public class DelayT {
	private int nodeId;                     //节点id
	private int period;                     //测试周期数
	private int m_iSec;                     //节点发包时间（s）
	private int m_iUsec;                    //节点发包时间（us）
	public int getNodeId() {
		return nodeId;
	}
	/**
	 * 
	 * @Title: setNodeId 
	 * @Description: TODO
	 * @param nodeId
	 * @return: void
	 */
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * 
	 * @Title: getPeriod 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPeriod() {
		return period;
	}
	/**
	 * 
	 * @Title: setPeriod 
	 * @Description: TODO
	 * @param period
	 * @return: void
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	/**
	 * 
	 * @Title: getM_iSec 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getM_iSec() {
		return m_iSec;
	}
	/**
	 * 
	 * @Title: setM_iSec 
	 * @Description: TODO
	 * @param m_iSec
	 * @return: void
	 */
	public void setM_iSec(int m_iSec) {
		this.m_iSec = m_iSec;
	}
	/**
	 * 
	 * @Title: getM_iUsec 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getM_iUsec() {
		return m_iUsec;
	}
	/**
	 * 
	 * @Title: setM_iUsec 
	 * @Description: TODO
	 * @param m_iUsec
	 * @return: void
	 */
	public void setM_iUsec(int m_iUsec) {
		this.m_iUsec = m_iUsec;
	}
	
}
