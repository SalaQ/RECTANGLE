package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.UwPathLength;
/**
 * 
 * @ClassName: UwPathLengthDaoImpl 
 * @Description: 平均路径长度数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:56:43
 */
@Repository("uwPathLengthDao")
public class UwPathLengthDaoImpl extends SqlSessionDaoSupport implements UwPathLengthDao{
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
	 * @Title: insertUwPathLength
	 * @Description: 新增平均路径长度测试结果
	 * @param uwPathLength 
	 * @see com.seu.wsn.Common.Dao.UwPathLengthDao#insertUwPathLength(com.seu.wsn.Core.Pojo.UwPathLength)
	 */
	@Override
	public void insertUwPathLength(UwPathLength uwPathLength) {
		getSqlSession().insert("com.seu.wsn.uwPathLength.mapper.insertUwPathLength", uwPathLength);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteUwPathLength
	 * @Description: 删除本次平均路径长度测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.UwPathLengthDao#deleteUwPathLength(java.lang.String)
	 */
	@Override
	public void deleteUwPathLength(String testId) {
		getSqlSession().delete("com.seu.wsn.uwPathLength.mapper.deleteUwPathLength", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectUwPathLengthList
	 * @Description: 获取本次测试平均路径长度测试结果
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.UwPathLengthDao#selectUwPathLengthList(java.lang.String)
	 */
	@Override
	public List<UwPathLength> selectUwPathLengthList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.uwPathLength.mapper.selectUwPathLengthList", testId);
	}

}
