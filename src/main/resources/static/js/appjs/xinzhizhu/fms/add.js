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
//	alert('|'+$("#fileUrl").val()+'|');
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xinzhizhu/fms/save",
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
			fmTitle : {
				required : true
			},
			content : {
				required : true
			},
			courseId : {
				required : true
			},
			fileUrl : {
				required : true,
				minlength : 8 
			},
			imageUrl : {
				required : true
			}	
		},
		messages : {
			fmTitle : {
				required : icon + "请输入Fm名称"
			},
			content : {
				required : icon + "请输入FM简介"
			},
			courseId : {
				required : icon + "请选择Fm分类"
			},
			fileUrl : {
				required : icon + "请上传FM音频",
				minlength : icon + "请上传FM音频"
			},
			imageUrl : {
				required : icon + "请输入FM图图片"
			}	
			
		}
	})
}