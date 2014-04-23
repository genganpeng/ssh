<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +path+ "/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>登陆页面</title>
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login/login.css">
  </head>
  <body>
  	<div class="container">
	    <form class="form-signin">
		    <h2 class="form-signin-heading">请登录</h2>
			<input id="username" autofocus="autofocus" placeholder="用户名" class="form-control" required="required"/>
			<input id="password" required="required" placeholder="密码" class="form-control" type="password"/>
			<label class="checkbox">
	          <input type="checkbox" id="userCookie" checked="checked"> 下次自动登陆
	        </label>
			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="validate()">登陆</button>
		</form>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	document.onkeydown = function(evt) {
    		 var evt = window.event?window.event:evt;
    		 if (evt.keyCode == 13) {
    			 validate();
    		 }
    	}
    	
	    function validate() {
	    	var username = $("#username").val();
	    	var password = $("#password").val();
	    	var userCookie = $("#userCookie").is(":checked");
	    	$.ajax({
	    		url : "login/login.action",
	    		type : "post",
	    		data : {
	    			"userName" : username,
	    			"password" : password,
	    			"userCookie" : userCookie
	    		},
	    		//data : "username="+username+"&password=" + password,
	    		success : function(json) {
	    			if (json.flag) {
	    				location.href="success.jsp";
	    			}
    				else {
    					alert("用户名或者密码错误！");
    				}
	    		},
	    		error : function(json) {
	    			alert(json);
	    		}
	    	});
	    }
    </script>
  </body>
</html>
