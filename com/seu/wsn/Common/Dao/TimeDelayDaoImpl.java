package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.TimeDelay;

/**
 * 
 * @ClassName: TimeDelayDaoImpl 
 * @Description: 时延测试数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:44:07
 */
@Repository("timeDelayDao")
public class TimeDelayDaoImpl extends SqlSessionDaoSupport implements TimeDelayDao{
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
     * @param timeDelay
     * @return 
     * @see com.seu.wsn.Common.Dao.TimeDelayDao#getCycleNum(com.seu.wsn.Core.Pojo.TimeDelay)
     */
	@Override
	public int getCycleNum(TimeDelay timeDelay) {
		return getSqlSession().selectOne("com.seu.wsn.timeDelay.mapper.selectNum", timeDelay);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertTimeDelay
	 * @Description: 新增时延测试结果
	 * @param timeDelay 
	 * @see com.seu.wsn.Common.Dao.TimeDelayDao#insertTimeDelay(com.seu.wsn.Core.Pojo.TimeDelay)
	 */
	@Override
	public void insertTimeDelay(TimeDelay timeDelay) {
		getSqlSession().insert("com.seu.wsn.timeDelay.mapper.insertTimeDelay", timeDelay);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteTimeDelay
	 * @Description: 删除本次时延测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.TimeDelayDao#deleteTimeDelay(java.lang.String)
	 */
	@Override
	public void deleteTimeDelay(String testId) {
		getSqlSession().delete("com.seu.wsn.timeDelay.mapper.deleteTimeDelay", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectTimeDelayList
	 * @Description: 获取时延测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.TimeDelayDao#selectTimeDelayList(java.lang.String)
	 */
	@Override
	public List<TimeDelay> selectTimeDelayList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.timeDelay.mapper.selectTimeDelayList", testId);
	}

}
