<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp"%>
<title>数控中心</title>
</head>
<body style="background: #f1f1f1">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数控中心 <span class="c-gray en">&gt;</span> 数控中心 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container cl clearfix" style="box-sizing: border-box">

		<div class="row r">
				<a href="../readPort.action" class="btn btn-primary radius">打开串口</a> 
				<a href="../closePort.action" class="btn btn-danger radius">关闭串口</a>
		</div>
		<div class="row">
			<div id="container1" style="min-width:400px;height:400px"></div>
			<br><br><br>
	
			<div id="container2" style="min-width:400px;height:400px"></div>
			<br><br>
	
			<div id="container3" style="min-width:400px;height:400px"></div>
			<br><br>
	
			<div id="container4" style="min-width:400px;height:400px"></div>
			<br><br>
	
			<div id="container5" style="min-width:400px;height:400px"></div>
		</div>
	</div>
	<script type="text/javascript">
	
	var chart = null;
	
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});
	function activeLastPointToolip(chart) {
			var points = chart.series[0].points;
			chart.tooltip.refresh(points[points.length -1]);
	}
	chart = Highcharts.chart('container1', {
			chart: {
					type: 'spline',
					marginRight: 10,
					events: {
							load: function () {
									var series = this.series[0],
											chart = this;
									activeLastPointToolip(chart);
									setInterval(function () {
											var x = (new Date()).getTime(), // 当前时间
												y = getFm1();
											series.addPoint([x, y], true, true);
											activeLastPointToolip(chart);
									}, 1000);
							}
					}
			},
			title: {
					text: '农田1'
			},
			xAxis: {
					type: 'datetime',
					tickPixelInterval: 150
			},
			yAxis: {
					title: {
							text: '土壤湿度'
					}
			},
			tooltip: {
					formatter: function () {
							return '<b>' + this.series.name + '</b><br/>' +
									Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
									Highcharts.numberFormat(this.y, 2);
					}
			},
			legend: {
					enabled: false
			},
			series: [{
					name: '土壤湿度',
					data: (function () {
							var data = [],
								time = (new Date()).getTime(),
								i;
					        for (i = -19; i <= 0; i += 1) {
								data.push({
									x: time + i * 1000,
									y: getFm1()
								});
							}
							return data;
					}())
			}]
	});
	
	chart = Highcharts.chart('container2', {
		chart: {
				type: 'spline',
				marginRight: 10,
				events: {
						load: function () {
								var series = this.series[0],
										chart = this;
								activeLastPointToolip(chart);
								setInterval(function () {
										var x = (new Date()).getTime(), // 当前时间
											y = getFm2();
										series.addPoint([x, y], true, true);
										activeLastPointToolip(chart);
								}, 1000);
						}
				}
		},
		title: {
				text: '农田2'
		},
		xAxis: {
				type: 'datetime',
				tickPixelInterval: 150
		},
		yAxis: {
				title: {
						text: '土壤湿度'
				}
		},
		tooltip: {
				formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
								Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
								Highcharts.numberFormat(this.y, 2);
				}
		},
		legend: {
				enabled: false
		},
		series: [{
				name: '土壤湿度',
				data: (function () {
						var data = [],
							time = (new Date()).getTime(),
							i;
				        for (i = -19; i <= 0; i += 1) {
							data.push({
								x: time + i * 1000,
								y: getFm2()
							});
						}
						return data;
				}())
		}]
	});
	
	chart = Highcharts.chart('container3', {
		chart: {
				type: 'spline',
				marginRight: 10,
				events: {
						load: function () {
								var series = this.series[0],
										chart = this;
								activeLastPointToolip(chart);
								setInterval(function () {
										var x = (new Date()).getTime(), // 当前时间
											y = getFm2();
										series.addPoint([x, y], true, true);
										activeLastPointToolip(chart);
								}, 1000);
						}
				}
		},
		title: {
				text: '农田3'
		},
		xAxis: {
				type: 'datetime',
				tickPixelInterval: 150
		},
		yAxis: {
				title: {
						text: '土壤湿度'
				}
		},
		tooltip: {
				formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
								Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
								Highcharts.numberFormat(this.y, 2);
				}
		},
		legend: {
				enabled: false
		},
		series: [{
				name: '土壤湿度',
				data: (function () {
						var data = [],
							time = (new Date()).getTime(),
							i;
				        for (i = -19; i <= 0; i += 1) {
							data.push({
								x: time + i * 1000,
								y: getFm2()
							});
						}
						return data;
				}())
		}]
	});
	
	chart = Highcharts.chart('container4', {
		chart: {
				type: 'spline',
				marginRight: 10,
				events: {
						load: function () {
								var series = this.series[0],
										chart = this;
								activeLastPointToolip(chart);
								setInterval(function () {
										var x = (new Date()).getTime(), // 当前时间
											y = Math.random();          // 随机值
										series.addPoint([x, y], true, true);
										activeLastPointToolip(chart);
								}, 1000);
						}
				}
		},
		title: {
				text: '空气湿度（随机数）'
		},
		xAxis: {
				type: 'datetime',
				tickPixelInterval: 150
		},
		yAxis: {
				title: {
						text: '空气湿度'
				}
		},
		tooltip: {
				formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
								Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
								Highcharts.numberFormat(this.y, 2);
				}
		},
		legend: {
				enabled: false
		},
		series: [{
				name: '空气湿度',
				data: (function () {
						var data = [],
							time = (new Date()).getTime(),
							i;
				        for (i = -19; i <= 0; i += 1) {
							data.push({
								x: time + i * 1000,
								y: Math.random()          // 随机值
							});
						}
						return data;
				}())
		}]
	});
	
	chart = Highcharts.chart('container5', {
		chart: {
				type: 'spline',
				marginRight: 10,
				events: {
						load: function () {
								var series = this.series[0],
										chart = this;
								activeLastPointToolip(chart);
								setInterval(function () {
										var x = (new Date()).getTime(), // 当前时间
											y = getAt();          // 随机值
										series.addPoint([x, y], true, true);
										activeLastPointToolip(chart);
								}, 1000);
						}
				}
		},
		title: {
				text: '空气温度'
		},
		xAxis: {
				type: 'datetime',
				tickPixelInterval: 150
		},
		yAxis: {
				title: {
						text: '温度'
				},
				labels: {
					formatter: function () {
							return this.value + '°C';
					}
				}
		},
		tooltip: {
				formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
								Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
								Highcharts.numberFormat(this.y, 2);
				}
		},
		legend: {
				enabled: false
		},
		series: [{
				name: '空气温度',
				data: (function () {
						var data = [],
							time = (new Date()).getTime(),
							i;
				        for (i = -19; i <= 0; i += 1) {
							data.push({
								x: time + i * 1000,
								y: getAt()
							});
						}
						return data;
				}())
		}]
	});
	
	function getFm1(){
		var result;
		$.ajax({
			url: "../farmland/getFm1.action",
            type: "get",
            async: false,  //设置为异步请求
            dataType: "json",
            success: function (value) {
            	result = value;
            }
		});
		return result;
	}
	
	function getFm2(){
		var result;
		$.ajax({
			url: "../farmland/getFm2.action",
            type: "get",
            async: false,  //设置为异步请求
            dataType: "json",
            success: function (value) {
            	result = value;
            }
		});
		return result;
	}
	
	function getFm3(){
		var result;
		$.ajax({
			url: "../farmland/getFm3.action",
            type: "get",
            async: false,  //设置为异步请求
            dataType: "json",
            success: function (value) {
            	result = value;
            }
		});
		return result;
	}
	
	function getAm(){
		var result;
		$.ajax({
			url: "../farmland/getAm.action",
            type: "get",
            async: false,  //设置为异步请求
            dataType: "json",
            success: function (value) {
            	result = value;
            }
		});
		return result;
	}
	
	function getAt(){
		var result;
		$.ajax({
			url: "../farmland/getAt.action",
            type: "get",
            async: false,  //设置为异步请求
            dataType: "json",
            success: function (value) {
            	result = value;
            }
		});
		return result;
	}
	
	</script>
	
	<footer class="mt-20">
		<div class="footer">Copyright 2014162-农田信息管理系统</div>
	</footer>
	<!--_footer 作为公共模版分离出去-->
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>


