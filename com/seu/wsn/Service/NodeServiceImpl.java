package com.seu.wsn.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.seu.wsn.Common.Dao.NodeDao;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.TopologyLink;
import com.seu.wsn.Core.Pojo.TopologyNode;
import com.seu.wsn.Core.StaticConst.WebConst;
/**
 * 
 * @ClassName: NodeServiceImpl 
 * @Description: 节点业务逻辑层实现类
 * @author: CSS
 * @date: 2016-11-3 下午2:12:41
 */
@Service("nodeService")
public class NodeServiceImpl implements NodeService{
	
	@Autowired
	private NodeDao nodeDao;
	/**
	 * 
	 * @Title: setNodeDao 
	 * @Description: 注入nodeDao
	 * @param nodeDao
	 * @return: void
	 */
	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}
	/*
	 * (non Javadoc) 
	 * @Title: insert
	 * @Description: 插入新节点
	 * @param node 
	 * @see com.seu.wsn.Service.NodeService#insert(com.seu.wsn.Core.Pojo.Node)
	 */
	@Override
	public void insert(Node node) {
		node.setDownload(false);
		node.setJoinNetwork(true);
		node.setNodeType(WebConst.COMMON_NODE);
		node.setOnLine(true);
		node.setBurner(false);
		node.setParentId("null");
		nodeDao.insert(node);
	}
	/*
	 * (non Javadoc) 
	 * @Title: update
	 * @Description: 更新节点信息
	 * @param node 
	 * @see com.seu.wsn.Service.NodeService#update(com.seu.wsn.Core.Pojo.Node)
	 */
	@Override
	public void update(Node node) {
		nodeDao.update(node);
	}
	/*
	 * (non Javadoc) 
	 * @Title: nodeList
	 * @Description: 获取节点列表
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#nodeList()
	 */
	@Override
	public List<Node> nodeList() {
		return nodeDao.nodeList();
	}
	/*
	 * (non Javadoc) 
	 * @Title: getNodeListByTestId
	 * @Description: 根据测试编号获取满足条件的节点列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#getNodeListByTestId(java.lang.String)
	 */
	@Override
	public List<Node> getNodeListByTestId(String testId) {
		return nodeDao.getNodeListByTestId(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: select
	 * @Description: 根据条件选择节点
	 * @param nodeId
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#select(java.lang.String, java.lang.String)
	 */
	@Override
	public Node select(String nodeId,String testId) {
		Node node = new Node();
		node.setNodeId(nodeId);
		node.setTestId(testId);
		return nodeDao.select(node);
	}
	@Override
	public Node selectByIp(String ip,String testId) {
		Node node = new Node();
		node.setIp(ip);
		node.setTestId(testId);
		return nodeDao.selectByIp(node);
	}
	/*
	 * (non Javadoc) 
	 * @Title: getNodeListByNodeType
	 * @Description: 根据节点类型获取满足条件的节点列表
	 * @param testId
	 * @param nodeType
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#getNodeListByNodeType(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Node> getNodeListByNodeType(String testId,String nodeType) {
		Node node = new Node();
		node.setTestId(testId);
		node.setNodeType(nodeType);
		return nodeDao.getNodeListByNodeType(node);
	}
	/*
	 * (non Javadoc) 
	 * @Title: getTopologyNodes
	 * @Description: 获得拓扑图中节点信息
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#getTopologyNodes(java.lang.String)
	 */
	@Override
	public List<List<TopologyNode>> getTopologyNodes(String testId) {
		List<List<TopologyNode>> list = new ArrayList<List<TopologyNode>>();
		List<Node> nodeList = nodeDao.getNodeListByTestId(testId);
		List<TopologyNode> topologyNodeList = new ArrayList<TopologyNode>();
		List<TopologyNode> topologyChildNodeList = new ArrayList<TopologyNode>();
		for(int i=0;i<nodeList.size();i++){
			TopologyNode topologyNode = new TopologyNode();
			topologyNode.setIp(nodeList.get(i).getIp());
			topologyNode.setType(nodeList.get(i).getNodeType());
			if("commonNode".equals(topologyNode.getType())){
				topologyNode.setStatus(1);
				topologyNode.setExpand(false);
				topologyChildNodeList.add(topologyNode);
			}else{
				if("gatewayNode".equals(topologyNode.getType())){
					topologyNode.setStatus(0);
					topologyNode.setExpand(false);
				}else{
					topologyNode.setStatus(1);
					topologyNode.setExpand(true);
				}
				topologyNodeList.add(topologyNode);
			}
		}
		list.add(topologyNodeList);
		list.add(topologyChildNodeList);
		return list;
	}
	/*
	 * (non Javadoc) 
	 * @Title: getTopologyLinks
	 * @Description: 获得拓扑图中节点连接信息
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#getTopologyLinks(java.lang.String)
	 */
	@Override
	public List<List<TopologyLink>> getTopologyLinks(String testId) {
		List<List<TopologyLink>> list = new ArrayList<List<TopologyLink>>();
		List<Node> nodeList = nodeDao.getNodeListByTestId(testId);
		List<TopologyLink> topologyLinkList = new ArrayList<TopologyLink>();
		List<TopologyLink> topologyChildLinkList = new ArrayList<TopologyLink>();
		for(int i=0;i<nodeList.size();i++){
			if(!"null".equals(nodeList.get(i).getParentId())){
				TopologyLink topologyLink = new TopologyLink();
				String parentIp = nodeList.get(i).getIp().substring(0,8)+nodeList.get(i).getParentId();  //所有节点ip均由10.10.1.**开头
				topologyLink.setSource(parentIp);
				topologyLink.setTarget(nodeList.get(i).getIp());
				
				if("clusterHeadNode".equals(nodeList.get(i).getNodeType())){
					topologyLinkList.add(topologyLink);
				}else{
					topologyChildLinkList.add(topologyLink);
				}
			}
		}
		list.add(topologyLinkList);
		list.add(topologyChildLinkList);
		return list;
	}
	/*
	 * (non Javadoc) 
	 * @Title: fileUpload
	 * @Description: 上传协议文件
	 * @param file
	 * @param path
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#fileUpload(org.springframework.web.multipart.commons.CommonsMultipartFile, java.lang.String)
	 */
	@Override
	public String fileUpload(CommonsMultipartFile file, String path){
		try {
			InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(
					new File(path,file.getOriginalFilename()));
			int len = 0;
			byte[] buffer = new byte[400];
			while((len=is.read(buffer))!=-1){
				os.write(buffer, 0, len);
			}
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return WebConst.FAIL;
		}
		return WebConst.SUCCESS;
	}
	/*
	 * (non Javadoc) 
	 * @Title: resetDownload
	 * @Description: 将节点是否下载协议标志设为初始值
	 * @param nodeList
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#resetDownload(java.util.List)
	 */
	@Override
	public String resetDownload(List<Node> nodeList) {
		if(nodeList!=null){
			Node node;
			for(int i=0;i<nodeList.size();i++){
				node = nodeList.get(i);
				node.setDownload(false);
				nodeDao.update(node);
			}
		}
		return WebConst.SUCCESS;
	}
	/*
	 * (non Javadoc) 
	 * @Title: resetBurner
	 * @Description: 将节点是否烧录协议标志设为初始值
	 * @param nodeList
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#resetBurner(java.util.List)
	 */
	@Override
	public String resetBurner(List<Node> nodeList) {
		if(nodeList!=null){
			Node node;
			for(int i=0;i<nodeList.size();i++){
				node = nodeList.get(i);
				node.setBurner(false);
				nodeDao.update(node);
			}
		}
		return WebConst.SUCCESS;
	}
}
