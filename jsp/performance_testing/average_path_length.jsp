<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平均路径长度</title>
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
	    	if($("#pathLengthing").val()=='true'){
				$.dialog({
	        	    title: '正在进行平均路径长度测试...',
	        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
	        	});
				refresh(0);
			}
	    	getPathLengthList(1,20);
	    });
     
	    var pathLengthList;
	   	  //获取节点丢包率信息
	   	function getPathLengthList(page,pageSize){
	   	    	 $.ajax( { 
	   				    url:'getPathLengthInfo.json',// 跳转到 controller 
	   				    type:'post', 
	   				    cache:false, 
	   				    dataType:'json', 
	   				    async: false,
	   				    success:function(data) { 
	   				    	flag = data.testingEndFlag;
	   				    	if(data.data==true || data.data == "true"){
		   				     	pathLengthList = data. pathLengthList;
		   				     	$("#average_path_length").text(data.avePathLength);
		   				    	var html = "";
		   				    	var sumPage=0;
		   				    	var paginationHtml="";
		   				    	var next = page+1;
		   				    	var previous = page-1;
		   				    	var order=0;
		   				    	for(var i=(page-1)*pageSize;i< pathLengthList.length&&i<page*pageSize;i++){
		   				    		order = i+1;
		   				    		html +="<tr><td>"+order+"</td><td>"+ pathLengthList[i].testNum+"</td><td>"+ pathLengthList[i].nodeId+"</td><td>"+ pathLengthList[i].cycleNum+"</td><td>"+ pathLengthList[i].pathLength+"</td></tr>";
		   				    	}
		   				    		
		   				    	$("#pathLengthTable").html(html);
		   				    	if(pathLengthList.length%pageSize==0){
		   				    		sumPage = Math.floor(pathLengthList.length/pageSize);
		   				    	}else{
		   				    		sumPage = Math.floor(pathLengthList.length/pageSize)+1;
		   				    	}
		   				    	if(page==1){
		   				    		if(previous<=0){
		   				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
		   				    		}else{
		   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getPathLengthList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
		   				    		}
		   				    	}else{
		   				    		paginationHtml+="<li><a href='#' onclick='getPathLengthList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
		   				    	}
		   				    	for(var j=1;j<=sumPage;j++){
		   				    		if(j==page){
		   				    			paginationHtml+="<li class='active'><a href='#' onclick='getPathLengthList("+j+","+pageSize+")'>"+j+"</a></li>";
		   				    		}else{
		   				    			paginationHtml+="<li><a href='#' onclick='getPathLengthList("+j+","+pageSize+")'>"+j+"</a></li>";
		   				    		}
		   				    	}
		   				    	if(page==sumPage){
		   				    		if(next>sumPage){
		   				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
		   				    		}else{
		   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getPathLengthList("+next+","+pageSize+")'>"+">>"+"</a></li>";
		   				    		}
		   				    	}else{
		   				    		paginationHtml+="<li><a href='#' onclick='getPathLengthList("+next+","+pageSize+")'>"+">>"+"</a></li>";
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
	   	  
     
     	//显示隐藏的参数设置列表
    	function show(){
    		if($("#netWorkingFlag").val()=="false" || $("#netWorkingFlag").val()==false){
	   			 $.alert({
		                      title: '提示',
		                      type: 'red',
		                      content: '请先组网！'
		              });
	   			 return;
   			}
    		$('#myModal').modal();
    		$('#cycle_length').val(15);
    		$('#cycle_number').val(2);
    		$('#cycle_interval').val(2);
    	}
    	//刷新节点丢包率信息列表
   	    var num = 0;
   	    function refresh(i){
   			if(i<parseInt($("#testTime").val())+150){
   				num = num+1;
   				getPathLengthList(1,20);
   				if(flag == true || flag=="true"){
   					window.location.href="average_path_length.wsn";
   				}else{
   					setTimeout('refresh('+num+')',1000);
   				}
   			}else{
   				window.location.href="average_path_length.wsn";
   			}
   		}
        
        //刷新列表
        function fresh(){
        	getPathLengthList(1,20);
        }
     	
     	
    	//开始平均路径长度测试
    	function startPathLengthTest(){
    		
    		/**************************************************/
    		if(!($('#cycle_length').val()!='' &&  1<=$('#cycle_length').val() && 180>=$('#cycle_length').val())){
    			$('#span_cycle_length').attr('class','reda');
    			$('#span_cycle_length').text("*每个测试周期所经历的时间，以秒为单位，范围[1-180]");
    			return;
    		}else{
    			$('#span_cycle_length').attr('class','bluea');
    			$('#span_cycle_length').text("每个测试周期所经历的时间，以秒为单位，范围[1-180]");
    		}
    		/***************************************************/
    		if(!($('#cycle_number').val()!='' &&  2<=$('#cycle_number').val() && 10>=$('#cycle_number').val())){
    			$('#span_cycle_number').attr('class','reda');
    			$('#span_cycle_number').text("*测试周期的个数，范围[2-10]");
    			return;
    		}else{
    			$('#span_cycle_number').attr('class','bluea');
    			$('#span_cycle_number').text("测试周期的个数，范围[2-10]");
    		}
    		/*********************************************************/
    		if(!($('#cycle_interval').val() !='' &&  1<=$('#cycle_interval').val() && 120>=$('#cycle_interval').val())){
    			$('#span_cycle_interval').attr('class','reda');
    			$('#span_cycle_interval').text("*相邻两周期的时间，以秒为单位，范围[1-120]");
    			return;
    		}else{
    			$('#span_cycle_interval').attr('class','bluea');
    			$('#span_cycle_interval').text("相邻两周期的时间，以秒为单位，范围[1-120]");
    		}
    		
    	
    		$.ajax( { 
			    url:'averagePathLength.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             orderType:'averagePathLength',
		             cycleLength: $('#cycle_length').val(),
		             cycleNumber: $('#cycle_number').val(),
		             cycleInterval: $('#cycle_interval').val()
		    	}, 
			    dataType:'json', 
			    async: false,
			    success:function(data) { 
			    	window.location.href="average_path_length.wsn?pathLength=true";
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
	<input type="hidden" id="pathLengthing" value=${pathLengthing}>
	<input type="hidden" id="testTime" value=${testTime}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>
	<!-- 隐藏修改节点信息窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
     <h4 class="modal-title" id="myModalLabel">网络平均路径长度测试参数</h4>
    </div>
    <div class="modal-body">
    <div class="form-group">
      <label for="cycle_length">周期长度（秒）</label><span class="bluea" id="span_cycle_length">每个测试周期所经历的时间，以秒为单位，范围[1-180]</span>
      <input type="text" class="form-control" id="cycle_length" >
     </div>
     <div class="form-group">
      <label for="cycle_number">测试周期数（个）</label><span class="bluea" id="span_cycle_number">测试周期的个数，范围[2-10]</span>
      <input type="text" class="form-control" id="cycle_number" >
     </div>
     <div class="form-group">
      <label for="cycle_interval">周期间隔（秒）</label><span class="bluea" id="span_cycle_interval">相邻两周期的时间，以秒为单位，范围[1-120]</span>
      <input type="text" class="form-control" id="cycle_interval" >
     </div>
    </div>
    <div class="modal-footer">
     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     <button type="button" id="btn_submit" class="btn btn-primary"  onclick="startPathLengthTest()">测试开始</button>
    </div>
   </div>
  </div>
 </div>
 
 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络平均路径长度测试</span></h2>
 		</div>
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="show()">参数设置</button>
 		</div>
 	</div>
 </div>
 
  <div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络平均路径长度(跳):</span>&nbsp;&nbsp;&nbsp;<span id="average_path_length" class="bluea">0</span>
	 	<br>
	 	<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>普通节点ID</th>
	              <th>测试周期数</th>
	              <th>路径长度</th>
	            </tr>
	        </thead>
	        <tbody id="pathLengthTable">
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