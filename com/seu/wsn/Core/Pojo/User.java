/**
 * 
 */
package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: User 
 * @Description: 用户类
 * @author: CSS
 * @date: 2016-10-23 上午11:24:29
 */
public class User {
	private String userName;			//用户名
	private String pwd;					//用户密码
	private String registerDate;		//注册日期
	private boolean onLine;				//是否在线
	
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * 
	 * @param passWord
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 
	 * @return
	 */
	public String getRegisterDate() {
		return registerDate;
	}
	/**
	 * 
	 * @param registerDate
	 */
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isOnLine() {
		return onLine;
	}
	/**
	 * 
	 * @param admin
	 */
	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}

}
