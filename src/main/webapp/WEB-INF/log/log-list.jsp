<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>日志管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统日志 <span class="c-gray en">&gt;</span> 日志管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmin" class="input-text Wdate" style="width:170px;">
				-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmax" class="input-text Wdate" style="width:170px;">
		<button type="button" class="btn btn-success radius" id="seach" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索日志</button>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		</span>
	</div>
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25">选择</th>
					<th width="80">ID</th>
					<th width="100">操作人员</th>
					<th width="100">操作类型</th>
					<th width="100">操作对象</th>
					<th width="100">操作路径</th>
					<th width="100">用户ip</th>
					<th width="100">操作时间</th>
				</tr>
			</thead>
			<tbody id="logList">
			</tbody>
		</table>
		<!-- 分页 -->
		<div style="width:70%;white-spacing:nowrap;margin:0 auto;"> 
			<div id="kkpager"></div> 
		</div>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<script type="text/javascript">
	$(function(){
		loadLog(1,15);
	})
	function loadLog(page,rows,startTime,endTime){
		$.ajax({
			url:'../log/getLogByPage.action',
			type:'get',
			data:{'page':page,'rows':rows,'startTime':startTime,'endTime':endTime},
			dataType:'json',
			success:function(obj){
				var data = obj.rows;
				var count = obj.total;
				$("#logList").empty();
				$("#count").html(count);
				kkpagerHtml(count,rows,page,startTime,endTime);
				if(data!=null){
					for(var i = 0;i<data.length;i++){
						logListHtml(i+1,data[i]);
					}
				}
			}
		});
	}
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
		var startTime = $("#logmin").val();
		var endTime = $("#logmax").val();
		loadLog(1,15,startTime,endTime);
	 });
	
	 /**
	  * 日志列表table
	  */
	 function logListHtml(index,info){
	 	var table='';
	 	table+='<tr class="text-c">';
	 	table+='<td><input type="checkbox" value="'+info.logId+'" name="checkedLog"></td>';
	 	table+='<td>'+index+'</td>';
	 	table+='<td>'+info.userName+'</td>';
	 	table+='<td>'+info.operType+'</td>';
	 	table+='<td>'+info.operObject+'</td>';
	 	table+='<td>'+info.operPath+'</td>';
	 	table+='<td>'+info.ipAddress+'</td>';
	 	table+='<td>'+formatterDateTime(info.addTime)+'</td>';
	 	table+='</tr>';
	 	$("#logList").append(table);
	 }
	 
	 
	 // 批量删除日志
	 function datadel(){
		 
		// 1. 获取所有被选中的 input 元素
		   var inputs = $("input[name='checkedLog']:checked");
		   
		   // 2. 定义一个数组，存放选中的 input 对应的 id
		   var ids = new Array();
		   
		   // 3. 遍历取出每一个 id，放入 ids 数组
		   inputs.each(function(){
			   
			   // 取出每一个 input 的 value 值，放入 ids 数组
			   ids.push($(this).val());
		   });
		   
		   // 执行删除请求
		   loadDelete(ids);
	 }
	 
	 function loadDelete(ids){
		 
		layer.confirm('确定删除吗？',function(){
			$.ajax({
				url:'../log/admin_deleteLogs.action', 
			    type:'POST',
                contentType:'application/json;charset=utf8',
			    data:JSON.stringify({
			    	'ids':ids
			    }),
			    dataType:'json',
				success:function(data){
			    	// alert(JSON.stringify(data));
			    	layer.msg('已删除!',{icon:1,time:1000});
					loadLog(1,15);
				},
			    error:function(message) {
			    	alert(JSON.stringify(message));
			    }
			});
		});
	 }
	 
	 
	 
	 
	 /**
	  * kkpager分页工具
	  */
	 function kkpagerHtml(sum,rows,current,startTime,endTime){
	     var totalPage = Math.ceil(sum/rows);//总页数总数据条数/每页显示数 向上取整  
	     if(current == 0){  
	        current = 1;  
	     }  
	     //初始化函数  
	     kkpager.generPageHtml({  
	         pno : current, //当前页数  
	         total : totalPage,//总页数  
	         totalRecords : sum, //总数据条数  
	         mode:'click', //这里设置为click模式  
	         isShowTotalRecords:true,
	         //点击页码的函数，这里发送ajax请求后台  
	         click:function(n){
	        	 loadLog(n,rows,startTime,endTime);
	         	this.selectPage(n); //手动条用selectPage进行页码选中切换 
	         	return false;
	        },   
	 	},true);  
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
	     return y + '-' + m + '-' + d+' '+ h+':'+minute+':'+second;  
	 }
	
</script>


</body>
</html>