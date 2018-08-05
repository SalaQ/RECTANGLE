package com.seu.wsn.Core.Pojo;
/**
 * 
 * @ClassName: SystemPacketLoss 
 * @Description: 系统丢包率
 * @author: CSS
 * @date: 2016-11-29 下午1:44:24
 */
public class SystemPacketLoss {
	private String 	testId;						//本次测试ID
	private int    	testNum;					//测试次数
	private int    	cycleNum;					//测试周期数
	private double  sendSpeed;					//测试包发送速率
	private int     sumRecievePacket;			//总收包数
	private int     sunSendPacket;				//总发包数
	private double  systemPakcetLoss;			//系统丢包率
	/**
	 * 
	 * @Title: getTestId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getTestId() {
		return testId;
	}
	/**
	 * 
	 * @Title: setTestId 
	 * @Description: TODO
	 * @param testId
	 * @return: void
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
	/**
	 * 
	 * @Title: getTestNum 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getTestNum() {
		return testNum;
	}
	/**
	 * 
	 * @Title: setTestNum 
	 * @Description: TODO
	 * @param testNum
	 * @return: void
	 */
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getCycleNum() {
		return cycleNum;
	}
	/**
	 * 
	 * @Title: setCycleNum 
	 * @Description: TODO
	 * @param cycleNum
	 * @return: void
	 */
	public void setCycleNum(int cycleNum) {
		this.cycleNum = cycleNum;
	}
	/**
	 * 
	 * @Title: getSendSpeed 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getSendSpeed() {
		return sendSpeed;
	}
	/**
	 * 
	 * @Title: setSendSpeed 
	 * @Description: TODO
	 * @param sendSpeed
	 * @return: void
	 */
	public void setSendSpeed(double sendSpeed) {
		this.sendSpeed = sendSpeed;
	}
	/**
	 * 
	 * @Title: getSumRecievePacket 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getSumRecievePacket() {
		return sumRecievePacket;
	}
	/**
	 * 
	 * @Title: setSumRecievePacket 
	 * @Description: TODO
	 * @param sumRecievePacket
	 * @return: void
	 */
	public void setSumRecievePacket(int sumRecievePacket) {
		this.sumRecievePacket = sumRecievePacket;
	}
	/**
	 * 
	 * @Title: getSunSendPacket 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getSunSendPacket() {
		return sunSendPacket;
	}
	/**
	 * 
	 * @Title: setSunSendPacket 
	 * @Description: TODO
	 * @param sunSendPacket
	 * @return: void
	 */
	public void setSunSendPacket(int sunSendPacket) {
		this.sunSendPacket = sunSendPacket;
	}
	/**
	 * 
	 * @Title: getSystemPakcetLoss 
	 * @Description: TODO
	 * @return
	 * @return: double
	 */
	public double getSystemPakcetLoss() {
		return systemPakcetLoss;
	}
	/**
	 * 
	 * @Title: setSystemPakcetLoss 
	 * @Description: TODO
	 * @param systemPakcetLoss
	 * @return: void
	 */
	public void setSystemPakcetLoss(double systemPakcetLoss) {
		this.systemPakcetLoss = systemPakcetLoss;
	}
	
	
}
