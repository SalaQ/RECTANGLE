package com.seu.wsn.Service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.seu.wsn.Core.Pojo.UwNode;
import com.seu.wsn.Core.Pojo.TopologyLink;
import com.seu.wsn.Core.Pojo.TopologyNode;
/**
 * 
 * @ClassName: NodeService 
 * @Description: 节点管理业务逻辑层接口
 * @author: CSS
 * @date: 2016-11-3 下午2:11:04
 */
public interface UwNodeService {
	/**
	 * 
	 * @Title: select 
	 * @Description: 根据条件选择节点
	 * @param node
	 * @return
	 * @return: Node
	 */
	public UwNode select(String nodeId,String testId);
	
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据节点类型获取满足条件的节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<UwNode> getUwNodeListByNodeType(String testId,String nodeType);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 新增节点
	 * @param node
	 * @return: void
	 */
	public void insert(UwNode uwNode);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新节点信息
	 * @param node
	 * @return: void
	 */
	public void update(UwNode uwNode); 
	
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
	public List<UwNode> uwNodeList();
	/**
	 * 
	 * @Title: getNodeListByTestId 
	 * @Description: 根据测试编号获取节点列表
	 * @param testId
	 * @return
	 * @return: List<Node>
	 */
	public List<UwNode> getUwNodeListByTestId(String testId);
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
	
}
