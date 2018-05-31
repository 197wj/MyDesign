<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String data=request.getParameter("data");
%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="common/_meta.jsp" %>


<link href="<%=path%>/res/hui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/hui/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/hui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/hui/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>农田信息管理系统</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div  class="loginBox">
    <form class="form form-horizontal" id="loginform" action="<%=path%>/login/main.action" method="post" >
    	<div style="margin:5px; color:#c35d00; text-align: center;position:absolute;width:8em;left:50%;margin-left:-4em;z-index:2;top:-35px" id="error_box" >
			<span id="error_tips"></span>
		</div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="phone" name="phone" type="text" placeholder="账户" class="input-text size-L" onkeydown="keydownEvent()">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="userpwd" name="pwd" type="password" placeholder="密码" class="input-text size-L" onkeydown="keydownEvent()">
        </div>
      </div>
      		
		<div class="row cl">
			<div class="formControls col-xs-8 col-xs-offset-3">
				<input class="input-text size-L" type="text" placeholder="验证码" style="width:130px;" name="validateCode" id="validateCode" onkeydown="keydownEvent()">
				<img src="<%=path%>/login/getVerifyCodeImage.action" id="randImage" onclick="javascript:loadimage('<%=path%>/login/getVerifyCodeImage.action?');">
				<a id="kanbuq" onclick="javascript:loadimage('<%=path%>/login/getVerifyCodeImage.action?');">看不清，换一张</a>
			</div>
		</div> 
     
      <div class="row cl">
      	<div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" onclick="checkAccount()" id="login" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       	  <a href="javascript:;" onclick="register('注册','<%=path%>/login/toRegister.action','4','','510')"><input name="" id="register" type="button" class="btn btn-success radius size-L" value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;"></a>
         </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 2014162-农田信息管理系统</div>

<!--_footer 作为公共模版分离出去-->
<%@ include file="common/_footer.jsp" %>

<script type="text/javascript">

$(document).ready(function () {

    layer_close();
})

function loadimage(src){
	document.getElementById("randImage").src=src+Math.random();
};
//回车按钮
function keydownEvent() {
     var e = window.event || arguments.callee.caller.arguments[0];
     if (e && e.keyCode == 13 ) {
     	document.getElementById("login").click();
     }
};
</script>
<script type="text/javascript">
//$(function(){
	//错误提示 
	var jsonStr='${data}';
	var data = eval('(' + jsonStr + ')')
	if(data!=null || data!=''){
	$('#error_tips').text(data.msg);
	$('#error_box').slideDown(400);
	setTimeout(function(){$('#error_box').slideUp(400);}, 1000);
	}


	function checkAccount(){
		$('#error_box').hide();
		var phone = $("#phone").val().trim();//用户名称
		var pwd = $("#userpwd").val().trim();//用户密码
		var code = $("#validateCode").val().trim();//验证码
		if(code==null ||code==''){
			$('#validateCode').focus();
	        $('#error_tips').text('请输入验证码');
	        $('#error_box').slideDown(400);
	        setTimeout(function(){$('#error_box').slideUp(400);}, 1000);
	        return false;
		}
		if(phone==null ||phone==''){
			$('#phone').focus();
	        $('#error_tips').text('请输入账号');
	        $('#error_box').slideDown(400);
	        setTimeout(function(){$('#error_box').slideUp(400);}, 1000);
	        return false;
		}
		if(pwd==null ||pwd==''){
			$('#pwd').focus();
	        $('#error_tips').text('请输入密码');
	        $('#error_box').slideDown(400);
	        setTimeout(function(){$('#error_box').slideUp(400);}, 1000);
	        return false;
		}
		$("#loginform").submit();
		
	}
</script>

<script type="text/javascript">
function register(title,url,id,w,h){
	layer_show(title,url,w,h);
}

</script>
</body>
</html>