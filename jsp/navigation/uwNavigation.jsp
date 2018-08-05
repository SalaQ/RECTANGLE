<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resource/icon/icon1.ico">
<!-- Bootstrap core CSS -->
    <link href="/resource/css/bootstrap.min.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	 $.ajax( { 
				    url:'getHistoryTestInfoList.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) {
				    	if(data.data==true || data.data=="true"){
							var testInfoList = 	data.testInfoList;			    
					    	for(var i=0;i<testInfoList.length;i++){
					    		$("#testNameList").append("<option value="+testInfoList[i].testId+">"+testInfoList[i].testName+"</option>");
					    	}
				    	}
				    },
				    error : function() { 
  				    	 $.alert({
  		                      title: 'Oh no',
  		                      type: 'red',
  		                      content: '系统异常'
  		                  });  
  				     } 
  				});
	    });
    	//显示隐藏的对话框
	    function showHiddenModel(){
	    	 $('#hiddenModal').modal(); 	
	    }
    	//查询历史记录
    	function query(){
    		if($("#testNameList").val()==null){
    			$.alert({
                      title: 'Notice',
                      type: 'red',
                      content: '无历史记录查询'
                  });  
    		}else{
    			window.location.href='getHistoryInfo.wsn?testName='+$("#testNameList").text()+"&testId="+$("#testNameList").val();// 跳转到 controller
    		}
    	}
   
    </script>

</head>
<body >

	<!-- 隐藏修改节点信息窗口 -->
	<div class="modal fade" id="hiddenModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	   <div class="modal-content">
	    <div class="modal-header">
	     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	     <h4 class="modal-title" id="myModalLabel">选择测试名称进行查询</h4>
	    </div>
	    <div class="modal-body">
	     <div class="form-group">
	      <label for="cycle_interval">测试名称：</label>
	      <select class="form-control" id="testNameList">
		  </select>
	     </div>
	    </div>
	    <div class="modal-footer">
	     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	     <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="query()">查询</button>
	   </div>
	  </div>
	 </div>
	</div>

	 <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">水声通信网络测试系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	<c:choose>
          		<c:when test="${navItem=='homePage'}">
          			 <li class="active" ><a href="uwHomePage.wsn">首页</a></li>
          	 	</c:when>
          	 	<c:when test="${navItem==null}">
          			 <li class="active" ><a href="uwHomePage.wsn">首页</a></li>
          	 	</c:when>
          		<c:otherwise>
          			  <li><a href="uwHomePage.wsn">首页</a></li>
          		</c:otherwise>
          	</c:choose>
          	<c:choose>
          		<c:when test="${navItem=='nodeInfo'}">
          			 <li class="active" ><a href="uwNodeInfo.wsn">节点管理</a></li>
          	 	</c:when>
          		<c:otherwise>
          			 <li ><a href="uwNodeInfo.wsn">节点管理</a></li>
          		</c:otherwise>
          	</c:choose>
          	<c:choose>
          		<c:when test="${navItem=='networking'}">
          			 <li class="active"><a href="uwNetworking.wsn">自主组网</a></li>
          	 	</c:when>
          		<c:otherwise>
          			 <li><a href="uwNetworking.wsn">自主组网</a></li>
          		</c:otherwise>
          	</c:choose>
          	<c:choose>
          		<c:when test="${navItem=='performance_testing'}">
          			 <li class="dropdown active">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown">性能测试<span class="caret"></span></a>
		              <ul class="dropdown-menu" role="menu">
		                <li><a href="uw_node_packet_loss.wsn">网络丢包率测试</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_average_path_length.wsn">网络平均路径长度</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_network_throughput.wsn">网络吞吐量测试</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_time_delay.wsn">网络时延测试</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_connectivity_degree.wsn">网络联通度测试</a></li>
		              </ul>
		            </li>
          	 	</c:when>
          		<c:otherwise>
          			 <li class="dropdown">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown">性能测试<span class="caret"></span></a>
		              <ul class="dropdown-menu" role="menu">
		                <li><a href="uw_node_packet_loss.wsn">网络丢包率测试</a></li>
		                <li class="divider"></li>
		                 <li><a href="uw_average_path_length.wsn">网络平均路径长度</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_network_throughput.wsn">网络吞吐量测试</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_time_delay.wsn">网络时延测试</a></li>
		                <li class="divider"></li>
		                <li><a href="uw_connectivity_degree.wsn">网络联通度测试</a></li>
		              </ul>
		            </li>
          		</c:otherwise>
          	</c:choose>
          	<c:choose>
          		<c:when test="${navItem=='historyQuery'}">
          			 <li class="active" ><a href="#" onclick="showHiddenModel()">历史查询</a></li>
          	 	</c:when>
          		<c:otherwise>
          			 <li ><a href="#" onclick="showHiddenModel()">历史查询</a></li>
          		</c:otherwise>
          	</c:choose>
         	<c:choose>
         		<c:when test="${navItem=='performanceComparison'}">
         			 <li class="active"><a href="comparisonPacketLoss.wsn">性能对比</a></li>
         	 	</c:when>
         		<c:otherwise>
         			 <li><a href="comparisonPacketLoss.wsn">性能对比</a></li>
         		</c:otherwise>
          	</c:choose>
          	<c:choose>
         		<c:when test="${navItem=='directionForUse'}">
         			 <li class="active"><a href="directionForUse.wsn">使用说明</a></li>
         	 	</c:when>
         		<c:otherwise>
         			 <li><a href="directionForUse.wsn">使用说明</a></li>
         		</c:otherwise>
          	</c:choose>
          	
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
</body>
</html>