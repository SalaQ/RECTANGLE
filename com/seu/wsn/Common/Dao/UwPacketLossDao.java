package com.seu.wsn.Common.Dao;

import java.util.List;

import com.seu.wsn.Core.Pojo.UwNodePacketLoss;

public interface UwPacketLossDao {
	public void insertPacketLoss(UwNodePacketLoss packetLoss);

	public void deletePacketLoss(String testId);

	public List<UwNodePacketLoss> selectPacketLossList(String testId);
}
