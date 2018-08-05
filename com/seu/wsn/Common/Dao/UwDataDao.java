package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.UwData;

public interface UwDataDao {

	public UwData select(UwData uwData);
	
	public void insert(UwData uwData);
	
	public void update(UwData uwData);
	
	public void delete();
	
	public void deleteByTime(UwData uwData);
	
	public List<UwData> uwDataList();
	
}
