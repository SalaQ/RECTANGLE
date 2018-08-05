package com.seu.wsn.Core.StaticConst;
/**
 * 
 * @ClassName: WebConst 
 * @Description: 系统相关的常量
 * @author: CSS
 * @date: 2016-11-18 下午7:26:48
 */
public class WebConst {
	
	/***************************************JSP页面路径信息*******************************************/
	public static final String UWNODE_INFO              		    =          "//WEB-INF//jsp//nodeManage//uwNodeInfo.jsp";
	public static final String UWINTRODUCTION              		    =          "//WEB-INF//jsp//homePage//uwIntroduction.jsp";
	public static final String UWNETWORKING              		    =          "//WEB-INF//jsp//networking//uwNetworking.jsp";
	public static final String CHOOSE		              		    =          "//WEB-INF//jsp//homePage//choose.jsp";
	public static final String CHART                 				=          "//WEB-INF//jsp//chart//chart.jsp";
	public static final String TOPOLOGY								=		   "//WEB-INF//jsp//chart//topology.jsp";
	/*
	 * 管理员登录页面 
	 */
	public static final String ADMIN_LOGIN              			=          "//WEB-INF//jsp/homePage//adminLogin.jsp";
	/*
	 * 系统简介页面
	 */
	public static final String INTRODUCTION              		    =          "//WEB-INF//jsp/homePage//introduction.jsp";
	/*
	 * 用户登录页面
	 */
	public static final String USER_LOGIN               			=          "//WEB-INF//jsp/homePage//userLogin.jsp";
	/*
	 * 用户注册页面
	 */
	public static final String USER_REGISTER            			=          "//WEB-INF//jsp/homePage//userRegister.jsp";
	/*
	 * 首页
	 */
	public static final String INDEX                     			=          "//index.jsp";
	/*
	 * 节点管理页面
	 */
	public static final String NODE_INFO                 			=          "//WEB-INF//jsp//nodeManage//nodeInfo.jsp";
	/*
	 * 系统使用说明
	 */
	public static final String DIRECTION                 			=          "//WEB-INF//jsp//directionForUse//direction.jsp";
	/*
	 * 协议烧录及组网页面
	 */
	public static final String NETWORKING                 			=         "//WEB-INF//jsp//networking//networking.jsp";
	/*
	 * 节点丢包率页面
	 */
	public static final String NODE_PACKET_LOSS          			=          "//WEB-INF//jsp//performance_testing//node_packet_loss.jsp";
	public static final String UW_NODE_PACKET_LOSS          		=          "//WEB-INF//jsp//performance_testing//uw_node_packet_loss.jsp";
	/*
	 * 系统丢包率页面
	 */
	public static final String SYSTEM_PACKET_LOSS         			=			"//WEB-INF//jsp//performance_testing//system_packet_loss.jsp";
	/*
	 * 网络时延测试页面
	 */
	public static final String TIME_DELAY				   			=         "//WEB-INF//jsp//performance_testing//time_delay.jsp";
	public static final String UW_TIME_DELAY				   		=         "//WEB-INF//jsp//performance_testing//uw_time_delay.jsp";
	/*
	 * 网络吞吐量测试页面
	 */
	public static final String NETWORKING_THROUGHPUT       			= 		  "//WEB-INF//jsp//performance_testing//network_throughput.jsp";
	public static final String UW_NETWORKING_THROUGHPUT       		= 		  "//WEB-INF//jsp//performance_testing//uw_network_throughput.jsp";
	/*
	 * 网络平均路径长度页面
	 */
	public static final String AVERAGE_PATH_LENGTH         			=          "//WEB-INF//jsp//performance_testing//average_path_length.jsp";
	public static final String UW_AVERAGE_PATH_LENGTH         		=          "//WEB-INF//jsp//performance_testing//uw_average_path_length.jsp";
	/*
	 * 网络连通度页面
	 */
	public static final String CONNECTIVITY_DEGREE		   			=           "//WEB-INF//jsp//performance_testing//connectivity_degree.jsp";
	public static final String UW_CONNECTIVITY_DEGREE		   		=           "//WEB-INF//jsp//performance_testing//uw_connectivity_degree.jsp";
	/*
	 * 历史查询丢包率页面
	 */
	public static final String HISTORY_PACKET_LOSS                  =	      "//WEB-INF//jsp//historyQuery//history_packet_loss.jsp";
	/*
	 * 历史查询网络时延测试页面
	 */
	public static final String HISTORY_TIME_DELAY				    =         "//WEB-INF//jsp//historyQuery//history_time_delay.jsp";
	/*
	 * 历史查询网络吞吐量测试页面
	 */
	public static final String HISTORY_NETWORKING_THROUGHPUT        = 		  "//WEB-INF//jsp//historyQuery//history_network_throughput.jsp";
	/*
	 * 历史查询网络平均路径长度页面
	 */
	public static final String HISTORY_AVERAGE_PATH_LENGTH          =          "//WEB-INF//jsp//historyQuery//history_average_path_length.jsp";
	/*
	 * 历史查询网络连通度页面
	 */
	public static final String HISTORY_CONNECTIVITY_DEGREE		    =           "//WEB-INF//jsp//historyQuery//history_connectivity_degree.jsp";
	/*
	 * 历史查询网络拓扑页面
	 */
	public static final String HISTORY_NETWORKING                   =           "//WEB-INF//jsp//historyQuery//history_networking.jsp";
	/*
	 * 丢包率性能对比
	 */
	public static final String COMPARISON_PACKET_LOSS               =           "//WEB-INF//jsp//performanceComparison//packetLossComparison.jsp";
	/*
	 * 时延测试结果性能对比
	 */
	public static final String COMPARISON_TIME_DELAY                =           "//WEB-INF//jsp//performanceComparison//timeDelayComparison.jsp";
	
	
	
	
	/*********************************************系统相关常量信息*********************************************************/
	/*
	 * 错误信息
	 */
	public static final String ERROR_MSG                 			=           "errorMsg";
	/*
	 * 返回信息
	 */
	public static final String MESSAGE                   			=            "msg";
	/*
	 * 用户名
	 */
	public static final String USERNAME                  			=            "userName";
	/*
	 * 
	 */
	public static final String TESTNAME                             =             "testName";
	/*
	 * 测试名称
	 */
	public static final String TESTID                    			=            "testId";
	/*
	 * 导航项
	 */
	public static final String NAV_ITEM                 			=            "navItem";
	/*
	 * 节点信息列表
	 */
	public static final String NODE_LIST                 			=            "nodeList";
	public static final String UWNODE_LIST                 			=            "uwNodeList";
	public static final String UWDATA_LIST                 			=            "uwDataList";
	/*
	 * 拓扑簇头与网关节点信息列表
	 */
	public static final String NODES                     			=            "nodes";
	/*
	 * 拓扑普通节点信息列表
	 */
	public static final String CHILD_NODES                			=            "childNodes";
	/*
	 * 拓扑簇头与网关节点连线信息列表
	 */
	public static final String LINKS                      			=            "links";
	/*
	 * 拓扑普通节点连线信息列表
	 */
	public static final String CHILD_LINKS                			=            "childLinks";
	/*
	 * 启动连接
	 */
	public static final String START_CONN				   			=           "startConn";
	/*
	 * 成功
	 */
	public static final String SUCCESS				        		=           "success";
	/*
	 * 失败
	 */
	public static final String FAIL				            		=           "fail";
	/*
	 * 返回的结果
	 */
	public static final String RESULT				        		=           "result";
	/*
	 * socket连接端口号
	 */
	public static final int PORT									=           5000;
	/*
	 * 判断是否下载协议
	 */
	public static final String BURNER_FINISHED			    		=           "burnerFinished";
	/*
	 * 协议分段下载每段大小
	 */
	public static final int LENGTH									=           1024;
	/*
	 * 已下载协议的节点类型
	 */
	public static final String DOWNLOAD_NODE_TYPE           		=           "downloadNodeType";
	/*
	 * 组网是否完成标志
	 */
	public static final String NETWORKINGFLAG                		=          "netWorkingFlag";
	/*
	 * 正在进行丢包率测试标志
	 */
	public static final String PACKETLOSSING                		=           "packetLossing";   
	/*
	 * 正在进行时延测试
	 */
	public static final String TIMEDELAYING                         =           "timeDelaying";
	/*
	 * 正在进行吞吐量测试
	 */
	public static final String THROUGHPUTING                        =            "throughputing";
	/*
	 * 正在进行平均路径长度测试
	 */
	public static final String PATHLENGTHTING                       =            "pathLengthing";
	/*
	 * 正在进行联通度测试
	 */
	public static final String CONNECTIVITYDEGREEING                =            "connectivityDegreeing";
	/*
	 * 一次性能测试所用的时间
	 */
	public static final  String TEST_TIME                   		=          "testTime";
	/*
	 * 系统丢包率列表
	 */
	public static final String SYS_PACKET_LOSS_LIST         		 =          "sysPacketLossList";
	
	
	
	
	/***********************************************节点类型***************************************************************/
	/*
	 * 节点类型
	 */
	public static final String NODE_TYPE                  			=           "nodeType";
	/*
	 * 普通节点
	 */
	public static final String COMMON_NODE               			=           "commonNode";
	/*
	 * 簇头节点
	 */
	public static final String CLUSTER_HEAD_NODE          			=           "clusterHeadNode";
	/*
	 * 网关节点
	 */
	public static final String GATEWAY_NODE               			=           "gatewayNode";
	/*
	 * 攻击节点
	 */
	public static final String ATTACK_NODE                          =            "attackNode";
	public static final String BUOY_NODE                         	=            "buoyNode";
	
	
	/************************************************协议文件所在路径***********************************************************/
	/*
	 * 普通节点烧录的协议所在路径
	 */
	public static final String COMMON_FILE                 			=            "/protocolFile/common";
	
	/*
	 * 簇头节点烧录的协议所在路径
	 */
	public static final String CLUSTER_HEAD_FILE            		=           "/protocolFile/clusterHead";
	/*
	 * 攻击节点烧录的协议所在路径
	 */
	public static final String ATTACK_FILE            		         =           "/protocolFile/attack";
	public static final String BUOY_FILE            		         =           "/protocolFile/buoy";
	
	/*
	 * 普通节点烧录的协议所在路径
	 */
	public static final String GATEWAY_FILE                 		=            "/protocolFile/gateway";
	
	/**************************************************与下层开发板通信msgType*******************************************************/
	/*
	 * 协议下载
	 */
	public static final int DOWNLOAD_MSG_TYPE               		=                 0;
	/*
	 * 协议烧录
	 */
	public static final int BURNER_MSG_TYPE                 		=                 2;
	/*
	 * 节点软复位
	 */
	public static final int NODE_RESET_MSG_TYPE              		=                 17;
	/*
	 * 网络拓扑信息
	 */
	public static final int NETWORKING_INFORMATION_MSG_TYPE  		=                 5;
	/*
	 * 丢包率测试
	 */
	public static final int PACKET_LOSS_TEST						=                  7;
	/*
	 * 时延测试
	 */
	public static final int TIME_DELAY_TEST                         =              		9;
	/*
	 * 吞吐量测试
	 */
	public static final int THROUGHPUT_TEST                           =                10;
	/*
	 * 平均路径长度测试
	 */
	public static final int AVE_PATH_LENGTH                        	=                  11;
	/*
	 * 连通度测试
	 */
	public static final int CONNECTIVITY_DEGREE_TEST               =                   12;
	/*
	 * 开始新一轮的性能测试
	 */
	public static final int START_NEW_CYCLE                			=                  8;
	/*
	 * 一轮测试结束
	 */
	public static final int CYCLE_END                               =                 18;
	
	/*
	 * 停止下层程序1
	 */
	public static final int STOP_PROGRAM1                               =               49;
	/*
	 * 停止下层程序2
	 */
	public static final int STOP_PROGRAM2                               =               51;
	
}
