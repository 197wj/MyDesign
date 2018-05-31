<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>农田信息</title>

<style>
	.qin li:hover .zi{
	display:block !important;
	}
	.ti{
	height:1px;background:white;
	} 
</style>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" ><img src="../res/image/logo.png"/></a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li id="currentTime"></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;${user.name !="admin"?"普通用户":"管理员"}</li>
					<li class="dropDown dropDown_hover">
						<a href="#" class="dropDown_A">${user.name } <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
							<li><a href="../login.action">切换账户</a></li>
							<li><a href="../login/loginOut.action">退出</a></li>
						</ul>
					</li>
				</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown">
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont"></i> 导航栏<i class="Hui-iconfont menu_dropdown-arrow"></i></dt>
		</dl>
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe63c;</i> 数控中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${path }/farmland/farmlandRead.action" data-title="数控中心" href="javascript:void(0)">数控中心</a></li>
					<li><a data-href="${path }/farmland/farmlandForecast.action" data-title="湿度预测" href="javascript:void(0)">湿度预测</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 数据管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${path }/farmland/farmlandView.action" data-title="农田数据" href="javascript:void(0)">农田数据</a></li>
					<li><a data-href="${path }/crop/cropView.action" data-title="农作物数据" href="javascript:void(0)">农作物数据</a></li>
				</ul>
			</dd>
		</dl>
		<!-- 系统统计 -->
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 图形信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href='<c:url value="/report/toGetDayReport.action"></c:url>' href="javascript:void(0)" data-title="农田日报表" >农田日报表</a></li>
					<li><a data-href='<c:url value="/report/toGetMonthReport.action"></c:url>' href="javascript:void(0)" data-title="农田月报表">农田月报表</a></li>
					<li><a data-href='<c:url value="/report/toGetYearReport.action"></c:url>' href="javascript:void(0)" data-title="农田年报表">农田年报表</a></li>
				</ul>
			</dd>
		</dl>
		<!-- 用户管理 -->
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${path }/user/userView.action" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
				</ul>
			</dd>
		</dl>
		<!-- 系统管理 -->
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 系统日志<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${path }/log/logView.action" data-title="系统日志" href="javascript:void(0)">日志管理</a></li>
				</ul>
			</dd>
		</dl>
</div>


</aside>

<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs" >
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="${path }/welcome.action">我的桌面</span>
					<em></em>
				</li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
<div id="iframe_box" class="Hui-article">
	<div class="show_iframe">
		<div style="display:none" class="loading"></div>
		<div id="inde">
			<iframe scrolling="yes" frameborder="0" src="${path }/welcome.action"></iframe>
		</div>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
	</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>


<script type="text/javascript">

$(function(){
	LoadTime();
})

/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>${user.name}</div><br><div>Tel:${user.phone}</div>'
	});
}

/* 当前时间 */
function LoadTime() {

	var myDate = new Date();
	//获取当前年
	var year = myDate.getFullYear();
	//获取当前月
	var month = myDate.getMonth() + 1;
	//获取当前日
	var date = myDate.getDate();
	var h = myDate.getHours(); //获取当前小时数(0-23)
	var m = myDate.getMinutes(); //获取当前分钟数(0-59)
	var s = myDate.getSeconds();

	var now = year + '-' + month + "-" + date + " " + h
			+ ':' + m + ":" + s;

	$("#currentTime").html(now);
}
</script> 
</body>
</html>