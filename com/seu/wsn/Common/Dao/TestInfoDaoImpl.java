package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.TestInfo;
/**
 * 
 * @ClassName: TestInfoDaoImpl 
 * @Description: 测试信息数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月2日 下午4:03:47
 */
@Repository("testInfoDao")
public class TestInfoDaoImpl extends SqlSessionDaoSupport implements TestInfoDao{
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
	 * @Title: selectTestInfo
	 * @Description: 选择一个测试信息
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.TestInfoDao#selectTestInfo(java.lang.String)
	 */
	@Override
	public TestInfo selectTestInfo(String testId) {
		return getSqlSession().selectOne("com.seu.wsn.testInfo.mapper.selectTestInfo", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertTestInfo
	 * @Description: 插入一个测试信息
	 * @param testInfo 
	 * @see com.seu.wsn.Common.Dao.TestInfoDao#insertTestInfo(com.seu.wsn.Core.Pojo.TestInfo)
	 */
	@Override
	public void insertTestInfo(TestInfo testInfo) {
		getSqlSession().insert("com.seu.wsn.testInfo.mapper.insertTestInfo", testInfo);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteTestInfo
	 * @Description: 删除一个测试信息
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.TestInfoDao#deleteTestInfo(java.lang.String)
	 */
	@Override
	public void deleteTestInfo(String testId) {
		getSqlSession().delete("com.seu.wsn.testInfo.mapper.deleteTestInfo", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectTestInfoList
	 * @Description: 根据用户名选择测试信息列表
	 * @param userName
	 * @return 
	 * @see com.seu.wsn.Common.Dao.TestInfoDao#selectTestInfoList(java.lang.String)
	 */
	@Override
	public List<TestInfo> selectTestInfoList(String userName) {
		return getSqlSession().selectList("com.seu.wsn.testInfo.mapper.testInfoList", userName);
	}

}
