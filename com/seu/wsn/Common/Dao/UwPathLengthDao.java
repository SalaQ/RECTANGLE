package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.UwPathLength;

/**
 * 
 * @ClassName: UwPathLengthDao 
 * @Description: 平均路径长度数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月1日 下午5:51:05
 */
public interface UwPathLengthDao {

	/**
	 * 
	 * @Title: insertUwPathLength 
	 * @Description:  新增平均路径长度测试结果
	 * @param uwPathLength
	 * @return: void
	 */
	public void insertUwPathLength(UwPathLength uwPathLength);
	/**
	 * 
	 * @Title: deleteUwPathLength 
	 * @Description:删除本次平均路径长度测试结果
	 * @param testId
	 * @return: void
	 */
	public void deleteUwPathLength(String testId);
	/**
	 * 
	 * @Title: selectUwPathLengthList 
	 * @Description: 获取本次测试平均路径长度测试结果
	 * @param testId
	 * @return
	 * @return: List<UwPathLength>
	 */
	public List<UwPathLength> selectUwPathLengthList(String testId);

}
