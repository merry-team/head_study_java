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
		url : "/xinzhizhu/category/save",
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
			categoryName : {
				required : true
			},
			categoryType : {
				required : true
			},
			content : {
				required : true
			},
			imageUrl : {
				required : true
			}
		},
		messages : {
			categoryName : {
				required : icon + "请输入分类名称"
			},
			categoryType : {
				required : icon + "请选择分类类型"
			},
			content : {
				required : icon + "请输入分类介绍"
			},
			imageUrl : {
				required : icon + "请上传分类的图片"
			}
		}
	})
}