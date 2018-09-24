//默认校验规则
//(1)required:true               必输字段
//(2)remote:"check.php"          使用ajax方法调用check.php验证输入值
//(3)email:true                  必须输入正确格式的电子邮件
//(4)url:true                    必须输入正确格式的网址
//(5)date:true                   必须输入正确格式的日期 日期校验ie6出错，慎用
//(6)dateISO:true                必须输入正确格式的日期(ISO)，例如：2009-06-23，1998/01/22 只验证格式，不验证有效性
//(7)number:true                 必须输入合法的数字(负数，小数)
//(8)digits:true                 必须输入整数
//(9)creditcard:                 必须输入合法的信用卡号
//(10)equalTo:"#field"           输入值必须和#field相同
//(11)accept:                    输入拥有合法后缀名的字符串（上传文件的后缀）
//(12)maxlength:5                输入长度最多是5的字符串(汉字算一个字符)
//(13)minlength:10               输入长度最小是10的字符串(汉字算一个字符)
//(14)rangelength:[5,10]         输入长度必须介于 5 和 10 之间的字符串")(汉字算一个字符)
//(15)range:[5,10]               输入值必须介于 5 和 10 之间
//(16)max:5                      输入值不能大于5
//(17)min:10                     输入值不能小于10


//自定义规则
(function ($)
{
    $.validator.addMethod(
            "notnull",
            function (value, element)
            {
                if (!value) return true;
                return !$(element).hasClass("l-text-field-null");
            },
            "不能为空"
    );
    //数字
    jQuery.validator.addMethod("nums", function (value, element)
    {
       return this.optional(element) || /^[0-9]*$/g.test(value);
    }, "只能为数字");  
    //数字
    jQuery.validator.addMethod("nums10", function (value, element)
    {
       return this.optional(element) || /^([1-9]|10)$/g.test(value);
    }, "只能为数字1-10");  
    //数字
    jQuery.validator.addMethod("nums100", function (value, element)
    {
       return this.optional(element) || /^([1-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100)$/g.test(value);
    }, "只能为0-100数字最多两位小数"); 
    //数字
    jQuery.validator.addMethod("money", function (value, element)
    {
       return this.optional(element) || /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/g.test(value);
    }, "请输入正确的金额格式");  
    jQuery.validator.addMethod("tax", function (value, element)
    	    {
    	       return this.optional(element) || /^(([0])|([1-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,2})))$/g.test(value);
    	    }, "请输入正确的税率格式，最多两位小数");  
    // 账户名
    jQuery.validator.addMethod("account", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
    }, "账户名格式不正确");
    // 正整数
    jQuery.validator.addMethod("positive_int", function (value, element)
    {
        var tel = /^[0-9]*[1-9][0-9]*$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入正整数");
    //英文名验证
    jQuery.validator.addMethod("ywmyz", function (value, element)
    	    {
		    	if(value == null || value == "")  
		            return false || this.optional(element);  
		        var obj = this.optional(element);  
		        var flag = 1;
		        if(!/^[A-Za-z]+$/.test(value)) {
		        	flag = 0;
		        }
	            return flag == 0 ? false : true
    	    }, "只能为英文");
    
    //字母数字
    jQuery.validator.addMethod("alnum", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
    }, "只能包括英文字母和数字");

    //字母数字
    jQuery.validator.addMethod("letter", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z]+$/.test(value);
    }, "只能输入英文字母");
    
   // 手机号码验证      
    jQuery.validator.addMethod("isMobile", function(value, element) {       
	    var length = value.length;   
	    var mobile = /^[1][23578][0-9]{9}$/;   
	    return this.optional(element) || (length == 11 && mobile.test(value));       
    }, "请正确填写您的手机号码");   
    
  //整数
    jQuery.validator.addMethod("zhengshu", function (value, element)
    {
       return this.optional(element) || /^-?[1-9]\d*$/g.test(value);
    }, "只能为整数");
    
    
    // 电话号码验证   
    jQuery.validator.addMethod("telephone", function (value, element)
    {
        var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写电话号码(区号-号码)");
    
    // 电话号码包括手机号验证   
    jQuery.validator.addMethod("phone", function (value, element)
    {
    var tel = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(17[0-9]\d{8}$)|(15[0-9]\d{8}$)|(18[0-9]\d{8}$)/;
    return this.optional(element) || (tel.test(value));
    }, "请正确填写电话号码");
    
    // 邮政编码验证
    jQuery.validator.addMethod("zipcode", function (value, element)
    {
        var tel = /^[1-9][0-9]{5}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写邮政编码");
    
    
    
    //email 验证
    jQuery.validator.addMethod("email", function (value, element) {
        var tel = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写邮箱号码");
    
    
    // 汉字
    jQuery.validator.addMethod("chcharacter", function (value, element)
    {
        var tel = /^[\u4e00-\u9fa5]+$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入汉字");



    // QQ
    jQuery.validator.addMethod("qq", function (value, element)
    {
        var tel = /^[1-9][0-9]{4,}$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入正确的QQ");

    
    // 用户名
    jQuery.validator.addMethod("username", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
    }, "用户名格式不正确");

    
    
    //身份证验证
    jQuery.validator.addMethod("isIdCardNo", function(value, element) {      
    	 
    	  return this.optional(element) ||  $.idcardvalidate(value);       
    },  "身份证号码校验错误(位数/出生日期/非法字符)!");

    
    //IP地址验证
    jQuery.validator.addMethod("ip", function(value, element) { 
    	var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
    	return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
	}, "Ip地址格式错误"); 


    //密码强度判断
    jQuery.validator.addMethod("mimaqiangdu", function (value, element){
    	var flag = 1;
    	if(checkStrong(value)<2){
    		flag = 0;
    	};
    	return flag == 1 ? true:false;
    },  "密码过于简单!");
    
  //判断用户编号是否已经存在
    jQuery.validator.addMethod("unocheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"systemuser/JudgeUnoRepeat",  
                data:{uno:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该用户编号已存在!');
    
  //判断用户手机号码是否已经存在
    jQuery.validator.addMethod("phonecheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"systemuser/JudgePhoneRepeat",  
                data:{phone:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该手机号码已存在!');
    
    //公式合法性
    jQuery.validator.addMethod("expcheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"base/expcheck",  
                data:{value:value},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'公式不合法!');
    //判断用户帐号是否已经存在
    jQuery.validator.addMethod("useridcheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"base/useridcheck",  
                data:{value:value,flag:$("#id").val()==null?'add':'modify',id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该帐号已存在!');
    //判断客户或客户联系人手机号码是否已经存在
    jQuery.validator.addMethod("phonecheck2", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"base/phonecheck2",  
                data:{phone:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该手机号码已存在!');
  //判断身份证号不能重复
    jQuery.validator.addMethod("idcardcheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({
    	  		type:'POST',
                url: basepath+"systemuser/JudgeRepeat",  
                data:{
                	table:'lefang_insider_user',
                	record:'idcard',
                	record2:'id',
                	value:value,
                	flag:$('#flag').val(),
                	notparams:$("#id").val()
                },  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该身份证号已存在!');
  //判断用户账户是否已经存在
    jQuery.validator.addMethod("accountcheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"systemuser/JudgeUserAccountRepeat",  
                data:{account:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该账号名称已存在!');
    //判断用户Email是否已经存在
    jQuery.validator.addMethod("emailcheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"systemuser/JudgeEmailRepeat",  
                data:{email:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该电子邮箱已存在!');
    //判断用户工号是否已经存在
    jQuery.validator.addMethod("worknocheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"systemuser/JudgeWorknoRepeat",  
                data:{workno:value,flag:$('#flag').val(),id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该工号已存在!');
    //判断是否已有 基准商品（龙翌）
    jQuery.validator.addMethod("l_isifcomm", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"public_template/l_isifcomm",  
                data:{l_isifcomm:value,l_id:$("#l_id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'基准商品已存在!');
    
  //判断是否已有 基准商品（上海）
    jQuery.validator.addMethod("s_isifcomm", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"public_template/s_isifcomm",  
                data:{s_isifcomm:value,s_id:$("#id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'基准商品已存在!');
    
  //判断菜单名称不能重复
    jQuery.validator.addMethod("menucheck", function (value, element){
    	var flag = 1;
    	  	$.ajax({
    	  		type:'POST',
                url: basepath+"systemuser/JudgeRepeat",  
                data:{
                	table:'lefang_member_menu',
                	record:'name',
                	record2:'id',
                	value:value,
                	flag:$('#flag').val(),
                	notparams:$("#id").val()
                },  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'该菜单名称已存在!');
    
  //判断是否已有 基准商品（广东）
    jQuery.validator.addMethod("g_isifcomm", function (value, element){
    	var flag = 1;
    	  	$.ajax({  
                url: basepath+"public_template/g_isifcomm",  
                data:{g_isifcomm:value,g_id:$("#g_id").val()},  
                async: false,  
                dataType: "json", 
                success: function(data) {  
                    if(data=="1")  
                    	flag = 0;  
                }
            });
    	return flag == 1 ? true:false;
    },'基准商品已存在!');
    
    jQuery.validator.addMethod("xiaoshu", function (value, element)
    	    {
    	       return this.optional(element) || /^(([0])|([1-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,2})))$/g.test(value);
    	    }, "精确到小数点后2位");
   
    
    //验证 正负数和两位小数
    jQuery.validator.addMethod("zhengfu", function (value, element)
    {
       return this.optional(element) || /^[-+]?(([0])|([1-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,2})))+$/g.test(value);
    }, "只能为数字");
    
    // 账户名
    jQuery.validator.addMethod("account2", function (value, element)
    {
        return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
    }, "请输入字母开头，数字，或者下划线最少2位");
    
})(jQuery);




//CharMode函数         
//测试某个字符是属于哪一类.         
function CharMode(iN) {
	if (iN >= 48 && iN <= 57) //数字                 
		return 1;
	if (iN >= 65 && iN <= 90) //大写字母                 
		return 2;
	if (iN >= 97 && iN <= 122) //小写                 
		return 4;
	else
		return 8; //特殊字符         
}
//bitTotal函数         
//计算出当前密码当中一共有多少种模式         
function bitTotal(num) {
	modes = 0;
	for (i = 0; i < 4; i++) {
		if (num & 1)
			modes++;
		num >>>= 1;
	}
	return modes;
}
//checkStrong函数         
//返回密码的强度级别         
function checkStrong(sPW) {
	if (sPW.length <= 4)
		return 0; //密码太短            
	Modes = 0;
	for (i = 0; i < sPW.length; i++) {
		//测试每一个字符的类别并统计一共有多少种模式.                 
		Modes |= CharMode(sPW.charCodeAt(i));
	}
	return bitTotal(Modes);
}