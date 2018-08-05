package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName:TopologyLink
 * @Description: 拓扑图中节点连线信息
 * @author: CSS
 * @date: 2016-11-11 下午1:51:40
 */
public class TopologyLink {
	private String source;				//源节点ip
	private String target;				//目标节点ip
	
	/**
	 * 
	 * @Title: getSource 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 
	 * @Title: setSource 
	 * @Description: TODO
	 * @param source
	 * @return: void
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 
	 * @Title: getTarget 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * 
	 * @Title: setTarget 
	 * @Description: TODO
	 * @param target
	 * @return: void
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
