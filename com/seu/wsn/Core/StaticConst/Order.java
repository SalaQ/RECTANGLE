package com.seu.wsn.Core.StaticConst;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.net.Socket;

import com.seu.wsn.Core.Pojo.DelayT;



/**
 * 
 * @ClassName: Order 
 * @Description: 系统向开发板下达的指令标记
 * @author: CSS
 * @date: 2016-11-19 上午8:54:33
 */
public class Order {
	public volatile static Timer timer;
	public volatile static double now;
	public volatile static double delayNow;
	public volatile static Map<String,Socket> uwSocketList = new HashMap<String,Socket>();
	/*
	 * 系统启动连接标志
	 */
	public volatile static boolean startConnection = true; 
	
	/*
	 * testId
	 */
	public volatile static String testId;
	/*
	 * historyTestId
	 */
	public volatile static String historyTestId;
	/*
	 * historyTestName
	 */
	public volatile static String historyTestName;
	/*
	 * 丢包率性能测试第几次
	 */
	public volatile static int packetLossTestNum = 0;
	public volatile static int uwPacketLossTestNum = 0;
	/*
	 * 时延性能测试第几次
	 */
	public volatile static int timeDelayTestNum = 0;
	public volatile static int uwTimeDelayTestNum = 0;
	/*
	 * 连通度性能测试第几次
	 */
	public volatile static int connectivityDegreeTestNum = 0;
	public volatile static int uwConnectivityDegreeTestNum = 0;
	/*
	 * 平均路径长度性能测试第几次
	 */
	public volatile static int pathLengthTestNum = 0;
	public volatile static int uwPathLengthTestNum = 0;
	/*
	 * 吞吐量性能测试第几次
	 */
	public volatile static int throughputTestNum = 0;
	public volatile static int uwThroughputTestNum = 0;
	/*
	 *  组网是否完成标志
	 */
	public volatile static boolean netWorkingFlag = false;
	/*
	 * 性能测试结束标志
	 */
	public volatile static boolean testingEndFlag = false;
	/*
	 * 测试周期长度（秒）
	 */
	public volatile static byte cycleLength;
	/*
	 * 测试周期个数
	 */
	public volatile static byte cycleNumber;
	/*
	 * 周期间隔（秒）
	 */
	public volatile static byte cycleInterval;
	/*
	 * 测试包长度（字节）
	 */
	public volatile static byte packetLength;
	/*
	 * 测试包发送间隔（秒）
	 */
	public volatile static byte packetInterval;
	/*
	 * 测试包发送速率（字节/秒）
	 */
	public volatile static double packetSendSpeed;
	public volatile static byte uwPacketNumber;
	public volatile static byte uwPacketInterval;
	/*
	 * 时延测试时间
	 */
	public  volatile static int delay_testTime = 0;
	
	/*
	 * ***
	 */
	public volatile static boolean flag = false;
	/*
	 * 临时变量，用于存储时延测试时各个节点的收发包时间
	 */
	public volatile static Map<String,DelayT> dealyMap = new HashMap<String,DelayT>();
}
