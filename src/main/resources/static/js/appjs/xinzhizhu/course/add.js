$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var price = $("#coursePrice_1").val();
	$("#coursePrice").val(parseFloat(price)*100);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xinzhizhu/course/save",
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
			courseName : {
				required : true
			},
			categoryId : {
				required : true
			},
			content : {
				required : true
			},
			imageUrl : {
				required : true
			},
			coursePrice_1 : {
				required : true ,
				number : true ,
				minlength : 1 ,
				maxlength : 9
			}
		},
		messages : {
			courseName : {
				required : icon + "请输入课程名称"
			},
			categoryId : {
				required : icon + "请选择课程分类"
			},
			content : {
				required : icon + "请输入课程介绍"
			},
			imageUrl : {
				required : icon + "请上传课程的图片"
			},
			coursePrice_1 : {
				required : icon + "请输入课程价格",
				number : icon + "请输入数字",
				minlength : icon + "你没有输入价格",
				maxlength : icon + "价格有点贵"
			}
		}
	})
}