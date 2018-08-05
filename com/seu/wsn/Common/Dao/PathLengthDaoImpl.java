package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seu.wsn.Core.Pojo.PathLength;
/**
 * 
 * @ClassName: PathLengthDaoImpl 
 * @Description: 平均路径长度数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:56:43
 */
@Repository("pathLengthDao")
public class PathLengthDaoImpl extends SqlSessionDaoSupport implements PathLengthDao{
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
	 * @param pathLength
	 * @return 
	 * @see com.seu.wsn.Common.Dao.PathLengthDao#getCycleNum(com.seu.wsn.Core.Pojo.PathLength)
	 */
	@Override
	public int getCycleNum(PathLength pathLength) {
		return getSqlSession().selectOne("com.seu.wsn.pathLength.mapper.selectNum", pathLength);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertPathLength
	 * @Description: 新增平均路径长度测试结果
	 * @param pathLength 
	 * @see com.seu.wsn.Common.Dao.PathLengthDao#insertPathLength(com.seu.wsn.Core.Pojo.PathLength)
	 */
	@Override
	public void insertPathLength(PathLength pathLength) {
		getSqlSession().insert("com.seu.wsn.pathLength.mapper.insertPathLength", pathLength);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deletePathLength
	 * @Description: 删除本次平均路径长度测试结果
	 * @param testId 
	 * @see com.seu.wsn.Common.Dao.PathLengthDao#deletePathLength(java.lang.String)
	 */
	@Override
	public void deletePathLength(String testId) {
		getSqlSession().delete("com.seu.wsn.pathLength.mapper.deletePathLength", testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectPathLengthList
	 * @Description: 获取本次测试平均路径长度测试结果
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Common.Dao.PathLengthDao#selectPathLengthList(java.lang.String)
	 */
	@Override
	public List<PathLength> selectPathLengthList(String testId) {
		return getSqlSession().selectList("com.seu.wsn.pathLength.mapper.selectPathLengthList", testId);
	}

}
