package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.Throughput;

/**
 * 
 * @ClassName: ThroughputDaoImpl 
 * @Description: 吞吐量测试数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月1日 下午7:58:44
 */
@Repository("throughputDao")
public class ThroughputDaoImpl extends SqlSessionDaoSupport implements ThroughputDao{
	
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
	 * @Description: 获取测试周期数
	 * @param throughput
	 * @return 
	 * @see com.seu.wsn.Common.Dao.ThroughputDao#getCycleNum(com.seu.wsn.Core.Pojo.Throughput)
	 */
	@Override
	public int getCycleNum(Throughput throughput) {
		return getSqlSession().selectOne("com.seu.wsn.throughput.mapper.selectNum",throughput);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertThroughput
	 * @Description: 新增吞吐量测试结果
	 * @param throughput 
	 * @see com.seu.wsn.Common.Dao.ThroughputDao#insertThroughput(com.seu.wsn.Core.Pojo.Throughput)
	 */
	@Override
	public void insertThroughput(Throughput throughput) {
		getSqlSession().insert("com.seu.wsn.throughput.mapper.insertThroughput", throughput);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteThroughput
	 * @Description: 删除本次测试吞吐量测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.ThroughputDao#deleteThroughput(java.lang.String)
	 */
	@Override
	public void deleteThroughput(String testId) {
		getSqlSession().delete("com.seu.wsn.throughput.mapper.deleteThroughput", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectThroughputList
	 * @Description: 获取吞吐量测试列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.ThroughputDao#selectThroughputList(java.lang.String)
	 */
	@Override
	public List<Throughput> selectThroughputList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.throughput.mapper.selectThroughputList", testId);
	}

}
