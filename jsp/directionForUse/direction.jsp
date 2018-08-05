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
</head>
<body>
 <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>系统使用说明</h1>
      </div>
    	
      <!-- START THE FEATURETTES -->
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">用户模块</span></h2></legend>
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">用户登录</h4>
          <p class="lead">用户打开“http://localhost:8090/WSN”连接后进入登录页面，如图所示，输入用户名，密码和测试名称后点击登录按钮进入测试系统，若还没有用户名和密码可点击注册按钮进行注册。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/login.png">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/register.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">用户注册</h4>
          <p class="lead">进入注册页面后输入用户名和密码，注意，用户名需保持唯一，输入完毕点击提交按钮，若注册成功会跳转到登录页面。</p>
        </div>
      </div>

      <hr class="featurette-divider">
	</fieldset>
	
	<!-- ---------------------------------------- -->
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">首页</span></h2></legend>
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">介绍</h4>
          <p class="lead">用户登录成功后，会进入系统首页，如图所示，首先是系统简介，介绍平台的研究意义，其次有一个“启动连接”按钮，点击后，后台会开启服务器socket，等待下层开发板的连接，此时，按钮会变成“断开连接”按钮，点击后，后台会关闭serverSocket，并且释放连接。
                              最后是系统就使用说明，对于新用户可以根据该说明来具体操作系统。  </p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/homePage.png">
        </div>
      </div>
      <hr class="featurette-divider">
	</fieldset>
	<!-- -------------------------------------------- -->
	
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">节点管理</span></h2></legend>
	 
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/nodeManage.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">节点信息列表</h4>
          <p class="lead">点击导航栏的“节点管理”按钮，进入节点管理页面，如图所示，该页面会将当前所有连接到服务器的节点信息展示出来供用户浏览。</p>
        </div>
      </div>

      <hr class="featurette-divider">
	 
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">修改节点信息</h4>
          <p class="lead">用户若想修改节点信息，可点击修改按钮，会出现如图所示的对话框，用户可修改节点的类型以及规定节点是否可以参与组网。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/modifyNode.png">
        </div>
      </div>

      <hr class="featurette-divider">
     
	</fieldset>
	
	<!-- -------------------------------------------- -->
	
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">自主组网</span></h2></legend>
	 
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/networking.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">组网前</h4>
          <p class="lead">点击导航栏的“自主组网”按钮，进入组网页面，如图所示，该页面主要分为三部分，第一部分为组网准备，用户首先选择不同的节点类型，然后选择具体类型的协议文件，点击“协议下载”按钮，协议下载完成后，点击“协议烧录”按钮进行协议烧录，烧录完毕，节点会进行自主组网。
          	所有节点的协议下载及烧录情况会在下面的表格中显示给用户。第二部分为网络拓扑信息，会将组网节点实时的展示给用户。第三部分为拓扑图展示区域。</p>
        </div>
      </div>

      <hr class="featurette-divider">
	 
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">组网后</h4>
          <p class="lead">组网完成后，结果会在网络拓扑信息列表中展示，同时若用户对组网的节点不满意，可点击“节点复位”按钮，按钮再点击“协议烧录”按钮重新进行组网。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/netInfo.png">
        </div>
      </div>

      <hr class="featurette-divider">
      
      <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/topologicalGraph.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">拓扑图</h4>
          <p class="lead">若用户想查看网络拓扑图，可点击“拓扑图”按钮，查看拓扑图，如图所示。</p>
        </div>
      </div>

      <hr class="featurette-divider">
     
	</fieldset>
	
	<!-- -------------------------------------------- -->
	
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">性能测试</span></h2></legend>
	 
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">选择测试内容</h4>
          <p class="lead">组网完成后，可进行性能测试，点击导航栏的“性能测试”按钮，如图所示，选择测试内容。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/selectTest.png">
        </div>
      </div>

      <hr class="featurette-divider">
	 <fieldset>
	 <legend> <h4><span class="bluea">丢包率测试</span></h4></legend>
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/packetLossInit.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“网络丢包率测试”按钮，进入丢包率测试页面，如图所示。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/packetLossParam.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“参数设置”按钮，出现如图所示对话框，填写测试参数后，点击“开始测试”按钮，进行测试。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/nodePacketLossResult.png">
        </div>
        <div class="col-md-5">
          <p class="lead">测试结束后会在本页面显示节点丢包率测试结果，如图所示，若想查看系统丢包率，可点击“系统丢包率”按钮。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/sysPacketLoss.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“系统丢包率”按钮后出现系统丢包率测试结果，如图所示，曲线图实时拟合多次测试结果。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/sysPacketLoss1.png">
        </div>
        <div class="col-md-5">
          <p class="lead">曲线图默认是以测试数据包长度为X坐标，也可以选择以测试包发送间隔为X坐标，如图所示。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
      
	 </fieldset>
	 <fieldset>
	 <legend> <h4><span class="bluea">网络平均路径长度测试</span></h4></legend>
      <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">点击“网络平均路径长度测试”按钮，进入平均路径长度测试页面，如图所示。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/pathInit.png">
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">点击“参数设置”按钮，出现如图所示对话框，填写测试参数后，点击“开始测试”按钮，进行测试。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/pathParam.png">
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">测试结束后会在本页面显示平均路径长度测试结果，如图所示。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/netInfo.png">
        </div>
      </div>
      <hr class="featurette-divider">
      </fieldset>
      
      <fieldset>
	 <legend> <h4><span class="bluea">网络吞吐量测试</span></h4></legend>
      <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/throughputInit.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“网络吞吐量测试”按钮，进入网络吞吐量测试页面，如图所示。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
      <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/throughputParam.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“参数设置”按钮，出现如图所示对话框，填写测试参数后，点击“开始测试”按钮，进行测试。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      
      <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/throughput.png">
        </div>
        <div class="col-md-5">
          <p class="lead">测试结束后会在本页面显示吞吐量测试结果，如图所示。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      </fieldset>
      
       <fieldset>
	 <legend> <h4><span class="bluea">网络时延测试</span></h4></legend>
      
       <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">点击“网络时延测试”按钮，进入网络时延测试页面，如图所示。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/timeDelayInit.png">
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">点击“参数设置”按钮，出现如图所示对话框，填写测试参数后，点击“开始测试”按钮，进行测试。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/timeDelayParam.png">
        </div>
      </div>
      <hr class="featurette-divider">
      
       <div class="row featurette">
        <div class="col-md-5">
          <p class="lead">测试结束后会在本页面显示时延测试结果，如图所示.</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/timeDelay.png">
        </div>
      </div>
      <hr class="featurette-divider">
      </fieldset>
      
	  <fieldset>
	 <legend> <h4><span class="bluea">网络连通度测试</span></h4></legend>
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/connectivityDegree.png">
        </div>
        <div class="col-md-5">
          <p class="lead">点击“网络连通度测试”按钮，进入网络连通度测试页面，然后点击“开始测试”按钮，进行测试，测试结束后会将数据实时显示在本页面，如图所示。</p>
        </div>
      </div>
      <hr class="featurette-divider">
      </fieldset>

	</fieldset>
	
	
	<!-- -------------------------------------------- -->
	
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">历史查询</span></h2></legend>
	 
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/historyQuery.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">历史查询操作</h4>
          <p class="lead">点击导航栏的“历史 查询”按钮，出现如图所示的对话框，该对话框会列出该用户所进行的所有的测试名称，用户选择测试名称，点击“查询”按钮后进入 查询页面。</p>
        </div>
      </div>

      <hr class="featurette-divider">
	 
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">历史查询结果</h4>
          <p class="lead">进入查询页面，用户可选择查询网络拓扑信息、性能测试结果等，如图所示。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/queryResult.png">
        </div>
      </div>

      <hr class="featurette-divider">
     
	</fieldset>
	
	<!-- -------------------------------------------- -->
	
	<fieldset>
      <hr class="featurette-divider">
	 <legend> <h2><span class="reda">性能对比</span></h2></legend>
	 
	  <div class="row featurette">
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/packetLossCompare.png">
        </div>
        <div class="col-md-5">
          <h4 class="featurette-heading">丢包率对比</h4>
          <p class="lead">点击导航栏的“性能对比”按钮，首先出现的是丢包率性能对比页面，该页面左侧显示的是所有的测试名称，用户通过点击复选框来选择需要进行对比的测试名称，如图所示。</p>
        </div>
      </div>

      <hr class="featurette-divider">
	 
      <div class="row featurette">
        <div class="col-md-5">
          <h4 class="featurette-heading">时延对比</h4>
          <p class="lead">点击“时延测试”按钮，进入时延测试性能对比页面，该页面左侧显示的是所有的测试名称，用户通过点击复选框来选择需要进行对比的测试名称，如图所示。</p>
        </div>
        <div class="col-md-7">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" alt="Generic placeholder image" src="resource/image/timeDelayCompare.png">
        </div>
      </div>

      <hr class="featurette-divider">
     
	</fieldset>

      <!-- /END THE FEATURETTES -->
    </div><!-- /.container -->
</body>
</html>