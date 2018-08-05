package com.seu.wsn.Service;

import java.util.List;

import com.seu.wsn.Core.Pojo.UwData;

public interface UwDataService {

	public UwData select(String sourceId);
	
	public void insert(UwData uwData);
	
	public void update(UwData uwData);
	
	public void delete();
	
	public void deleteByTime(UwData uwData);
	
	public List<UwData> uwDataList();
}
