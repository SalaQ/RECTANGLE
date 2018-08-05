package com.seu.wsn.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Dao.UwDataDao;
import com.seu.wsn.Core.Pojo.UwData;

@Service("uwDataService")
public class UwDataServiceImpl implements UwDataService {

	@Autowired
	private UwDataDao uwDataDao;
	
	public void setUwDataDao(UwDataDao uwDataDao) {
		this.uwDataDao = uwDataDao;
	}
	
	@Override
	public UwData select(String sourceId) {
		UwData uwData = new UwData();
		uwData.setSourceId(sourceId);
		return uwDataDao.select(uwData);
	}
	
	@Override
	public void insert(UwData uwData) {
		uwDataDao.insert(uwData);
	}
	
	@Override
	public void update(UwData uwData) {
		uwDataDao.update(uwData);
	}
	
	@Override
	public void delete() {
		uwDataDao.delete();
	}
	
	@Override
	public void deleteByTime(UwData uwData) {
		uwDataDao.deleteByTime(uwData);
	}
	
	@Override
	public List<UwData> uwDataList() {
		return uwDataDao.uwDataList();
	}
	
}
