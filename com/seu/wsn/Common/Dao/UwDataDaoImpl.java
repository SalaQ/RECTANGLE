package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.UwData;

@Repository("uwDataDao")
public class UwDataDaoImpl extends SqlSessionDaoSupport implements UwDataDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public UwData select(UwData uwData) {
		return getSqlSession().selectOne("com.seu.wsn.uwData.mapper.selectUwData", uwData);
	}

	@Override
	public void insert(UwData uwData) {
		getSqlSession().insert("com.seu.wsn.uwData.mapper.insertUwData", uwData);
	}
	
	@Override
	public void update(UwData uwData) {
		getSqlSession().update("com.seu.wsn.uwData.mapper.updateUwData", uwData);
	}
	
	@Override
	public void delete() {
		getSqlSession().delete("com.seu.wsn.uwData.mapper.deleteUwData");
	}
	
	@Override
	public void deleteByTime(UwData uwData) {
		getSqlSession().delete("com.seu.wsn.uwData.mapper.deleteUwDataByTime");
	}
	
	@Override
	public List<UwData> uwDataList() {
		return getSqlSession().selectList("com.seu.wsn.uwData.mapper.uwDataList");
	}
	
}
