/**
 * 课程创建页面的js
 */
//显示下一页
function showNext() {
	var courseName = $("#courseName").val();
	if(courseName.length  == 0){
		layer.msg('进入下一步前，请先填写课程名称', {
		  time: 20000, //20s后自动关闭
		  btn: ['明白了']
		});
	}else {
		$("#two").show();
		$("#one").hide();
		$("#nextBtn").hide();
		$("#lastBtn").show();
	}
}
//显示上一页
function showLast() {
	$("#two").hide();
	$("#one").show();
	$("#nextBtn").show();
	$("#lastBtn").hide();
}

//子页面调用的保存选择指导老师信息的方法
function selTeacherWrite(selTeacher) { 
	$("#teacherTable").empty();
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
	$("#newcourseTeacher").val(JSON.stringify(selTeacher));
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
	$("#newcourseTeacher").val(JSON.stringify(selTeacher));
	$("#courseTeacher").val(JSON.stringify(selTeacher));
	$(btn).parent().parent().remove();
}
