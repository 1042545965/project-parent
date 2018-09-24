(function ($)
{
    // 全局系统对象
    window['DKZLG'] = {};
    
    DKZLG.cookies = (function ()
    {
        var fn = function ()
        {
        };
        fn.prototype.get = function (name)
        {
            var cookieValue = "";
            var search = name + "=";
            if (document.cookie.length > 0)
            {
                offset = document.cookie.indexOf(search);
                if (offset != -1)
                {
                    offset += search.length;
                    end = document.cookie.indexOf(";", offset);
                    if (end == -1) end = document.cookie.length;
                    cookieValue = decodeURIComponent(document.cookie.substring(offset, end));
                }
            }
            return cookieValue;
        };
        fn.prototype.set = function (cookieName, cookieValue, DayValue)
        {
            var expire = "";
            var day_value = 1;
            if (DayValue != null)
            {
                day_value = DayValue;
            }
            expire = new Date((new Date()).getTime() + day_value * 86400000);
            expire = "; expires=" + expire.toGMTString();
            document.cookie = cookieName + "=" + encodeURIComponent(cookieValue) + ";path=/" + expire;
        };
        fn.prototype.remvoe = function (cookieName)
        {
            var expire = "";
            expire = new Date((new Date()).getTime() - 1);
            expire = "; expires=" + expire.toGMTString();
            document.cookie = cookieName + "=" + escape("") + ";path=/" + expire;
            /*path=/*/
        };

        return new fn();
    })();

    

    //预加载图片
    DKZLG.prevLoadImage = function (rootpath, paths)
    {
        for (var i in paths)
        {
            $('<img />').attr('src', rootpath + paths[i]);
        }
    };
    
    /*判断是否为空
     * dkz 2018-1-10 15:51:03
     * */
    DKZLG.IsEmpty = function (value)
    {
    	return value=='' || value=='null' || value=='undefined' ||  typeof(value)=='undefined' || value==undefined || value==null;
    }
    
    /*上传的公共方法
     * id file的对象
     * myfile 后台接收参数
     * */
    DKZLG.uploadFile = function (File, url, path)
    {
    	var formData = new FormData();
    	formData.append("myfile", File);
    	formData.append("suffix", File.name);
    	formData.append("path", path);
    	var datastr; 
    	$.ajax({
    		  type:'POST',  
    		  url: url,
    		  data: formData,  
    		  async: false,  
    		  contentType: false,
    		  processData: false,
    		  success: function(data){
    			  datastr = data;
    		  },  
    		  error: function(err){
    		    alert('网络故障');
    		  }
    		});  
    	 var json2map=JSON.parse(datastr);
    	 if(!json2map.IsError){
		    	return json2map.Data;
		    }else{
		      alert('上传失败');
		    }
    }
    
    /* post 提交
     *  dkz 2018-1-10 15:32:41
     *  param {"key":"value"}
     * */
    DKZLG.getSelectData = function(selectUrl,param){
    	var mydata;
    	$.ajax({
    		 type:"POST",
    		 async:false,
    		 url:selectUrl,
    		 data:param,
    		 contentType:"application/x-www-form-urlencoded; charset=utf-8",
    		 success:function(data){
    			 mydata = data; 
    		 },
    		 dataType:"json"
    	});
    	return mydata;
    };
    
    
  
    
    /*返回安卓原生界面的方法
     * dkz 2018-1-16 11:05:50
     * 依赖于 
     * */
    DKZLG.goBackAndroid = function ()
    {
    	window.location.href='toyun://';
    }
    
    /*加载localStore 前端缓存
     * dkz 2018-1-16 11:05:50
     * 
     * */
    DKZLG.loadLocalStore = function (usercode,orgid)
    {
    	if(!window.localStorage){
            alert("浏览器不支持localstorage");
            return false;
        }else{
            var storage=window.localStorage;
            var usde = storage.getItem("usercode");
            var org = storage.getItem("orgid");
            if (LG.IsEmpty(storage.getItem("usercode")) || usde != usercode) {
            	storage.setItem("usercode",usercode);
			}
            if (LG.IsEmpty(storage.getItem("orgid")) || org != orgid) {
            	storage.setItem("orgid",orgid);
			}
        }
    }
    
   
    /*
     * dkz 2018-2-7 10:18:14
     * js处理时间 加减问题  时间相加 传 正数 （1） 相减传负数（-1）
     * timestamp：13位时间戳
     * day ： 被减的时间
     * format：格式 yyyy-mm-dd  yyyy-mm-dd hh:mm:ss这两种格式
     * */
    DKZLG.timestampToTime = function (timestamp , day , format)
    {
    	timestamp = timestamp - (86400*1000*day);
    	var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        if (format === 'yyyy-mm-dd') {
        	return Y+M+D;
		} else {
			return Y+M+D+h+m+s;
		}
    }
    
    
    
    /*
     *	原生js获取cookie值
     * */ 

    DKZLG.getCookie = function (name){
	    var strcookie = document.cookie;//获取cookie字符串
	    var arrcookie = strcookie.split("; ");//分割
	    //遍历匹配
	    for ( var i = 0; i < arrcookie.length; i++) {
	    var arr = arrcookie[i].split("=");
	    if (arr[0] == name){
	    	  return arr[1];
	    	}
	      }
	    return "";
    }
    
   })(jQuery);



