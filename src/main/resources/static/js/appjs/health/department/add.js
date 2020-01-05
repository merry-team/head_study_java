$().ready(function() {
	validateRule();
	setProvince();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/health/department/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.message)
			}

		}
	});
}


function setProvince() {
	$("#provinceId option").remove();
	var html = "<option value=''>--请选择省--</option>";
	$("#provinceId option").append(html);
	 
	$.ajax({
		cache : true,
		type : "POST",
		url : "/api/region/provinceList",
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.errno == 0) {
			    $.each(data.data,function(idx,item){	
			            html +="<option value="+item.id+" >"+ item.name +"</option> ";
			    });
			    $("#provinceId ").append(html);
			} else {
				$("#provinceId ").append(html);
			}

		}
	});
}

$("#provinceId").change(function(){
    if ($(this).val() == "") return;
    $("#cityId option").remove();
    //var code = $(this).find("option:selected").val();
    var code = $(this).find("option:selected").text();
    console.info($(this).find("option:selected").text())
    var html = "<option value=''>--请选择市---</option>";
    $("#cityId option").append(html);
    
	$.ajax({
		cache : true,
		type : "POST",
		url : "/api/region/cityList",
		async : true,
		data : "proviceName="+code,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.errno == 0) {
			    $.each(data.data,function(idx,item){	
			            html +="<option value="+item.id+" >"+ item.name +"</option> ";
			    });
			    $("#cityId ").append(html);
			} else {
				$("#cityId ").append(html);
			}

		}
	});
});

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			companyId : {
				required : true
			},
			departmentName : {
				required : true
			},
			timeId : {
				required : true
			},
			provinceId : {
				required : true
			},
			cityId : {
				required : true
			},
			address : {
				required : true
			}
		},
		messages : {
			companyId : {
				required : icon + "请选择体检机构"
			},
			departmentName : {
				required : icon + "请输入分公司的名称"
			},
			provinceId : {
				required  : icon + "请选择分公司所在省"
			},
			cityId : {
				required  : icon + "请选择分公司所在市"
			},
			address : {
				required  : icon + "请输入分公司的地址"
			}
		}
	})
}