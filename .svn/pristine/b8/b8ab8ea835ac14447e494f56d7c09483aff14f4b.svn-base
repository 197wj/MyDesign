/**
 * 课程创建页面的js
 */
//显示下一页
function showNext() {
	var courseName = $("#courseName").val();
	var length = Trim(courseName,'g').length;
	if(courseName.length<4){
		layer.msg('课程名称不能小于四个字符', {
			  time: 20000, //20s后自动关闭
			  btn: ['明白了']
			});
	}else {
		if(courseName.length  == 0){
			layer.msg('进入下一步前，请先填写课程名称', {
			  time: 20000, //20s后自动关闭
			  btn: ['明白了']
			});
		}else {
			if(length>=4){
				$("#two").show();
				$("#one").hide();
				$("#submitBtn").show();
				$("#nextBtn").hide();
				$("#lastBtn").show();	
			}else {
				layer.msg('课程名称请不要包含太多空格', {
					  time: 20000, //20s后自动关闭
					  btn: ['明白了']
					});
			}
		}
	}
		
}
//显示上一页
function showLast() {
	$("#two").hide();
	$("#one").show();
	$("#submitBtn").hide();
	$("#nextBtn").show();
	$("#lastBtn").hide();
}

//子页面调用的保存选择指导老师信息的方法
function selTeacherWrite(selTeacher) { 
	for (var int = 0; int < selTeacher.length; int++) {
		var tr = ["<tr>",
			"	<td>"+selTeacher[int].name+"</td>",
			"	<td>",
			"	<input id=\""+selTeacher[int].id+"\" class=\"btn btn-primary radius\" type=\"button\" value=\"点击移除授课老师\" onclick=\"delTeacher(this)\" /> ",
			"	</td>",
			"</tr>"].join("");
		$("#teacherTable").append(tr);
	}
	$("#courseTeacher").val(JSON.stringify(selTeacher));
	$("#selTutorDiv").show();
}

//删除选定指导教师
function delTeacher(btn) {
	var selTeacher = JSON.parse($("#courseTeacher").val());
	var id = $(btn).attr("id");
	for (var int = 0; int < selTeacher.length; int++) {
		if(id == selTeacher[int].id){
			selTeacher.splice(int,1);
		}
	}
	$("#courseTeacher").val(JSON.stringify(selTeacher));
	$(btn).parent().parent().remove();
}
