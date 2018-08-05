package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.User;

/**
 * 
 * @ClassName: UserDao 
 * @Description: 数据访问层UserDao接口
 * @author: CSS
 * @date: 2016-10-23 上午11:24:11
 */
public interface UserDao {
	
	/**
	 * 根据用户名选择用户
	 * @param userName
	 * @return
	 */
	public User select(String userName);
	
	/**
	 * 新增用户
	 * @param user
	 */
	public void insert(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void update(User user); 
	
	/**
	 * 根据用户名删除用户
	 * @param userName
	 */
	public void remove(String userName);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public List<User> userList();

}
