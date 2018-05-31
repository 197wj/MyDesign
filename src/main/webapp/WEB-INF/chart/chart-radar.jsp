<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>详情图</title>
</head>
<body>

<div id="container" style="width:650px;height:400px;margin: 0 auto"></div>


<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<%
List<Map.Entry<String, Double>> list = (List<Map.Entry<String, Double>>)request.getAttribute("similarCropList");
%>

<script type="text/javascript">
$(function () {
	
	Highcharts.chart('container', {
		 chart: {
		        //polar: true,
		        type: 'line'
		    },

		    title: {
		        text: '最适农作物相似度',
		        x: -80
		    },

		    pane: {
		        size: '80%'
		    },

		    xAxis: {
		        categories: ['<%=list.get(0).getKey() %>', '<%=list.get(1).getKey() %>', '<%=list.get(2).getKey() %>', '<%=list.get(3).getKey() %>',
		        	'<%=list.get(4).getKey() %>', '<%=list.get(5).getKey() %>'],
		        tickmarkPlacement: 'on',
		        lineWidth: 0
		    },

		    yAxis: {
		        gridLineInterpolation: 'polygon',
		        lineWidth: 0,
		        min: 0
		    },

		    tooltip: {
		        shared: true,
		        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>'
		    },

		    legend: {
		        align: 'right',
		        verticalAlign: 'top',
		        y: 70,
		    },

		    series: [{
		        name: '相似度',
		        data: [<%=list.get(0).getValue() %>, <%=list.get(1).getValue() %>, <%=list.get(2).getValue() %>, 
		        		<%=list.get(3).getValue() %>, <%=list.get(4).getValue() %>, <%=list.get(5).getValue() %>],
		        pointPlacement: 'on'
		    }]
	});
});
</script>

</body>
</html>