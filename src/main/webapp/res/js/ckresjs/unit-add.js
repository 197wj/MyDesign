/**
 * 课程创建页面的js
 */
//添加章节
function addUnit(btn) {
	var resInfo = null;
	if(units == 0 && infoList.length == 0 ){
		resInfo = thisUnitInfo.resInfo;
		var check = 0;
		var id = 0;
		for (var int = 0; int < resInfo.length; int++) {
			id = "#res-sort"+units+int;
			if($(id).val() == 0){
				check = "#res-sort"+units+int;
			}
		}
		if(check != 0){
			layer.tips('请输入资源排序值', id);
			return false;
		}else {
			for (var int = 0; int < resInfo.length; int++) {
				id = "#res-sort"+units+int;
				resInfo[int].resSort = $(id).val();
				resInfo[int].filename = $("#unitResNames"+units+int).val();
			}
			infoList.push(thisUnitInfo);
			thisUnitInfo = {};
		}
	}else {
		var checkUnit = 0;
		for (var int = 0; int < infoList.length; int++) { debugger
			if (infoList[int].unitNum == units) {
				checkUnit = 1;
			}
		}
		if(checkUnit == 0){
			var resInfo = null;
			resInfo = thisUnitInfo.resInfo;
			var check = 0;
			var id = 0;
			for (var int = 0; int < resInfo.length; int++) {
				id = "#res-sort"+units+int;
				if($(id).val() == 0){
					check = "#res-sort"+units+int;
				}
			}
			if(check != 0){
				layer.tips('请输入资源排序值', id);
				return false;
			}else {
				for (var int = 0; int < resInfo.length; int++) {
					id = "#res-sort"+units+int;
					resInfo[int].resSort = $(id).val();
					resInfo[int].filename = $("#unitResNames"+units+int).val();
				}
				infoList.push(thisUnitInfo);
				thisUnitInfo = {};
			}
		}
	}
	units++;
	$(btn).parent("td").children().hide();
	var unit = "<tr>"
			+ "<td><input type=\"text\" name=\"unitnames\" id=\"\""
			+ " placeholder=\"输入章节名\" value=\"\" class=\"input-text radius\""
			+ "style=\"width: 90%\"></td>"
			+ "<td id=\"unitResList"
			+ units
			+ "\"></td>"
			+["<td id=\"unitHandle"+units+"\"><input type=\"button\" onclick=\"upload(this)\" class=\"btn btn-secondary size-S radius\" value=\"上传资源\">",
				"<input type=\"button\"onclick=\"delUnit();\" class=\"btn btn-secondary size-S radius\" style=\"margin-top: 10px\" value=\"移除当前章节列\">",
				"</td>"].join("");
	$("#unitTable").append(unit);
}

//删除章节
function delUnit() {
	layer.confirm('时候确认删除该列，删除之后无法恢复？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			for (var int = 0; int < infoList.length; int++) {
				if (infoList[int].unitNum == units) {
					infoList.splice(int, 1);
				}
			}
			$("#unitTable").children('tr').eq(units--).remove();
			$("#unitHandle"+units).children().show();
			layer.msg('已删除', {icon: 1});
		}, function(){
		});
}


function resInfoWrite(response) {//接受上传的章节资源信息，暂时保存数据到thisUnitInfo中，当上传资源成功后，显示增加列按钮
	var thisUnitResList = [];
	for (var int = 0; int < response.length; int++) {
		var thisUnitJson = {};
		var s = "<input type=\"text\" onclick=\"textClick(this)\" name=\"\" id=\"unitResNames"
				+ units	
				+ int
				+ "\""
				+ " placeholder=\"\" value=\""
				+ response[int].filename
				+ "\" class=\"input-text radius\""
				+ "style=\"width: 90%\">"
				+ [ "<input type=\"text\" name=\"\" id=\"res-sort"
					+ units
					+ int
					+ "\" onclick=\"sortClick(this)\" placeholder=\"\" value=\"0\" class=\"input-text radius\" style=\"width: 10%\">" ]
					.join("");
		$("#" + tdId).append(s);
		thisUnitJson.filename = response[int].filename;
		thisUnitJson.fileId = response[int].fileId;
		thisUnitResList.push(thisUnitJson);
	}
	if(thisUnitResList.length > 0){
		thisUnitInfo.resInfo = thisUnitResList;
		var saveBtn = ["<input type=\"button\" onclick=\"addUnit(this);\" class=\"btn btn-secondary size-S radius\" style=\"margin-top: 10px\" value=\"保存并增加章节列\"> "].join("");
		$("#unitHandle"+units).append(saveBtn);
	}
}

function textClick(text) {
	textId = $(text).attr("id");
	layer.tips('请在此输入该资源名称，用来决定该资源在章节中的展示名称，请认真填写', '#' + textId, {
		  tips: [1, '#3595CC'],
		  time: 4000
		});
}

function sortClick(text) {
	textId = $(text).attr("id");
	layer.tips('请在此输入该资源排序值，用来决定该资源在章节中的排列顺序，排序时从大到小排列', '#' + textId);
}

