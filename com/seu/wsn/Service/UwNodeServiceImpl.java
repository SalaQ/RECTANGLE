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

import com.seu.wsn.Common.Dao.UwNodeDao;
import com.seu.wsn.Core.Pojo.UwNode;
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
@Service("uwNodeService")
public class UwNodeServiceImpl implements UwNodeService{
	
	@Autowired
	private UwNodeDao uwNodeDao;
	/**
	 * 
	 * @Title: setNodeDao 
	 * @Description: 注入nodeDao
	 * @param nodeDao
	 * @return: void
	 */
	public void setUwNodeDao(UwNodeDao uwNodeDao) {
		this.uwNodeDao = uwNodeDao;
	}
	/*
	 * (non Javadoc) 
	 * @Title: insert
	 * @Description: 插入新节点
	 * @param node 
	 * @see com.seu.wsn.Service.NodeService#insert(com.seu.wsn.Core.Pojo.Node)
	 */
	@Override
	public void insert(UwNode uwNode) {
		uwNode.setDownload(false);
		uwNode.setJoinNetwork(true);
		uwNode.setNodeType("commonNode");
		uwNode.setOnLine(true);
		uwNode.setBurner(false);
		uwNode.setParentId("null");
		uwNodeDao.insert(uwNode);
	}
	/*
	 * (non Javadoc) 
	 * @Title: update
	 * @Description: 更新节点信息
	 * @param node 
	 * @see com.seu.wsn.Service.NodeService#update(com.seu.wsn.Core.Pojo.Node)
	 */
	@Override
	public void update(UwNode uwNode) {
		uwNodeDao.update(uwNode);
	}
	/*
	 * (non Javadoc) 
	 * @Title: nodeList
	 * @Description: 获取节点列表
	 * @return 
	 * @see com.seu.wsn.Service.NodeService#nodeList()
	 */
	@Override
	public List<UwNode> uwNodeList() {
		return uwNodeDao.uwNodeList();
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
	public List<UwNode> getUwNodeListByTestId(String testId) {
		return uwNodeDao.getUwNodeListByTestId(testId);
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
	public UwNode select(String nodeId,String testId) {
		UwNode uwNode = new UwNode();
		uwNode.setNodeId(nodeId);
		uwNode.setTestId(testId);
		return uwNodeDao.select(uwNode);
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
	public List<UwNode> getUwNodeListByNodeType(String testId,String nodeType) {
		UwNode uwNode = new UwNode();
		uwNode.setTestId(testId);
		uwNode.setNodeType(nodeType);
		return uwNodeDao.getUwNodeListByNodeType(uwNode);
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
	
}
