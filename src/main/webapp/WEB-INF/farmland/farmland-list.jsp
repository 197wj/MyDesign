<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>我的桌面</title>
</head>
<body style="background:#f1f1f1">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据管理 <span class="c-gray en">&gt;</span> 农田数据 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container cl clearfix" style="box-sizing:border-box">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'testmax\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="testmin" class="input-text Wdate" style="width:170px;">
				-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'testmin\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="testmax" class="input-text Wdate" style="width:170px;">
		<input type="text" class="input-text" style="width:150px" placeholder="输入农田编号" id="farmlandId" name="farmlandId">
		<button type="submit" class="btn btn-success radius" id="seach" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		<button type="button" class="btn btn-success radius" id="downloadExcel" name=""><i class="Hui-iconfont">&#xe640;</i> 下载</button>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		<a href="javascript:;" onclick="farmland_add('添加检测数据','toAddFarmland.action','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加数据</a>
		</span>
		
	</div>
	
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="80">ID</th>
					<th width="100">农田编号</th>
					<th width="100">检测时间</th>
					<th width="100">农田湿度</th>
					<th width="100">湿度等级</th>
					<th width="100">空气温度</th>
					<th width="100">空气湿度</th>
					<th width="100">详情</th>
				</tr>
			</thead>
			<tbody id="farmlandInfoList">
			</tbody>
		</table>
		<!-- 分页 -->
		<div style="width:70%;white-spacing:nowrap;margin:0 auto;"> 
			<div id="kkpager"></div> 
		</div>
	</div>
	
</div>
<script type="text/javascript">
	$(function(){
		
		loadfarmlandInfo(1,15,null,null,null);
	})
	
	
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
		var startTime = $("#testmin").val();
		var endTime = $("#testmax").val();
		var farmlandId = $("#farmlandId").val();
		loadfarmlandInfo(1,15,farmlandId,startTime,endTime);
	 });
	
	function loadfarmlandInfo(page,rows,farmlandId,startTime,endTime){
		//debugger;
		$.ajax({
			url:'../farmland/getFarmlandByPage.action',
			type:'get',
			//contentType:'application/json;charset=utf8',
			data:{'page':page,'rows':rows,'farmlandId':farmlandId,'startTime':startTime,'endTime':endTime},
			dataType:'json',
			success:function(obj){
				var data = obj.rows;
				var count = obj.total;
				$("#farmlandInfoList").empty();
				$("#count").html(count);
				kkpagerHtml(count,rows,page,farmlandId,startTime,endTime);
				if(data!=null){
					for(var i = 0;i<data.length;i++){
						farmlandInfoListHtml(i+1,data[i]);
					}
				}
			}
		});
	}
	
	function farmlandInfoListHtml(index, info){
		
		var fmType = "";
		
		if (info.farmlandMoisture!=0) {
			if (info.farmlandMoisture<0.25) {
				fmType = '<font color="red">干旱</font>';
			}else if (info.farmlandMoisture<0.45) {
				fmType = "中湿度";
			}else {
				fmType = '<font color="blue">高湿度</font>';
			}
		}
		
		var table='';
	 	table+='<tr class="text-c">';
	 	table+='<td><input type="checkbox" value="'+info.id+'" name="checkedInfo"></td>';
	 	table+='<td>'+index+'</td>';
	 	table+='<td>'+info.farmlandId+'</td>';
	 	table+='<td>'+formatterDateTime(info.testTime)+'</td>';
	 	table+='<td>'+info.farmlandMoisture+'</td>';
	 	table+='<td id="fmType">'+fmType+'</td>';
	 	table+='<td>'+info.airTemp+'</td>';
	 	table+='<td>'+info.airMoisture+'</td>';
	 	table+='<td><a style="text-decoration:none" class="ml-5" onClick="farmland_radar_show(&apos;详情图&apos;,&apos;../similar/selectFarmlandInfo.action?id='+info.id+'&apos;,&apos;&apos;,&apos;510&apos;)" href="javascript:;" title="详情"><i class="Hui-iconfont">&#xe6c6;</i></a></td>';
	 	table+='</tr>';
	 	$("#farmlandInfoList").append(table);
	}
	
	// 点击下载
	$("#downloadExcel").click(function(){
		var startTime = $("#testmin").val();
		var endTime = $("#testmax").val();
		var farmlandId = $("#farmlandId").val();
		location.href='../download.action?farmlandId='+farmlandId+'&startTime='+startTime+'&endTime='+endTime;
	 });
	
	// 批量删除数据
	 function datadel(){
		 
		// 1. 获取所有被选中的 input 元素
		   var inputs = $("input[name='checkedInfo']:checked");
		   
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
		//debugger;
		layer.confirm('确定删除吗？',function(){
			$.ajax({
				url:'../farmland/admin_deleteFarmlands.action', 
			    type:'POST',
               	contentType:'application/json;charset=utf8',
			    data:JSON.stringify({
			    	'ids':ids
			    }),
			    dataType:'json',
				success:function(data){
			    	// alert(JSON.stringify(data));
			    	layer.msg('已删除!',{icon:1,time:1000});
			    	loadfarmlandInfo(1,15);
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
	 function kkpagerHtml(sum,rows,current,farmlandId,startTime,endTime){
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
	        	loadfarmlandInfo(n,rows,farmlandId,startTime,endTime);
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

<!-- 图形显示 -->
<script>

// 详情--返回相似度
function farmland_radar_show(title,url,w,h){
	
	layer_show(title,url,w,h);
}

// 添加 -- 返回相似度
function farmland_add(title,url,w,h){
	
	layer_show(title,url,w,h);
}

</script>
<footer class="mt-20">
	<div class="footer">Copyright 2014162-农田信息管理系统</div>
</footer>
<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>
</body>
</html>