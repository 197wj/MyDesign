<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp"%>
<title>湿度预测</title>
</head>
<body style="background: #f1f1f1">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		数控中心 <span class="c-gray en">&gt;</span> 湿度预测 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container cl clearfix" style="box-sizing: border-box">
		
		<div id="fm1" style="min-width: 550px; height: 400px; margin: 0 auto"></div>
		<h4 id="forecastFm1" class="Huialert-info"></h4>
		<br>
		<div id="fm2" style="min-width: 550px; height: 400px; margin: 0 auto"></div>
		<h4 id="forecastFm2" class="Huialert-info"></h4>
		<br>
		<div id="fm3" style="min-width: 550px; height: 400px; margin: 0 auto"></div>
		<h4 id="forecastFm3" class="Huialert-info"></h4>

	</div>
	<script type="text/javascript">
		$(function() {
			
			getfm1();
			getfm2();
			getfm3();
		});
		
		var chart = null;
		// 获取 CSV 数据并初始化图表
		function getfm1(){
			$.ajax({
				url:'../report/fm1.action',
				type:'get',
				dataType:'json',
				success:function(data){
					var forecastFm = data[0].forecastFm;
					var fmType = "";
					if (forecastFm<0.25) {
						fmType = '<font color="red">干旱</font>';
					}else if (forecastFm<0.45) {
						fmType = "中湿度";
					}else {
						fmType = '<font color="yellow">高湿度</font>';
					}
					var info = "农田1：预计明天的农田湿度:" + forecastFm + ", 湿度等级为：" + fmType;
					$("#forecastFm1").html(info);
					chart = 
						Highcharts.chart('fm1', {
							chart: {
								type: 'spline'
							},
							title: {
								text: '农田1最近一周的土壤湿度'
							},
							xAxis: {
								categories: [
									formatterDateTime(data[0].beforeDay),
									formatterDateTime(data[1].beforeDay),
									formatterDateTime(data[2].beforeDay),
									formatterDateTime(data[3].beforeDay),
									formatterDateTime(data[4].beforeDay),
									formatterDateTime(data[5].beforeDay),
									formatterDateTime(data[6].beforeDay)
								]
							},
							yAxis: {
								title: {
										text: '湿度'
								},
								labels: {
										formatter: function () {
												return this.value;
										}
								}
							},
							tooltip: {
								crosshairs: true,
								shared: true
							},
							plotOptions: {
								spline: {
										marker: {
												radius: 4,
												lineColor: '#666666',
												lineWidth: 1
										}
								}
							},
						    series: [{
								name: '农田1',
								marker: {
										symbol: 'square'
								},
								data: [
									data[0].avgFm,data[1].avgFm,data[2].avgFm,data[3].avgFm,data[4].avgFm,data[5].avgFm,data[6].avgFm,
					        	]
							}]
						});
				}
			});
		}
		
		function getfm2(){
			$.ajax({
				url:'../report/fm2.action',
				type:'get',
				dataType:'json',
				success:function(data){
					var forecastFm = data[0].forecastFm;
					var fmType = "";
					if (forecastFm<0.25) {
						fmType = '<font color="red">干旱</font>';
					}else if (forecastFm<0.45) {
						fmType = "中湿度";
					}else {
						fmType = '<font color="yellow">高湿度</font>';
					}
					var info = "农田2：预计明天的农田湿度:" + forecastFm + ", 湿度等级为：" + fmType;
					$("#forecastFm2").html(info);
					chart = 
						Highcharts.chart('fm2', {
							chart: {
								type: 'spline'
							},
							title: {
								text: '农田2最近一周的土壤湿度'
							},
							xAxis: {
								categories: [
									formatterDateTime(data[0].beforeDay),
									formatterDateTime(data[1].beforeDay),
									formatterDateTime(data[2].beforeDay),
									formatterDateTime(data[3].beforeDay),
									formatterDateTime(data[4].beforeDay),
									formatterDateTime(data[5].beforeDay),
									formatterDateTime(data[6].beforeDay)
								]
							},
							yAxis: {
								title: {
										text: '湿度'
								},
								labels: {
										formatter: function () {
												return this.value;
										}
								}
							},
							tooltip: {
								crosshairs: true,
								shared: true
							},
							plotOptions: {
								spline: {
										marker: {
												radius: 4,
												lineColor: '#666666',
												lineWidth: 1
										}
								}
							},
						    series: [{
								name: '农田2',
								marker: {
										symbol: 'square'
								},
								data: [
									data[0].avgFm,data[1].avgFm,data[2].avgFm,data[3].avgFm,data[4].avgFm,data[5].avgFm,data[6].avgFm,
					        	]
							}]
						});
				}
			});
		}
		
		
		function getfm3(){
			$.ajax({
				url:'../report/fm3.action',
				type:'get',
				dataType:'json',
				success:function(data){
					var forecastFm = data[0].forecastFm;
					var fmType = "";
					if (forecastFm<0.25) {
						fmType = '<font color="red">干旱</font>';
					}else if (forecastFm<0.45) {
						fmType = "中湿度";
					}else {
						fmType = '<font color="yellow">高湿度</font>';
					}
					var info = "农田3：预计明天的农田湿度:" + forecastFm + ", 湿度等级为：" + fmType;
					$("#forecastFm3").html(info);
					chart = 
						Highcharts.chart('fm3', {
							chart: {
								type: 'spline'
							},
							title: {
								text: '农田3最近一周的土壤湿度'
							},
							xAxis: {
								categories: [
									formatterDateTime(data[0].beforeDay),
									formatterDateTime(data[1].beforeDay),
									formatterDateTime(data[2].beforeDay),
									formatterDateTime(data[3].beforeDay),
									formatterDateTime(data[4].beforeDay),
									formatterDateTime(data[5].beforeDay),
									formatterDateTime(data[6].beforeDay)
								]
							},
							yAxis: {
								title: {
										text: '湿度'
								},
								labels: {
										formatter: function () {
												return this.value;
										}
								}
							},
							tooltip: {
								crosshairs: true,
								shared: true
							},
							plotOptions: {
								spline: {
										marker: {
												radius: 4,
												lineColor: '#666666',
												lineWidth: 1
										}
								}
							},
						    series: [{
								name: '农田3',
								marker: {
										symbol: 'square'
								},
								data: [
									data[0].avgFm,data[1].avgFm,data[2].avgFm,data[3].avgFm,data[4].avgFm,data[5].avgFm,data[6].avgFm,
					        	]
							}]
						});
				}
			});
		}
		
		
		/**
		  * 将时间long类型转换为String类型
		  */
		  function formatterDateTime(val){
		     var date = new Date(val);
		     var y = date.getFullYear();  
		     var m = date.getMonth() + 1;  
		     m = m < 10 ? ('0' + m) : m;  
		     var d = date.getDate();  
		     d = d < 10 ? ('0' + d) : d;  
		     var h = date.getHours();  
		     h = h < 10 ? ('0' + h) : h;  
		     
		     var minute = date.getMinutes();  
		     minute = minute < 10 ? ('0' + minute) : minute; 
		     var second = date.getSeconds();
		     second = second < 10 ? ('0' + second) : second; 
		     return y + '-' + m + '-' + d;  
		 }
		
	</script>


	<footer class="mt-20">
		<div class="footer">Copyright 2014162-农田信息管理系统</div>
	</footer>
	<!--_footer 作为公共模版分离出去-->
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>


