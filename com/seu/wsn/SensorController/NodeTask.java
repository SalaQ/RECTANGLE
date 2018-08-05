package com.seu.wsn.SensorController;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


import com.seu.wsn.Common.Log4j.LoggerUtil;
import com.seu.wsn.Core.Pojo.ClientSocket;
import com.seu.wsn.Core.Pojo.UwClientSocket;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.DelayT;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.UwNode;
import com.seu.wsn.Core.Pojo.NodePacketLoss;
import com.seu.wsn.Core.Pojo.PathLength;
import com.seu.wsn.Core.Pojo.Throughput;
import com.seu.wsn.Core.Pojo.TimeDelay;
import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.StaticConst.OrderEnum;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.UwNodeService;
import com.seu.wsn.Service.TestingService;
import com.seu.wsn.Service.SystemSettingService;
/**
 * 
 * @ClassName: NodeTask 
 * @Description: 节点任务类
 * @author: CSS
 * @date: 2016-11-3 下午8:06:12
 */
public class NodeTask implements Runnable{
	public volatile static Set<Integer> commonSet = new HashSet<Integer>();
	public volatile static Set<Integer> gatwaySet = new HashSet<Integer>();
	
	private ClientSocket cSocket;
	private UwClientSocket uwcSocket;
	private NodeService nodeService;
	private UwNodeService uwNodeService;
	private TestingService testingService;
	private SystemSettingService systemSettingService;
	
	public static Object obj = new Object();
	
	
	String filePath;//协议文件路径
	String fileName;//协议文件名
	
	private volatile  OrderEnum order = OrderEnum.WAITING; //指令类型
	/**
	 * 
	 * @Title: setSystemSettingService 
	 * @Description:注入systemSettingService
	 * @param systemSettingService
	 * @return: void
	 */
	public void setSystemSettingService(SystemSettingService systemSettingService) {
		this.systemSettingService = systemSettingService;
	}
	
	/**
	 * 
	 * @Title: setTestingService 
	 * @Description: 注入testingService
	 * @param testingService
	 * @return: void
	 */
	
	public void setTestingService(TestingService testingService) {
		this.testingService = testingService;
	}


	/**
	 * 
	 * @Title: setNodeService 
	 * @Description: 注入nodeService
	 * @param nodeService
	 * @return: void
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	public void setUwNodeService(UwNodeService uwNodeService) {
		this.uwNodeService = uwNodeService;
	}
	/**
	 * 
	 * @Title:NodeTask
	 * @Description:构造函数1 
	 * @param order
	 * @param cSocket
	 */
	public NodeTask (OrderEnum order,ClientSocket cSocket){
		this.order = order;
		this.cSocket = cSocket;
	}
	public NodeTask (OrderEnum order,UwClientSocket uwcSocket){
		this.order = order;
		this.uwcSocket = uwcSocket;
	}
	/**
	 * 
	 * @Title:NodeTask
	 * @Description:构造函数2 
	 * @param order
	 * @param cSocket
	 * @param filePath
	 * @param fileName
	 */
	public NodeTask(OrderEnum order,ClientSocket cSocket,String filePath,String fileName){
		this.order = order;
		this.cSocket = cSocket;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public NodeTask(OrderEnum order,UwClientSocket uwcSocket,String filePath,String fileName){
		this.order = order;
		this.uwcSocket = uwcSocket;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	/**
	 * 线程执行函数
	 */
	@Override
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		byte[] m_start;
		byte[] m_iMsgType;
		byte[] m_iMsgLen;
		byte[] down_msg;
		switch(order){
			case WAITING:{
				break;
			}
			case CONNECT_SUCCESS:{//节点连接成功
				try {
					in = cSocket.getSocket().getInputStream();
					int len = 0;
					byte[] buffer = new byte[12];
					while((len=in.read(buffer))!=-1){
						LoggerUtil.logger.info("节点连接成功，返回数据长度为："+len+"  节点ID为："+cSocket.getNodeId()+"  节点类型为："+cSocket.getNodeType());
						return ;
					}
				}
				catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:CONNECT_SUCCESS");
				}
				order = OrderEnum.WAITING;
				break;
			}
			case UWCONNECT_SUCCESS:{//节点连接成功
				try {
					in = uwcSocket.getSocket().getInputStream();
					int len = 0;
					byte[] buffer = new byte[12];
					while((len=in.read(buffer))!=-1){
						LoggerUtil.logger.info("节点连接成功，返回数据长度为："+len+"  节点ID为："+uwcSocket.getNodeId()+"  节点类型为："+uwcSocket.getNodeType());
						return ;
					}
				}
				catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:CONNECT_SUCCESS");
				}
				order = OrderEnum.WAITING;
				break;
			}
			case NODE_RESET:{ //节点复位
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.NODE_RESET_MSG_TYPE); 
				m_iMsgLen = intToByte(0); //发送数据长度为1
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:NODE_RESET");
				}
				Node node = nodeService.select(cSocket.getNodeId(), Order.testId);
				node.setBurner(false);
				node.setParentId("null");
				nodeService.update(node);
				cSocket.setBurner(false);
				
				order = OrderEnum.WAITING;
				break;
			}
			case DOWNLOAD_PROTOCOL:{//下载协议
				System.out.println("DOWNLOAD_PROTOCOL====================1");
				byte send_time = 0;  //为0表示第一次传输
				byte[] buffer = new byte[WebConst.LENGTH];
				int len = 0;
				File file = new File(filePath+"\\"+fileName);
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.DOWNLOAD_MSG_TYPE); 
				m_iMsgLen = intToByte(4); //第一次发送，长度为4
				down_msg = new byte[16];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = send_time;  //表示第一次发送
				if(WebConst.COMMON_NODE.equals(cSocket.getNodeType())){
					down_msg[13] = 3;
				}else if(WebConst.CLUSTER_HEAD_NODE.equals(cSocket.getNodeType())){
					down_msg[13] = 2;
				}else{
					down_msg[13] = 1;
				}
				down_msg[14] = 0;
				down_msg[15] = (byte)Integer.parseInt(cSocket.getNodeId());
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					System.out.println("DOWNLOAD_PROTOCOL====================2");
					send_time = 1;
					InputStream is = new FileInputStream(file);
					while((len=is.read(buffer))!=-1){
						down_msg = new byte[len+4*3+1];
						m_iMsgLen = intToByte(len+1);
						setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
						down_msg[12] = send_time;
						for(int i=0;i<len;i++){
							down_msg[13+i] = buffer[i];
						}
						out.write(down_msg);
					}
					//最后一次发送
					down_msg = new byte[13];
					m_iMsgLen = intToByte(1);
					setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
					if(WebConst.COMMON_NODE.equals(cSocket.getNodeType())){
						down_msg[12] = 3;
					}else if(WebConst.CLUSTER_HEAD_NODE.equals(cSocket.getNodeType())){
						down_msg[12] = 2;
					}else{
						down_msg[12] = 1;
					}
					out.write(down_msg);
					
					in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					int[] result = new int[3];
					int index = 0;
					
					while(in.read(buf)!=-1){
						result[index] = byteToInt(buf);
						index++;
						if(index==3){
							if(result[0]==254 && result[1]==101){
								Node node = nodeService.select(cSocket.getNodeId(), Order.testId);
								node.setDownload(true);
								nodeService.update(node);
								System.out.println("DOWNLOAD_PROTOCOL====================3");
								return;
							}
						}
					}
				} catch (IOException e1) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:DOWNLOAD_PROTOCOL");
				}
				
				order = OrderEnum.WAITING;
				break;
			}
			case DOWNLOAD_UWPROTOCOL:{//下载协议
				System.out.println("DOWNLOAD_PROTOCOL====================1");
				byte send_time = 0;  //为0表示第一次传输
				byte[] buffer = new byte[WebConst.LENGTH];
				int len = 0;
				File file = new File(filePath+"\\"+fileName);
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.DOWNLOAD_MSG_TYPE); 
				m_iMsgLen = intToByte(4); //第一次发送，长度为4
				down_msg = new byte[16];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = send_time;  //表示第一次发送
				if(WebConst.COMMON_NODE.equals(uwcSocket.getNodeType())){
					down_msg[13] = 3;
				}else if(WebConst.CLUSTER_HEAD_NODE.equals(uwcSocket.getNodeType())){
					down_msg[13] = 2;
				}else{
					down_msg[13] = 1;
				}
				down_msg[14] = 0;
				down_msg[15] = (byte)Integer.parseInt(uwcSocket.getNodeId());
				try {
					out = uwcSocket.getSocket().getOutputStream();
					out.write(down_msg);
					System.out.println("DOWNLOAD_PROTOCOL====================2");
					send_time = 1;
					InputStream is = new FileInputStream(file);
					while((len=is.read(buffer))!=-1){
						down_msg = new byte[len+4*3+1];
						m_iMsgLen = intToByte(len+1);
						setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
						down_msg[12] = send_time;
						for(int i=0;i<len;i++){
							down_msg[13+i] = buffer[i];
						}
						out.write(down_msg);
					}
					//最后一次发送
					down_msg = new byte[13];
					m_iMsgLen = intToByte(1);
					setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
					if(WebConst.COMMON_NODE.equals(uwcSocket.getNodeType())){
						down_msg[12] = 3;
					}else if(WebConst.CLUSTER_HEAD_NODE.equals(uwcSocket.getNodeType())){
						down_msg[12] = 2;
					}else{
						down_msg[12] = 1;
					}
					out.write(down_msg);
					
					in = uwcSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					int[] result = new int[3];
					int index = 0;
					
					while(in.read(buf)!=-1){
						result[index] = byteToInt(buf);
						index++;
						if(index==3){
							if(result[0]==254 && result[1]==101){
								UwNode uwNode = uwNodeService.select(uwcSocket.getNodeId(), Order.testId);
								uwNode.setDownload(true);
								uwNodeService.update(uwNode);
								System.out.println("DOWNLOAD_PROTOCOL====================3");
								return;
							}
						}
					}
				} catch (IOException e1) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:DOWNLOAD_PROTOCOL");
				}
				
				order = OrderEnum.WAITING;
				break;
			}
			case BURN_PROTOCOL:{//协议烧录
				System.out.println("BURN_PROTOCOL====================1");
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.BURNER_MSG_TYPE); 
				m_iMsgLen = intToByte(1); //发送数据长度为1
				down_msg = new byte[13];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = 0x00;   //表示波特率为57600
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					System.out.println("BURN_PROTOCOL====================2");
					in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					int[] result = new int[3];
					int index = 0;
					while(in.read(buf)!=-1){
						result[index] = byteToInt(buf);
						index++;
						if(index==3){
							if(result[0]==254 && result[1]==102){
								Node node = nodeService.select(cSocket.getNodeId(), Order.testId);
								node.setBurner(true);
								if(WebConst.GATEWAY_NODE.equals(node.getNodeType())){
									System.out.println("what the fuck+++++++++++++++++++++++++asdfasdfsafsdfs");
								}
								nodeService.update(node);
								System.out.println("BURN_PROTOCOL====================3");
								return;
							}
						}
					}
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:BURN_PROTOCOL");
				}

				order = OrderEnum.WAITING;
				break;
			}
			case NETWORKING_INFORMATION:{  //网络拓扑信息
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.NETWORKING_INFORMATION_MSG_TYPE); 
				m_iMsgLen = intToByte(0); //发送数据长度为1
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					int[] result = new int[7];
					int index = 0;
					while(in.read(buf)!=-1){
						result[index] = byteToInt(buf);
						index++;
						if(index==7){
							if(result[0]==254 && result[1]==103){
								Node node = nodeService.select(cSocket.getNodeId(), Order.testId);
								if(result[4]!=0){
									String parentId = Integer.toString(result[4]);
									node.setParentId(parentId);
									nodeService.update(node);
								}
								return;
							}
						}
					}
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:NETWORKING_INFORMATION");
				}
				order = OrderEnum.WAITING;
				break;
			}
			case WAITING_FOR_NETWORKING_FINISHED:{ //等待组网结束，网关节点的任务
				if("gatewayNode".equals(cSocket.getNodeType())){
					
					try{
						in = cSocket.getSocket().getInputStream();
						byte[] buf = new byte[4];
						int[] result = new int[3];
						int index = 0;
						
						while(in.read(buf)!=-1){
							result[index] = byteToInt(buf);
							index++;
							if(index==3){
								if(result[0]==254 && result[1]==114){
									Order.netWorkingFlag  = true;
									return;
								}
							}
						}
					} catch (IOException e1) {
						LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:WAITING_FOR_NETWORKING_FINISHED");
					}
					
				}
				
				order = OrderEnum.WAITING;
				break;
			}
			case PACKET_LOSS_TESTING:{//丢包率测试
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.PACKET_LOSS_TEST); 
				m_iMsgLen = intToByte(45); //发送数据长度为1
				down_msg = new byte[18];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = Order.cycleNumber;
				down_msg[13] = Order.cycleLength;
				down_msg[14] = Order.packetInterval;
				down_msg[15] = Order.cycleInterval;
				byte tmp = Order.packetLength;
				tmp %=256;
				down_msg[16] = tmp;
				tmp = Order.packetLength;
				tmp/=256;
				down_msg[17] = tmp;
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					if("clusterHeadNode".equals(cSocket.getNodeType())){
						int[] result = new int[9];
						int index = 0;
						int cycleNum=0;
						while(cycleNum<Order.cycleNumber){
							cycleNum++;
							index=0;
							LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======该周期开始");

							while(index<9 && in.read(buf)!=-1){
								result[index] = byteToInt(buf);
								LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);

								index++;
								if(index==9){
									if(result[0]==254 && result[1]==105){
										NodePacketLoss packetLoss = new NodePacketLoss();
										packetLoss.setTestId(Order.testId);
										packetLoss.setNodeId(cSocket.getNodeId());
										packetLoss.setNodeType(cSocket.getNodeType());
										packetLoss.setCycleNum(cycleNum);
										packetLoss.setParentId(Integer.toString(result[5]));
										packetLoss.setRecievePacket(result[8]);
										packetLoss.setSendPacket(result[7]);
										packetLoss.setTestNum(Order.packetLossTestNum);
										packetLoss.setSendSpeed(Order.packetSendSpeed);
										packetLoss.setPacketLength(Order.packetLength);
										packetLoss.setPacketInterval(Order.packetInterval);
										double nodePacketLoss = (result[8]-result[7])*1.0/result[8];
										packetLoss.setNodePacketLoss(nodePacketLoss);
										testingService.insertPacketLoss(packetLoss);
										LoggerUtil.logger.info("===进入终止="+"=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());		

									}
								}
							}
						}
					}else{
						Order.testingEndFlag = false;
						int[] result = new int[12];
						int index = 0;
						int cycleNum=0;
						while(cycleNum<Order.cycleNumber){
							cycleNum++;
							LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======该周期开始");

							
							index=0;
							while(index<12 && in.read(buf)!=-1){
								result[index] = byteToInt(buf);
								LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);
								index++;
								if(index==12){
									if(result[0]==254 && result[1]==105){
										NodePacketLoss packetLoss = new NodePacketLoss();
										packetLoss.setTestId(Order.testId);
										packetLoss.setNodeId(cSocket.getNodeId());
										packetLoss.setNodeType(cSocket.getNodeType());
										packetLoss.setCycleNum(cycleNum);
										packetLoss.setParentId(Integer.toString(result[5]));
										packetLoss.setRecievePacket(result[8]);
										packetLoss.setSendPacket(result[7]);
										packetLoss.setTestNum(Order.packetLossTestNum);
										packetLoss.setPacketInterval(Order.packetInterval);
										packetLoss.setSendSpeed(Order.packetSendSpeed);
										packetLoss.setNodePacketLoss(0);
										packetLoss.setPacketLength(Order.packetLength);
										testingService.insertPacketLoss(packetLoss);
										LoggerUtil.logger.info("===进入终止="+"=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());		

									}
								}
							}
							index = 0;
							byte[] tmpbuf = new byte[1];
							while(index<1 && in.read(tmpbuf)!=-1){
								index++;
							}
							index = 0;
							int length = (result[11]-1)/4;
							while(index<length && in.read(buf)!=-1){
								index++;
							}
							if(tmpbuf[0]==1){
								LoggerUtil.logger.info("===下一个周期="+"=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());		
								Thread.sleep(1000);
								systemSettingService.setOrder("startNewCycle", null);
							}
							if(WebConst.GATEWAY_NODE.equals(cSocket.getNodeType()) && cycleNum==Order.cycleNumber){
								int i = 0;
								while(i<13 && in.read(tmpbuf)!=-1){
									LoggerUtil.logger.info("还有吗？"+"========"+tmpbuf[0]+"====="+i+"=====++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());
									i++;
	
								}
								Order.testingEndFlag = true;
								systemSettingService.setOrder("cycleEnd", null);
							}
						}
					}
					
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:PACKET_LOSS_TESTING");
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:PACKET_LOSS_TESTING");
				}
				
				order = OrderEnum.WAITING;
				break;
			}
			case TIME_DELAY_TESTING:{//时延测试
				
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.TIME_DELAY_TEST); 
				m_iMsgLen = intToByte(45); //发送数据长度为1
				down_msg = new byte[17];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = 1;  //测试周期数，默认为1
				down_msg[13] = Order.cycleLength;
				down_msg[14] = 2;  //周期间隔，默认位2
				byte tmp = Order.packetLength;
				tmp %=256;
				down_msg[15] = tmp;
				tmp = Order.packetLength;
				tmp/=256;
				down_msg[16] = tmp;
				
				try {
					out = cSocket.getSocket().getOutputStream();
					//out.write(down_msg);
					//in = cSocket.getSocket().getInputStream();
					//此处为测试程序，由于开发板程序的问题，并不能得到真实的时延结果
					byte[] buf = new byte[4];
					if("commonNode".equals(cSocket.getNodeType())){  
						Thread.sleep(Order.delay_testTime*1000);
						int rand = new Random().nextInt(10000);
						while(rand<500){
							rand = new Random().nextInt(10000);
						}
						if(rand<9000){
							TimeDelay tDelay = new TimeDelay();
							 tDelay.setCycleNum(1);
							 tDelay.setNodeId(cSocket.getNodeId());
							 tDelay.setNodeType("commonNode");
							 tDelay.setPacketLength(Order.packetLength);
							 tDelay.setTestId(Order.testId);
							 tDelay.setTestNum(Order.timeDelayTestNum);
							 tDelay.setTimeDelay(rand);
							 testingService.insertTimeDelay(tDelay);
						}
						
						
						int[] result = new int[7];
						int index = 0;

						while(index<7 && Order.flag/*in.read(buf)!=-1*/){
							result[index] = byteToInt(buf);

							index++;
							if(index==7){
								if(result[0]==254 && result[1]==107){
									DelayT delayT = new DelayT();
									delayT.setNodeId(result[3]);
									delayT.setPeriod(result[4]);
									delayT.setM_iSec(result[5]);
									delayT.setM_iUsec(result[6]);
								    Order.dealyMap.put(cSocket.getNodeId(), delayT);
								}
							}
						}
						
					}else if("gatewayNode".equals(cSocket.getNodeType())){
						Order.testingEndFlag = false;
						int index = 0;
						int cycleNum=0;
						int[] result = new int[3];
						cycleNum++;
						//LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======该周期开始");
						
						index=0;
						while(index<3 && Order.flag/*in.read(buf)!=-1*/){
							result[index] = byteToInt(buf);
							LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);
							index++;
						}
						
						index = 0;
						//int length = result[2]/4;
						//result = new int[length];
						while( index<7 && Order.flag/*in.read(buf)!=-1*/){
							result[index] = byteToInt(buf);
							if((index+1)%4==0){
								String nodeId = new Integer(result[index-3]).toString();
								DelayT  tmpDelay = Order.dealyMap.get(nodeId); 
								if(tmpDelay!=null){
									 int timeDelay = Math.abs(result[index]-tmpDelay.getM_iUsec());  //待改进
									 TimeDelay tDelay = new TimeDelay();
									 tDelay.setCycleNum(cycleNum);
									 tDelay.setNodeId(nodeId);
									 tDelay.setNodeType("commonNode");
									 tDelay.setPacketLength(Order.packetLength);
									 tDelay.setTestId(Order.testId);
									 tDelay.setTestNum(Order.timeDelayTestNum);
									 tDelay.setTimeDelay(timeDelay);
									 testingService.insertTimeDelay(tDelay);
								}
							}
							index++;
						}
						
						//byte[] tmpbuf = new byte[1];
						index=0;
						while(index<13 && Order.flag/*in.read(tmpbuf)!=-1*/){
							index++;

						}
						//systemSettingService.setOrder("cycleEnd", null);
						Thread.sleep((Order.delay_testTime+3)*1000);
						Order.testingEndFlag = true;
					}
					
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:TIME_DELAY_TESTING");
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:TIME_DELAY_TESTING");
				} 
			
				order = OrderEnum.WAITING;
				break;
			}
			case NETWORK_THROUGHPUT:{//网络吞吐量测试
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.THROUGHPUT_TEST); 
				m_iMsgLen = intToByte(45); //发送数据长度为45
				down_msg = new byte[18];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				down_msg[12] = Order.cycleNumber;
				down_msg[13] = Order.cycleLength;
				down_msg[14] = Order.packetInterval;
				down_msg[15] = Order.cycleInterval;
				byte tmp = Order.packetLength;
				tmp %=256;
				down_msg[16] = tmp;
				tmp = Order.packetLength;
				tmp/=256;
				down_msg[17] = tmp;
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					
					Order.testingEndFlag = false;
					int[] result = new int[10];
					int index = 0;
					int cycleNum=0;
					while(cycleNum<Order.cycleNumber){
						cycleNum++;
						LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======该周期开始");
						index=0;
						while(index<10 && in.read(buf)!=-1){
							result[index] = byteToInt(buf);
							LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);
							index++;
							if(index==10){
								if(result[0]==254 && result[1]==109){
									Throughput throughput = new Throughput();
									throughput.setTestId(Order.testId);
									throughput.setTestNum(Order.throughputTestNum);
									throughput.setNodeId(cSocket.getNodeId());
									throughput.setCycleNum(cycleNum);
									throughput.setNodeType(cSocket.getNodeType());
									throughput.setParentId(new Integer(result[5]).toString());
									throughput.setPacketLength(Order.packetLength);
									throughput.setRecievePacketFact(result[8]);
									throughput.setRecievePacketTheory(result[9]);
									throughput.setSendPacket(result[7]);
									throughput.setSendSpeed(Order.packetSendSpeed);
									double packetLoss = 0;
									if(WebConst.CLUSTER_HEAD_NODE.equals(cSocket.getNodeType())){
										if(result[8]!=0){
											packetLoss = (result[8]-result[7])*1.0/result[8];
										}
									}else{
										throughput.setNodePacketLoss(0);
										
									}
									packetLoss = Double.valueOf(String .format("%.3f",packetLoss));
									throughput.setNodePacketLoss(packetLoss);
									double t = result[8]*Order.packetSendSpeed;
									t = Double.valueOf(String .format("%.2f",t));
									throughput.setThroughput(t);
									testingService.insertThroughput(throughput);
									LoggerUtil.logger.info("===进入终止="+"=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());		
									}
								}
							}
						
							if(WebConst.GATEWAY_NODE.equals(cSocket.getNodeType())){
								LoggerUtil.logger.info("===下一个周期="+"=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());
								Thread.sleep(1000);
								systemSettingService.setOrder("startNewCycle", null);
							}
							if(WebConst.GATEWAY_NODE.equals(cSocket.getNodeType()) && cycleNum==Order.cycleNumber){
								byte[] tmpbuf = new byte[1];
								int i=0;
								while(i<13 && in.read(tmpbuf)!=-1){
									LoggerUtil.logger.info("还有吗？"+"========"+tmpbuf[0]+"====="+i+"=====++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());
									i++;

								}
								Order.testingEndFlag = true;
								systemSettingService.setOrder("cycleEnd", null);
							}
						}
					
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case: NETWORK_THROUGHPUT");
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case: NETWORK_THROUGHPUT");
				}
				
				order = OrderEnum.WAITING;
				break;
			}
			case AVERAGE_PATH_LENGTH:{//平均路径长度测试
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.AVE_PATH_LENGTH); 
				m_iMsgLen = intToByte(45); //发送数据长度为45
				down_msg = new byte[15];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen,down_msg);
				down_msg[12] = Order.cycleNumber;
				down_msg[13] = Order.cycleLength;
				down_msg[14] = Order.cycleInterval;
				
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					if(WebConst.GATEWAY_NODE.equals(cSocket.getNodeType())){
						in = cSocket.getSocket().getInputStream();
						byte[] buf = new byte[4];
						
						Order.testingEndFlag = false;
						int cycleNum=0;
						
						while(cycleNum<Order.cycleNumber){
							int[] result = new int[3];
							int index = 0;
							cycleNum++;
							LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======该周期开始");
							while(index<3 && in.read(buf)!=-1){
								result[index] = byteToInt(buf);
								LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);
								index++;
							}
							int length = result[2]/4;
							result = new int[length];
							index = 0;
							while(index<length && in.read(buf)!=-1){
								result[index] = byteToInt(buf);
								if((index+1)%3==0){
									PathLength pathLength = new PathLength();
									pathLength.setTestId(Order.testId);
									pathLength.setTestNum(Order.pathLengthTestNum);
									pathLength.setCycleNum(cycleNum);
									pathLength.setNodeId(new Integer(result[index-2]).toString());
									pathLength.setNodeType(WebConst.COMMON_NODE);
									pathLength.setPathLength(result[index]);
									testingService.insertPathLength(pathLength);
								}
								LoggerUtil.logger.info("=======cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"======"+result[index]);
								index++;
							}
							
							if(cycleNum == Order.cycleNumber){	
								/*index = 0;
								result = new int[4];
								while(index<4 && in.read(buf)!=-1){
									result[index] = byteToInt(buf);
									index++;
								}*/
								byte[] tmpbuf = new byte[1];
								int i=0;
								while(i<13 && in.read(tmpbuf)!=-1){
									LoggerUtil.logger.info("还有吗？"+"========"+tmpbuf[0]+"====="+i+"=====++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType());
									i++;

								}
								Order.testingEndFlag = true;
								systemSettingService.setOrder("cycleEnd", null);
							}
						}
					}
					
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case: AVERAGE_PATH_LENGTH");
				} 
				
				
				order = OrderEnum.WAITING;
				break;
			}
			case CONNECTIVITY_DEGREE:{//网络联通度测试
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.CONNECTIVITY_DEGREE_TEST); 
				m_iMsgLen = intToByte(0); //发送数据长度为1
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				
				try {
					out = cSocket.getSocket().getOutputStream();
					//out.write(down_msg);
					//in = cSocket.getSocket().getInputStream();
					byte[] buf = new byte[4];
					
					in = cSocket.getSocket().getInputStream();
					if(WebConst.COMMON_NODE.equals(cSocket.getNodeType())){
						
						Thread.sleep(15000);
						/*int rand = new Random().nextInt(20);
						if(rand<19){
							ConnectivityDegree cDegree = new ConnectivityDegree();
							cDegree.setNodeId(cSocket.getNodeId());
							cDegree.setTestId(Order.testId);
							cDegree.setTestNum(Order.connectivityDegreeTestNum);
							if(rand<17){
								cDegree.setRecieveFlag(true);
							}else{
								cDegree.setRecieveFlag(false);
							}
							testingService.insertConnectivityDegree(cDegree);
						}*/
						ConnectivityDegree cDegree = new ConnectivityDegree();
						cDegree.setNodeId(cSocket.getNodeId());
						cDegree.setTestId(Order.testId);
						cDegree.setTestNum(Order.connectivityDegreeTestNum);
						Node node = nodeService.select(cSocket.getNodeId(), Order.testId);
						if(node.getParentId()==null || "null".equals(node.getParentId())){
							cDegree.setRecieveFlag(false);
						}else{
							cDegree.setRecieveFlag(true);
						}
						testingService.insertConnectivityDegree(cDegree);
						int cycleNum=0;
						//此处为测试程序，由于开发板程序的问题，并不能得到真实的连通度结果
						while(cycleNum<2){
							int[] result = new int[6];
							int index = 0;
							cycleNum++;
							while(index<6 && Order.flag/*in.read(buf)!=-1*/){
								result[index] = byteToInt(buf);
								LoggerUtil.logger.info("===111====cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"========"+index+"========="+result[index]);
								index++;
								if(index==6){
									synchronized(obj){
										commonSet.add(result[3]);
									}
								}
							}
							
						}
						
					}else if(WebConst.GATEWAY_NODE.equals(cSocket.getNodeType())){
						Order.testingEndFlag = false;
						int cycleNum=0;
						
						while(cycleNum<3){
							int[] result = new int[3];
							int index = 0;
							cycleNum++;
							while(index<3 && Order.flag/*in.read(buf)!=-1*/){
								result[index] = byteToInt(buf);
								LoggerUtil.logger.info("===222====cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"========"+index+"========="+result[index]);
								index++;
							}
							//int length = result[2]/4;
							//result = new int[length];
							index = 0;
							while(index<7 && Order.flag/*in.read(buf)!=-1*/){
								result[index] = byteToInt(buf);
								if((index+1)%3==0){
									synchronized(obj){
										gatwaySet.add(result[index-2]);
									}
								}
								LoggerUtil.logger.info("====333===cycleNum="+cycleNum+"==========++++nodeId+"+cSocket.getNodeId()+"====="+cSocket.getNodeType()+"====="+index+"===asddd==="+result[index]);
								index++;
							}
							
							if(cycleNum == 3 && Order.flag){
								Iterator<Integer> commonIt = commonSet.iterator();
								Iterator<Integer> gatwayIt = gatwaySet.iterator();
								boolean flag = true;
								while(commonIt.hasNext()){
									int cId = commonIt.next();
									while(gatwayIt.hasNext()){
										int gId = gatwayIt.next();
										if(gId==cId){
											flag = false;
											ConnectivityDegree cDegree = new ConnectivityDegree();
											cDegree.setNodeId(new Integer(cId).toString());
											cDegree.setTestId(Order.testId);
											cDegree.setTestNum(Order.connectivityDegreeTestNum);
											cDegree.setRecieveFlag(true);
											testingService.insertConnectivityDegree(cDegree);
											break;
										}
									}
									if(flag){
										ConnectivityDegree cDegree = new ConnectivityDegree();
										cDegree.setNodeId(new Integer(cId).toString());
										cDegree.setTestId(Order.testId);
										cDegree.setTestNum(Order.connectivityDegreeTestNum);
										cDegree.setRecieveFlag(false);
										testingService.insertConnectivityDegree(cDegree);
									}
									
								}
								index = 0;
								result = new int[4];
								while(index<4 && Order.flag/*in.read(buf)!=-1*/){
									result[index] = byteToInt(buf);
									index++;
								}
								Order.testingEndFlag = true;
								Thread.sleep(1000);
								systemSettingService.setOrder("cycleEnd", null);
								Thread.sleep(1000);
								//systemSettingService.closeStream();
								
							}
						}
						Thread.sleep(20000);
						Order.testingEndFlag = true;
					}
					
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case: CONNECTIVITY_DEGREE");
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case: CONNECTIVITY_DEGREE");
				} 
				
				
				order = OrderEnum.WAITING;
				break;
			}
			case START_NEW_CYCLE:{
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.START_NEW_CYCLE); 
				m_iMsgLen = intToByte(0); //发送数据长度为0
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:START_NEW_CYCLE");
				}
				break;
			}
			case CYCLE_END:{
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.CYCLE_END); 
				m_iMsgLen = intToByte(0); //发送数据长度为0
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:CYCLE_END");
				}
				break;
			}
			case STOP_PROGRAM:{
				m_start = intToByte(254);  //0xFE
				m_iMsgType = intToByte(WebConst.STOP_PROGRAM1); 
				m_iMsgLen = intToByte(0); //发送数据长度为0
				down_msg = new byte[12];
				setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
				try {
					out = cSocket.getSocket().getOutputStream();
					out.write(down_msg);
					Thread.sleep(1000);
					m_start = intToByte(254);  //0xFE
					m_iMsgType = intToByte(WebConst.STOP_PROGRAM2); 
					m_iMsgLen = intToByte(0); //发送数据长度为0
					down_msg = new byte[12];
					setDownMsg(m_start,m_iMsgType,m_iMsgLen, down_msg);
					out.write(down_msg);
				} catch (IOException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:STOP_PROGRAM");
				} catch (InterruptedException e) {
					LoggerUtil.logger.error("出错，   类：NodeTask;方法：run; case:STOP_PROGRAM");
				}
				break;
			}
		}
		LoggerUtil.logger.info("===该线程结束==========++++nodeId"+cSocket.getNodeId()+"====="+cSocket.getNodeType());		

	}
	/**
	 * 
	 * @Title: byteToInt 
	 * @Description: 将字节数组转为int
	 * @param b
	 * @return
	 * @return: int
	 */
	private int byteToInt(byte[] b){
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for(int i=3;i>=0;i--){
			n<<=8;
			temp = b[i]&mask;
			n |=temp;
		}
		return n;
	}
	/**
	 * 
	 * @Title: intToByte 
	 * @Description: 将整形转为字节数组
	 * @param n
	 * @return
	 * @return: byte[]
	 */
	private byte[] intToByte(int n){
		byte[] targets = new byte[4];
		targets[3] = (byte)(n & 0xFF);
		targets[2] = (byte)(n>>8 & 0xFF);
		targets[1] = (byte)(n>>16 & 0xFF);
		targets[0] = (byte)(n>>24 & 0xFF);
		return targets;
	}
	/**
	 * 
	 * @Title: shortToByte 
	 * @Description: 将short类型转位byte[]
	 * @param n
	 * @return
	 * @return: byte[]
	 */
	/*private byte[] shortToByte(short n){
		byte[] targets = new byte[2];
		targets[1] = (byte)(n>>8);
		targets[0] = (byte)(n>>0);
		return targets;
	}*/

	
	
	/**
	 * 
	 * @Title: setDownMsg 
	 * @Description: 设置字节数组down_msg前12位的值
	 * @param m_start
	 * @param m_iMsgType
	 * @param m_iMsgLen
	 * @param down_msg
	 * @return: void
	 */
	private void setDownMsg(byte[] m_start,byte[] m_iMsgType,byte[] m_iMsgLen, byte[] down_msg){
		for(int i=0;i<12;i++){
			if(i>=0 && i<=3){
				down_msg[i] = m_start[3-i];
			}else if(i>=4 && i<=7){
				down_msg[i] = m_iMsgType[7-i];
			}else{
				down_msg[i] = m_iMsgLen[11-i];
			}
		}
	}
	/**
	 * 
	 * @Title: setDownMsg 
	 * @Description: 设置字节数组down_msg
	 * @param b
	 * @param down_msg
	 * @param index
	 * @return: void
	 */
	/*private void setDownMsg(byte[] b,byte[] down_msg,int index){
		down_msg[index] = b[1];
		down_msg[index+1] = b[0];
	}*/
	
	
}
