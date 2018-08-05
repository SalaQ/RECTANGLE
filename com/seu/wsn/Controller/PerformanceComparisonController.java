package com.seu.wsn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.wsn.Core.Pojo.Data;
import com.seu.wsn.Core.Pojo.PointInfo;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.TestInfo;
import com.seu.wsn.Core.Pojo.TimeDelay;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.TestInfoService;
import com.seu.wsn.Service.TestingService;
/**
 * 
 * @ClassName: PerformanceComparisonController 
 * @Description: 性能对比控制器
 * @author: CSS
 * @date: 2016-12-14 下午2:27:57
 */
@Controller
public class PerformanceComparisonController {
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
	 * @Title: comparisonPacketLoss 
	 * @Description: 丢包率性能对比
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/comparisonPacketLoss")
	public String comparisonPacketLoss(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "performanceComparison");
		return WebConst.COMPARISON_PACKET_LOSS;
	}
	/**
	 * 
	 * @Title: comparisonTimeDelay 
	 * @Description: 时延性能对比
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/comparisonTimeDelay")
	public String comparisonTimeDelay(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		model.put(WebConst.NAV_ITEM, "performanceComparison");
		return WebConst.COMPARISON_TIME_DELAY;
	}
	
	/**
	 * 
	 * @Title: getHistoryPacketLossList 
	 * @Description: 获取选中的测试名称的丢包率信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param testIdList
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryPacketLossList")
	@ResponseBody
	public ModelMap getHistoryPacketLossList(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String testIdList){
		String[] list =  testIdList.split("&");
		List<ArrayList<SystemPacketLoss>> sysList = new ArrayList<ArrayList<SystemPacketLoss>>();
		//List<ArrayList<PointInfo>> pointInfoList = new ArrayList<ArrayList<PointInfo>>();
		//List<ArrayList<Double>> yInfoByLengthList = new ArrayList<ArrayList<Double>>();
		//List<ArrayList<Double>> yInfoByIntervalList = new ArrayList<ArrayList<Double>>();
		List<ArrayList<Double>> yInfoList = new ArrayList<ArrayList<Double>>();
		List<HashMap<Integer,Double>> listByLength = new ArrayList<HashMap<Integer,Double>>();
		List<HashMap<Integer,Double>> listByInterval = new ArrayList<HashMap<Integer,Double>>();
		List<Data> dataListByLength = new ArrayList<Data>();
		ArrayList<PointInfo> pointInfo = new ArrayList<PointInfo>();
		ArrayList<Double> yInfoByLength = new ArrayList<Double>() ;
		ArrayList<Double> yInfoByInterval = new ArrayList<Double>();
		ArrayList<Double> yInfo = new ArrayList<Double>();
		List<Data> dataListByInterval = new ArrayList<Data>();
		for(int i=1;i<list.length;i++){
			HashMap<Integer,Double> sysPacketLossByLength = new HashMap<Integer,Double>();
			HashMap<Integer,Double> sysPacketLossByInterval = new HashMap<Integer,Double>();
			ArrayList<SystemPacketLoss> sysPacketLossList = testingService.getSysPacketLossList(sysPacketLossByLength,sysPacketLossByInterval,list[i]);
			if(sysPacketLossByLength!=null && sysPacketLossByLength.size()!=0 && sysPacketLossByInterval!=null && sysPacketLossByInterval.size()!=0){
				sysList.add(sysPacketLossList);
				listByLength.add(sysPacketLossByLength);
				listByInterval.add(sysPacketLossByInterval);
				TestInfo testInfo = testInfoService.selectTestInfo(list[i]);
				Data dataByLength = new Data();
				Data dataByInterval = new Data();
				dataByLength.setName(testInfo.getTestName());
				dataByInterval.setName(testInfo.getTestName());
				dataListByLength.add(dataByLength);
				dataListByInterval.add(dataByInterval);
			}
		}
		if(sysList.size()!=0){
			for(int i=0;i<sysList.size();i++){
				testingService.getXY(listByLength.get(i), listByInterval.get(i), pointInfo, yInfoByLength, yInfoByInterval, yInfo);
				dataListByLength.get(i).setData(yInfoByLength);
				dataListByInterval.get(i).setData(yInfoByInterval);
				//pointInfoList.add(pointInfo);
				//yInfoByLengthList.add(yInfoByLength);
				//yInfoByIntervalList.add(yInfoByInterval);
				yInfoList.add(yInfo);
				//pointInfo = new ArrayList<PointInfo>();
				yInfoByLength = new ArrayList<Double>();
				yInfoByInterval = new ArrayList<Double>();
				yInfo = new ArrayList<Double>();
			}
			yInfo = testingService.getYInfo(yInfoList);
			model.put("pointInfo", pointInfo);
			model.put("yInfo", yInfo);
			model.put("dataListByLength", dataListByLength);
			model.put("dataListByInterval", dataListByInterval);
			model.put("data", true);
			
		}else{
			model.put("data", false);
		}
		
		return model;
	}
	
	
	/**
	 * 
	 * @Title: getHistoryTimeDelayList 
	 * @Description: 获取选中的测试名称的时延信息
	 * @param req
	 * @param resp
	 * @param model
	 * @param testIdList
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/getHistoryTimeDelayList")
	@ResponseBody
	public ModelMap getHistoryTimeDelayList(HttpServletRequest req,HttpServletResponse resp,ModelMap model,String testIdList){
		String[] list =  testIdList.split("&");
		List<List<TimeDelay>> timedelayList = new ArrayList<List<TimeDelay>>();
		List<ArrayList<Double>> yInfoList = new ArrayList<ArrayList<Double>>();
		List<HashMap<Integer,Integer>> mapTimeDelayByList = new ArrayList<HashMap<Integer,Integer>>();
		List<Data> dataListByLength = new ArrayList<Data>();
		PointInfo point = new PointInfo();
		for(int i=1;i<list.length;i++){
			ArrayList<Double> yInfoByLength = new ArrayList<Double>() ;
			ArrayList<Double> yInfo = new ArrayList<Double>();
			HashMap<Integer,Integer> mapTimeDelay = new HashMap<Integer,Integer>();
			List<TimeDelay> delayList = testingService.selectTimeDelayList(list[i]);
			if(delayList!=null && delayList.size()!=0){
				timedelayList.add(delayList);
				testingService.getNetTimeDelayList(mapTimeDelay,delayList);
				if(mapTimeDelay.size()!=0){
					testingService.getXY(mapTimeDelay, point, yInfoByLength,yInfo);
					mapTimeDelayByList.add(mapTimeDelay);
					yInfoList.add(yInfo);
					TestInfo testInfo = testInfoService.selectTestInfo(list[i]);
					Data dataByLength = new Data();
					dataByLength.setName(testInfo.getTestName());
					dataByLength.setData(yInfoByLength);
					dataListByLength.add(dataByLength);
				}
			}
		}
		
		if(timedelayList.size()!=0){
			ArrayList<Double> yInfo = testingService.getYInfo(yInfoList);
			model.put("pointInfo", point);
			model.put("yInfo", yInfo);
			model.put("dataListByLength", dataListByLength);
			model.put("data", true);
		}else{
			model.put("data", false);
		}
		
		return model;
	}
	
}
