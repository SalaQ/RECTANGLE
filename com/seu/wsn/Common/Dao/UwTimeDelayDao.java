package com.seu.wsn.Common.Dao;

import java.util.List;


import com.seu.wsn.Core.Pojo.UwTimeDelay;

/**
 * 
 * @ClassName: UwTimeDelayDao 
 * @Description: 时延数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:37:18
 */
public interface UwTimeDelayDao {
	/**
	 * 
	 * @Title: insertUwTimeDelay 
	 * @Description: 新增时延测试结果
	 * @param UwTimeDelay
	 * @return: void
	 */
	public void insertUwTimeDelay(UwTimeDelay uwTimeDelay);
	/**
	 * 
	 * @Title: deleteUwTimeDelay 
	 * @Description: 删除本次时延测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteUwTimeDelay(String testId);
	/**
	 * 
	 * @Title: selectUwTimeDelayList 
	 * @Description: 获取时延测试结果列表
	 * @param testId
	 * @return
	 * @return: List<UwTimeDelay>
	 */
	public List<UwTimeDelay> selectUwTimeDelayList(String testId);
}
