<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp"%>
<title>我的桌面</title>
</head>
<body style="background: #f1f1f1">
	<div class="page-container cl clearfix" style="box-sizing: border-box">
		<div class="welcome_bar">
			<img src='<c:url value="/res/image/desktop1.jpg"></c:url>'
				class="img-rounded" width="100%" height="200px">
			<div>
				<font>欢迎进入农田信息管理平台 !</font>
			</div>
			<br>
		</div>


		<div class="row">
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-12">
						<div class="Huialert-info"><font color="blue" size="50"><i class="Hui-iconfont">&#xe637;</i></font><i>当前可检测农田数量：3</i></div>
						<br>
						<div class="Huialert-info"><font color="blue" size="50"><i class="Hui-iconfont">&#xe62b;</i></font><i>当前用户数量：${userCount }</i></div>
					</div>
				</div>
				<!-- <div id="container" style="min-width: 550px; height: 400px; margin: 0 auto"></div> -->
			</div>
		
			<div class="col-md-8">
				<div class="panel panel-primary">
					<div class="panel-header">
						<h3>天气预报</h3>
					</div>
					<div class="panel-body">
							<span> 请选择城市： <select name="cityId" id="cityId">
									<option value="101180101">郑州</option>
									<option value="101010100">北京</option>
									<option value="101020100">上海</option>
									<option value="101030100">天津</option>
									<option value="101040100">重庆</option>
									<option value="101050101">哈尔滨</option>
									<option value="101060101">长春</option>
									<option value="101070101">沈阳</option>
									<option value="101080101">呼和浩特</option>
									<option value="101120101">济南</option>
									<option value="101190101">南京</option>
									<option value="101200101">武汉</option>
									<option value="101210101">杭州</option>
									<option value="101230101">福州</option>
									<option value="101240101">南昌</option>
									<option value="101250101">长沙</option>
									<option value="101260101">贵阳</option>
									<option value="101270101">成都</option>
									<option value="101280101">广州</option>
									<option value="101290101">昆明</option>
									<option value="101300101">南宁</option>
									<option value="101310101">海口</option>
									<option value="101320101">香港</option>
									<option value="101330101">澳门</option>
									<option value="101340101">台北</option>
							</select>
							<input type="submit" class="btn btn-primary radius" value="确定" onclick="getWeather()">
							</span> 
							<h4 id="cityName"></h4>
						<table class="table table-border table-bordered table-hover table-bg table-sort" id="weatherTable">
					<thead>
						<tr class="text-c" id="weatherTime"></tr>
					</thead>
					<tbody>
						<tr class="text-c"  id="weatherBody"></tr>
					</tbody>
				</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			getWeather();
			
		});
	</script>

	<script type="text/javascript">
		function getWeather(){
			var cityId = $("#cityId").val();
			$.ajax({
				url:'http://localhost:9090/FarmlandInformation/getWeather.action',
				type:'get',
				data:{'cityId':cityId},
				dataType:'json',
				success:function(data){
					
					var city = data.city;
					var weatherList = data.weather;
					
					// alert(city);
					// alert(weatherList);
					
					$("#cityName").html(city);
					$("#weatherTime").empty();
					$("#weatherBody").empty();
					weatherTitleHtml(weatherList);
					weatherBodyHtml(weatherList);
				}
			});
		}
		
		function weatherTitleHtml(weatherList){
			
			
			var table="";
			//debugger;
			for(var i=0; i<weatherList.length; i++){
				table+='<th>'+weatherList[i].dayTime+'</th>';
			}
			
			$("#weatherTime").append(table);
		}
		
		function weatherBodyHtml(weatherList){
			var table = "";
			
			for(var i=0; i<weatherList.length; i++){
				table+='<td><p>'+weatherList[i].weather + "</p><p>" + weatherList[i].temp + "</p><p>" + "风力：" + weatherList[i].wind + '</p></td>';
			}
			$("#weatherBody").append(table);
		}
		
	</script>

	<footer class="mt-20">
		<div class="footer">Copyright 2014162-农田信息管理系统</div>
	</footer>
	<!--_footer 作为公共模版分离出去-->
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>