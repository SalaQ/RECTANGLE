package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: UpMsg 
 * @Description: 与开发板通信消息结构体
 * @author: CSS
 * @date: 2016-11-22 上午11:04:50
 */

//消息包的结构
public class DownMsg{
	
    private int   m_iMsgType;                        //消息类型
	private char[]  m_pMsgBuf = new char[1024];       //存放消息内容
	
	
	/**
	 * 
	 * @Title: getM_iMsgType 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getM_iMsgType() {
		return m_iMsgType;
	}
	/**
	 * 
	 * @Title: setM_iMsgType 
	 * @Description: TODO
	 * @param m_iMsgType
	 * @return: void
	 */
	public void setM_iMsgType(int m_iMsgType) {
		this.m_iMsgType = m_iMsgType;
	}
	
	
	/**
	 * 
	 * @Title: getM_pMsgBuf 
	 * @Description: TODO
	 * @return
	 * @return: char[]
	 */
	public char[] getM_pMsgBuf() {
		return m_pMsgBuf;
	}
	/**
	 * 
	 * @Title: setM_pMsgBuf 
	 * @Description: TODO
	 * @param m_pMsgBuf
	 * @return: void
	 */
	public void setM_pMsgBuf(char[] m_pMsgBuf) {
		this.m_pMsgBuf = m_pMsgBuf;
	}
	
}
