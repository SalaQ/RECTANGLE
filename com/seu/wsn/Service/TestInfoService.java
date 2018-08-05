package com.seu.wsn.Service;

import java.util.List;

import com.seu.wsn.Core.Pojo.TestInfo;

/**
 * 
 * @ClassName: TestInfoService 
 * @Description: 测试信息业务逻辑层接口
 * @author: shenyu.css
 * @date: 2016年12月2日 下午4:10:42
 */
public interface TestInfoService {
	/**
	 * 
	 * @Title: selectTestInfo 
	 * @Description: 选择一个测试信息
	 * @param testId
	 * @return
	 * @return: TestInfo
	 */
	public TestInfo selectTestInfo(String testId);
	/**
	 * 	
	 * @Title: insertTestInfo 
	 * @Description: 插入一个测试信息
	 * @param 
	 * @return: void
	 */
	public void insertTestInfo(String testName,String userName);
	/**
	 * 
	 * @Title: deleteTestInfo 
	 * @Description: 删除一个测试信息
	 * @param testId
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
