package com.seu.wsn.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.SensorController.ServerController;
import com.seu.wsn.Service.UwDataService;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.SystemSettingService;
import com.seu.wsn.Service.TestInfoService;
import com.seu.wsn.Service.UwTestingService;

import com.seu.wsn.SensorController.UwServerController;
import com.seu.wsn.Service.UwNodeService;
/**
 * 
 * @ClassName: SystemSettingController 
 * @Description: 系统设置
 * @author: CSS
 * @date: 2016-11-14 下午3:15:02
 */
@Controller
public class SystemSettingController {
	private ServerSocket serverSocket = null;
	@Autowired
	private NodeService nodeService;
	@Autowired
	private TestInfoService testInfoService;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private UwTestingService uwTestingService;
	
	@Autowired
	private UwNodeService uwNodeService;
	@Autowired
	private UwDataService uwDataService;
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
	
	public void setUwNodeService(UwNodeService uwNodeService) {
		this.uwNodeService = uwNodeService;
	}
	public void setUwDataService(UwDataService uwDataService) {
		this.uwDataService = uwDataService;
	}
	public void setUwTestingService(UwTestingService uwTestingService) {
		this.uwTestingService = uwTestingService;
	}

	@RequestMapping("/uwStartConnection")
	@ResponseBody
	public ModelMap uwStartConnection(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
//		if(serverSocket==null){
//			try {
//				serverSocket = new ServerSocket(WebConst.PORT);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		uwDataService.delete();
//		//uwNodeService.delete();
//		Order.startConnection = true;
//		Order.now = 0;
//		Order.timer = new Timer(true);
//		Order.timer.schedule(new TimerTask() {
//			public void run() {
//				Order.now += 0.1;
//				Order.now = (double)Math.round(Order.now*10)/10;
//				System.out.println(Order.now);
//			}
//		}, 5000, 100);
//		new Thread(new UwServerController(uwDataService, uwNodeService, serverSocket)).start();
//		req.getSession().setAttribute(WebConst.START_CONN, "true");
//		return model;
		String testId = UUID.randomUUID().toString();
		Order.testId = testId;
		req.getSession().setAttribute(WebConst.TESTID, testId);
		String userName = (String)req.getSession().getAttribute(WebConst.USERNAME);
		String testName = (String)req.getSession().getAttribute(WebConst.TESTNAME);
		testInfoService.insertTestInfo(testName, userName);
		if(serverSocket==null){
			try {
				serverSocket = new ServerSocket(WebConst.PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Order.startConnection = true;
		Order.now = 0;
		Order.timer = new Timer(true);
		Order.timer.schedule(new TimerTask() {
			public void run() {
				Order.now += 0.1;
				Order.now = (double)Math.round(Order.now*10)/10;
				//System.out.println(Order.now);
			}
		}, 5000, 100);
		new Thread(new UwServerController(testId,uwDataService,nodeService,serverSocket,uwTestingService)).start();
		req.getSession().setAttribute(WebConst.START_CONN, "true");
		return model;
	}
	
	@RequestMapping("/uwStopConnection")
	@ResponseBody
	public ModelMap uwStopConnection(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
//		Order.startConnection = false;
//		if (Order.timer != null) {
//			Order.timer.cancel();
//		}
//		req.getSession().setAttribute(WebConst.START_CONN, "false");
//		
//		return model;
		Order.startConnection = false;
		if (Order.timer != null) {
			Order.timer.cancel();
		}
		req.getSession().setAttribute(WebConst.TESTID, "");
		req.getSession().setAttribute(WebConst.START_CONN, "false");
		Order.packetLossTestNum = 0;
		Order.timeDelayTestNum = 0;
		Order.throughputTestNum = 0;
		Order.connectivityDegreeTestNum = 0;
		Order.pathLengthTestNum = 0;
		Order.netWorkingFlag = false;
		
		systemSettingService.setOrder(orderType,nodeService);
		try {
			new Socket("localhost",WebConst.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {    
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 
	 * @Title: startConnection 
	 * @Description: 启动连接
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/startConnection")
	@ResponseBody
	public ModelMap startConnection(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		String testId = UUID.randomUUID().toString();
		//String testId = "2b0a21ce-83ea-43df-99e3-ee33c72a4db3";
		Order.testId = testId;
		//req.getSession().setAttribute(WebConst.TESTID, "6ae39f95-9465-4fcf-8abd-71797171ccf4");
		req.getSession().setAttribute(WebConst.TESTID, testId);
		String userName = (String)req.getSession().getAttribute(WebConst.USERNAME);
		String testName = (String)req.getSession().getAttribute(WebConst.TESTNAME);
		testInfoService.insertTestInfo(testName, userName);
		if(serverSocket==null){
			try {
				serverSocket = new ServerSocket(WebConst.PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Order.startConnection = true;
		new Thread(new ServerController(testId,nodeService,serverSocket)).start();
		req.getSession().setAttribute(WebConst.START_CONN, "true");
		return model;
	}
	/**
	 * 
	 * @Title: stopConnection 
	 * @Description: 关闭连接
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/stopConnection")
	@ResponseBody
	public ModelMap stopConnection(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		Order.startConnection = false;
		req.getSession().setAttribute(WebConst.TESTID, "");
		req.getSession().setAttribute(WebConst.START_CONN, "false");
		Order.packetLossTestNum = 0;
		Order.timeDelayTestNum = 0;
		Order.throughputTestNum = 0;
		Order.connectivityDegreeTestNum = 0;
		Order.pathLengthTestNum = 0;
		Order.netWorkingFlag = false;
		
		systemSettingService.setOrder(orderType,nodeService);
		try {
			new Socket("localhost",WebConst.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {    
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 
	 * @Title: stopProgram 
	 * @Description: 停止下层程序
	 * @param req
	 * @param resp
	 * @param model
	 * @param orderType
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/stopProgram")
	@ResponseBody
	public ModelMap stopProgram(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String orderType){
		Order.packetLossTestNum = 0;
		Order.timeDelayTestNum = 0;
		Order.throughputTestNum = 0;
		Order.connectivityDegreeTestNum = 0;
		Order.pathLengthTestNum = 0;
		Order.netWorkingFlag = false;
		
		systemSettingService.setOrder(orderType,nodeService);
		return model;
	}
}
