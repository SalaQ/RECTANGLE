package com.seu.wsn.Common.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.seu.wsn.Core.Pojo.User;
/**
 * 
 * @ClassName: UserDaoImpl 
 * @Description: UserDaoImpl
 * @author: CSS
 * @date: 2016-10-23 上午11:23:18
 */
@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
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
	 * @Title: userList
	 * @Description: 获取用户列表
	 * @return 
	 * @see com.seu.wsn.Common.Dao.UserDao#userList()
	 */
	@Override
	public List<User> userList() {
		return getSqlSession().selectList("com.seu.wsn.user.mapper.userList");
	}
	/*
	 * (non Javadoc) 
	 * @Title: select
	 * @Description: 根据用户名选择用户
	 * @param userName
	 * @return 
	 * @see com.seu.wsn.Common.Dao.UserDao#select(java.lang.String)
	 */
	@Override
	public User select(String userName) {
		return getSqlSession().selectOne("com.seu.wsn.user.mapper.selectUser", userName);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insert
	 * @Description: 新增用户
	 * @param user 
	 * @see com.seu.wsn.Common.Dao.UserDao#insert(com.seu.wsn.Core.Pojo.User)
	 */
	@Override
	public void insert(User user) {
		getSqlSession().insert("com.seu.wsn.user.mapper.insertUser", user);
	}
	/*
	 * (non Javadoc) 
	 * @Title: update
	 * @Description: 更新用户信息
	 * @param user 
	 * @see com.seu.wsn.Common.Dao.UserDao#update(com.seu.wsn.Core.Pojo.User)
	 */
	@Override
	public void update(User user) {
		getSqlSession().update("com.seu.wsn.user.mapper.updateUser", user);
	}
	/*
	 * (non Javadoc) 
	 * @Title: remove
	 * @Description: 更加用户名删除用户
	 * @param userName 
	 * @see com.seu.wsn.Common.Dao.UserDao#remove(java.lang.String)
	 */
	@Override
	public void remove(String userName) {
		getSqlSession().delete("com.seu.wsn.user.mapper.removeUser", userName);
	}

}
