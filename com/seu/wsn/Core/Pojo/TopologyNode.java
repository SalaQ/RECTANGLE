package com.seu.wsn.Core.Pojo;
/**
 * 
 * @ClassName: TopologyNode 
 * @Description: 拓扑节点信息
 * @author: CSS
 * @date: 2016-11-11 下午1:49:08
 */
public class TopologyNode {
	private String ip;                 //拓扑节点ip
	private String type;               //拓扑节点类型
	private int status;                //无意义，根据不同的值显示拓扑图中线的不同的颜色
	boolean expand;                    //是否可扩展
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
	 * @Title: getType 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @Title: setType 
	 * @Description: TODO
	 * @param type
	 * @return: void
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 
	 * @Title: getStatus 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * 
	 * @Title: setStatus 
	 * @Description: TODO
	 * @param status
	 * @return: void
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 
	 * @Title: isExpand 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	public boolean isExpand() {
		return expand;
	}
	/**
	 * 
	 * @Title: setExpand 
	 * @Description: TODO
	 * @param expand
	 * @return: void
	 */
	public void setExpand(boolean expand) {
		this.expand = expand;
	}
	
}
