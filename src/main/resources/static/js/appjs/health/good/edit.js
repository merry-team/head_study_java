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
	var content = $("#goodsDesc").val();

	$('#content_sn').summernote('code', content);
    $("#counterPrice").val($("#counterPrice").val()/100);
    $("#retailPrice").val($("#retailPrice").val()/100);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var content_sn = $("#content_sn").summernote('code');
	$("#goodsDesc").val(content_sn);
    $("#counterPrice").val($("#counterPrice").val()*100);
    $("#retailPrice").val($("#retailPrice").val()*100);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/health/good/update",
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
				goodName : {
					required : true
				},
				goodsBrief : {
					required : true
				},
				goodsDesc : {
					required : true
				},
				type : {
					required : true
				},
				counterPrice : {
					required : true,
					number : true ,
					minlength : 1 ,
					maxlength : 9
				},
				retailPrice : {
					required : true,
					number : true ,
					minlength : 1 ,
					maxlength : 9
				},
				primaryPicUrl : {
					required : true
				},
				listPicUrl : {
					required : true
				}		
			},
			messages : {
				goodName : {
					required : icon + "请输入商品名称"
				},
				goodsBrief : {
					required : icon + "请商品简介"
				},
				goodsDesc : {
					required : icon + "请输入商品描述"
				},
				type : {
					required :  icon + "请选择商品类别"
				},
				counterPrice : {
					required  :  icon + "请输入商品零售价格",
					number : icon + "请输入数字",
					minlength : icon + "你没有输入价格",
					maxlength : icon + "价格有点贵"
				},
				retailPrice : {
					required  :  icon + "请选择商品实际价格",
					number : icon + "请输入数字",
					minlength : icon + "你没有输入价格",
					maxlength : icon + "价格有点贵"
				},
				primaryPicUrl : {
					required  :  icon + "请上传商品主图"
				},
				listPicUrl : {
					required :  icon + "请上传商品列表图"
				}	
			}
		})
	}