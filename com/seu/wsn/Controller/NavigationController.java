package com.seu.wsn.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
/**
 * 
 * @ClassName: NavigationController 
 * @Description: 导航栏控制器
 * @author: CSS
 * @date: 2016-11-8 上午9:45:01
 */
@Controller
public class NavigationController {
	/**
	 * 
	 * @Title: homePage 
	 * @Description: 获取系统简介页面
	 * @param req
	 * @param resp
	 * @return
	 * @return: String
	 */
	@RequestMapping("/homePage")
	public String homePage(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "homePage");
		String start = (String)req.getSession().getAttribute(WebConst.START_CONN);
		if("true".equals(start)){
			model.put(WebConst.START_CONN, "true");
		}else if("false".equals(start)){
			model.put(WebConst.START_CONN, "false");
		}
		return WebConst.INTRODUCTION;
	}
	@RequestMapping("/uwHomePage")
	public String uwHomePage(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "homePage");
		String start = (String)req.getSession().getAttribute(WebConst.START_CONN);
		if("true".equals(start)){
			model.put(WebConst.START_CONN, "true");
		}else if("false".equals(start)){
			model.put(WebConst.START_CONN, "false");
		}
		return WebConst.UWINTRODUCTION;
	}
	
	
	
	/**
	 * 
	 * @Title: nodeInfo 
	 * @Description: 获取节点信息页面
	 * @param req
	 * @param resp
	 * @return
	 * @return: String
	 */
	@RequestMapping("/nodeInfo")
	public String nodeInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "nodeInfo");
		return WebConst.NODE_INFO;
	}
	@RequestMapping("/uwNodeInfo")
	public String uwNodeInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "nodeInfo");
		return WebConst.UWNODE_INFO;
	}
	/**
	 * 
	 * @Title: directionForUse 
	 * @Description:获取系统使用说明页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/directionForUse")
	public String directionForUse(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "directionForUse");
		return WebConst.DIRECTION;
	}
	/**
	 * 
	 * @Title: nodeInfo 
	 * @Description: 获取节点信息页面
	 * @param req
	 * @param resp
	 * @return
	 * @return: String
	 */
	@RequestMapping("/networking")
	public String networking(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String burnerFinished,
			String downloadNodeType){
		model.put(WebConst.NAV_ITEM, "networking");
		model.put(WebConst.BURNER_FINISHED, burnerFinished);
		//model.put(WebConst.DOWNLOAD_NODE_TYPE,downloadNodeType);
		model.put(WebConst.NODE_TYPE, downloadNodeType);
		return WebConst.NETWORKING;
	}
	@RequestMapping("/uwNetworking")
	public String uwNetworking(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String burnerFinished,
			String downloadNodeType){
		model.put(WebConst.NAV_ITEM, "networking");
		model.put(WebConst.BURNER_FINISHED, burnerFinished);
		//model.put(WebConst.DOWNLOAD_NODE_TYPE,downloadNodeType);
		model.put(WebConst.NODE_TYPE, downloadNodeType);
		return WebConst.UWNETWORKING;
	}
	@RequestMapping("/chart")
	public String chart(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String burnerFinished,
			String downloadNodeType){
		model.put(WebConst.NAV_ITEM, "chart");
		return WebConst.CHART;
	}
	@RequestMapping("/topology")
	public String topology(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String burnerFinished,
			String downloadNodeType){
		model.put(WebConst.NAV_ITEM, "topology");
		return WebConst.TOPOLOGY;
	}
	/**
	 * 
	 * @Title: packet_loss 
	 * @Description: 获取节点丢包率页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/node_packet_loss")
	public String nodePacketLoss(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String packetLossing){
		if(req.getSession().getAttribute(WebConst.TEST_TIME)!=null){
			int testTime = (Integer)req.getSession().getAttribute(WebConst.TEST_TIME);
			model.put(WebConst.TEST_TIME, testTime);
		}
		model.put(WebConst.NETWORKINGFLAG,Order.netWorkingFlag);
		model.put(WebConst.NAV_ITEM, "performance_testing");
		model.put(WebConst.PACKETLOSSING, packetLossing);
		return WebConst.NODE_PACKET_LOSS;
	}
	@RequestMapping("/uw_node_packet_loss")
	public String uwNodePacketLoss(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String packetLossing){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.UW_NODE_PACKET_LOSS;
	}
	/**
	 * 
	 * @Title: systemPacketLoss 
	 * @Description: 获取系统丢包率页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/system_packet_loss")
	public String systemPacketLoss(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.SYSTEM_PACKET_LOSS;
	}
	/**
	 * 
	 * @Title: timeDelay 
	 * @Description: 获取网络时延页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/time_delay")
	public String timeDelay(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String timeDelay){
		if(req.getSession().getAttribute(WebConst.TEST_TIME)!=null){ 
			int testTime = (Integer)req.getSession().getAttribute(WebConst.TEST_TIME);
			model.put(WebConst.TEST_TIME, testTime);
		}
		model.put(WebConst.NETWORKINGFLAG,Order.netWorkingFlag);
		model.put(WebConst.NAV_ITEM, "performance_testing");
		model.put(WebConst.TIMEDELAYING, timeDelay);
		return WebConst.TIME_DELAY;
	}
	@RequestMapping("/uw_time_delay")
	public String uwTimeDelay(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String timeDelay){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.UW_TIME_DELAY;
	}
	/**
	 * 
	 * @Title: networkThroughput 
	 * @Description: 获取网络吞吐量页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/network_throughput")
	public String networkThroughput(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String throughput,
			String packetSendSpeed){
		if(req.getSession().getAttribute(WebConst.TEST_TIME)!=null){
			int testTime = (Integer)req.getSession().getAttribute(WebConst.TEST_TIME);
			model.put(WebConst.TEST_TIME, testTime);
		}
		model.put(WebConst.NETWORKINGFLAG,Order.netWorkingFlag);
		model.put(WebConst.NAV_ITEM, "performance_testing");
		if(packetSendSpeed!=null && !"".equals(packetSendSpeed)){
			model.put("packetSendSpeed", packetSendSpeed);
		}else{
			model.put("packetSendSpeed", "0");
		}
		
		model.put(WebConst.THROUGHPUTING, throughput);
		return WebConst.NETWORKING_THROUGHPUT;
	}
	@RequestMapping("/uw_network_throughput")
	public String uwNetworkThroughput(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String throughput,
			String packetSendSpeed){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.UW_NETWORKING_THROUGHPUT;
	}
	/**
	 * 
	 * @Title: averagePathLength 
	 * @Description: 获取平均路径长度页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/average_path_length")
	public String averagePathLength(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String pathLength){
		if(req.getSession().getAttribute(WebConst.TEST_TIME)!=null){
			int testTime = (Integer)req.getSession().getAttribute(WebConst.TEST_TIME);
			model.put(WebConst.TEST_TIME, testTime);
		}
		model.put(WebConst.NETWORKINGFLAG,Order.netWorkingFlag);
		model.put(WebConst.NAV_ITEM, "performance_testing");
		model.put(WebConst.PATHLENGTHTING, pathLength);
		return WebConst.AVERAGE_PATH_LENGTH;
	}
	@RequestMapping("/uw_average_path_length")
	public String uwAveragePathLength(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String pathLength){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.UW_AVERAGE_PATH_LENGTH;
	}
	/**
	 * 
	 * @Title: connectivityDegree 
	 * @Description: 获取网络连通度页面
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/connectivity_degree")
	public String connectivityDegree(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String connectivityDegree){
		if(req.getSession().getAttribute(WebConst.TEST_TIME)!=null){
			int testTime = (Integer)req.getSession().getAttribute(WebConst.TEST_TIME);
			model.put(WebConst.TEST_TIME, testTime);
		}
		model.put(WebConst.NETWORKINGFLAG,Order.netWorkingFlag);
		model.put(WebConst.NAV_ITEM, "performance_testing");
		model.put(WebConst.CONNECTIVITYDEGREEING, connectivityDegree);
		return WebConst.CONNECTIVITY_DEGREE;
	}
	@RequestMapping("/uw_connectivity_degree")
	public String uwConnectivityDegree(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String connectivityDegree){
		model.put(WebConst.NAV_ITEM, "performance_testing");
		return WebConst.UW_CONNECTIVITY_DEGREE;
	}
}
