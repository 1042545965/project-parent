$.ligerui.controls.ComboBox.prototype.openMapSelect = function(options){
	 var g = this, p = this.options;
	options = $.extend({
        title: '地图选择器',     //窗口标题
        width: 800,            //窗口宽度     
        height: 500,           //窗口高度
        top: null,
        left: null,
        valueField: null,    //接收表格的value字段名
        textField: null   //接收表格的text字段名
    }, options || {});
    
    //三个 ligerui 对象
    var win ,form;
    g.bind('beforeOpen', function ()
    {
    	showMap();
        return false;
    });
    
   
    function showMap()
    {
        if (win)
        {
            win.show();
        }
        else
        {
        	
        	$(document.body).append('<div id="mymaptarget" style="height:410px;width:98%;float:left;border-right:2px solid #bcbcbc;">');
        	var map = new BMap.Map("mymaptarget");
    		map.centerAndZoom("苏州市",12);                   // 初始化地图,设置城市和地图级别。
    		map.enableScrollWheelZoom(); //允许用滚轮调节地图
    		//添加控件
    		map.addControl(new BMap.NavigationControl()); //平移缩放控件
    		var panle = $("<div></div>");
            var formPanle = $('<form><label for="mapsearch">地址:</label><input id="mapsearch" type="text"/><input type="button" value="搜索" onclick="myfunction()"></form>');
    		panle.append(formPanle).append($('#mymaptarget'));
    		function myfunction(){
    			alert("21212");
    		}
        	//dialog
            win = $.ligerDialog.open({
                title: options.title,
                width: options.width,
                height: options.height,
                top: options.top,
                left: options.left,
                target: panle,
                buttons: [
                 { text: '选择', onclick: function (item, dialog) { getMap(); dialog.hide(); } },
                 { text: '取消', onclick: function (item, dialog) { dialog.hide(); } }
                ]
            });
           
        }
    }
    function getMap(){
    	
    }
 
};