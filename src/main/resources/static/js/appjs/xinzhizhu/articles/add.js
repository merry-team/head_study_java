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
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xinzhizhu/articles/save",
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
				articleTitle : {
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
				}				
			},
			messages : {
				articleTitle : {
					required : icon + "请输入文章题目"
				},
				categoryId : {
					required : icon + "请选择分类名称"
				},
				content : {
					required : icon + "请输入文章内容"
				},
				imageUrl : {
					required :  icon + "请输入文章图片"
				}
			}
	})
}