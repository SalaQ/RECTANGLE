package com.seu.wsn.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seu.wsn.Common.Dao.UwPathLengthDao;
import com.seu.wsn.Common.Dao.UwTimeDelayDao;
import com.seu.wsn.Common.Dao.UwPacketLossDao;
import com.seu.wsn.Common.Dao.UwThroughputDao;
import com.seu.wsn.Common.Dao.ConnectivityDegreeDao;
import com.seu.wsn.Core.Pojo.UwNodePacketLoss;
import com.seu.wsn.Core.Pojo.UwPathLength;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.UwTimeDelay;
import com.seu.wsn.Core.Pojo.UwThroughput;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.WebConst;

@Service("uwTestingService")
public class UwTestingServiceImpl implements UwTestingService {

	@Autowired
	private UwPacketLossDao uwPacketLossDao;
	@Autowired
	private UwPathLengthDao uwPathLengthDao;
	@Autowired
	private UwTimeDelayDao uwTimeDelayDao;
	@Autowired
	private UwThroughputDao uwThroughputDao;
	@Autowired
	private ConnectivityDegreeDao connectivityDegreeDao;
	
	public void setPacketLossDao(UwPacketLossDao uwPacketLossDao) {
		this.uwPacketLossDao = uwPacketLossDao;
	}
	
	public void setUwPathLengthDao(UwPathLengthDao uwPathLengthDao) {
		this.uwPathLengthDao = uwPathLengthDao;
	}
	
	public void setTimeDelayDao(UwTimeDelayDao uwTimeDelayDao) {
		this.uwTimeDelayDao = uwTimeDelayDao;
	}

	public void setThroughputDao(UwThroughputDao uwThroughputDao) {
		this.uwThroughputDao = uwThroughputDao;
	}
	
	@Override
	public void insertPacketLoss(UwNodePacketLoss packetLoss) {
		uwPacketLossDao.insertPacketLoss(packetLoss);
	}

	
	@Override
	public void deletePacketLoss(String testId) {
		uwPacketLossDao.deletePacketLoss(testId);
	}

	@Override
	public List<UwNodePacketLoss> selectPacketLossList(String testId) {
		return uwPacketLossDao.selectPacketLossList(testId);
	}
	
	@Override
	public void insertUwPathLength(UwPathLength uwPathLength) {
		uwPathLengthDao.insertUwPathLength(uwPathLength);
	}

	@Override
	public void deleteUwPathLength(String testId) {
		uwPathLengthDao.deleteUwPathLength(testId);
	}

	@Override
	public List<UwPathLength> selectUwPathLengthList(String testId) {
		return uwPathLengthDao.selectUwPathLengthList(testId);
	}
	
	@Override
	public void insertTimeDelay(UwTimeDelay uwTimeDelay) {
		uwTimeDelayDao.insertUwTimeDelay(uwTimeDelay);
	}

	@Override
	public void deleteTimeDelay(String testId) {
		uwTimeDelayDao.deleteUwTimeDelay(testId);
		
	}

	@Override
	public List<UwTimeDelay> selectTimeDelayList(String testId) {
		return uwTimeDelayDao.selectUwTimeDelayList(testId);
	}

	@Override
	public void insertThroughput(UwThroughput uwThroughput) {
		uwThroughputDao.insertUwThroughput(uwThroughput);
	}

	@Override
	public void deleteThroughput(String testId) {
		uwThroughputDao.deleteUwThroughput(testId);
	}

	@Override
	public List<UwThroughput> selectThroughputList(String testId) {
		return uwThroughputDao.selectUwThroughputList(testId);
	}

	@Override
	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree) {
		connectivityDegreeDao.insertConnectivityDegree(connectivityDegree);
	}

	@Override
	public void deleteConnectivityDegree(String testId) {
		connectivityDegreeDao.deleteConnectivityDegree(testId);
	}

	@Override
	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId) {
		return connectivityDegreeDao.selectConnectivityDegreeList(testId);
	}

}
