package com.seu.wsn.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.UwNode;
import com.seu.wsn.Core.Pojo.TopologyLink;
import com.seu.wsn.Core.Pojo.TopologyNode;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.UwNodeService;
import com.seu.wsn.Service.TestingService;
import com.seu.wsn.Service.SystemSettingService;
/**
 * 
 * @ClassName: NetworkingController 
 * @Description: 自主组网
 * @author: CSS
 * @date: 2016-11-11 下午1:36:04
 */
@Controller
public class NetworkingController {
	@Autowired
	private NodeService nodeService;
	@Autowired
	private UwNodeService uwNodeService;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private TestingService testingService;
	/**
	 * 
	 * @Title: setPacketLossService 
	 * @Description: 注入testingService
	 * @param packetLossService
	 * @return: void
	 */
	public void setTestingService(TestingService testingService) {
		this.testingService = testingService;
	}
	/**
	 * 
	 * @Title: setSystemSettingService 
	 * @Description: 注入systemSettingService
	 * @param systemSettingService
	 * @return: void
	 */
	public void setSystemSettingService(SystemSettingService systemSettingService) {
		this.systemSettingService = systemSettingService;
	}

	/**
	 * 
	 * @Title: setNodeService 
	 * @Description: 注入nodeService
	 * @param nodeService
	 * @return: void
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	public void setUwNodeService(UwNodeService uwNodeService) {
		this.uwNodeService = uwNodeService;
	}
	/**
	 * 
	 * @Title: getTopology 
	 * @Description: 获取拓扑图信息
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getTopology")
	@ResponseBody
	public ModelMap getTopology(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		String testId = (String)req.getSession().getAttribute(WebConst.TESTID);
		List<List<TopologyNode>> topologyNodeList = nodeService.getTopologyNodes(testId);
		List<List<TopologyLink>> topologyLinkList = nodeService.getTopologyLinks(testId);
		if(topologyNodeList.size()==2 && topologyLinkList.size()==2){
			model.put(WebConst.NODES, topologyNodeList.get(0));
			model.put(WebConst.CHILD_NODES, topologyNodeList.get(1));
			model.put(WebConst.LINKS, topologyLinkList.get(0));
			model.put(WebConst.CHILD_LINKS, topologyLinkList.get(1));
		}else{
			model.put(WebConst.ERROR_MSG, "拓扑图信息错误");
		}
		return model;
	}
	/**
	 * 
	 * @Title: fileUpload 
	 * @Description: 上传协议文件
	 * @param file
	 * @param nodeType
	 * @param req
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("file")CommonsMultipartFile file,String nodeType,
			HttpServletRequest req,ModelMap model){
		String path;
		if(WebConst.COMMON_NODE.equals(nodeType)){
			path = req.getRealPath(WebConst.COMMON_FILE);
		}else if(WebConst.CLUSTER_HEAD_NODE.equals(nodeType)){
			path = req.getRealPath(WebConst.CLUSTER_HEAD_FILE);
		}else if(WebConst.ATTACK_NODE.equals(nodeType)){
			path = req.getRealPath(WebConst.ATTACK_FILE);
		}else{
			path = req.getRealPath(WebConst.GATEWAY_FILE);
		}
		if(WebConst.SUCCESS.equals(nodeService.fileUpload(file, path))){
			systemSettingService.setOrder("downloadProtocol",path,file.getOriginalFilename(),nodeType,nodeService);
		}
		model.put(WebConst.NODE_TYPE, nodeType);
		model.put(WebConst.NAV_ITEM, "networking");
		return WebConst.NETWORKING;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping("/uwFileUpload")
	public String uwFileUpload(@RequestParam("file")CommonsMultipartFile file,String nodeType,
			HttpServletRequest req,ModelMap model){
		String path;
		if(WebConst.COMMON_NODE.equals(nodeType)){
			path = req.getRealPath(WebConst.COMMON_FILE);
		}else if(WebConst.BUOY_NODE.equals(nodeType)){
			path = req.getRealPath(WebConst.BUOY_FILE);
		}else{
			path = req.getRealPath(WebConst.GATEWAY_FILE);
		}
		if(WebConst.SUCCESS.equals(uwNodeService.fileUpload(file, path))){
			systemSettingService.setOrder("downloadUwProtocol",path,file.getOriginalFilename(),nodeType,nodeService);
		}
		model.put(WebConst.NODE_TYPE, nodeType);
		model.put(WebConst.NAV_ITEM, "networking");
		return WebConst.UWNETWORKING;
	}
	/**
	 * 
	 * @Title: burnProtocol 
	 * @Description: 协议烧录
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/burnProtocol")
	@ResponseBody
	public ModelMap burnProtocol(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		Order.packetLossTestNum = 0;
		Order.timeDelayTestNum = 0;
		Order.throughputTestNum = 0;
		Order.connectivityDegreeTestNum = 0;
		Order.pathLengthTestNum = 0;
		Order.netWorkingFlag = false;
		//if(packetLossService.selectPacketLossList(Order.testId)!=null){
			testingService.deletePacketLoss(Order.testId);
		//}
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
	/**
	 * 
	 * @Title: nodeReset 
	 * @Description: 节点复位
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/nodeReset")
	@ResponseBody
	public ModelMap nodeReset(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		Order.netWorkingFlag = false;
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
	/**
	 * 
	 * @Title: reDownloadProtocol 
	 * @Description: 确定重新下载协议
	 * @param req
	 * @param resp
	 * @param model
	 * @param nodeType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/reDownloadProtocol")
	@ResponseBody
	public ModelMap reDownloadProtocol(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String nodeType){
		String testId = (String)req.getSession().getAttribute(WebConst.TESTID);
		List<Node> nodeList= nodeService.getNodeListByNodeType(testId, nodeType);
		nodeService.resetDownload(nodeList);
		return model;
	}
	/**
	 * 
	 * @Title: getNetWorkingFlag 
	 * @Description: 获取组网是否完成标志
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getNetWorkingFlag")
	@ResponseBody
	public ModelMap getNetWorkingFlag(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
			model.put(WebConst.NETWORKINGFLAG, Order.netWorkingFlag);
		return model;
	}
	/**
	 * 
	 * @Title: waitForNetworkingFinished 
	 * @Description:等待网关节点发来组网结束信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/waitForNetworkingFinished")
	@ResponseBody
	public ModelMap waitForNetworkingFinished(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
	/**
	 * 
	 * @Title: getNodeParentId 
	 * @Description: 获取父节点信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getNodeParentId")
	@ResponseBody
	public ModelMap getNodeParentId(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
}
