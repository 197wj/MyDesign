<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../common/_meta.jsp" %>

<title>修改农作物信息 </title>

</head>
<body>
<article class="cl pd-20">
	<form action="../crop/updateCropInfo.action" method="post" class="form form-horizontal" id="form-change-password">
		
		<input type="hidden" value="${changedCrop.id }" id="id" name="id">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>作物名称：</label>
			<div class="formControls col-xs-8 col-sm-9"> ${changedCrop.cropName } </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>最适农田湿度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${changedCrop.cropLandMoisture }" placeholder="" id="cropLandMoisture" name="cropLandMoisture">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>最适空气湿度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${changedCrop.cropAirMoisture }" placeholder="" id="cropAirMoisture" name="cropAirMoisture">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>最适空气温度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${changedCrop.cropAirTemp }" placeholder="" id="cropAirTemp" name="cropAirTemp">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<%@ include file="../common/_footer.jsp" %>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.page.js"></script> 
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			cropLandMoisture:{
				required:true,
			},
			cropAirMoisture:{
				required:true,
			},
			cropAirTemp:{
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
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>