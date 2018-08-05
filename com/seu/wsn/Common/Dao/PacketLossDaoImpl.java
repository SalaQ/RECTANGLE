package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.NodePacketLoss;
/**
 * 
 * @ClassName: PacketLossDaoImpl 
 * @Description: 节点丢包率信息数据访问层实现类
 * @author: CSS
 * @date: 2016-11-29 下午2:26:25
 */
@Repository("packetLossDao")
public class PacketLossDaoImpl extends SqlSessionDaoSupport implements PacketLossDao{
	/*
	 * (non Javadoc) 
	 * @Title: setSqlSessionFactory
	 * @Description: 注入SqlSessionFactory
	 * @param sqlSessionFactory 
	 * @see org.mybatis.spring.support.SqlSessionDaoSupport#setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory)
	 */
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	/*
	 * (non Javadoc) 
	 * @Title: getCycleNum
	 * @Description: 获取每次测试的测试周期数
	 * @param packetLoss
	 * @return 
	 * @see com.seu.wsn.Common.Dao.PacketLossDao#getCycleNum(com.seu.wsn.Core.Pojo.NodePacketLoss)
	 */
	@Override
	public int getCycleNum(NodePacketLoss packetLoss) {
		return getSqlSession().selectOne("com.seu.wsn.packetLoss.mapper.selectNum", packetLoss);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertPacketLoss
	 * @Description: 新增节点丢包率测试结果
	 * @param packetLoss 
	 * @see com.seu.wsn.Common.Dao.PacketLossDao#insertPacketLoss(com.seu.wsn.Core.Pojo.NodePacketLoss)
	 */
	@Override
	public void insertPacketLoss(NodePacketLoss packetLoss) {
		getSqlSession().insert("com.seu.wsn.packetLoss.mapper.insertPacketLoss", packetLoss);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deletePacketLoss
	 * @Description: 删除本次丢包率测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.PacketLossDao#deletePacketLoss(java.lang.String)
	 */
	@Override
	public void deletePacketLoss(String testId) {
		getSqlSession().delete("com.seu.wsn.packetLoss.mapper.deletePacketLoss", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectPacketLossList
	 * @Description: 获取丢包率列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.PacketLossDao#selectPacketLossList(java.lang.String)
	 */
	@Override
	public List<NodePacketLoss> selectPacketLossList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.packetLoss.mapper.selectPacketLossList", testId);
	}

}
