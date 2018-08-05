<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络联通度</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script type="text/javascript">
    var flag = false;
    $(function(){
    	if($("#connectivityDegreeing").val()=='true'){
			$.dialog({
        	    title: '正在进行联通度测试...',
        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
        	});
			refresh(0);
		}
    	getConnectivityDegreeList(1,20);
    });
 
    var connectivityDegreeList;
 	  //获取节点丢包率信息
 	    function getConnectivityDegreeList(page,pageSize){
 	    	 $.ajax( { 
 				    url:'getConnectivityDegreeInfo.json',// 跳转到 controller 
 				    type:'post', 
 				    cache:false, 
 				    dataType:'json', 
 				    async: false,
 				    success:function(data) { 
 				    	flag = data.testingEndFlag;
 				    	if(data.data==true || data.data == "true"){
	 				    	connectivityDegreeList = data.connectivityDegreeList;
	 				    	var html = "";
	 				    	var sumPage=0;
	 				    	var paginationHtml="";
	 				    	var next = page+1;
	 				    	var previous = page-1;
	 				    	var order=0;
	 				    	var connectivity = 0;
	 				    	for(var i=(page-1)*pageSize;i<connectivityDegreeList.length&&i<page*pageSize;i++){
	 				    		order = i+1;
	 				    		html +="<tr><td>"+order+"</td><td>"+connectivityDegreeList[i].testNum+"</td><td>"+connectivityDegreeList[i].nodeId+"</td>";
	 				    		if(connectivityDegreeList[i].recieveFlag=="false" || connectivityDegreeList[i].recieveFlag==false){
	 				    			html+="<td><span class='reda'>"+connectivityDegreeList[i].recieveFlag+"</span></td></tr>"
	 				    		}else{
	 				    			html+="<td><span class='bluea'>"+connectivityDegreeList[i].recieveFlag+"</span></td></tr>"
	 				    		}
	 				    	}
	 				    	for(var i =0;i<connectivityDegreeList.length;i++){
	 				    		if(connectivityDegreeList[i].recieveFlag=="true" || connectivityDegreeList[i].recieveFlag==true){
	 				    			connectivity = connectivity+1;
	 				    		}
	 				    	}
	 				    	if(parseInt(connectivityDegreeList.length)!=0){
	 				    		$("#connectivity_degree").text(parseInt(connectivity)/parseInt(connectivityDegreeList.length));
	 				    	}
	 				    	$("#connectivityDegreeTable").html(html);
	 				    	if(connectivityDegreeList.length%pageSize==0){
	 				    		sumPage = Math.floor(connectivityDegreeList.length/pageSize);
	 				    	}else{
	 				    		sumPage = Math.floor(connectivityDegreeList.length/pageSize)+1;
	 				    	}
	 				    	if(page==1){
	 				    		if(previous<=0){
	 				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
	 				    		}else{
	 				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getConnectivityDegreeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	 				    		}
	 				    	}else{
	 				    		paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	 				    	}
	 				    	for(var j=1;j<=sumPage;j++){
	 				    		if(j==page){
	 				    			paginationHtml+="<li class='active'><a href='#' onclick='getConnectivityDegreeList("+j+","+pageSize+")'>"+j+"</a></li>";
	 				    		}else{
	 				    			paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+j+","+pageSize+")'>"+j+"</a></li>";
	 				    		}
	 				    	}
	 				    	if(page==sumPage){
	 				    		if(next>sumPage){
	 				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
	 				    		}else{
	 				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getConnectivityDegreeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	 				    		}
	 				    	}else{
	 				    		paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	 				    	}
	 				    	$("#pagination").html(paginationHtml);
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
    		}
 	  
 	 //刷新节点丢包率信息列表
   	    var num = 0;
   	    function refresh(i){
   			if(i<60){
   				num = num+1;
   				getConnectivityDegreeList(1,20);
   				if(flag == true || flag=="true"){
   					window.location.href="connectivity_degree.wsn";
   				}else{
   					setTimeout('refresh('+num+')',1000);
   				}
   			}else{
   				window.location.href="connectivity_degree.wsn";
   			}
   		}
        
        //刷新列表
        function fresh(){
        	getConnectivityDegreeList(1,20);
        }
    
    //开始联通度测试
    function startConnectivityDegreeTest(){
    	
    	if($("#netWorkingFlag").val()=="false" || $("#netWorkingFlag").val()==false){
			 $.alert({
                     title: '提示',
                     type: 'red',
                     content: '请先组网！'
             });
			 return;
		}
    	
		$.ajax( { 
		    url:'connectivityDegree.json',// 跳转到 controller 
		    type:'post', 
		    cache:false, 
		    data:{ 
	             orderType:'connectivityDegree'
	    	}, 
		    dataType:'json', 
		    async: false,
		    success:function(data) { 
		    	window.location.href="connectivity_degree.wsn?connectivityDegree=true";
		    }, 
		    error : function() { 
		    	$.alert({
                     title: 'Oh no',
                     type: 'red',
                     content: '系统异常'
                 });    
		     } 
		});
	}
    </script>
</head>
<body>
<!-- 隐藏的变量 -->
	<input type="hidden" id="connectivityDegreeing" value=${connectivityDegreeing}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>

<div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络联通度测试</span></h2>
 		</div>
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="startConnectivityDegreeTest()">开始测试</button>
 		</div>
 	</div>
</div>

<div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络连通度:</span>&nbsp;&nbsp;&nbsp;<span id="connectivity_degree" class="bluea">0</span>
	 	<br>
	 	<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>参与测试节点ID</th>
	              <th>是否收到该节点信息</th>
	            </tr>
	        </thead>
	        <tbody id="connectivityDegreeTable">
	        </tbody>
		</table>
		<div class="pull-right">
			<nav>
			  <ul class="pagination" id="pagination">
			  </ul>
			</nav>
		</div>
 	</fieldset>
 </div>
</body>
</html>