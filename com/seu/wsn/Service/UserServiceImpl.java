package com.seu.wsn.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Dao.UserDao;
import com.seu.wsn.Core.Pojo.User;
/**
 * 
 * @ClassName: UserServiceImpl 
 * @Description: 用户管理业务逻辑层实现类
 * @author: CSS
 * @date: 2016-10-23 上午11:25:07
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;	
	
	/**
	 * 
	 * @Title: setUserDao 
	 * @Description: 注入userDao
	 * @param userDao
	 * @return: void
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * (non Javadoc) 
	 * @Title: select
	 * @Description: 根据用户名选择用户
	 * @param userName
	 * @return 
	 * @see com.seu.wsn.Service.UserService#select(java.lang.String)
	 */
	@Override
	public User select(String userName) {
		return userDao.select(userName);
	}

	/*
	 * (non Javadoc) 
	 * @Title: insert
	 * @Description: 新增用户
	 * @param userName
	 * @param pwd 
	 * @see com.seu.wsn.Service.UserService#insert(java.lang.String, java.lang.String)
	 */
	@Override
	public void insert(String userName,String pwd) {
		User user = new User();
		user.setUserName(userName);
		user.setPwd(pwd);
		user.setOnLine(false);  //新用户设置离线
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setRegisterDate(df.format(new Date()));
		userDao.insert(user);
	}

	/*
	 * (non Javadoc) 
	 * @Title: update
	 * @Description: 更新用户信息
	 * @param user 
	 * @see com.seu.wsn.Service.UserService#update(com.seu.wsn.Core.Pojo.User)
	 */
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	/*
	 * (non Javadoc) 
	 * @Title: remove
	 * @Description: 根据用户名删除用户
	 * @param userName 
	 * @see com.seu.wsn.Service.UserService#remove(java.lang.String)
	 */
	@Override
	public void remove(String userName) {
		userDao.remove(userName);
	}

	/*
	 * (non Javadoc) 
	 * @Title: userList
	 * @Description: 获取用户列表
	 * @return 
	 * @see com.seu.wsn.Service.UserService#userList()
	 */
	@Override
	public List<User> userList() {
		return userDao.userList();
	}

}
