package com.seu.wsn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.PathLength;
import com.seu.wsn.Core.Pojo.PointInfo;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.TestInfo;
import com.seu.wsn.Core.Pojo.Throughput;
import com.seu.wsn.Core.Pojo.TimeDelay;
import com.seu.wsn.Core.Pojo.TopologyLink;
import com.seu.wsn.Core.Pojo.TopologyNode;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.TestInfoService;
import com.seu.wsn.Service.TestingService;
/**
 * 
 * @ClassName: HistoryQueryController 
 * @Description: 历史查询控制器
 * @author: shenyu.css
 * @date: 2016年12月1日 下午10:16:43
 */
@Controller
public class HistoryQueryController {
	@Autowired
	private NodeService nodeService;
	@Autowired
	private TestingService testingService;
	@Autowired
	private TestInfoService testInfoService;
	/**
	 * 
	 * @Title: setTestInfoService 
	 * @Description: 注入testInfoService
	 * @param testInfoService
	 * @return: void
	 */
	public void setTestInfoService(TestInfoService testInfoService) {
		this.testInfoService = testInfoService;
	}
	/**
	 * 
	 * @Title: setTestingService 
	 * @Description: 注入testingService
	 * @param testingService
	 * @return: void
	 */
	public void setTestingService(TestingService testingService) {
		this.testingService = testingService;
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
	/**
	 * 
	 * @Title: hqNetworking 
	 * @Description: 获取历史查询网络拓扑页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqNetworking")
	public String hqNetworking(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_NETWORKING;
	}
	/**
	 * 
	 * @Title: hqPacketLoss 
	 * @Description: 获取历史查询丢包率页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqPacketLoss")
	public String hqPacketLoss(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_PACKET_LOSS;
	}
	/**
	 * 
	 * @Title: hqPathLength 
	 * @Description: 获取历史查询平均路径长度页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqPathLength")
	public String hqPathLength(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_AVERAGE_PATH_LENGTH;
	}
	/**
	 * 
	 * @Title: hqTimeDelay
	 * @Description: 获取历史查询时延页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqTimeDelay")
	public String hqTimeDelay(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_TIME_DELAY;
	}
	/**
	 * 
	 * @Title: hqConnectivityDegree 
	 * @Description: 获取历史查询联通度页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqConnectivityDegree")
	public String hqConnectivityDegree(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_CONNECTIVITY_DEGREE;
	}
	/**
	 * 
	 * @Title: hqThroughput 
	 * @Description: 获取历史查询吞吐量页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/hqThroughput")
	public String hqThroughput(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_NETWORKING_THROUGHPUT;
	}
	/**
	 * 
	 * @Title: getHistoryInfo 
	 * @Description: 进行历史查询首先获取网络拓扑信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param testName
	 * @param testId
	 * @return
	 * @return: String
	 */
	@RequestMapping("/getHistoryInfo")
	public String getHistoryInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String testName,String testId){
		Order.historyTestId = testId;
		Order.historyTestName = testName;
		model.put(WebConst.NAV_ITEM, "historyQuery");
		return WebConst.HISTORY_NETWORKING;
	}
	
	/**
	 * 
	 * @Title: getHistoryNodePacektLossInfo 
	 * @Description: 获取历史记录中的节点丢包率测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryNodePacektLossInfo")
	@ResponseBody
	public ModelMap getHistoryNodePacektLossInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<NodePacketLoss> packetLossList = testingService.selectPacketLossList(Order.historyTestId);
		model.put("nodePacketLossList",packetLossList);
		return model;
	}
	
	/**
	 * 
	 * @Title: getHistorySysPacketLossInfo 
	 * @Description:  获取历史记录中的系统丢包率测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistorySysPacketLossInfo")
	@ResponseBody
	public ModelMap getHistorySysPacketLossInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		
		Map<Integer,Double> sysPacketLossByLength = new HashMap<Integer,Double>();
		Map<Integer,Double> sysPacketLossByInterval = new HashMap<Integer,Double>();
		List<PointInfo> pointInfo = new ArrayList<PointInfo>();
		List<Double> yInfoByLength = new ArrayList<Double>() ;
		List<Double> yInfoByInterval = new ArrayList<Double>();
		List<Double> yInfo = new ArrayList<Double>();
		List<SystemPacketLoss> sysPacketLossList = testingService.getSysPacketLossList(sysPacketLossByLength,sysPacketLossByInterval,Order.historyTestId);
		if(sysPacketLossByLength!=null && sysPacketLossByLength.size()!=0 && sysPacketLossByInterval!=null && sysPacketLossByInterval.size()!=0){
			testingService.getXY(sysPacketLossByLength, sysPacketLossByInterval, pointInfo, yInfoByLength, yInfoByInterval, yInfo);
			model.put("pointInfo", pointInfo);
			model.put("yInfoByLength", yInfoByLength);
			model.put("yInfoByInterval", yInfoByInterval);
			model.put("yInfo", yInfo);
			model.put(WebConst.SYS_PACKET_LOSS_LIST, sysPacketLossList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryTimeDelayInfo 
	 * @Description: 获取历史记录时延测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryTimeDelayInfo")
	@ResponseBody
	public ModelMap getHistoryTimeDelayInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		Map<Integer,Integer> mapTimeDelay = new HashMap<Integer,Integer>();
		List<TimeDelay> delayList = testingService.selectTimeDelayList(Order.historyTestId);
		PointInfo point = new PointInfo();
		List<Double> yInfo = new ArrayList<Double>();
		List<Double> yInfoByLength = new ArrayList<Double>();
		Integer netTimeDelay = new Integer(0);
		if(delayList!=null && delayList.size()!=0){
			netTimeDelay = testingService.getNetTimeDelayList(mapTimeDelay,delayList);
			if(mapTimeDelay.size()!=0){
				testingService.getXY(mapTimeDelay, point, yInfoByLength,yInfo);
			}
			model.put("netTimeDelay", netTimeDelay);
			model.put("timeDelayList", delayList);
			model.put("yInfoByLength", yInfoByLength);
			model.put("yInfo", yInfo);
			model.put("pointInfo", point);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryNodeList 
	 * @Description: 获取历史记录网络拓扑信息
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryNodeList")
	@ResponseBody
	public ModelMap getHistoryNodeList(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<Node> nodeList = nodeService.getNodeListByTestId(Order.historyTestId);
		model.put("nodeList", nodeList);
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryThroughputInfo 
	 * @Description: 获取历史记录吞吐量测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryThroughputInfo")
	@ResponseBody
	public ModelMap getHistoryThroughputInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<Throughput> throughputList = testingService.selectThroughputList(Order.historyTestId);
		if(throughputList!=null && throughputList.size()!=0){
			model.put("throughputList", throughputList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryConnectivityDegreeInfo 
	 * @Description:  获取历史记录联通度测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryConnectivityDegreeInfo")
	@ResponseBody
	public ModelMap getHistoryConnectivityDegreeInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<ConnectivityDegree> connectivityDegreeList = testingService.selectConnectivityDegreeList(Order.historyTestId);
		if(connectivityDegreeList!=null && connectivityDegreeList.size()!=0){
			model.put("connectivityDegreeList", connectivityDegreeList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryPathLengthInfo 
	 * @Description: 获取历史记录平均路径长度测试结果
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryPathLengthInfo")
	@ResponseBody
	public ModelMap getHistoryPathLengthInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<PathLength> pathLengthList = testingService.selectPathLengthList(Order.historyTestId);
		if(pathLengthList!=null && pathLengthList.size()!=0){
			int avePathLength = testingService.getAveLength(pathLengthList);
			model.put("avePathLength", avePathLength);
			model.put("pathLengthList", pathLengthList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryTestInfoList 
	 * @Description: 获取历史测试信息列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryTestInfoList")
	@ResponseBody
	public ModelMap getHistoryTestInfoList(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		String userName = (String)req.getSession().getAttribute(WebConst.USERNAME);
		List<TestInfo> testInfoList = testInfoService.selectTestInfoList(userName);
		if(testInfoList!=null && testInfoList.size()!=0){
			model.put("testInfoList", testInfoList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		return model;
	}
	/**
	 * 
	 * @Title: getHistoryTopology 
	 * @Description: 历史查询获得拓扑图
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryTopology")
	@ResponseBody
	public ModelMap getHistoryTopology(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<List<TopologyNode>> topologyNodeList = nodeService.getTopologyNodes(Order.historyTestId);
		List<List<TopologyLink>> topologyLinkList = nodeService.getTopologyLinks(Order.historyTestId);
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
}
