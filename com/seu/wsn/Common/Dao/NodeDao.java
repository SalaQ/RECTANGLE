package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.Node;

/**
 * 
 * @ClassName: NodeDao 
 * @Description: 节点数据访问层接口
 * @author: CSS
 * @date: 2016-11-3 上午9:55:30
 */
public interface NodeDao {
	/**
	 * 
	 * @Title: select 
	 * @Description: 根据条件选择节点
	 * @param node
	 * @return
	 * @return: Node
	 */
	public Node select(Node node);
	public Node selectByIp(Node node);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 插入新节点
	 * @param node
	 * @return: void
	 */
	public void insert(Node node);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新节点信息
	 * @param node
	 * @return: void
	 */
	public void update(Node node); 
	
	/**
	 * 
	 * @Title: remove 
	 * @Description: 根据节点id和测试编号删除节点
	 * @param nodeId
	 * @param testId
	 * @return: void
	 */
	//public void remove(String nodeId,String testId);
	
	/**
	 * 获取节点列表
	 * @return
	 */
	public List<Node> nodeList();
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据测试编号获取满足条件的节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<Node> getNodeListByTestId(String testId);
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据节点类型获取满足条件的节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<Node> getNodeListByNodeType(Node node);
}
