package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.NodePacketLoss;

/**
 * 
 * @ClassName: PacketLossDao 
 * @Description: 节点丢包率数据访问层接口
 * @author: CSS
 * @date: 2016-11-29 下午2:18:14
 */
public interface PacketLossDao {
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
}
