package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.Throughput;

/**
 * 
 * @ClassName: ThroughputDao 
 * @Description: 吞吐量数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午7:51:53
 */
public interface ThroughputDao {
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: 获取测试周期数
	 * @param throughput
	 * @return
	 * @return: int
	 */
	public int getCycleNum(Throughput throughput);
	/**
	 * 
	 * @Title: insertThroughput 
	 * @Description: 新增吞吐量测试结果
	 * @param throughput
	 * @return: void
	 */
	public void insertThroughput(Throughput throughput);
	/**
	 * 
	 * @Title: deleteThroughput 
	 * @Description: 删除本次测试吞吐量测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteThroughput(String testId);
	/**
	 * 
	 * @Title: selectThroughputList 
	 * @Description:获取吞吐量测试列表
	 * @param testId
	 * @return
	 * @return: List<Throughput>
	 */
	public List<Throughput> selectThroughputList(String testId);
}
