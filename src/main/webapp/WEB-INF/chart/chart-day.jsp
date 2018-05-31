<%@page import="com.design.model.vo.SysReportVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>农田日报表</title>
</head>
<body>

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 图形信息
	<span class="c-gray en">&gt;</span> 农田日报表 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>

<br>

	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="startTime" class="input-text Wdate" style="width:170px;">
		-<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d\'}'})" id="endTime" class="input-text Wdate" style="width:170px;">
		<input type="text" class="input-text" style="width:150px" placeholder="输入农田编号" id="farmlandId" name="farmlandId">
		<button type="button" class="btn btn-success radius" id="seach" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>

<div id="container" style="min-width:550px;height:400px;margin: 0 auto"></div>


<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<script type="text/javascript">
$(function(){
	
	dayChart();
});

//回车按钮
function keydownEvent() {
     var e = window.event || arguments.callee.caller.arguments[0];
     if (e && e.keyCode == 13 ) {
     	document.getElementById("seach").click();
     }
};

/**
 * 点击搜索,
 */
 $("#seach").click(function(){
	var farmlandId = $("#farmlandId").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	dayChart(farmlandId,startTime,endTime);
 });
 
 
function dayChart(farmlandId,startTime,endTime){
	var chart=null;

	$.ajax({
		url:'../report/getDayReport.action',
		type:'get',
		data:{'farmlandId':farmlandId,'startTime':startTime,'endTime':endTime},
		dataType:'json',
		success:function(data){
			alert(data);
			charHtml(data.listTime,data.listFm,data.listAt,data.listAm)
			
		}
	});
}


function charHtml(listTime, listFm,listAt, listAm){
	
	alert(listTime);
	
	chart = Highcharts.chart('container', {
		chart: {
				zoomType: 'xy'
		},
		title: {
				text: '农田数据日报表'
		},
		subtitle: {
				text: '数据来源: com.jdbc.mysql.Driver'
		},
		xAxis: [{
				categories: eval("[" + listTime + "]"),
				crosshair: true
		}],
		yAxis: [{
				labels: {
						format: '{value}°C',
						style: {
								color: Highcharts.getOptions().colors[2]
						}
				},
				title: {
						text: '温度',
						style: {
								color: Highcharts.getOptions().colors[2]
						}
				},
				opposite: true
		}, { // Secondary yAxis
				gridLineWidth: 0,
				title: {
						text: '土壤湿度',
						style: {
								color: Highcharts.getOptions().colors[0]
						}
				},
				labels: {
						format: '{value}',
						style: {
								color: Highcharts.getOptions().colors[0]
						}
				}
		}, { // Tertiary yAxis
				gridLineWidth: 0,
				title: {
						text: '空气湿度',
						style: {
								color: Highcharts.getOptions().colors[1]
						}
				},
				labels: {
						format: '{value}',
						style: {
								color: Highcharts.getOptions().colors[1]
						}
				},
				opposite: true
		}],
		tooltip: {
				shared: true
		},
		legend: {
				layout: 'vertical',
				align: 'left',
				x: 80,
				verticalAlign: 'top',
				y: 55,
				floating: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
		},
		series: [{
				name: '土壤湿度',
				type: 'column',
				yAxis: 1,
				data: eval("[" + listFm + "]"),
		}, {
				name: '空气湿度',
				type: 'spline',
				yAxis: 2,
				data: eval("[" + listAm + "]"),
				marker: {
						enabled: false
				},
				dashStyle: 'shortdot',
		}, {
				name: '温度',
				type: 'spline',
				data: eval("[" +listAt + "]"),
				tooltip: {
						valueSuffix: ' °C'
				}
		}]
});
}
</script>

</body>
</html>