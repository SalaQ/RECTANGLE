package com.seu.wsn.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seu.wsn.Core.Pojo.UwNodePacketLoss;
import com.seu.wsn.Core.Pojo.UwPathLength;
import com.seu.wsn.Core.Pojo.SystemPacketLoss;
import com.seu.wsn.Core.Pojo.UwTimeDelay;
import com.seu.wsn.Core.Pojo.UwThroughput;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;

public interface UwTestingService {

	public void insertPacketLoss(UwNodePacketLoss packetLoss);
	
	public void deletePacketLoss(String testId);
	
	public List<UwNodePacketLoss> selectPacketLossList(String testId);
	
	public void insertUwPathLength(UwPathLength uwPathLength);

	public void deleteUwPathLength(String testId);

	public List<UwPathLength> selectUwPathLengthList(String testId);
	
	public void insertTimeDelay(UwTimeDelay uwTimeDelay);

	public void deleteTimeDelay(String testId);

	public List<UwTimeDelay> selectTimeDelayList(String testId);

	public void insertThroughput(UwThroughput uwThroughput);

	public void deleteThroughput(String testId);

	public List<UwThroughput> selectThroughputList(String testId);

	public void insertConnectivityDegree(ConnectivityDegree connectivityDegree);

	public void deleteConnectivityDegree(String testId);

	public List<ConnectivityDegree> selectConnectivityDegreeList(String testId);

}
