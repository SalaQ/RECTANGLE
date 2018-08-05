package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.ConnectivityDegree;
/**
 * 
 * @ClassName: ConnectivityDegreeDaoImpl 
 * @Description: 联通度数据访问层实现类
 * @author: shenyu.css
 * @date: 2016年12月1日 下午8:10:51
 */
@Repository("connectivityDegreeDao")
public class ConnectivityDegreeDaoImpl extends SqlSessionDaoSupport implements ConnectivityDegreeDao{
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
	 * @param connectivityDegree
	 * @return 
	 * @see com.seu.wsn.Common.Dao.ConnectivityDegreeDao#getCycleNum(com.seu.wsn.Core.Pojo.ConnectivityDegree)
	 */
	@Override
	public int getCycleNum(ConnectivityDegree connectivityDegree) {
		return getSqlSession().selectOne("com.seu.wsn.connectivityDegree.mapper.selectNum",connectivityDegree);

	}
	/*
	 * (non Javadoc) 
	 * @Title: insertConnectivityDegree
	 * @Description: 新增联通度测试结果
	 * @param connectivityDegree 
	 * @see com.seu.wsn.Common.Dao.ConnectivityDegreeDao#insertConnectivityDegree(com.seu.wsn.Core.Pojo.ConnectivityDegree)
	 */
	@Override
	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree) {
		getSqlSession().insert("com.seu.wsn.connectivityDegree.mapper.insertConnectivityDegree", connectivityDegree);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteConnectivityDegree
	 * @Description: 删除本次联通度测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.ConnectivityDegreeDao#deleteConnectivityDegree(java.lang.String)
	 */
	@Override
	public void deleteConnectivityDegree(String testId) {
		getSqlSession().delete("com.seu.wsn.connectivityDegree.mapper.deleteConnectivityDegree", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectConnectivityDegreeList
	 * @Description: 获取联通度测试结果列表
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.ConnectivityDegreeDao#selectConnectivityDegreeList(java.lang.String)
	 */
	@Override
	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.connectivityDegree.mapper.selectConnectivityDegreeList", testId);
	}

}
