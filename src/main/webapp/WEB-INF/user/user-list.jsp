<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<article class="cl pd-20">
	<div class="text-c">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户电话" id="phone" name="phone">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称" id="name" name="name">
		<button type="submit" class="btn btn-success radius" id="searchUser" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="50">ID</th>
					<th width="50">用户名</th>
					<th width="100">用户等级</th>
					<th width="100">手机号</th>
					<th width="100">注册时间</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody id="userList">
			</tbody>
		</table>
		<!-- 分页 -->
		<div style="width:70%;white-spacing:nowrap;margin:0 auto;"> 
			<div id="kkpager"></div> 
		</div>
	</div>
</article>
</div>
<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<script type="text/javascript">
	$(function(){
		
		//加载用户信息
		loadList(1,10,null,null);
	})
	function loadList(page,rows,phone,name){
		$.ajax({
			url:'../user/getUserByPage.action',
			type:'get',
			data:{'page':page,'rows':rows,'phone':phone,'name':name},
			dataType:'json',
			success:function(obj){
				var data = obj.rows;
				var count = obj.total;
				$("#userList").empty();
				$("#count").html(count);
				kkpagerHtml(count,rows,page,phone,name);
				if(data!=null){
					for(var i = 0;i<data.length;i++){
						listHtml(i+1,data[i]);
					}
				}
			}
		});
	}
	//回车按钮
	function keydownEvent() {
	     var e = window.event || arguments.callee.caller.arguments[0];
	     if (e && e.keyCode == 13 ) {
	     	document.getElementById("searchUser").click();
	     }
	};
	/**
	 * 点击搜索,
	 */
 	$("#searchUser").click(function(){
 		var phone = $("#phone").val();
 		var name = $("#name").val();
 		loadList(1,10,phone,name);
 	 }); 
	
	 /**
	  * 用户列表table
	  */
	 function listHtml(index,info){
		 
		 var table='';
		 	table+='<tr class="text-c">';
		 	table+='<td>'+index+'</td>';
		 	table+='<td>'+info.name+'</td>';
		 	table+='<td>'+(info.name=="admin"?"管理员":"普通用户")+'</td>';
		 	table+='<td>'+info.phone+'</td>';
		 	table+='<td>'+formatterDateTime(info.gmtCreate)+'</td>';
		 	table+='<td class="td-manage"><a style="text-decoration:none" class="ml-5" onClick="change_password(&apos;修改密码&apos;,&apos;../user/admin_updateUser.action?id='+info.id+'&apos;,'+info.id+',&apos;600&apos;,&apos;270&apos;)" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'+info.id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>'
		 	table+='</tr>';
		 	$("#logList").append(table);
	 	$("#userList").append(table);
	 }
	 /**
	  * kkpager分页工具
	  */
	 function kkpagerHtml(sum,rows,current,phone,name){
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
	        	 loadList(n,rows,phone,name);
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

<script type="text/javascript">

/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}

/*用户-删除*/
function member_del(obj,id){
	
	layer.confirm('确认要删除吗？',function(){
		
		$.ajax({
			url:'../user/admin_deleteUser.action', 
		    type:'get',
		    data:{'id':id},
		    dataType:'json',
			success:function(data){
		    	layer.msg('已删除！',{icon:1,time:1000});
		    	loadList(1,10,null,null);
			},
		    error:function(message) {
		    	alert(JSON.stringify(message));
		    }
		});
	});
}
</script> 
</body>
</html>