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
		url : "/health/users/save",
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

function checkidcard(num){
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	else{
		//alert("请输入15或18位身份证号,您输入的是 "+len+ "位"); 
		return false;
	}
	var a = num.match(re);
	if (a != null){
		if (len==15){
			var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}else{
			var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}
		if (!B){
			//alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); 
			return false;
		}
	}
 
	return true;
} 
//添加验证方法 (身份证号码验证)
jQuery.validator.addMethod("isIdCardNo", function(value, element) {   
	return this.optional(element) || checkidcard(value);   
}, "请正确输入您的身份证号码"); 

//自定义手机号验证
jQuery.validator.addMethod("isphoneNum", function(value, element) {
   // debugger
    var length = value.length;
    var mobile = /^1[3|4|5|6|7|8|9]{1}[0-9]{9}$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			idcard : {
				required : true,
				isIdCardNo: true
			},
			mobile : {
				required : true,
                maxlength:11,
                maxlength:11,
                isphoneNum:true
			},
			gender : {
				required : true,
			},
			marry : {
				required : true,
			},
			type : {
				required : true,
			},
			companyId : {
				required : true,
			},
			goodId : {
				required : true,
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			},
			idcard : {
				required : icon + "请输入身份证号码",
				isIdCardNo : icon + "请输入正确的身份证号码",
			},
			mobile :{
	            required: icon + "请输入手机号",	
	            maxlength: icon + "请填写11位的手机号",
	            minlength: icon + "请填写11位的手机号",
	            isphoneNum: icon + "请填写正确的手机号码"
			},
			gender : {
				required : icon + "请选择性别"
			},
			marry : {
				required : icon + "请选择婚否"
			},
			type : {
				required : icon + "请选择付费类型"
			},
			companyId : {
				required  : icon + "请选择员工企业"
			},
			goodId : {
				required : icon + "请选择体检套餐"
			}
		}
	})
}

$("#idcard").blur(function(){
	var idnumber = $("#idcard").val();
	if(checkidcard(idnumber)){
		 $("#gender option").remove();
		 var html = "<option value=''>---请选性别---</option>";
		if(parseInt(idnumber.substr(16,1))%2==1){
			html +="<option value=0 >女</option> ";
			html +="<option value=1 selected=selected >男</option> ";
		    $("#gender").append(html);
		    //$("#gender").attr("disabled","disabled");
		}else{
			html +="<option value=0 selected=selected >女</option>";
			html +="<option value=1  >男</option> ";
		    $("#gender").append(html);	
		    //$("#gender").attr("disabled","disabled");
		}
	}
});

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
