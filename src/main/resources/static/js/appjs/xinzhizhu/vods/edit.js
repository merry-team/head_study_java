$().ready(function() {
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	var content = $("#content").val();

	$('#content_sn').summernote('code', content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xinzhizhu/vods/update",
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
			vodTitle : {
				required : true
			},
			content : {
				required : true
			},
			courseId : {
				required : true
			},
			fileUrl : {
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
			},
			orderSort : {
				required : true ,
				number : true ,
				minlength : 1 ,
				maxlength : 4
			}
		},
		messages : {
			vodTitle : {
				required : icon + "请输入视频名称"
			},
			content : {
				required : icon + "请输入视频简介"
			},
			courseId : {
				required : icon + "请选择课程分类"
			},
			fileUrl : {
				required : icon + "请上传视频文件"
			},
			imageUrl : {
				required :  icon + "请输入视频图片"
			},
			coursePrice_1 : {
				required : icon + "请输入课程价格",
				number : icon + "请输入数字",
				minlength : icon + "你没有输入价格",
				maxlength : icon + "价格有点贵"
			},
			orderSort : {
				required : icon + "请输入排序",
				number : icon + "请输入数字",
				minlength : icon + "你没有输入排序",
				maxlength : icon + "排序最大值9999"
			}
		}
	})
}