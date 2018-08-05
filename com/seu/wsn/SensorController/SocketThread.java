package com.seu.wsn.SensorController;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.Socket;

import com.seu.wsn.Core.StaticConst.Order;
import com.seu.wsn.Core.Pojo.UwData;
import com.seu.wsn.Core.Pojo.UwNodePacketLoss;
import com.seu.wsn.Core.Pojo.ConnectivityDegree;
import com.seu.wsn.Core.Pojo.Node;
import com.seu.wsn.Core.Pojo.UwPathLength;
import com.seu.wsn.Core.Pojo.UwThroughput;
import com.seu.wsn.Core.Pojo.UwTimeDelay;
import com.seu.wsn.Service.UwDataService;
import com.seu.wsn.Service.NodeService;
import com.seu.wsn.Service.UwTestingService;
import com.seu.wsn.Service.TestingService;

public class SocketThread extends Thread {
	
	private UwDataService uwDataService;
	private NodeService nodeService;
	private UwTestingService uwTestingService;
    public Socket socket;
    private String testId;

    public SocketThread(Socket socket, UwDataService uwDataService, NodeService nodeService, String testId, UwTestingService uwTestingService) {
        this.socket = socket;
        this.uwDataService = uwDataService;
        this.nodeService = nodeService;
        this.uwTestingService = uwTestingService;
        this.testId = testId;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();  
            byte[] recData = null;  
            while (true) {  
                recData = new byte[20];  
                int r = in.read(recData);  
                if (r > -1) {  
                    String data = new String(recData);  
                    System.out.println("time:" + Order.now + "读取到客户端" + 
                    		socket.getInetAddress().toString().substring(1)+ "发送的来数据：" + data);
                    if (data.substring(0, 1).equals("A")) {
                    	UwData uwData = new UwData();
                    	uwData.setSourceId(data.substring(1, 3));
                    	if (data.substring(4, 6).equals("xx")) {
                    		uwData.setTargetId("99");
                    	} else {
                    		uwData.setTargetId(data.substring(4, 6));
                    	}
	                    if (data.substring(3, 4).equals("C")) {
	                    	uwData.setNodeType("commonNode");
	                    } else if (data.substring(3, 4).equals("G")) {
	                    	uwData.setNodeType("gatewayNode");
	                    } else if (data.substring(3, 4).equals("B")) {
	                    	uwData.setNodeType("buoyNode");
	                    }
	                    uwData.setDataType(data.substring(7, 8));
	                    uwData.setSendOrReceive(data.substring(8, 9));
	                    uwData.setTime(Double.toString(Order.now));
	                    uwData.setPoint(new double[] {});
	                    uwData.setNote("");
	                    
	                    uwDataService.insert(uwData);
//	                    String str = uwData.getDataType();
//	                    if (str.equals("R") || str.equals("C") || str.equals("D") || str.equals("A")) {
//	                    	if (data.substring(8, 9).equals("S")) {
//	                    		uwData.setSendOrReceive("R");
//	                    		String temp = uwData.getSourceId();
//	                    		uwData.setSourceId(uwData.getTargetId());
//	                    		uwData.setTargetId(temp);
//	                    		uwData.setTime(Double.toString(Order.now + 3));
//	                    		uwData.setDataType("T");
//	                    		uwDataService.insert(uwData);
//	                    	}
//	                    	else if (data.substring(8, 9).equals("R")) {
//	                    		uwData.setDataType("T");
//	                    		uwDataService.deleteByTime(uwData);
//	                    		uwData.setTime(Double.toString(Order.now - 0.1));
//	                    		uwDataService.deleteByTime(uwData);
//	                    		uwData.setTime(Double.toString(Order.now - 0.2));
//	                    		uwDataService.deleteByTime(uwData);
//	                    		uwData.setTime(Double.toString(Order.now + 0.1));
//	                    		uwDataService.deleteByTime(uwData);
//	                    		uwData.setTime(Double.toString(Order.now + 0.2));
//	                    		uwDataService.deleteByTime(uwData);
//	                    	}
//	                    }
                    }
                    if (data.substring(0, 1).equals("B")) {
                    	Node node = nodeService.selectByIp(socket.getInetAddress().toString().substring(1), testId);
                    	if (node == null)
                    	{
                    		System.out.println("null error");
                    	} else {
                        	node.setNodeId(data.substring(1, 3));
    	                    if (data.substring(3, 4).equals("C")) {
    	                    	node.setNodeType("commonNode");
    	                    } else if (data.substring(3, 4).equals("G")) {
    	                    	node.setNodeType("gatewayNode");
    	                    } else if (data.substring(3, 4).equals("B")) {
    	                    	node.setNodeType("buoyNode");
    	                    }
    	                    if (data.substring(4, 6).equals("xx")) {
    	                    	
    	                    } else {
    	                    	node.setParentId(data.substring(4, 6));
    	                    }
    	                    
    	                    nodeService.update(node);
                    	}
                    }
                    if (recData[0] == (byte)(254)) {
                    	if ((int)recData[1] == 3) {
                    		int sendPacket = data.charAt(7) - '0';
                    		Double dsendPacket = new Double((double)sendPacket);
                    		int successPacket = data.charAt(8) - '0';
                    		Double dsuccessPacket = new Double((double)successPacket);
                    		int receivePacket = data.charAt(9) - '0';
                    		Double d = 1 - dsuccessPacket / dsendPacket;
                    		BigDecimal b = new   BigDecimal(d);  
                    		double d1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
    	            		UwNodePacketLoss loss = new UwNodePacketLoss();
    	            		loss.setNodeId(data.substring(2, 4));
    	            		loss.setNodePacketLoss(d1);
    	                    if (data.substring(4, 5).equals("C")) {
    	                    	loss.setNodeType("commonNode");
    	                    } else if (data.substring(4, 5).equals("G")) {
    	                    	loss.setNodeType("gatewayNode");
    	                    } else if (data.substring(4, 5).equals("B")) {
    	                    	loss.setNodeType("buoyNode");
    	                    }
    	            		loss.setPacketInterval(3);
    	            		loss.setParentId(data.substring(5, 7));
    	            		loss.setreceivePacket(receivePacket);
    	            		loss.setSendPacket(sendPacket);
    	            		loss.setSuccessPacket(successPacket);
    	            		loss.setTestId(testId);
    	            		loss.setTestNum(Order.uwPacketLossTestNum);
    	            		uwTestingService.insertPacketLoss(loss);
                    	}
                    	else if ((int)recData[1] == 6) {
                    		UwPathLength path = new UwPathLength();
                    		path.setNodeId(data.substring(2, 4));
    	                    if (data.substring(4, 5).equals("C")) {
    	                    	path.setNodeType("commonNode");
    	                    } else if (data.substring(4, 5).equals("G")) {
    	                    	path.setNodeType("gatewayNode");
    	                    } else if (data.substring(4, 5).equals("B")) {
    	                    	path.setNodeType("buoyNode");
    	                    }
    	                    path.setPathLength(data.charAt(5) - '0');
    	                    path.setTestId(testId);
    	                    path.setTestNum(Order.uwPathLengthTestNum);
    	                    uwTestingService.insertUwPathLength(path);
                    	}
                    	else if ((int)recData[1] == 4) {
                    		UwTimeDelay delay = new UwTimeDelay();
                    		delay.setNodeId(data.substring(2, 4));
    	                    if (data.substring(4, 5).equals("C")) {
    	                    	delay.setNodeType("commonNode");
    	                    } else if (data.substring(4, 5).equals("G")) {
    	                    	delay.setNodeType("gatewayNode");
    	                    } else if (data.substring(4, 5).equals("B")) {
    	                    	delay.setNodeType("buoyNode");
    	                    }
    	                    delay.setTestId(testId);
    	                    delay.setTestNum(Order.uwTimeDelayTestNum);
    	                    double temp = (Order.now - Order.delayNow)/Order.uwPacketNumber;
    	                    delay.setTimeDelay((double)Math.round(temp*10)/10);
    	                    uwTestingService.insertTimeDelay(delay);
                    	}
                    	else if ((int)recData[1] == 5) {
                    		int sendPacket = data.charAt(7) - '0';
                    		Double dsendPacket = new Double((double)sendPacket);
                    		int successPacket = data.charAt(8) - '0';
                    		Double dsuccessPacket = new Double((double)successPacket);
                    		int receivePacket = data.charAt(9) - '0';
                    		Double d = 1 - dsuccessPacket / dsendPacket;
                    		BigDecimal b = new   BigDecimal(d);  
                    		double d1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
                    		UwThroughput th = new UwThroughput();
                    		th.setNodeId(data.substring(2, 4));
    	                    if (data.substring(4, 5).equals("C")) {
    	                    	th.setNodeType("commonNode");
    	                    } else if (data.substring(4, 5).equals("G")) {
    	                    	th.setNodeType("gatewayNode");
    	                    } else if (data.substring(4, 5).equals("B")) {
    	                    	th.setNodeType("buoyNode");
    	                    }
    	                    th.setParentId(data.substring(5, 7));
    	                    th.setReceivePacket(receivePacket);
    	                    th.setSendPacket(sendPacket);
    	                    th.setSuccessPacket(successPacket);
    	                    th.setTestId(testId);
    	                    th.setTestNum(Order.uwThroughputTestNum);
    	                    double temp = 48 / ((Order.now - Order.delayNow)/Order.uwPacketNumber);
    	                    th.setThroughput((double)Math.round(temp*10)/10);
    	                    uwTestingService.insertThroughput(th);
                    	}
                    	else if ((int)recData[1] == 7) {
                    		ConnectivityDegree c = new ConnectivityDegree();
                    		c.setNodeId(data.substring(2, 4));
    	                    c.setTestId(testId);
    	                    c.setTestNum(Order.uwConnectivityDegreeTestNum);
    	                    c.setRecieveFlag(true);
    	                    uwTestingService.insertConnectivityDegree(c);
                    	}
                    }
                }
                else {
                    System.out.println("数据读取完毕！");
                    socket.close();
                    break;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
        	try {
                if (socket != null) {
                    socket.close();
                }
            }
        	catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}