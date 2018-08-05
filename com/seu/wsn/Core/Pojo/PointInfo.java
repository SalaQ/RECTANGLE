package com.seu.wsn.Core.Pojo;

/**
 * 
 * @ClassName: PointInfo 
 * @Description: 设置曲线图的起点与横坐标间隔
 * @author: CSS
 * @date: 2016-11-30 下午6:59:20
 */
public class PointInfo {
	private int pointStart;                //起点
	private int pointInterval;			   //横坐标间隔
	/**
	 * 
	 * @Title: getPointStart 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPointStart() {
		return pointStart;
	}
	/**
	 * 
	 * @Title: setPointStart 
	 * @Description: TODO
	 * @param pointStart
	 * @return: void
	 */
	public void setPointStart(int pointStart) {
		this.pointStart = pointStart;
	}
	/**
	 * 
	 * @Title: getPointInterval 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int getPointInterval() {
		return pointInterval;
	}
	/**
	 * 
	 * @Title: setPointInterval 
	 * @Description: TODO
	 * @param pointInterval
	 * @return: void
	 */
	public void setPointInterval(int pointInterval) {
		this.pointInterval = pointInterval;
	}
	
}
