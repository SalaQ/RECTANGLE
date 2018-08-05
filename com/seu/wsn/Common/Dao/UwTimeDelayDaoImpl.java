package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.UwTimeDelay;

/**
 * 
 * @ClassName: UwTimeDelayDaoImpl 
 * @Description: 时延测试数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:44:07
 */
@Repository("uwTimeDelayDao")
public class UwTimeDelayDaoImpl extends SqlSessionDaoSupport implements UwTimeDelayDao{
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
	 * @Title: insertUwTimeDelay
	 * @Description: 新增时延测试结果
	 * @param uwTimeDelay 
	 * @see com.seu.wsn.Common.Dao.UwTimeDelayDao#insertUwTimeDelay(com.seu.wsn.Core.Pojo.UwTimeDelay)
	 */
	@Override
	public void insertUwTimeDelay(UwTimeDelay uwTimeDelay) {
		getSqlSession().insert("com.seu.wsn.uwTimeDelay.mapper.insertUwTimeDelay", uwTimeDelay);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteUwTimeDelay
	 * @Description: 删除本次时延测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.UwTimeDelayDao#deleteUwTimeDelay(java.lang.String)
	 */
	@Override
	public void deleteUwTimeDelay(String testId) {
		getSqlSession().delete("com.seu.wsn.uwTimeDelay.mapper.deleteUwTimeDelay", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectUwTimeDelayList
	 * @Description: 获取时延测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.UwTimeDelayDao#selectUwTimeDelayList(java.lang.String)
	 */
	@Override
	public List<UwTimeDelay> selectUwTimeDelayList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.uwTimeDelay.mapper.selectUwTimeDelayList", testId);
	}

}
