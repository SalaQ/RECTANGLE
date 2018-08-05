package com.seu.wsn.SensorController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.seu.wsn.Core.Pojo.UwNodePacketLoss;
import com.seu.wsn.Service.UwTestingService;

public class test {
	@Autowired
	private static UwTestingService uwTestingService;
	
	public void setNodeService(UwTestingService uwTestingService) {
		this.uwTestingService = uwTestingService;
	}
	
	public static void main(String[] args) {
		UwNodePacketLoss loss = new UwNodePacketLoss();
		loss.setNodeId("01");
		loss.setNodePacketLoss(0.1);
		loss.setNodeType("commonNode");
		loss.setPacketInterval(3);
		loss.setParentId("00");
		loss.setreceivePacket(0);
		loss.setSendPacket(10);
		loss.setSuccessPacket(9);
		loss.setTestId("111");
		loss.setTestNum(123);
		uwTestingService.insertPacketLoss(loss);
	}
}
