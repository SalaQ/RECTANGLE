<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link href="resource/css/bootstrap.min.css" rel="stylesheet">
 	<link href="resource/css/nodeInfo.css" rel="stylesheet">
 	<link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
<title>系统说明</title>
<script type="text/javascript">
	$(document).ready(function() {
	    $('.progress .progress-bar').progressbar({transition_delay: 3000});
	});
	//打开隐藏的系统设置窗口
	function showModal(){
		$("#normal_send").val(50000);
		$("#disnormal_send").val(5000);
		$("#disnormal_sendTime").val(5);
		$('#myModal').modal();
	}
	//启动系统连接
	function startOrStop(){
		if($("#startOrStop").text()=="启动连接"){
			$.ajax( { 
			    url:'startConnection.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	$("#startOrStop").text("关闭连接");
			    	$.alert({
	                    title: 'success!',
	                    content: "系统启动成功！",
	                    icon: 'fa fa-rocket',
	                    animation: 'zoom',
	                    closeAnimation: 'zoom',
	                    buttons: {
	                        okay: {
	                            text: 'Okay',
	                            btnClass: 'btn-primary',
	                        }
	                    }
	                });
			    	/*$.confirm({
                        title: '性能测试',
                        content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
                        animation: 'zoom',
                        animationClose: 'top',
                        buttons: {
                            cancel: {
                            	 text: '停止性能测试',
                                 class: 'btn-primary',
                                 action: function () {
                                     setTimeout('refresh()',3000);
                                     return false; // prevent dialog from closing.
                                 }
                            }
                        },
                    });*/
	               // var time = 5000;
			    	//setTimeout('refresh()',time);
			    }, 
			    error : function() { 
			    	  $.alert({
	                      title: 'Oh no',
	                      type: 'red',
	                      content: '系统异常'
	                  }); 
			     }
		 	});
		}else{
			$.ajax({ 
			    url:'stopConnection.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json',
			    data:{
			    	orderType:'stopProgram'
			    },
			    async: false,
			    success:function(data) {
			    	$("#startOrStop").text("启动连接");
			    	$.alert({
	                    title: 'success!',
	                    content: '系统关闭成功',
	                    icon: 'fa fa-rocket',
	                    animation: 'zoom',
	                    closeAnimation: 'zoom',
	                    buttons: {
	                        okay: {
	                            text: 'Okay',
	                            btnClass: 'btn-primary'
	                        }
	                    }
	                });
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
     <h4 class="modal-title" id="myModalLabel">系统设置</h4>
    </div>
    <div class="modal-body">
    	
	    <div class="form-group">
		      <label for="txt_departmentname">正常发送间隔（ms）</label>
		      <input type="text" class="form-control" id="normal_send">
	    </div>
	    <div class="form-group">
		     <label for="txt_departmentname">连接异常发送间隔（ms）</label>
		     <input type="text" class="form-control" id="disnormal_send">
	    </div>
	    <div class="form-group">
		      <label for="txt_departmentname">连接异常重发次数</label>
		      <input type="text" class="form-control" id="disnormal_sendTime">
	    </div>
    
    </div>
    <div class="modal-footer">
     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="saveModify()">确定</button>
    </div>
   </div>
  </div>
</div>


 <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>系统简介</h1>
        <p class="text-muted">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物联网感知层测试床系统是为物联网感知层提供的一种协议测试系统。物联网感知层具有规模大，节点数量多，节点能耗低等特点。用户开发的传感器网络如果不经过测试容易造成系统不稳定、寿命短、传输时延长和有效节点比例低等问题。同时未经测试的物联网感知层在投入使用时，很有可能会遭受各种恶意攻击（如女巫攻击、黑洞攻击、虫洞攻击等），给使用者带来一定的损失。
			物联网感知层测试床系统提供了一个供用户测试的软硬件平台，使用该测试床对协议进行测试，可以有效模拟物联网感知层部署的真实场景，从而对用户协议的各项性能进行测试和研究，促进物联网感知层协议的改进和完善。
		</p>
	
        <p>
        	<c:choose>
          		<c:when test="${startConn==false}">
          			 <a href="#" class="btn btn-success" role="button" onclick="startOrStop()" id="startOrStop">启动连接</a>
          	 	</c:when>
          	 	<c:when test="${startConn==null}">
          			 <a href="#" class="btn btn-success" role="button" onclick="startOrStop()" id="startOrStop">启动连接</a>
          	 	</c:when>
          	 	<c:otherwise>
          			 <a href="#" class="btn btn-success" role="button" onclick="startOrStop()" id="startOrStop">关闭连接</a>
          		</c:otherwise>
          		
          	</c:choose>
	        <!--<a href="#" class="btn btn-primary" role="button" onclick="showModal()">系统设置 </a>-->
        </p>
      </div>
      
      
       <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="resource/image/sensor.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2><span class="reda">传感器节点</span></h2>
          <p>传感器节点之间通过RF射频模块通信，根据节点中运行协议的不同自组织形成不同的网络。传感器节点主要实现的功能有：感知环境数据功能、数据处理分析功能、无线通信功能、串口通信功能和定时器功能等。</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="resource/image/kaifaban.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2><span class="reda">控制板</span></h2>
          <p>控制板主要实现传感器终端与中心服务器的通信。控制单元和传感器终端通过串口通信，和中心服务器通过网口通信。使用控制单元，可以实现协议的批量下载。其主要功能有：网口通信功能，数据处理功能，串口通信功能等。</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="resource/image/jiaohuanji.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2><span class="reda">交换机</span></h2>
          <p>交换机主要是使传感器通信终端之间以及传感器通信终端与中心服务器之间形成局域网环境，在该环境内可以实现数据的传输。</p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->

      
      
      
      
      
      <!-- /END THE FEATURETTES -->
    </div><!-- /.container -->
</body>
</html>