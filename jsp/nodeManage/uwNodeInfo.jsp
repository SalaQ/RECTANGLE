<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/uwNavigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resource/icon/icon2.ico">
<title>节点管理</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script type="text/javascript">
    	var curPage;
    	var curPageSize;
    	$(function(){
    		getNodeListPagination(1,10);
    	});
    	//获取节点信息列表
    	function getNodeListPagination(page,pageSize){
    		 $.ajax( { 
				    url:'getNodeList.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) { 
				    	curPage = page;
				    	curPageSize = pageSize;
				    	var nodeList = data.nodeList
				    	var html = "";
				    	var sumPage=0;
				    	var paginationHtml="";
				    	var next = page+1;
				    	var previous = page-1;
				    	for(var i=(page-1)*pageSize;i<nodeList.length&&i<page*pageSize;i++){
				    		html +="<tr><td>"+nodeList[i].nodeId+"</td><td>"+nodeList[i].ip+"</td><td>"+nodeList[i].parentId+"</td>";
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
				    		//根据是否在线显示不同颜色
				    		if(nodeList[i].onLine==false){
				    			html+= "<td><span class='reda'>"+nodeList[i].onLine+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodeList[i].onLine+"</td>";
				    		}
				    		//根据是否组网显示不同颜色
				    		if(nodeList[i].joinNetwork==false){
				    			html+= "<td><span class='reda'>"+nodeList[i].joinNetwork+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodeList[i].joinNetwork+"</td>";
				    		}
				    		//根据是否烧录协议显示不同颜色
				    		if(nodeList[i].burner==false){
				    			html+= "<td><span class='reda'>"+nodeList[i].burner+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodeList[i].burner+"</td>";
				    		}
				    		//添加修改按钮
				    		html+= "<td><button type='button' class='btn btn-warning' onclick='showModal("+nodeList[i].nodeId+",\""+nodeList[i].nodeType+"\","+nodeList[i].joinNetwork+")'>修改</button></td></tr>";
				    		
				    	}
				    	$("#content").html(html);
				    	if(nodeList.length%pageSize==0){
				    		sumPage = Math.floor(nodeList.length/pageSize);
				    	}else{
				    		sumPage = Math.floor(nodeList.length/pageSize)+1;
				    	}
				    	if(page==1){
				    		if(previous<=0){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeListPagination("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodeListPagination("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    	}
				    	for(var j=1;j<=sumPage;j++){
				    		if(j==page){
				    			paginationHtml+="<li class='active'><a href='#' onclick='getNodeListPagination("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}else{
				    			paginationHtml+="<li><a href='#' onclick='getNodeListPagination("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}
				    	}
				    	if(page==sumPage){
				    		if(next>sumPage){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
				    		}else{
					    		paginationHtml+="<li class='disabled'><a href='#' onclick='getNodeListPagination("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodeListPagination("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    	}
				    	$("#pagination").html(paginationHtml);
				    	
				    }, 
				    error : function() { 
				    	alert("系统错误！");
				     } 
				});
    	}
    	//显示修改节点信息窗口
    	function showModal(nodeId,nodeType,joinNetwork){
    		$("#select_nodeId").val(nodeId);
             
    		
    		//有问题，需解决
    		if(nodeType=='commonNode'){
    			$('#nodeType option:eq(0)').attr('selected','selected');
    			$('#nodeType option:eq(1)').attr('selected',false);
    			$('#nodeType option:eq(2)').attr('selected',false);
    			$('#nodeType option:eq(3)').attr('selected',false);
    		}else if(nodeType=='clusterHeadNode'){
    			$('#nodeType option:eq(1)').attr('selected','selected');
    			$('#nodeType option:eq(0)').attr('selected',false);
    			$('#nodeType option:eq(2)').attr('selected',false);
    			$('#nodeType option:eq(3)').attr('selected',false);
    		}
    		else if(nodeType=='gatewayNode'){
    			$('#nodeType option:eq(2)').attr('selected','selected');
    			$('#nodeType option:eq(1)').attr('selected',false);
    			$('#nodeType option:eq(0)').attr('selected',false);
    			$('#nodeType option:eq(3)').attr('selected',false);
    		}else{
    			$('#nodeType option:eq(3)').attr('selected','selected');
    			$('#nodeType option:eq(1)').attr('selected',false);
    			$('#nodeType option:eq(2)').attr('selected',false);
    			$('#nodeType option:eq(0)').attr('selected',false);
    		}
    		if(joinNetwork==true){
    			$('#joinNetwork option:eq(0)').attr('selected','selected');
    		}else{
    			$('#joinNetwork option:eq(1)').attr('selected','selected');
    		}
    		
    		$('#myModal').modal();
    	}
    	
    	//保存修改的节点信息
    	function saveModify(){
    		$.ajax( { 
			    url:'modifyNodeInfo.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             nodeId : $("#select_nodeId").val(),
		             nodeType:$("#nodeType").val(),
		             joinNetwork:$("#joinNetwork").val()
		    	}, 
			    dataType:'json', 
			    async: false,
			    success:function(data) { 
			    	getNodeListPagination(curPage,curPageSize);
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
	<!-- 隐藏修改节点信息窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
     <h4 class="modal-title" id="myModalLabel">修改节点信息</h4>
    </div>
    <div class="modal-body">
    <div class="form-group">
      <label for="txt_departmentname">节点id</label>
      <input type="text" class="form-control" id="select_nodeId" disabled>
     </div>
    <div class="form-group">
      <label for="txt_departmentname">节点类型</label>
      <select class="form-control" id="nodeType">
		  <option >commonNode</option>
		  <option>clusterHeadNode</option>
		  <option>gatewayNode</option>
		  <option>attackNode</option>
	  </select>
     </div>
      <div class="form-group">
      <label for="txt_parentdepartment">是否参与组网</label>
      <select class="form-control" id="joinNetwork">
		  <option>true</option>
		  <option>false</option>
	  </select>
     </div>
    </div>
    <div class="modal-footer">
     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="saveModify()">保存</button>
    </div>
   </div>
  </div>
 </div>

	 
	
	<!-- -------------------------------------------------------- -->
	<div class="container theme-showcase" role="main">
	    <div class="jumbotron">
	   		<h2>节点信息列表</h2>
		</div>
		<div class="pull-right">
			<button type="button" class="btn btn-primary btn-sm" onclick="getNodeListPagination(1,10)">刷新列表</button>
		</div>
			<table class="table table-striped table-hover">
	        	<thead>
	              <tr>
	                <th>节点id</th>
	                <th>节点ip地址</th>
	                <th>父节点id</th>
	                <th>节点类型</th>
	                <th>是否在线</th>
	                <th>是否参与组网</th>
	                <th>是否烧录协议</th>
	                <th>操作</th>
	              </tr>
	            </thead>
	            <tbody id="content">
	            </tbody>
			</table>
		<div class="pull-right">
			<nav>
			  <ul class="pagination" id="pagination">
			  </ul>
			</nav>
		</div>
</body>
</html>