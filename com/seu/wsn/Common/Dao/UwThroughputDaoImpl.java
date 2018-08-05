package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.UwThroughput;

/**
 * 
 * @ClassName: UwThroughputDaoImpl 
 * @Description: 吞吐量测试数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月1日 下午7:58:44
 */
@Repository("uwThroughputDao")
public class UwThroughputDaoImpl extends SqlSessionDaoSupport implements UwThroughputDao{
	
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
	 * @Title: insertUwThroughput
	 * @Description: 新增吞吐量测试结果
	 * @param uwThroughput 
	 * @see com.seu.wsn.Common.Dao.UwThroughputDao#insertUwThroughput(com.seu.wsn.Core.Pojo.UwThroughput)
	 */
	@Override
	public void insertUwThroughput(UwThroughput uwThroughput) {
		getSqlSession().insert("com.seu.wsn.uwThroughput.mapper.insertUwThroughput", uwThroughput);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteUwThroughput
	 * @Description: 删除本次测试吞吐量测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.UwThroughputDao#deleteUwThroughput(java.lang.String)
	 */
	@Override
	public void deleteUwThroughput(String testId) {
		getSqlSession().delete("com.seu.wsn.uwThroughput.mapper.deleteUwThroughput", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectUwThroughputList
	 * @Description: 获取吞吐量测试列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.UwThroughputDao#selectUwThroughputList(java.lang.String)
	 */
	@Override
	public List<UwThroughput> selectUwThroughputList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.uwThroughput.mapper.selectUwThroughputList", testId);
	}

}
