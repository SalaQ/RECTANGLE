package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.UwThroughput;

/**
 * 
 * @ClassName: UwThroughputDao 
 * @Description: 吞吐量数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午7:51:53
 */
public interface UwThroughputDao {
	/**
	 * 
	 * @Title: insertUwThroughput 
	 * @Description: 新增吞吐量测试结果
	 * @param uwThroughput
	 * @return: void
	 */
	public void insertUwThroughput(UwThroughput uwThroughput);
	/**
	 * 
	 * @Title: deleteUwThroughput 
	 * @Description: 删除本次测试吞吐量测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteUwThroughput(String testId);
	/**
	 * 
	 * @Title: selectUwThroughputList 
	 * @Description:获取吞吐量测试列表
	 * @param testId
	 * @return
	 * @return: List<UwThroughput>
	 */
	public List<UwThroughput> selectUwThroughputList(String testId);
}
