package com.seu.wsn.Core.Pojo;

import java.util.List;
/**
 * 
 * @ClassName: Data 
 * @Description: 性能对比中每个测试名称对应的数据
 * @author: CSS
 * @date: 2016-12-15 下午2:19:03
 */
public class Data {
	private String name;                               //测试名称
	private List<Double> data;                         //数据
	/**
	 * 
	 * @Title: getName 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @Title: setName 
	 * @Description: TODO
	 * @param name
	 * @return: void
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @Title: getData 
	 * @Description: TODO
	 * @return
	 * @return: List<Double>
	 */
	public List<Double> getData() {
		return data;
	}
	/**
	 * 
	 * @Title: setData 
	 * @Description: TODO
	 * @param data
	 * @return: void
	 */
	public void setData(List<Double> data) {
		this.data = data;
	}
}
