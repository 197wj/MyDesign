<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>农作物管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 数据管理 <span class="c-gray en">&gt;</span> 农作物数据 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<input type="text" class="input-text" style="width:250px" placeholder="输入农作物名称" id="cropName" name="cropName">
		<button type="button" class="btn btn-success radius" id="seach" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索 </button>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		<a href="javascript:;" onclick="add('添加信息','../crop/toAddCrop.action','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加数据</a>
		</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="80">ID</th>
					<th width="100">名称</th>
					<th width="100">最适土壤湿度</th>
					<th width="100">最适空气湿度</th>
					<th width="100">最适空气温度 ℃</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody id="cropList">
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
		//location.reload();
		loadCrop(1,15);
		layer_close();
	})
	function loadCrop(page,rows,cropName){
		$.ajax({
			url:'../crop/getCropByPage.action',
			type:'get',
			data:{'page':page,'rows':rows,'cropName':cropName},
			dataType:'json',
			success:function(obj){
				var data = obj.rows;
				var count = obj.total;
				$("#cropList").empty();
				$("#count").html(count);
				kkpagerHtml(count,rows,page,cropName);
				if(data!=null){
					for(var i = 0;i<data.length;i++){
						cropListHtml(i+1,data[i]);
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
		var cropName = $("#cropName").val();
		loadCrop(1,15,cropName);
	 });
	
	 /**
	  * 日志列表table
	  */
	 function cropListHtml(index,info){
	 	var table='';
	 	table+='<tr class="text-c">';
	 	table+='<td><input type="checkbox" value="'+info.id+'" name="checkedCrop"></td>';
	 	table+='<td>'+index+'</td>';
	 	table+='<td>'+info.cropName+'</td>';
	 	table+='<td>'+info.cropLandMoisture+'</td>';
	 	table+='<td>'+info.cropAirMoisture+'</td>';
	 	table+='<td>'+info.cropAirTemp+'</td>';
	 	table+='<td><a style="text-decoration:none" class="ml-5" onClick="change_crop(&apos;编辑&apos;,&apos;../crop/updateCrop.action?id='+info.id+'&apos;,'+info.id+',&apos;&apos;,&apos;&apos;)" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a></td>';
	 	table+='</tr>';
	 	$("#cropList").append(table);
	 }
	 
	 
	 // 批量删除日志
	 function datadel(){
		 
		// 1. 获取所有被选中的 input 元素
		   var inputs = $("input[name='checkedCrop']:checked");
		   
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
				url:'../crop/admin_deleteCrops.action', 
			    type:'POST',
                contentType:'application/json;charset=utf8',
			    data:JSON.stringify({
			    	'ids':ids
			    }),
			    dataType:'json',
				success:function(data){
			    	// alert(JSON.stringify(data));
			    	layer.msg('已删除!',{icon:1,time:1000});
					loadCrop(1,15);
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
	 function kkpagerHtml(sum,rows,current,cropName){
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
	        	 loadCrop(n,rows,cropName);
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
	 
	  function add(title,url,id,w,h){
			layer_show(title,url,w,h);
		}
	  
	  function change_crop(title,url,id,w,h){
			layer_show(title,url,w,h);
		}
	
</script>


</body>
</html>