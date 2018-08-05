package com.seu.wsn.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Dao.ConnectivityDegreeDao;
import com.seu.wsn.Common.Dao.PacketLossDao;
import com.seu.wsn.Common.Dao.PathLengthDao;
import com.seu.wsn.Common.Dao.ThroughputDao;
import com.seu.wsn.Common.Dao.TimeDelayDao;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.PathLength;
import com.seu.wsn.Core.Pojo.PointInfo;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.Throughput;
import com.seu.wsn.Core.Pojo.TimeDelay;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;
/**
 * 
 * @ClassName: PacketLossServiceImpl 
 * @Description: 节点丢包率业务逻辑层实现类
 * @author: CSS
 * @date: 2016-11-29 下午2:40:22
 */
@Service("testingService")
public class TestingServiceImpl implements TestingService{
	@Autowired
	private PacketLossDao    			packetLossDao;
	@Autowired
	private TimeDelayDao     			timeDelayDao;
	@Autowired
	private ThroughputDao    			throughputDao;
	@Autowired
	private PathLengthDao    			pathLengthDao;
	@Autowired
	private ConnectivityDegreeDao 		connectivityDegreeDao;
	
	/**
	 * 
	 * @Title: setTimeDelayDao 
	 * @Description: 注入timeDelayDao
	 * @param timeDelayDao
	 * @return: void
	 */
	public void setTimeDelayDao(TimeDelayDao timeDelayDao) {
		this.timeDelayDao = timeDelayDao;
	}
	/**
	 * 
	 * @Title: setThroughputDao 
	 * @Description: 注入throughputDao
	 * @param throughputDao
	 * @return: void
	 */
	public void setThroughputDao(ThroughputDao throughputDao) {
		this.throughputDao = throughputDao;
	}
	/**
	 * 
	 * @Title: setPathLengthDao 
	 * @Description: 注入pathLengthDao
	 * @param pathLengthDao
	 * @return: void
	 */
	public void setPathLengthDao(PathLengthDao pathLengthDao) {
		this.pathLengthDao = pathLengthDao;
	}
	/**
	 * 
	 * @Title: setConnectivityDegreeDao 
	 * @Description: 注入connectivityDegreeDao
	 * @param connectivityDegreeDao
	 * @return: void
	 */
	public void setConnectivityDegreeDao(ConnectivityDegreeDao connectivityDegreeDao) {
		this.connectivityDegreeDao = connectivityDegreeDao;
	}

	/**
	 * 
	 * @Title: setPacketLossDao 
	 * @Description: 注入packetLossDao
	 * @param packetLossDao
	 * @return: void
	 */
	public void setPacketLossDao(PacketLossDao packetLossDao) {
		this.packetLossDao = packetLossDao;
	}
/**********************************************丢包率测试*********************************/
	
	/*
	 * (non Javadoc) 
	 * @Title: getCycleNum
	 * @Description: 获取每次测试的测试周期数
	 * @param packetLoss
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#getCycleNum(com.seu.wsn.Core.Pojo.NodePacketLoss)
	 */
	@Override
	public int getCycleNum(NodePacketLoss packetLoss) {
		return packetLossDao.getCycleNum(packetLoss);
	}
	
	
	/*
	 * (non Javadoc) 
	 * @Title: insertPacketLoss
	 * @Description: 新增节点丢包率测试结果
	 * @param packetLoss 
	 * @see com.seu.wsn.Service.TestingService#insertPacketLoss(com.seu.wsn.Core.Pojo.NodePacketLoss)
	 */
	@Override
	public void insertPacketLoss(NodePacketLoss packetLoss) {
		packetLossDao.insertPacketLoss(packetLoss);
	}

	
	/*
	 * (non Javadoc) 
	 * @Title: deletePacketLoss
	 * @Description: 删除本次丢包率测试结果
	 * @param testId 
	 * @see com.seu.wsn.Service.TestingService#deletePacketLoss(java.lang.String)
	 */
	@Override
	public void deletePacketLoss(String testId) {
		packetLossDao.deletePacketLoss(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectPacketLossList
	 * @Description: 获取丢包率列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#selectPacketLossList(java.lang.String)
	 */
	@Override
	public List<NodePacketLoss> selectPacketLossList(String testId) {
		return packetLossDao.selectPacketLossList(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: getSysPacketLossList
	 * @Description: 获取系统丢包率相关信息
	 * @param sysPacketLossByLength
	 * @param sysPacketLossByInterval
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#getSysPacketLossList(java.util.Map, java.util.Map, java.lang.String)
	 */
	@Override
	public ArrayList<SystemPacketLoss> getSysPacketLossList(
			Map<Integer, Double> sysPacketLossByLength,Map<Integer,Double> sysPacketLossByInterval,String testId) {
		List<NodePacketLoss> packetLossList = packetLossDao.selectPacketLossList(testId);
		ArrayList<SystemPacketLoss> sysPacketLossList = new ArrayList<SystemPacketLoss>();
		if(packetLossList!=null && packetLossList.size()>0){
			int sumRecievePacket=0;
			int sumSendPacket=0;
			double cyclePacketLoss=0;
			double sumPacketLoss=0;
			int testNum = 1;
			int cycleNum = 1;
			for(int i=0;i<packetLossList.size();i++){
				if(testNum==packetLossList.get(i).getTestNum()){
					if(cycleNum==packetLossList.get(i).getCycleNum()){
						if(WebConst.COMMON_NODE.equals(packetLossList.get(i).getNodeType())){
							sumSendPacket= sumSendPacket+packetLossList.get(i).getSendPacket();
						}else if(WebConst.GATEWAY_NODE.equals(packetLossList.get(i).getNodeType())){
							sumRecievePacket = packetLossList.get(i).getRecievePacket();
						}
					}else{
						cyclePacketLoss = (sumSendPacket-sumRecievePacket)*1.0/sumSendPacket;
						cyclePacketLoss = Double.valueOf(String .format("%.3f",cyclePacketLoss));
						sumPacketLoss = sumPacketLoss+cyclePacketLoss;
						SystemPacketLoss loss = new SystemPacketLoss();
						loss.setCycleNum(cycleNum);
						loss.setSendSpeed(packetLossList.get(i).getSendSpeed());
						loss.setSumRecievePacket(sumRecievePacket);
						loss.setSunSendPacket(sumSendPacket);
						loss.setSystemPakcetLoss(cyclePacketLoss);
						loss.setTestId(Order.testId);
						loss.setTestNum(testNum);
						sysPacketLossList.add(loss);
						cycleNum = packetLossList.get(i).getCycleNum();
						sumSendPacket=0;
						sumRecievePacket=0;
						if(WebConst.COMMON_NODE.equals(packetLossList.get(i).getNodeType())){
							sumSendPacket= sumSendPacket+packetLossList.get(i).getSendPacket();
						}else if(WebConst.GATEWAY_NODE.equals(packetLossList.get(i).getNodeType())){
							sumRecievePacket = packetLossList.get(i).getRecievePacket();
						}
					}
				}else{
					cyclePacketLoss = (sumSendPacket-sumRecievePacket)*1.0/sumSendPacket;
					cyclePacketLoss = Double.valueOf(String .format("%.3f",cyclePacketLoss));
					sumPacketLoss = sumPacketLoss+cyclePacketLoss;
					SystemPacketLoss loss = new SystemPacketLoss();
					loss.setCycleNum(cycleNum);
					loss.setSendSpeed(packetLossList.get(i-1).getSendSpeed());
					loss.setSumRecievePacket(sumRecievePacket);
					loss.setSunSendPacket(sumSendPacket);
					loss.setSystemPakcetLoss(cyclePacketLoss);
					loss.setTestId(Order.testId);
					loss.setTestNum(testNum);
					sysPacketLossList.add(loss);
					sumSendPacket=0;
					sumRecievePacket=0;
					sumPacketLoss = sumPacketLoss/cycleNum;
					sumPacketLoss = Double.valueOf(String .format("%.3f",sumPacketLoss));
					sysPacketLossByLength.put(packetLossList.get(i-1).getPacketLength(),sumPacketLoss);
					sysPacketLossByInterval.put(packetLossList.get(i-1).getPacketInterval(), sumPacketLoss);
					sumPacketLoss = 0;
					cycleNum = 1;
					testNum=packetLossList.get(i).getTestNum();
					if(WebConst.COMMON_NODE.equals(packetLossList.get(i).getNodeType())){
						sumSendPacket= sumSendPacket+packetLossList.get(i).getSendPacket();
					}else if(WebConst.GATEWAY_NODE.equals(packetLossList.get(i).getNodeType())){
						sumRecievePacket = packetLossList.get(i).getRecievePacket();
					}
				}
			}
			cyclePacketLoss = (sumSendPacket-sumRecievePacket)*1.0/sumSendPacket;
			cyclePacketLoss = Double.valueOf(String .format("%.3f",cyclePacketLoss));
			sumPacketLoss = sumPacketLoss+cyclePacketLoss;
			SystemPacketLoss loss = new SystemPacketLoss();
			loss.setCycleNum(cycleNum);
			loss.setSendSpeed(packetLossList.get(packetLossList.size()-1).getSendSpeed());
			loss.setSumRecievePacket(sumRecievePacket);
			loss.setSunSendPacket(sumSendPacket);
			loss.setSystemPakcetLoss(cyclePacketLoss);
			loss.setTestNum(testNum);
			sysPacketLossList.add(loss);
			sumPacketLoss = sumPacketLoss/cycleNum;
			sumPacketLoss = Double.valueOf(String .format("%.3f",sumPacketLoss));
			sysPacketLossByLength.put(packetLossList.get(packetLossList.size()-1).getPacketLength(),sumPacketLoss);
			sysPacketLossByInterval.put(packetLossList.get(packetLossList.size()-1).getPacketInterval(), sumPacketLoss);
		}
		
		
		
		return sysPacketLossList;
	}
	/*
	 * (non Javadoc) 
	 * @Title: getXY
	 * @Description: 获得系统丢包率曲线图XY轴相关信息
	 * @param sysPacketLossByLength
	 * @param sysPacketLossByInterval
	 * @param pointInfo
	 * @param yInfoByLength
	 * @param yInfoByInterval
	 * @param yInfo 
	 * @see com.seu.wsn.Service.TestingService#getXY(java.util.Map, java.util.Map, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void getXY(Map<Integer, Double> sysPacketLossByLength,
			Map<Integer, Double> sysPacketLossByInterval,List<PointInfo> pointInfo,
			List<Double> yInfoByLength,List<Double> yInfoByInterval,List<Double> yInfo) {
		
		getListY(sysPacketLossByLength,yInfo,pointInfo,yInfoByLength);
		
		getListY(sysPacketLossByInterval,yInfo,pointInfo,yInfoByInterval);
		
	}
	/**
	 * 
	 * @Title: getListY 
	 * @Description: TODO
	 * @param sysPacketLoss
	 * @param yInfo
	 * @param pointInfo
	 * @return
	 * @return: List<Double>
	 */
	private void getListY(Map<Integer, Double> sysPacketLoss,List<Double> yInfo,List<PointInfo> pointInfo,
			List<Double> yInfoBy){
		int[] list = new int[sysPacketLoss.size()];
		Iterator<Map.Entry<Integer,Double>> it = sysPacketLoss.entrySet().iterator();
		int i=0;
		while(it.hasNext()){
			Map.Entry<Integer,Double> entry = (Map.Entry<Integer,Double>)it.next();
			list[i] = entry.getKey();
			i++;
		}
		Arrays.sort(list);
		PointInfo point = new PointInfo();
		point.setPointStart(list[0]);
		
		if(list.length>1){
			point.setPointInterval((list[list.length-1]-list[0])/(list.length-1));
		}else{
			point.setPointInterval(1);
		}
		pointInfo.add(point);
		double maxLoss = 0;
		for(int j=0;j<list.length;j++){
			if(maxLoss<sysPacketLoss.get(list[j])){
				maxLoss=sysPacketLoss.get(list[j]);
			}
			
		}
		double interval=0; 
		if(yInfo.size()<5){
			for(int j=0;j<4;j++){
				yInfo.add(interval);
				interval = interval+maxLoss/5;
				interval = Double.valueOf(String .format("%.3f",interval));
			}
			maxLoss = Double.valueOf(String .format("%.3f",maxLoss));
			yInfo.add(maxLoss);
		}
		int start = point.getPointStart();
		double tmpLoss = 0;
		yInfoBy.add(sysPacketLoss.get(start));
		for(int j=1;j<list.length;j++){
			tmpLoss = (sysPacketLoss.get(list[j])+sysPacketLoss.get(list[j-1]))/2;
			tmpLoss = Double.valueOf(String .format("%.3f",tmpLoss));
			yInfoBy.add(tmpLoss);
		}
	}
/***************************************时延测试*************************************************/	
	
	/*
	 * (non Javadoc) 
	 * @Title: insertTimeDelay
	 * @Description: 新增时延测试结果
	 * @param timeDelay 
	 * @see com.seu.wsn.Service.TestingService#insertTimeDelay(com.seu.wsn.Core.Pojo.TimeDelay)
	 */
	@Override
	public void insertTimeDelay(TimeDelay timeDelay) {
		timeDelayDao.insertTimeDelay(timeDelay);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteTimeDelay
	 * @Description: 删除时延测试结果
	 * @param testId 
	 * @see com.seu.wsn.Service.TestingService#deleteTimeDelay(java.lang.String)
	 */
	@Override
	public void deleteTimeDelay(String testId) {
		timeDelayDao.deleteTimeDelay(testId);
		
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectTimeDelayList
	 * @Description: 获取时延测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#selectTimeDelayList(java.lang.String)
	 */
	@Override
	public List<TimeDelay> selectTimeDelayList(String testId) {
		return timeDelayDao.selectTimeDelayList(testId);
	}
	
/*******************************************吞吐量测试***************************************/
	/*
	 * (non Javadoc) 
	 * @Title: insertThroughput
	 * @Description: 新增吞吐量测试结果
	 * @param throughput 
	 * @see com.seu.wsn.Service.TestingService#insertThroughput(com.seu.wsn.Core.Pojo.Throughput)
	 */
	@Override
	public void insertThroughput(Throughput throughput) {
		throughputDao.insertThroughput(throughput);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteThroughput
	 * @Description: 删除吞吐量测试结果
	 * @param testId 
	 * @see com.seu.wsn.Service.TestingService#deleteThroughput(java.lang.String)
	 */
	@Override
	public void deleteThroughput(String testId) {
		throughputDao.deleteThroughput(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectThroughputList
	 * @Description: 获取吞吐量测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#selectThroughputList(java.lang.String)
	 */
	@Override
	public List<Throughput> selectThroughputList(String testId) {
		return throughputDao.selectThroughputList(testId);
	}
/****************************************平均路径长度测试**********************************/	
	
	/*
	 * (non Javadoc) 
	 * @Title: insertPathLength
	 * @Description: 新增平均路径长度测试结果
	 * @param pathLength 
	 * @see com.seu.wsn.Service.TestingService#insertPathLength(com.seu.wsn.Core.Pojo.PathLength)
	 */
	@Override
	public void insertPathLength(PathLength pathLength) {
		pathLengthDao.insertPathLength(pathLength);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deletePathLength
	 * @Description: 删除平均路径长度测试结果
	 * @param testId 
	 * @see com.seu.wsn.Service.TestingService#deletePathLength(java.lang.String)
	 */
	@Override
	public void deletePathLength(String testId) {
		pathLengthDao.deletePathLength(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectPathLengthList
	 * @Description: 获取平均路径长度测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#selectPathLengthList(java.lang.String)
	 */
	@Override
	public List<PathLength> selectPathLengthList(String testId) {
		return pathLengthDao.selectPathLengthList(testId);
	}
/******************************************联通度测试********************************/	
	
	/*
	 * (non Javadoc) 
	 * @Title: insertConnectivityDegree
	 * @Description: 新增联通度测试结果
	 * @param connectivityDegree 
	 * @see com.seu.wsn.Service.TestingService#insertConnectivityDegree(com.seu.wsn.Core.Pojo.ConnectivityDegree)
	 */
	@Override
	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree) {
		connectivityDegreeDao.insertConnectivityDegree(connectivityDegree);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteConnectivityDegree
	 * @Description: 删除联通度测试结果
	 * @param testId 
	 * @see com.seu.wsn.Service.TestingService#deleteConnectivityDegree(java.lang.String)
	 */
	@Override
	public void deleteConnectivityDegree(String testId) {
		connectivityDegreeDao.deleteConnectivityDegree(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectConnectivityDegreeList
	 * @Description: 获取联通度测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#selectConnectivityDegreeList(java.lang.String)
	 */
	@Override
	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId) {
		return connectivityDegreeDao.selectConnectivityDegreeList(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: getNetTimeDelayList
	 * @Description: 获取时延列表相关信息
	 * @param mapTimeDelay
	 * @param delayList
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#getNetTimeDelayList(java.util.Map, java.util.List)
	 */
	@Override
	public int getNetTimeDelayList(Map<Integer, Integer> mapTimeDelay,
			List<TimeDelay> delayList) {
		int netTimeDelay = 0;
		int testNum = delayList.get(0).getTestNum();
		int sumDelay = 0;
		int num = 0;
		for(int i=0;i<delayList.size();i++){
			if(testNum==delayList.get(i).getTestNum()){
				sumDelay += delayList.get(i).getTimeDelay();
				num++;
			}else{
				mapTimeDelay.put(delayList.get(i-1).getPacketLength(), sumDelay/num);
				testNum = delayList.get(i).getTestNum();
				sumDelay = delayList.get(i).getTimeDelay();
				num = 1;
			}
			if(i==delayList.size()-1){
				mapTimeDelay.put(delayList.get(i).getPacketLength(), sumDelay/num);
				netTimeDelay = sumDelay/num;
			}
		}
		return netTimeDelay;
	}
	
	/*
	 * (non Javadoc) 
	 * @Title: getXY
	 * @Description: 获得网络时延曲线图XY轴相关信息
	 * @param mapTimeDelay
	 * @param pointInfo
	 * @param yInfoByLength
	 * @param yInfoByInterval
	 * @param yInfo 
	 * @see com.seu.wsn.Service.TestingService#getXY(java.util.Map, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void getXY(Map<Integer, Integer> mapTimeDelay,PointInfo point, List<Double> yInfoByLength, List<Double> yInfo) {
		int[] list = new int[mapTimeDelay.size()];
		Iterator<Map.Entry<Integer,Integer>> it = mapTimeDelay.entrySet().iterator();
		int i=0;
		while(it.hasNext()){
			Map.Entry<Integer,Integer> entry = (Map.Entry<Integer,Integer>)it.next();
			list[i] = entry.getKey();
			i++;
		}
		Arrays.sort(list);
		point.setPointStart(list[0]);
		
		if(list.length>1){
			point.setPointInterval((list[list.length-1]-list[0])/(list.length-1));
		}else{
			point.setPointInterval(1);
		}
		int maxLoss = 0;
		for(int j=0;j<list.length;j++){
			if(maxLoss<mapTimeDelay.get(list[j])){
				maxLoss=mapTimeDelay.get(list[j]);
			}
			
		}
		double interval=0; 
		for(int j=0;j<4;j++){
			yInfo.add(interval);
			interval += (interval+maxLoss*1.0/5)/1000;
			interval = Double.valueOf(String .format("%.3f",interval));
		}
		yInfo.add(Double.valueOf(String .format("%.3f",maxLoss*1.0/1000)));
		int start = point.getPointStart();
		double tmpLoss = 0;
		yInfoByLength.add(Double.valueOf(String .format("%.3f",mapTimeDelay.get(start)*1.0/1000)));
		for(int j=1;j<list.length;j++){
			tmpLoss = ((mapTimeDelay.get(list[j])+mapTimeDelay.get(list[j-1])))*1.0/2000;
			tmpLoss = Double.valueOf(String .format("%.3f",tmpLoss));
			yInfoByLength.add(tmpLoss);
		}
		
	}
	/*
	 * (non Javadoc) 
	 * @Title: getAveLength
	 * @Description: 获取平均最长路径
	 * @param pathLengthList
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#getAveLength(java.util.List)
	 */
	@Override
	public int getAveLength(List<PathLength> pathLengthList) {
		int  length = 0;
		for(int i=0;i<pathLengthList.size();i++){
			length += pathLengthList.get(i).getPathLength();
		}
		return length/pathLengthList.size();
	}
	/*
	 * (non Javadoc) 
	 * @Title: getPointInfo
	 * @Description: 性能对比获取横坐标起始点和间距
	 * @param pointInfoList
	 * @return 
	 * @see com.seu.wsn.Service.TestingService#getPointInfo(java.util.List)
	 */
	@Override
	public ArrayList<Double> getYInfo(
			List<ArrayList<Double>> yInfoList) {
		ArrayList<Double> yInfo = new ArrayList<Double>();
		double[] y = new double[yInfoList.size()];
		for(int i=0;i<yInfoList.size();i++){
			y[i] = yInfoList.get(i).get(yInfoList.get(i).size()-1);
		}
		Arrays.sort(y);
		double interval=0;
		double maxLoss = y[yInfoList.size()-1];
		for(int j=0;j<4;j++){
			yInfo.add(interval);
			interval = interval+maxLoss*1.0/5;
			interval = Double.valueOf(String .format("%.3f",interval));
		}
		yInfo.add(Double.valueOf(String .format("%.3f",maxLoss*1.0)));
		
		return yInfo;
	}
}
