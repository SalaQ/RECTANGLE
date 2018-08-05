package com.seu.wsn.Service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.TopologyLink;
import com.seu.wsn.Core.Pojo.TopologyNode;
/**
 * 
 * @ClassName: NodeService 
 * @Description: 节点管理业务逻辑层接口
 * @author: CSS
 * @date: 2016-11-3 下午2:11:04
 */
public interface NodeService {
	/**
	 * 
	 * @Title: select 
	 * @Description: 根据条件选择节点
	 * @param node
	 * @return
	 * @return: Node
	 */
	public Node select(String nodeId,String testId);
	public Node selectByIp(String ip,String testId);
	
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据节点类型获取满足条件的节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<Node> getNodeListByNodeType(String testId,String nodeType);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 新增节点
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
	 * @Description: 删除节点
	 * @param nodeId
	 * @param testId
	 * @return: void
	 */
	//public void remove(String nodeId,String testId);
	
	/**
	 * 获取节点信息列表
	 * @return
	 */
	public List<Node> nodeList();
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据测试编号获取节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<Node> getNodeListByTestId(String testId);
	
	/**
	 * 
	 * @Title: getTopologyNodes 
	 * @Description: 获得拓扑图中节点信息
	 * @param testId
	 * @return: List<TopologyNode>
	 */
	public List<List<TopologyNode>> getTopologyNodes(String testId);
	/**
	 * 
	 * @Title: getTopologyLinks 
	 * @Description: 获得拓扑图中节点连接信息
	 * @param testId
	 * @return
	 * @return: List<TopologyLink>
	 */
	public List<List<TopologyLink>> getTopologyLinks(String testId);
	/**
	 * 
	 * @Title: fileUpload 
	 * @Description: 上传协议文件
	 * @param file
	 * @param path
	 * @return
	 * @return: String
	 */
	public String fileUpload(CommonsMultipartFile file,String path);
	/**
	 * 
	 * @Title: resetDownload 
	 * @Description: 将节点是否下载协议标志设为初始值
	 * @param nodeList
	 * @return
	 * @return: String
	 */
	public String resetDownload(List<Node> nodeList);
	/**
	 * 
	 * @Title: resetBurner 
	 * @Description: 将节点是否烧录协议标志设为初始值
	 * @param nodeList
	 * @return
	 * @return: String
	 */
	public String resetBurner(List<Node> nodeList);
}
