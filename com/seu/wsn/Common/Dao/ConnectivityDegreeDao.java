package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.ConnectivityDegree;


/**
 * 
 * @ClassName: ConnectivityDegreeDao 
 * @Description: 联通度数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午8:05:27
 */
public interface ConnectivityDegreeDao {
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: 获取测试周期数
	 * @param connectivityDegree
	 * @return
	 * @return: int
	 */
	public int getCycleNum(ConnectivityDegree connectivityDegree);
	/**
	 * 
	 * @Title: insertConnectivityDegree 
	 * @Description: 新增联通度测试结果
	 * @param connectivityDegree
	 * @return: void
	 */
	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree);
	/**
	 * 
	 * @Title: deleteConnectivityDegree 
	 * @Description: 删除本次联通度测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteConnectivityDegree(String testId);
	/**
	 * 
	 * @Title: selectConnectivityDegreeList 
	 * @Description: 获取联通度测试结果列表
	 * @param testId
	 * @return
	 * @return: List<ConnectivityDegree>
	 */
	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId);
}
