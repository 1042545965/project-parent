LG.userapply = function(content, callback) {
	window.myapplyWin='';
	if (!window.myapplyWin) {
		var tablepanel = $('<table></table>"');
		if(content){
			if(!content.columnline){
				content.columnline = 3;
			}
			if(content.field){
				var fields = content.field;
				var tablebody = '<tr style="">';
				$.each(fields,function(i,field){
					if(i%3==0){
						tablebody+='</tr><tr>';
					}
					var colspan = field.colspan?field.colspan:1;
					var value=field.value=='undefined'||field.value==''||field.value==null?'':field.value;
					 tablebody+='<td style="border:1px solid #9db3c5; padding:0 5px 0;font-size:14px;background-color:#fbd8e8;height:60px;wight:100px;">'+field.display+'</td>'+'<td colspan="'+colspan+'" style="border:1px solid #9db3c5; padding:0 5px 0;font-size:14px;">'+value+'</td>';
				});
				tablepanel.append(tablebody);
			}
		}
		

		var applyPanle = $('<form><table  style="margin-left: auto;margin-right: auto; width: 600px"><tr>'
				+'<td><input type="radio" name="apply" value="0" >不同意'
				+'    <input type="radio" name ="apply" value="1" checked=true>同意</td>'
				+' <td>审批意见：</td><td width="150px"><textarea rows="2" cols="80" id="applyremark010239383i"></textarea></td>'
				+ '</tr></table></form>');
		var panel = $("<div></div>");

		var hanel = $('<div ><span style="color:#5F72CF;panding-left:8px;font-size:14px; border-bottom: 1px solid #58E15A;width:400px;">审批详情:</span></div>');
		var mypanel = $('<div  style="margin-left: auto;margin-right: auto; width: 600px"></div>');
		mypanel.append(tablepanel);
		panel.append(applyPanle).append(hanel).append(mypanel);

		window.myapplyWin = $.ligerDialog.open({
			width : 800,
			height : 500,
			top : 10,
			isResize : true,
			title : '审批',
			target : panel,
			buttons : [ {
				text : '确定',
				onclick : function() {
				var appvalue = $('input[name="apply"]:checked').val();
				var appremark = $('#applyremark010239383i').val();
				if(appvalue==0&&!appremark){
					alert("不同意时，请填写不同意意见");
					return;
				}
					callback(appvalue,appremark);
					window.myapplyWin.close();
				}
			}, {
				text : '取消',
				onclick : function() {
					window.myapplyWin.close();
					panel.remove();
				}
			} ]
		});
	} else {
		window.myapplyWin.show();
	}
};