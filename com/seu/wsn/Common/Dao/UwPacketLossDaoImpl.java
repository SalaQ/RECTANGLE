package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.UwNodePacketLoss;

@Repository("uwPacketLossDao")
public class UwPacketLossDaoImpl extends SqlSessionDaoSupport implements UwPacketLossDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public void insertPacketLoss(UwNodePacketLoss packetLoss) {
		getSqlSession().insert("com.seu.wsn.uwPacketLoss.mapper.insertPacketLoss", packetLoss);
	}

	@Override
	public void deletePacketLoss(String testId) {
		getSqlSession().delete("com.seu.wsn.uwPacketLoss.mapper.deletePacketLoss", testId);
	}

	@Override
	public List<UwNodePacketLoss> selectPacketLossList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.uwPacketLoss.mapper.selectPacketLossList", testId);
	}

}
