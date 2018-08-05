package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.PathLength;

/**
 * 
 * @ClassName: PathLengthDao 
 * @Description: 平均路径长度数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:51:05
 */
public interface PathLengthDao {
	/**
	 * 
	 * @Title: getCycleNum 
	 * @Description: 获取测试周期数
	 * @param pathLength
	 * @return
	 * @return: int
	 */
	public int getCycleNum(PathLength pathLength);
	/**
	 * 
	 * @Title: insertPathLength 
	 * @Description:  新增平均路径长度测试结果
	 * @param pathLength
	 * @return: void
	 */
	public void insertPathLength(PathLength pathLength);
	/**
	 * 
	 * @Title: deletePathLength 
	 * @Description:删除本次平均路径长度测试结果
	 * @param testId
	 * @return: void
	 */
	public void deletePathLength(String testId);
	/**
	 * 
	 * @Title: selectPathLengthList 
	 * @Description: 获取本次测试平均路径长度测试结果
	 * @param testId
	 * @return
	 * @return: List<PathLength>
	 */
	public List<PathLength> selectPathLengthList(String testId);

}
