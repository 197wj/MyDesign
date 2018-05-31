<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>

<!--/meta 作为公共模版分离出去-->
<%@ include file="../common/_meta.jsp" %>

<title>添加农作物</title>

</head>
<body>
<article class="cl pd-20">

	<div class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>农田编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="farmlandId" name="farmlandId">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>农田湿度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="farmlandMoisture" name="farmlandMoisture">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>空气湿度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="airMoisture" name="airMoisture">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>空气温度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="airTemp" name="airTemp">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>检测时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'testTime\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="testTime" name="testTime" class="input-text Wdate" placeholder="默认当前时间" style="width:170px;">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button class="btn btn-primary radius" type="submit" onclick="addFarmland()">提交</button>
			</div>
		</div>
	</div>
</article>

<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			farmlandId:{
				required:true,
			},
			farmlandMoisture:{
				required:true,
			},
			airMoisture:{
				required:true,
			},
			airTemp:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});


function addFarmland(){
	$.ajax({
		url:'../similar/insertFarmland.action',
		type:'post',
		data:{'farmlandId':$('#farmlandId').val(),'farmlandMoisture':$('#farmlandMoisture').val(),'airMoisture':$('#airMoisture').val(),'airTemp':$('#airTemp').val(),'testTime':$('#testTime').val()},
		dataType:'json',
		success:function(obj){
			var infoId = obj;
			layer_show('详情图','../similar/selectFarmlandInfo.action?id='+infoId,'','530');
		}
	});
}

	








</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>