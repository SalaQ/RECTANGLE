<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resource/icon/icon1.ico">
<title>用户注册页面</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/signin.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$(".a1").each(function(){
    			var hdw = $("<strong><span class='reda'>*</span></strong>");
    			$(this).parent().append(hdw);
    		});
    		
    		var name = false;
    		var password = false;
    		var confirmPassword = false;
    		
    		$("form :input").blur(function(){
    			$(this).parent().find(".a2").remove();
    			if($(this).is("#userName")){
	    			if(this.value==""||this.value.length<6){
	    				name = false;
	    				var hdw1 = $("<span class='a2'>用户名不得小于6位</span>");
	    				$(this).parent().append(hdw1);
	    			}else{
	    				var unique;
	    				 $.ajax( { 
	    					    url:'checkUnique.json',// 跳转到 controller 
	    					    data:{ 
	    					             userName : this.value 
	    					    }, 
	    					    type:'post', 
	    					    cache:false, 
	    					    dataType:'json', 
	    					    async: false,
	    					    success:function(data) { 
	    					        unique = data.msg;
	    					     }, 
	    					    error : function() { 
	    					          alert("系统异常!"); 
	    					     } 
	    					});
	    				 if(unique=='true'){ 
	    					 name = true;
					         var hdw1 = $("<span class='a2'>输入正确</span>");
			    			 $(this).parent().append(hdw1);
				         }else if(unique=='false'){ 
				        	 name = false;
				        	 var hdw1 = $("<span class='a2'>用户名已存在</span>");
		    				 $(this).parent().append(hdw1); 
				         } 
	    				 
	    			}
    			}
    			if($(this).is("#password")){
	    			if(this.value==""||this.value.length<6){
	    				password = false;
	    				var hdw1 = $("<span class='a2'>密码长度不得小于6位</span>");
	    				$(this).parent().append(hdw1);
	    			}else{
	    				password = true;
	    				var hdw1 = $("<span class='a2'>输入正确</span>");
	    				$(this).parent().append(hdw1);
	    			}
	    		}
    			if($(this).is("#confirmPassword")){
	    			if(this.value!=$("#password").val()){
	    				confirmPassword = false;
	    				var hdw1 = $("<span class='a2'>密码输入不一致</span>");
	    				$(this).parent().append(hdw1);
	    			}else{
	    				confirmPassword = true;
	    				var hdw1 = $("<span class='a2'>输入正确</span>");
	    				$(this).parent().append(hdw1);
	    			}
	    		}
    		});
    		
    		$("#submit").click(function () { //“提交”按钮单击事件
    			if(name==false || password==false || confirmPassword==false){
    				return;
    			}
    		    //获取用户名称
    		    var userName = encodeURI($("#userName").val());
    		    //获取输入密码
    		    var password = encodeURI($("#password").val());
    		    //开始发送数据
    		    $.ajax
    		    ({ //请求登录处理页
    		     url: "registerUser.json", //登录处理页
    		     dataType:'json',
    		     //传送请求数据
    		     data: { userName: userName, pwd: password },
    		     success: function (data) { //登录成功后返回的数据
    		    	 
    		      //根据返回值进行状态显示
    		      if (data.msg == "true") {//注意是True,不是true
    		    	  alert("注册成功！");
    		    	  window.location.href="userLogin.wsn"
    		      }
    		      else {
    		    	 alert("系统错误！");
    		      }
    		     }
    		    });
    		  });
    		
    		
    	});
    </script>
</head>
<body>
	 <div class="container">
	 <fieldset>
      <form class="form-signReg" role="form" method="post">
        <legend>注册</legend>
        <div class="one">
        	<label for="userName">用户名：</label>
        	<input type="text" id="userName" class="a1" required autofocus/>
        </div>
        <div class="one">
        	<label for="userName">密码：</label>
        	<input type="password" id="password" class="a1" required/>
        </div>
        <div class="one">
        	<label for="userName">确认密码：</label>
        	<input type="password" id="confirmPassword" class="a1" required/>
        </div>
        <div class="two">
        	<button type="button" class="btn btn-primary" id="submit">提交</button>
        	<input type="reset" class="btn btn-warning" id="res" value="重置"/>
        </div>
      </form>
      </fieldset>
     </div> <!-- /container -->
     
</body>
</html>