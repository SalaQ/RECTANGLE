<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>自主组网</title>
	<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
     <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/tuopu.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script type="text/javascript">
   		var nodeListByType;
   		var nodeList;
		$(function(){
			
			if(window.location.pathname=="/WSN/fileUpload.wsn"){
				showDownloadDialog();
			};
			getNodeListByType(1,10);
			getNodeList(1,15);
			for(var j=0;j<nodeList.length;j++){
				if(nodeList[j].download==true){
					$("#burnProtocol").attr("disabled",false);
				}
				if(nodeList[j].burner==true){
					$("#nodeReset").attr("disabled",false);
			    	$("#topological").attr("disabled",false);
				}
			}
			if($("#burnerFinished").val()=='true'){
				$.dialog({
	        	    title: '正在组网...',
	        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
	        	});
				waitForNetworkingFinished();
			}
			
		});    
    
   		//获取组网信息
	    function getNodeList(page,pageSize){
	    	 $.ajax( { 
				    url:'getNodeList.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) { 
				    	//curPage = page;
				    	//curPageSize = pageSize;
				    	 nodeList = data.nodeList
				    	var html = "";
				    	var sumPage=0;
				    	var paginationHtml="";
				    	var next = page+1;
				    	var previous = page-1;
				    	var order=0;
				    	for(var i=(page-1)*pageSize;i<nodeList.length&&i<page*pageSize;i++){
				    		order = i+1;
				    		html +="<tr><td>"+order+"</td><td>"+nodeList[i].nodeId+"</td><td>"+nodeList[i].parentId+"</td>";
				    		//根据不同的节点类型显示不同的颜色
				    		if(nodeList[i].nodeType=="gatewayNode"){
				    			html+= "<td><span class='reda'>"+nodeList[i].nodeType+"</span></td>";
				    		}else if(nodeList[i].nodeType=="clusterHeadNode"){
				    			html+= "<td><span class='bluea'>"+nodeList[i].nodeType+"</span></td>";
				    		}else if(nodeList[i].nodeType=="attackNode"){
				    			html+= "<td><span class='reda'>"+nodeList[i].nodeType+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodeList[i].nodeType+"</td>";
				    		}
				    	}
				    	$("#nodeList").html(html);
				    	if(nodeList.length%pageSize==0){
				    		sumPage = Math.floor(nodeList.length/pageSize);
				    	}else{
				    		sumPage = Math.floor(nodeList.length/pageSize)+1;
				    	}
				    	if(page==1){
				    		if(previous<=0){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    	}
				    	for(var j=1;j<=sumPage;j++){
				    		if(j==page){
				    			paginationHtml+="<li class='active'><a href='#' onclick='getNodeList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}else{
				    			paginationHtml+="<li><a href='#' onclick='getNodeList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}
				    	}
				    	if(page==sumPage){
				    		if(next>sumPage){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    	}
				    	$("#pagination_net").html(paginationHtml);
				    	
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
  
	    //根据类型获取列表
	    function getNodeListByType(page,pageSize){
	    	$.ajax( { 
			    url:'getNodeListByType.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{
			    	nodeType:$("#nodeType").val()
			    },
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	//$("#downloadList").html("");
			    	nodeListByType = data.nodeList;
			    	var html = "";
			    	var sumPage=0;
			    	var paginationHtml="";
			    	var next = page+1;
			    	var previous = page-1;
			    	var order=0;
			    	for(var i=(page-1)*pageSize;i<nodeListByType.length&&i<page*pageSize;i++){
			    		order = i+1;
			    		html +="<tr><td>"+order+"</td><td>"+nodeListByType[i].nodeId+"</td>";
			    		//根据是否下载协议显示不同颜色
			    		if(nodeListByType[i].download==false){
			    			html+= "<td><span class='reda'>"+nodeListByType[i].download+"</span></td>";
			    		}else{
			    			html+= "<td>"+nodeListByType[i].download+"</td>";
			    		}
			    		
			    		//根据是否烧录协议显示不同颜色
			    		if(nodeListByType[i].burner==false){
			    			html+= "<td><span class='reda'>"+nodeListByType[i].burner+"</span></td>";
			    		}else{
			    			html+= "<td>"+nodeListByType[i].burner+"</td>";
			    		}
			    		
			    	}
			    	$("#downloadList").html(html);
			    	if(nodeListByType.length%pageSize==0){
			    		sumPage = Math.floor(nodeListByType.length/pageSize);
			    	}else{
			    		sumPage = Math.floor(nodeListByType.length/pageSize)+1;
			    	}
			    	if(page==1){
			    		if(previous<=0){
			    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
			    		}else{
			    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeListByType("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
			    		}
			    	}else{
			    		paginationHtml+="<li><a href='#' onclick='getNodeListByType("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
			    	}
			    	for(var j=1;j<=sumPage;j++){
			    		if(j==page){
			    			paginationHtml+="<li class='active'><a href='#' onclick='getNodeListByType("+j+","+pageSize+")'>"+j+"</a></li>";
			    		}else{
			    			paginationHtml+="<li><a href='#' onclick='getNodeListByType("+j+","+pageSize+")'>"+j+"</a></li>";
			    		}
			    	}
			    	if(page==sumPage){
			    		if(next>sumPage){
			    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
			    		}else{
			    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeListByType("+next+","+pageSize+")'>"+">>"+"</a></li>";
			    		}
			    	}else{
			    		paginationHtml+="<li><a href='#' onclick='getNodeListByType("+next+","+pageSize+")'>"+">>"+"</a></li>";
			    	}
			    	$("#pagination").html(paginationHtml);
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
	    //刷新列表一
	    function refreshList1(){
	    	getNodeListByType(1,10);
	    }
	    //刷新列表二
	    function refreshList2(){
	    	getNodeList(1,15);
	    }
	    //点击协议下载按钮
	   function sub(){
		   if($("#InputFile").val()==""){
			   $("#warning").text("请选择协议文件！");
			   return;
		   }
		   var down=false;
			for(var j=0;j<nodeListByType.length;j++){
				if(nodeListByType[j].download==true){
					down = true;
					break;
				}
			}
			if(down==true){
				$.confirm({
				    title: '注意!',
				    content: '节点已下载协议，是否重新下载',
				    animation: 'zoom',
		            animationClose: 'top',
				    buttons: {
				        	确定: function () {
				        	$.ajax( { 
							    url:'reDownloadProtocol.json',// 跳转到 controller 
							    type:'post', 
							    cache:false, 
							    data:{
							    	nodeType:$("#nodeType").val()
							    },
							    dataType:'json', 
							    async: false,
							    success:function(data) {
							    	var type = $("#nodeType").val();
							    	$("#input_node_type").val(type);
							    	$("#selectProtocol").submit();
							    }, 
							    error : function() { 
							    	 $.alert({
					                      title: 'Oh no',
					                      type: 'red',
					                      content: '系统异常'
					                  });  
							     }
				    	 	});	
				        },
				                        取消: function () {
				            return;
				        }
				    }
				});
			}else{
				var type = $("#nodeType").val();
		    	$("#input_node_type").val(type);
		    	$("#selectProtocol").submit();
			}
		   
	    	
	    }
	   //打开协议下载提示窗口
	   var num=0;
	   function showDownloadDialog(){
           $.dialog({
        	    title: "正在下载协议...",
        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
        	});
		   refresh(num);
	   }
	   //定时刷新列表
	   function refresh(i){
			if(i<50){
				getNodeListByType(1,10);
				num = num +1;
				var down=true;
				for(var j=0;j<nodeListByType.length;j++){
					if(nodeListByType[j].download==false){
						down = false;
						break;
					}
				}
				if(down==false){
					setTimeout('refresh('+num+')',2000);
				}else{
					num = 0;
					window.location.href="networking.wsn?downloadNodeType="+$("#nodeType").val();
				}
				
			}else{
				num = 0;
				window.location.href="networking.wsn?downloadNodeType="+$("#nodeType").val();
			}
		   
		} 
	    //刷新烧录信息列表
		function refresh_burner(i){
			if(i<60){
				getNodeListByType(1,10);
				num = num +1;
				var down=true;
				for(var j=0;j<nodeList.length;j++){
					if(nodeList[j].burner==false){
						down = false;
						break;
					}
				}
				if(down==false){
					setTimeout('refresh_burner('+num+')',2000);
				}else{
					num = 0;
					window.location.href="networking.wsn?burnerFinished=true";
				}
				
			}else{
				num = 0;
				window.location.href="networking.wsn?burnerFinished=true";
				
			}		
		}
	    //让网关节点对应的socket接受组网结束标志
	    function waitForNetworkingFinished(){
	    	$.ajax( { 
			    url:'waitForNetworkingFinished.json',// 跳转到 controller 
			    type:'post', 
			    data:{
			    	orderType:'waitingForNetworkingFinished'
			    },
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	num = 0;
			    	refresh_net(num);
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
	    
	    
	    
		//刷新组网情况信息列表
		var flag = false;
		function refresh_net(i){
			if(i<140){
				num = num+1;
				getNetWorkingFlag();
				if(flag == true || flag=="true"){
					$.ajax( { 
					    url:'getNodeParentId.json',// 跳转到 controller 
					    type:'post', 
					    data:{
					    	orderType:'networkingInformation'
					    },
					    cache:false, 
					    dataType:'json', 
					    async: false,
					    success:function(data) {
					    	setTimeout('fresh()',2000);
					    }, 
					    error : function(){ 
					    	 $.alert({
			                      title: 'Oh no',
			                      type: 'red',
			                      content: '系统异常'
			                  });  
					     }
		    	 	});	
					num = 0;
				}else{
					setTimeout('refresh_net('+num+')',1000);
				}
			}else{
		    	 $.alert({
	                     title: 'Oh no',
	                     type: 'red',
	                     content: '组网失败！'
	             });  
				num = 0;
			}
		}
	    //刷新页面
	    function fresh(){
	    	window.location.href="networking.wsn";
	    }
		
	    //获取组网是否完成标志
	    function getNetWorkingFlag(){
	    	$.ajax( { 
			    url:'getNetWorkingFlag.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	//alert(data.netWorkingFlag);
					flag = data.netWorkingFlag;
			    }, 
			    error : function(){ 
			    	 $.alert({
	                      title: 'Oh no',
	                      type: 'red',
	                      content: '系统异常'
	                  });  
			     }
    	 	});	
	    }
	    
	   //协议烧录
	   function burn_protocol(){
		   $.ajax( { 
			    url:'burnProtocol.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{
			    	orderType:'burnProtocol'
			    },
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	$.dialog({
		        	    title: '正在烧录协议...',
		        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
		        	});
			    	num=0;
			    	getNodeList(1,15);  //此处调用该函数是为了获取最新的nodeList
			    	refresh_burner(num);
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
	   //节点复位
	  function node_reset(){
		  $.ajax( { 
			    url:'nodeReset.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{
			    	orderType:'nodeReset'
			    },
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	$("#nodeReset").attr("disabled",true);
			    	$("#topological").attr("disabled",true);
			    	//$("#burnProtocol").attr("disabled",true);
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
	<input type="hidden" id="burnerFinished" value=${burnerFinished}>
	<input type="hidden" id="downloadNodeType" value=${downloadNodeType}>


	  <!-- Main jumbotron for a primary marketing message or call to action -->
	
    <div class="jumbotron">
      <div id='networking'>
      <div class="container">
      	<div class="row">
        	<div class="col-md-6">
        		<h3>组网准备</h3>
				<form role="form" id="selectProtocol" action="fileUpload.wsn" method="post" enctype="multipart/form-data">
				  <div class="form-group">
				       <label for="nodeType">节点类型</label>
				       
				       <c:choose>
			          		<c:when test="${nodeType=='commonNode'}">
			          			<select class="form-control" id="nodeType" onChange="getNodeListByType(1,10)">
								    <option >commonNode</option>
								    <option>clusterHeadNode</option>
								    <option>gatewayNode</option>
								    <option>attackNode</option>
							    </select>
			          	 	</c:when>
			          	 	<c:when test="${nodeType==null}">
				          	 	<select class="form-control" id="nodeType" onChange="getNodeListByType(1,10)">
									  <option >commonNode</option>
									  <option>clusterHeadNode</option>
									  <option>gatewayNode</option>
									  <option>attackNode</option>
								</select>
			          	 	</c:when>
			          	 	<c:when test="${nodeType=='clusterHeadNode'}">
				          	 	<select class="form-control" id="nodeType" onChange="getNodeListByType(1,10)">
									  <option >commonNode</option>
									  <option selected>clusterHeadNode</option>
									  <option>gatewayNode</option>
									  <option>attackNode</option>
								</select>
			          	 	</c:when>
			          	 	<c:when test="${nodeType=='gatewayNode'}">
				          	 	<select class="form-control" id="nodeType" onChange="getNodeListByType(1,10)">
									  <option >commonNode</option>
									  <option >clusterHeadNode</option>
									  <option selected>gatewayNode</option>
									  <option>attackNode</option>
								</select>
			          	 	</c:when>
			          		<c:otherwise>
			          			 <select class="form-control" id="nodeType" onChange="getNodeListByType(1,10)">
									  <option >commonNode</option>
									  <option >clusterHeadNode</option>
									  <option >gatewayNode</option>
									  <option selected>attackNode</option>
								</select>
			          		</c:otherwise>
			          	</c:choose>
				  </div>
				   <div class="form-group">
				     <input type="hidden" name="nodeType" id="input_node_type">
				  </div>
				 
				  <div class="form-group">
				    <label for="InputFile">选择协议文件</label>
				    <input type="file" id="InputFile" name = "file"><span style="color:red;" id="warning"></span>
				  </div>
				  <button type="button" class="btn btn-primary" onclick="sub()">协议下载</button>
				  <button type="button" class="btn btn-warning" disabled="disabled" id="burnProtocol" onclick="burn_protocol()">协议烧录</button>
			      <button type="button" class="btn btn-danger" disabled="disabled" id="nodeReset" onclick="node_reset()">节点复位</button>
			      <a type="button" class="btn btn-success" href="#container" onclick="show()" disabled="disabled" id="topological">拓扑图</a>
				</form>
				<br><br>
				<div class="pull-right">
          			<button type="button" class="btn btn-success btn-sm" onclick="refreshList1()">刷新</button>
          		</div>
				<table class="table table-hover table-striped">
		        	<thead>
		              <tr>
		                <th>序号</th>
		                <th>节点id</th>
		                <th>是否下载协议</th>
		                <th>是否烧录协议</th>
		              </tr>
		            </thead>
		            <tbody id="downloadList">
		            </tbody>
				</table>
			 	<div class="pull-right">
					<nav>
					  <ul class="pagination" id="pagination">
					  </ul>
					</nav>
				</div>
				       		
        	</div>
        	<div class="col-md-6">
          		<h3>网络拓扑信息</h3>
          		<div class="pull-right">
          			<button type="button" class="btn btn-success btn-sm" onclick="refreshList2()">刷新</button>
          		</div>
          		<table class="table table-bordered table-hover table-striped">
		        	<thead>
		              <tr>
		                <th>序号</th>
		                <th>节点id</th>
		                <th>父节点id</th>
		                <th>节点类型</th>
		              </tr>
		            </thead>
		            <tbody id="nodeList">
		            </tbody>
				</table>
		 		<div class="pull-right">
					<nav>
					  <ul class="pagination" id="pagination_net">
					  </ul>
					</nav>
				</div>
       	 </div>
      </div>
     </div>
    </div>
    </div>
    
    
    
    
    <div  align="center">
        <h2>拓扑图展示</h2>
    </div> 
    
    <div id='container'>
    </div>
    <div  align="center">
    <span>网关节点：</span><img src="resource/image/smile1.jpg" alt="Responsive image" class="img-circle" style="width:30px;height:30px;">&nbsp;&nbsp;
    <span>簇头节点：</span><img src="resource/image/smile2.jpg" alt="Responsive image" class="img-circle" style="width:30px;height:30px;">&nbsp;&nbsp;
    <span>普通节点：</span><img src="resource/image/smile3.jpg" alt="Responsive image" class="img-circle" style="width:30px;height:30px;">
    </div>
    <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
      </footer>
	</div>
    <script type="text/javascript">
 
    function show(){
    	$("#container").html("");
    	function Topology(ele){
    		typeof(ele)=='string' && (ele=document.getElementById(ele));
    		var w=ele.clientWidth, h=ele.clientHeight, self=this;
    		this.force = d3.layout.force().gravity(.05).distance(200).charge(-800).size([w, h]);
    		this.nodes=this.force.nodes();
    		this.links=this.force.links();
    		this.clickFn=function(){};
    		this.vis = d3.select(ele).append("svg:svg")
    		  .attr("width", w).attr("height", h).attr("pointer-events", "all");
    		
    		this.force.on("tick", function(x) {
    		self.vis.selectAll("g.node")
    		 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
    		
    		self.vis.selectAll("line.link")
    		 .attr("x1", function(d) { return d.source.x; })
    		 .attr("y1", function(d) { return d.source.y; })
    		 .attr("x2", function(d) { return d.target.x; })
    		 .attr("y2", function(d) { return d.target.y; });
    		});
    	}
    	 
    	 
    	Topology.prototype.doZoom=function(){
    	 	d3.select(this).select('g').attr("transform","translate(" + d3.event.translate + ")"+ " scale(" + d3.event.scale + ")");
    	}
    	 
    	 
    	//增加节点
    	Topology.prototype.addNode=function(node){
    	 	this.nodes.push(node);
    	}
    	 
    	Topology.prototype.addNodes=function(nodes){
    		 if (Object.prototype.toString.call(nodes)=='[object Array]' ){
    			 var self=this;
    			 nodes.forEach(function(node){
    			 	self.addNode(node);
    			 });
    		 }
    	}
    	 
    	//增加连线
    	Topology.prototype.addLink=function(source,target){
    		this.links.push({source:this.findNode(source),target:this.findNode(target)});
    	}
    	 
    	//增加多个连线
    	Topology.prototype.addLinks=function(links){
    		 if (Object.prototype.toString.call(links)=='[object Array]' ){
    			 var self=this;
    			 links.forEach(function(link){
    			   self.addLink(link['source'],link['target']);
    			 });
    		 }
    	}
    	 
    	 
    	//删除节点
    	Topology.prototype.removeNode=function(ip){
    		 var i=0,
    		 n=this.findNode(ip),
    		 links=this.links;
    		 while ( i < links.length){
    		 	links[i]['source']==n || links[i]['target'] ==n ? links.splice(i,1) : ++i;
    		 }
    		 this.nodes.splice(this.findNodeIndex(ip),1);
    	}
    	 
    	//删除节点下的子节点，同时清除link信息
    	Topology.prototype.removeChildNodes=function(ip){
    		 var node=this.findNode(ip),
    		 nodes=this.nodes;
    		 links=this.links,
    		 self=this;
    		 
    		 var linksToDelete=[],
    		 childNodes=[];
    		  
    		 links.forEach(function(link,index){
    		 link['source']==node 
    		  && linksToDelete.push(index) 
    		  && childNodes.push(link['target']);
    		 });
    		 
    		 linksToDelete.reverse().forEach(function(index){
    		 links.splice(index,1);
    		 });
    		 
    		var remove=function(node){
    			var length=links.length;
    			for(var i=length-1;i>=0;i--){
    			  if (links[i]['source'] == node ){
    				  var target=links[i]['target'];
    				  links.splice(i,1);
    				  nodes.splice(self.findNodeIndex(node.ip),1);
    				  remove(target);
    		  	  }
    		    }
    		 }
    		 
    		 childNodes.forEach(function(node){
    		 remove(node);
    		 });
    		 
    		 //清除没有连线的节点
    		 for(var i=nodes.length-1;i>=0;i--){
    			 var haveFoundNode=false;
    			 for(var j=0,l=links.length;j&lt;l++,j++){
    			  	( links[j]['source']==nodes[i] || links[j]['target']==nodes[i] ) && (haveFoundNode=true) 
    			 }
    			 !haveFoundNode && nodes.splice(i,1);
    		 }
    	}
    	 
    	 
    	 
    	//查找节点
    	Topology.prototype.findNode=function(ip){
    		 var nodes=this.nodes;
    		 for (var i in nodes){
    		 	if (nodes[i]['ip']==ip ) return nodes[i];
    		 }
    		 return null;
    	}
    	 
    	 
    	//查找节点所在索引号
    	Topology.prototype.findNodeIndex=function(ip){
    		var nodes=this.nodes;
    	 	for (var i in nodes){
    	 		if (nodes[i]['ip']==ip ) return i;
    	 	}
    	 	return -1;
    	}
    	 
    	//节点点击事件
    	Topology.prototype.setNodeClickFn=function(callback){
    		this.clickFn=callback;
    	}
    	 
    	//更新拓扑图状态信息
    	Topology.prototype.update=function(){
    		 var link = this.vis.selectAll("line.link")
    		 .data(this.links, function(d) { return d.source.ip + "-" + d.target.ip; })
    		 .attr("class", function(d){
    		  	return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
    		 });
    		 
    		 link.enter().insert("svg:line", "g.node")
    		 .attr("class", function(d){
    		  	return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
    		 });
    		 
    		 link.exit().remove();
    		 
    		 var node = this.vis.selectAll("g.node")
    		 .data(this.nodes, function(d) { return d.ip;});
    		 
    		 var nodeEnter = node.enter().append("svg:g")
    		 .attr("class", "node")
    		 .call(this.force.drag);
    		 
    		 //增加图片，可以根据需要来修改
    		 var self=this;
    		 nodeEnter.append("svg:image")
    		 .attr("class", "circle")
    		 .attr("xlink:href", function(d){
    		  //根据类型来使用图片
    		  if(d.type=="gatewayNode"){
    			  return "resource/image/smile1.jpg";
    		  }else if(d.type=="clusterHeadNode"){
    			  return "resource/image/smile2.jpg";
    		  }else if(d.type=="commonNode"){
    			  return "resource/image/smile3.jpg";
    		  }
    		 })
    		 .attr("x", "-20px")
    		 .attr("y", "-20px")
    		 .attr("width", "40px")
    		 .attr("height", "40px")
    		 .on('click',function(d){ d.expand && self.clickFn(d);})
    		 
    		 nodeEnter.append("svg:text")
    		 .attr("class", "nodetext")
    		 .attr("dx", 15)
    		 .attr("dy", -35)
    		 .text(function(d) { return d.ip;});
    		 
    		  
    		 node.exit().remove();
    		 
    		 this.force.start();
    	}
    	 
    	
    	 
    		 
    	var topology=new Topology('container');
    	var nodes;
    	var childNodes;
    	var links;
    	var childLinks;
    	
    	 $.ajax( { 
			    url:'getTopology.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	if(data.errorMsg!=null){
			    		alert(data.errorMsg);
			    	}else{
			    		nodes = data.nodes;
			    		childNodes = data.childNodes;
			    		links = data.links;
			    		childLinks = data.childLinks;
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
			    
    	
    	
    	
    	
    	 
    	topology.addNodes(nodes);
    	topology.addLinks(links);
    	
    	//可展开节点的点击事件
    	topology.setNodeClickFn(function(node){
    		if(!node['_expanded']){
    			expandNode(node.ip);
    	 		node['_expanded']=true;
    	 	}else{
    	 		collapseNode(node.ip);
    	 		node['_expanded']=false;
    	 	}
    	});
    	topology.update();
    	 
    	 
    	function expandNode(ip){
    		topology.addNodes(childNodes);
    		topology.addLinks(childLinks);
    		topology.update();
    	}
    	 
    	function collapseNode(ip){
    		topology.removeChildNodes(ip);
    		topology.update();
    	}
    } 
 
</script>
  
</body>
</html>