package com.seu.wsn.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
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

import com.seu.wsn.Common.Log4j.LoggerUtil;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.UwNodePacketLoss;
import com.seu.wsn.Core.Pojo.PathLength;
import com.seu.wsn.Core.Pojo.UwPathLength;
import com.seu.wsn.Core.Pojo.UwTimeDelay;
import com.seu.wsn.Core.Pojo.PointInfo;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.Throughput;
import com.seu.wsn.Core.Pojo.UwThroughput;
import com.seu.wsn.Core.Pojo.TimeDelay;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.TestingService;
import com.seu.wsn.Service.UwTestingService;
import com.seu.wsn.Service.SystemSettingService;

/**
 * 
 * @ClassName: PerformTestingController 
 * @Description: 性能测试
 * @author: CSS
 * @date: 2016-11-19 上午10:10:47
 */
@Controller
public class PerformTestingController {
	@Autowired
	private NodeService nodeService;
	@Autowired
	private SystemSettingService systemSettingService;
	
	@Autowired
	private TestingService testingService;
	@Autowired
	private UwTestingService uwTestingService;
	/**
	 * 
	 * @Title: setPacketLossService 
	 * @Description: 注入packetLossService
	 * @param packetLossService
	 * @return: void
	 */
	public void setTestingService(TestingService testingService) {
		this.testingService = testingService;
	}
	public void setUwTestingService(UwTestingService uwTestingService) {
		this.uwTestingService = uwTestingService;
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
	 * @Title: setOrderSettingService 
	 * @Description: 注入orderSettingService
	 * @param orderSettingService
	 * @return: void
	 */
	public void setOrderSettingService(SystemSettingService systemSettingService) {
		this.systemSettingService = systemSettingService;
	}
	/**
	 * 
	 * @Title: modifyNodeInformation 
	 * @Description: 修改节点类型
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/modifyNodeInformation")
	@ResponseBody
	public ModelMap modifyNodeInformation(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
	/**
	 * 
	 * @Title: downloadProtocol 
	 * @Description: 下载协议文件
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/downloadProtocol")
	@ResponseBody
	public ModelMap downloadProtocol(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
	
	/**
	 * 
	 * @Title: packetLossTesting 
	 * @Description: 丢包率测试
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/packetLossTesting")
	@ResponseBody
	public ModelMap packetLossTesting(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String cycleLength,String cycleNumber,String cycleInterval,String packetLength,String packetInterval,
			String packetSendSpeed){
		Order.testingEndFlag = false;
		int testTime = Integer.valueOf(cycleNumber)*Integer.valueOf(cycleLength)+(Integer.valueOf(cycleNumber)-1)*Integer.valueOf(cycleInterval);
		req.getSession().setAttribute(WebConst.TEST_TIME, testTime);
		
		Order.cycleLength = Byte.parseByte(cycleLength);
		Order.cycleNumber = Byte.parseByte(cycleNumber);
		Order.cycleInterval = Byte.parseByte(cycleInterval);
		Order.packetLength = Byte.parseByte(packetLength);
		Order.packetInterval = Byte.parseByte(packetInterval);
		Order.packetSendSpeed = Double.parseDouble(packetSendSpeed);
		Order.packetLossTestNum = Order.packetLossTestNum+1;
		systemSettingService.setOrder(orderType,"packetLoss",nodeService,testingService,systemSettingService);
		return model;
	}
	@RequestMapping("/uwPacketLossTesting")
	@ResponseBody
	public ModelMap uwPacketLossTesting(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String packetNumber,String packetInterval) {
		Order.testingEndFlag = false;
		//int testTime = Integer.valueOf(cycleNumber)*Integer.valueOf(cycleLength)+(Integer.valueOf(cycleNumber)-1)*Integer.valueOf(cycleInterval);
		//req.getSession().setAttribute(WebConst.TEST_TIME, testTime);
		
		Order.uwPacketNumber = Byte.parseByte(packetNumber);
		Order.uwPacketInterval = Byte.parseByte(packetInterval);
		Order.uwPacketLossTestNum = Order.uwPacketLossTestNum+1;
		Collection<Socket> c = Order.uwSocketList.values();
		OutputStream out = null;
		for (Socket s : c) {
			try {
				out = s.getOutputStream();
				byte[] b = new byte[6];
				b[0] = (byte)254;
				b[1] = (byte)2;
				b[2] = (byte)3;
				b[3] = Order.uwPacketNumber;
				b[4] = Order.uwPacketInterval;
				b[5] = (byte)15;
				out.write(b);
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：PerformTestingController;方法：uwPacketLossTesting");
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: timeDelayTesting 
	 * @Description: 网络时延测试
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/timeDelayTesting")
	@ResponseBody
	public ModelMap timeDelayTesting(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String cycleLength,String packetLength){
		int testTime = Integer.valueOf(cycleLength);
		req.getSession().setAttribute(WebConst.TEST_TIME, testTime);
		Order.delay_testTime = testTime+7;
		Order.testingEndFlag = false;
		Order.cycleLength = Byte.parseByte(cycleLength);
		Order.packetLength = Byte.parseByte(packetLength);
		Order.timeDelayTestNum = Order.timeDelayTestNum+1;
		Order.dealyMap.clear();
		systemSettingService.setOrder(orderType,"timeDelay",systemSettingService,testingService);
		return model;
	}
	@RequestMapping("/uwTimeDelayTesting")
	@ResponseBody
	public ModelMap uwTimeDelayTesting(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String packetNumber,String packetInterval){
		Order.testingEndFlag = false;
		
		Order.uwPacketNumber = Byte.parseByte(packetNumber);
		Order.uwPacketInterval = Byte.parseByte(packetInterval);
		Order.uwTimeDelayTestNum++;
		Order.delayNow = Order.now;
		Collection<Socket> c = Order.uwSocketList.values();
		OutputStream out = null;
		for (Socket s : c) {
			try {
				out = s.getOutputStream();
				byte[] b = new byte[6];
				b[0] = (byte)254;
				b[1] = (byte)2;
				b[2] = (byte)4;
				b[3] = Order.uwPacketNumber;
				b[4] = Order.uwPacketInterval;
				b[5] = (byte)15;
				out.write(b);
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：PerformTestingController;方法：uwPacketLossTesting");
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: networkThroughput 
	 * @Description: 网络吞吐量测试
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/networkThroughput")
	@ResponseBody
	public ModelMap networkThroughput(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String cycleLength,String cycleNumber,String cycleInterval,String packetLength,String packetInterval,
			String packetSendSpeed){
		int testTime = Integer.valueOf(cycleNumber)*Integer.valueOf(cycleLength)+(Integer.valueOf(cycleNumber)-1)*Integer.valueOf(cycleInterval);
		req.getSession().setAttribute(WebConst.TEST_TIME, testTime);
		Order.testingEndFlag = false;
		Order.cycleLength = Byte.parseByte(cycleLength);
		Order.cycleNumber = Byte.parseByte(cycleNumber);
		Order.cycleInterval = Byte.parseByte(cycleInterval);
		Order.packetLength = Byte.parseByte(packetLength);
		Order.packetInterval = Byte.parseByte(packetInterval);
		Order.packetSendSpeed = Double.parseDouble(packetSendSpeed);
		Order.throughputTestNum = Order.throughputTestNum+1;
		systemSettingService.setOrder(orderType,"throughput",nodeService,testingService,systemSettingService);
		return model;
	}
	@RequestMapping("/uwNetworkThroughput")
	@ResponseBody
	public ModelMap uwNetworkThroughput(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String packetNumber,String packetInterval){
		Order.testingEndFlag = false;
		
		Order.uwPacketNumber = Byte.parseByte(packetNumber);
		Order.uwPacketInterval = Byte.parseByte(packetInterval);
		Order.uwThroughputTestNum++;
		Order.delayNow = Order.now;
		Collection<Socket> c = Order.uwSocketList.values();
		OutputStream out = null;
		for (Socket s : c) {
			try {
				out = s.getOutputStream();
				byte[] b = new byte[6];
				b[0] = (byte)254;
				b[1] = (byte)2;
				b[2] = (byte)5;
				b[3] = Order.uwPacketNumber;
				b[4] = Order.uwPacketInterval;
				b[5] = (byte)15;
				out.write(b);
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：PerformTestingController;方法：uwPacketLossTesting");
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: averagePathLength 
	 * @Description: 网络平均路径长度测试
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/averagePathLength")
	@ResponseBody
	public ModelMap averagePathLength(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String cycleLength,String cycleNumber,String cycleInterval){
		int testTime = Integer.valueOf(cycleNumber)*Integer.valueOf(cycleLength)+(Integer.valueOf(cycleNumber)-1)*Integer.valueOf(cycleInterval);
		req.getSession().setAttribute(WebConst.TEST_TIME, testTime);
		Order.testingEndFlag = false;
		Order.cycleLength = Byte.parseByte(cycleLength);
		Order.cycleNumber = Byte.parseByte(cycleNumber);
		Order.cycleInterval = Byte.parseByte(cycleInterval);
		Order.pathLengthTestNum = Order.pathLengthTestNum + 1;
		systemSettingService.setOrder(orderType,"averagePathLength",nodeService,testingService,systemSettingService);
		return model;
	}
	@RequestMapping("/uwAveragePathLength")
	@ResponseBody
	public ModelMap uwAveragePathLength(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType,
			String packetNumber,String packetInterval){
		Order.testingEndFlag = false;
		
		Order.uwPacketNumber = Byte.parseByte(packetNumber);
		Order.uwPacketInterval = Byte.parseByte(packetInterval);
		Order.uwPathLengthTestNum = Order.uwPathLengthTestNum+1;
		Collection<Socket> c = Order.uwSocketList.values();
		OutputStream out = null;
		for (Socket s : c) {
			try {
				out = s.getOutputStream();
				byte[] b = new byte[6];
				b[0] = (byte)254;
				b[1] = (byte)2;
				b[2] = (byte)6;
				b[3] = Order.uwPacketNumber;
				b[4] = Order.uwPacketInterval;
				b[5] = (byte)15;
				out.write(b);
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：PerformTestingController;方法：uwPacketLossTesting");
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: connectivityDegree 
	 * @Description: 网络联通度测试
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/connectivityDegree")
	@ResponseBody
	public ModelMap connectivityDegree(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		LoggerUtil.logger.info("连通度测试开始**********************************************");
		Order.connectivityDegreeTestNum = Order.connectivityDegreeTestNum +1;
		systemSettingService.setOrder(orderType,"connectivityDegree",nodeService,testingService,systemSettingService);
		return model;
	}
	@RequestMapping("/uwConnectivityDegree")
	@ResponseBody
	public ModelMap uwConnectivityDegree(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		Order.testingEndFlag = false;
		
		Order.uwConnectivityDegreeTestNum++;
		Collection<Socket> c = Order.uwSocketList.values();
		OutputStream out = null;
		for (Socket s : c) {
			try {
				out = s.getOutputStream();
				byte[] b = new byte[4];
				b[0] = (byte)254;
				b[1] = (byte)2;
				b[2] = (byte)7;
				b[3] = (byte)15;
				out.write(b);
			} catch (IOException e) {
				LoggerUtil.logger.error("出错，   类：PerformTestingController;方法：uwPacketLossTesting");
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: getNodePacektLossInfo 
	 * @Description: 获取节点丢包率信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getNodePacektLossInfo")
	@ResponseBody
	public ModelMap getNodePacektLossInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<NodePacketLoss> packetLossList = testingService.selectPacketLossList(Order.testId);
		model.put("testingEndFlag",Order.testingEndFlag);
		model.put("nodePacketLossList",packetLossList);
		return model;
	}
	@RequestMapping("/getUwNodePacektLossInfo")
	@ResponseBody
	public ModelMap getUwNodePacektLossInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<UwNodePacketLoss> packetLossList = uwTestingService.selectPacketLossList(Order.testId);
		model.put("testingEndFlag",Order.testingEndFlag);
		model.put("nodePacketLossList",packetLossList);
		return model;
	}
	/**
	 * 
	 * @Title: getSysPacketLossInfo 
	 * @Description: 获取系统丢包率信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getSysPacketLossInfo")
	@ResponseBody
	public ModelMap getSysPacketLossInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		
		Map<Integer,Double> sysPacketLossByLength = new HashMap<Integer,Double>();
		Map<Integer,Double> sysPacketLossByInterval = new HashMap<Integer,Double>();
		List<PointInfo> pointInfo = new ArrayList<PointInfo>();
		List<Double> yInfoByLength = new ArrayList<Double>();
		List<Double> yInfoByInterval = new ArrayList<Double>();
		List<Double> yInfo = new ArrayList<Double>();
		List<SystemPacketLoss> sysPacketLossList = testingService.getSysPacketLossList(sysPacketLossByLength,sysPacketLossByInterval,Order.testId);
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
	 * @Title: getTimeDelayInfo 
	 * @Description: 获取时延测试结果信息列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getTimeDelayInfo")
	@ResponseBody
	public ModelMap getTimeDelayInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		Map<Integer,Integer> mapTimeDelay = new HashMap<Integer,Integer>();
		List<TimeDelay> delayList = testingService.selectTimeDelayList(Order.testId);
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
		model.put("testingEndFlag",Order.testingEndFlag);
		return model;
	}
	@RequestMapping("/getUwTimeDelayInfo")
	@ResponseBody
	public ModelMap getUwTimeDelayInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<UwTimeDelay> delayList = uwTestingService.selectTimeDelayList(Order.testId);
		double netTimeDelay = 0;
		for (int i = 0; i < delayList.size(); i++) {
			netTimeDelay += delayList.get(i).getTimeDelay();
		}
		netTimeDelay = netTimeDelay / delayList.size();
		if(delayList!=null && delayList.size()!=0){
			model.put("netTimeDelay", netTimeDelay);
			model.put("timeDelayList", delayList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		return model;
	}
	/**
	 * 
	 * @Title: getThroughputInfo 
	 * @Description: 获取吞吐量测试结果信息列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getThroughputInfo")
	@ResponseBody
	public ModelMap getThroughputInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<Throughput> throughputList = testingService.selectThroughputList(Order.testId);
		if(throughputList!=null && throughputList.size()!=0){
			model.put("throughputList", throughputList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}
	@RequestMapping("/getUwThroughputInfo")
	@ResponseBody
	public ModelMap getUwThroughputInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<UwThroughput> throughputList = uwTestingService.selectThroughputList(Order.testId);
		if(throughputList!=null && throughputList.size()!=0){
			model.put("throughputList", throughputList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}
	/**
	 * 
	 * @Title: getPathLengthInfo 
	 * @Description: 获取平均路径长度测试结果信息列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getPathLengthInfo")
	@ResponseBody
	public ModelMap getPathLengthInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<PathLength> pathLengthList = testingService.selectPathLengthList(Order.testId);
		if(pathLengthList!=null && pathLengthList.size()!=0){
			int avePathLength = testingService.getAveLength(pathLengthList);
			model.put("avePathLength", avePathLength);
			model.put("pathLengthList", pathLengthList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}
	@RequestMapping("/getUwPathLengthInfo")
	@ResponseBody
	public ModelMap getUwPathLengthInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<UwPathLength> pathLengthList = uwTestingService.selectUwPathLengthList(Order.testId);
		if(pathLengthList!=null && pathLengthList.size()!=0){
			model.put("avePathLength", 2);
			model.put("pathLengthList", pathLengthList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}
	/**
	 * 
	 * @Title: getConnectivityDegreeInfo 
	 * @Description: 获取联通度测试结果信息列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getConnectivityDegreeInfo")
	@ResponseBody
	public ModelMap getConnectivityDegreeInfo(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		List<ConnectivityDegree> connectivityDegreeList = testingService.selectConnectivityDegreeList(Order.testId);
		if(connectivityDegreeList!=null && connectivityDegreeList.size()!=0){
			model.put("connectivityDegreeList", connectivityDegreeList);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		model.put("testingEndFlag",Order.testingEndFlag);
		
		return model;
	}

}
