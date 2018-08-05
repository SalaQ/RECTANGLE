package com.seu.wsn.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Dao.TestInfoDao;
import com.seu.wsn.Core.Pojo.TestInfo;
import com.seu.wsn.Core.StaticConst.Order;

/**
 * 
 * @ClassName: TestInfoServiceImpl 
 * @Description: 测试信息业务逻辑层实现类
 * @author: shenyu.css
 * @date: 2016年12月2日 下午4:20:01
 */
@Service("testInfoService")
public class TestInfoServiceImpl implements TestInfoService{
	@Autowired
	private TestInfoDao testInfoDao;
	
	/**
	 * 
	 * @Title: setTestInfoDao 
	 * @Description:  注入testInfoDao
	 * @param testInfoDao
	 * @return: void
	 */
	public void setTestInfoDao(TestInfoDao testInfoDao) {
		this.testInfoDao = testInfoDao;
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectTestInfo
	 * @Description: 选择一个测试信息
	 * @param testId
	 * @return 
	 * @see com.seu.wsn.Service.TestInfoService#selectTestInfo(java.lang.String)
	 */
	@Override
	public TestInfo selectTestInfo(String testId) {
		return testInfoDao.selectTestInfo(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: insertTestInfo
	 * @Description: 插入一个测试信息
	 * @see com.seu.wsn.Service.TestInfoService#insertTestInfo()
	 */
	@Override
	public void insertTestInfo(String testName,String userName) {
		TestInfo testInfo = new TestInfo();
		testInfo.setTestId(Order.testId);
		testInfo.setTestName(testName);
		testInfo.setUserName(userName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		testInfo.setTestTime(df.format(new Date()));
		testInfoDao.insertTestInfo(testInfo);
	}
	/*
	 * (non Javadoc) 
	 * @Title: deleteTestInfo
	 * @Description: 删除一个测试信息
	 * @param testId 
	 * @see com.seu.wsn.Service.TestInfoService#deleteTestInfo(java.lang.String)
	 */
	@Override
	public void deleteTestInfo(String testId) {
		testInfoDao.deleteTestInfo(testId);
	}
	/*
	 * (non Javadoc) 
	 * @Title: selectTestInfoList
	 * @Description: 根据用户名选择测试信息列表
	 * @param userName
	 * @return 
	 * @see com.seu.wsn.Service.TestInfoService#selectTestInfoList(java.lang.String)
	 */
	@Override
	public List<TestInfo> selectTestInfoList(String userName) {
		return testInfoDao.selectTestInfoList(userName);
	}

}
