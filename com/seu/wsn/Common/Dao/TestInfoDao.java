package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.TestInfo;

/**
 * 
 * @ClassName: TestInfoDao 
 * @Description: 测试信息数据访问层接口
 * @author: shenyu.css
 * @date: 2016年12月2日 下午3:51:15
 */
public interface TestInfoDao {
	/**
	 * 
	 * @Title: selectTestInfo 
	 * @Description: 选择一个测试信息
	 * @param testInfo
	 * @return
	 * @return: TestInfo
	 */
	public TestInfo selectTestInfo(String testId);
	/**
	 * 	
	 * @Title: insertTestInfo 
	 * @Description: 插入一个测试信息
	 * @param testInfo
	 * @return: void
	 */
	public void insertTestInfo(TestInfo testInfo);
	/**
	 * 
	 * @Title: deleteTestInfo 
	 * @Description: 删除一个测试信息
	 * @param testInfo
	 * @return: void
	 */
	public void deleteTestInfo(String testId);
	/**
	 * 
	 * @Title: selectTestInfoList 
	 * @Description: 根据用户名选择测试信息列表
	 * @param userName
	 * @return
	 * @return: List<TestInfo>
	 */
	public List<TestInfo> selectTestInfoList(String userName);
}
