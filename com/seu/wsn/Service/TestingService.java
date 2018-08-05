package com.seu.wsn.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.PathLength;
import com.seu.wsn.Core.Pojo.PointInfo;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.Throughput;
import com.seu.wsn.Core.Pojo.TimeDelay;

/**
 * 
 * @ClassName: PacketLossService 
 * @Description: 节点丢包率业务逻辑层接口
 * @author: CSS
 * @date: 2016-11-29 下午2:32:01
 */
public interface TestingService {
	
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: 获取每次测试的测试周期数
	 * @param packetLoss
	 * @return
	 * @return: int
	 */
	public int getCycleNum(NodePacketLoss packetLoss);
	/**
	 * 
	 * @Title: insertPacketLoss 
	 * @Description: 新增节点丢包率测试结果
	 * @param packetLoss
	 * @return: void
	 */
	public void insertPacketLoss(NodePacketLoss packetLoss);
	/**
	 * 
	 * @Title: deletePacketLoss 
	 * @Description:  删除本次丢包率测试结果
	 * @param testId
	 * @return: void
	 */
	public void deletePacketLoss(String testId);
	/**
	 * 
	 * @Title: selectPacketLossList 
	 * @Description: 获取丢包率列表
	 * @param testId
	 * @return
	 * @return: List<NodePacketLoss>
	 */
	public List<NodePacketLoss> selectPacketLossList(String testId);
	/**
	 * 
	 * @Title: getSysPacketLossList 
	 * @Description: 获取系统丢包率相关信息
	 * @param sysPacketLoss
	 * @return
	 * @return: List<SystemPacketLoss>
	 */
	public ArrayList<SystemPacketLoss> getSysPacketLossList(Map<Integer,Double> sysPacketLossByLength,Map<Integer,Double> sysPacketLossByInterval,String testId);
	/**
	 * 
	 * @Title: getNetTimeDelayList 
	 * @Description: 获取时延列表相关信息
	 * @param netTimeDelay
	 * @param delayList
	 * @return
	 * @return: int
	 */
	public int  getNetTimeDelayList(Map<Integer,Integer> mapTimeDelay,List<TimeDelay> delayList);
	
	/**
	 * 
	 * @Title: getXY 
	 * @Description: 获得系统丢包率曲线图XY轴相关信息
	 * @param sysPacketLossByLength
	 * @param sysPacketLossByInterval
	 * @return: void
	 */
	public void getXY(Map<Integer,Double> sysPacketLossByLength,Map<Integer,Double> sysPacketLossByInterval,
			List<PointInfo> pointInfo,List<Double> yInfoByLength,List<Double> yInfoByInterval,List<Double> yInfo);
	
	/**
	 * 
	 * @Title: getXY 
	 * @Description: 获得网络时延曲线图XY轴相关信息
	 * @param mapTimeDelay
	 * @param pointInfo
	 * @param yInfoByLength
	 * @param yInfoByInterval
	 * @param yInfo
	 * @return: void
	 */
	public void getXY(Map<Integer,Integer> mapTimeDelay,PointInfo point,List<Double> yInfoByLength,List<Double> yInfo);
		
	/**
	 * 
	 * @Title: insertTimeDelay 
	 * @Description: 新增时延测试结果
	 * @param timeDelay
	 * @return: void
	 */
	public void insertTimeDelay(TimeDelay timeDelay);
	/**
	 * 
	 * @Title: deleteTimeDelay 
	 * @Description: 删除时延测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteTimeDelay(String testId);
	/**
	 * 
	 * @Title: selectTimeDelayList 
	 * @Description: 获取时延测试列表
	 * @param testId
	 * @return
	 * @return: List<TimeDelay>
	 */
	public List<TimeDelay> selectTimeDelayList(String testId);
	/**
	 * 
	 * @Title: insertThroughput 
	 * @Description: 新增吞吐量测试结果
	 * @param throughput
	 * @return: void
	 */
	public void insertThroughput(Throughput throughput);
	/**
	 * 
	 * @Title: deleteThroughput 
	 * @Description: 删除本次吞吐量测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteThroughput(String testId);
	/**
	 * 
	 * @Title: selectThroughputList 
	 * @Description: 获取吞吐量测试结果列表
	 * @param testId
	 * @return
	 * @return: List<Throughput>
	 */
	public List<Throughput> selectThroughputList(String testId);
	/**
	 * 
	 * @Title: insertPathLength 
	 * @Description: 新增平均路径长度测试结果
	 * @param pathLength
	 * @return: void
	 */
	public void insertPathLength(PathLength pathLength);
	/**
	 * 
	 * @Title: deletePathLength 
	 * @Description: 删除平均路径长度测试结果
	 * @param testId
	 * @return: void
	 */
	public void deletePathLength(String testId);
	/**
	 * 
	 * @Title: selectPathLengthList 
	 * @Description: 获取平均路径长度测试结果列表
	 * @param testId
	 * @return
	 * @return: List<PathLength>
	 */
	public List<PathLength> selectPathLengthList(String testId);
	/**
	 * 
	 * @Title: insertConnectivityDegree 
	 * @Description: 新增联通度测试结果
	 * @param connectivityDegree
	 * @return: void
	 */
	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree);
	/**
	 * 
	 * @Title: deleteConnectivityDegree 
	 * @Description: 删除联通度测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteConnectivityDegree(String testId);
	/**
	 * 
	 * @Title: selectConnectivityDegreeList 
	 * @Description: 获取联通度测试结果列表
	 * @param testId
	 * @return
	 * @return: List<ConnectivityDegree>
	 */
	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId);
	
	/**
	 * 
	 * @Title: getAveLength 
	 * @Description: 获取平均最长路径
	 * @param pathLengthList
	 * @return
	 * @return: int
	 */
	public int getAveLength(List<PathLength> pathLengthList);
	
	/**
	 * 
	 * @Title: getPointInfo 
	 * @Description: 性能对比获取横坐标起始点和间距
	 * @param pointInfoList
	 * @return
	 * @return: ArrayList<PointInfo>
	 */
	public ArrayList<Double> getYInfo(List<ArrayList<Double>> yInfoList);
	
}
