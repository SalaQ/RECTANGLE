package com.seu.wsn.Common.Dao;

import java.util.List;


import com.seu.wsn.Core.Pojo.TimeDelay;

/**
 * 
 * @ClassName: TimeDelayDao 
 * @Description: 时延数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:37:18
 */
public interface TimeDelayDao {
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: 获取测试周期数
	 * @param timeDelay
	 * @return
	 * @return: int
	 */
	public int getCycleNum(TimeDelay timeDelay);
	/**
	 * 
	 * @Title: insertTimeDelay 
	 * @Description: 新增时延测试结果
	 * @param timeDelay
	 * @return: void
	 */
	public void insertTimeDelay(TimeDelay timeDelay);
	/**
	 * 
	 * @Title: deleteTimeDelay 
	 * @Description: 删除本次时延测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteTimeDelay(String testId);
	/**
	 * 
	 * @Title: selectTimeDelayList 
	 * @Description: 获取时延测试结果列表
	 * @param testId
	 * @return
	 * @return: List<TimeDelay>
	 */
	public List<TimeDelay> selectTimeDelayList(String testId);
}
