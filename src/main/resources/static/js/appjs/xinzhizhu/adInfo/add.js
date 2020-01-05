$().ready(function() {
	validateRule();
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
		url : "/xinzhizhu/adInfo/save",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			adPositionId : {
				required : true
			},
			imageUrl : {
				required : true
			},
			content : {
				required : true
			},
			endTime : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入广告名称"
			},
			adPositionId : {
				required : icon + "请选择广告位置"
			},
			imageUrl : {
				required : icon + "请上传广告图片"
			},
			content : {
				required : icon + "请输入广告内容"
			},
			endTime : {
				required : icon + "请选择广告结束的时间"
			}
		}
	})
}