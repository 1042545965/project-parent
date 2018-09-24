(function($) {

	//全局系统对象
	window['LG'] = {};

	LG.cookies = (function() {
		var fn = function() {
		};
		fn.prototype.get = function(name) {
			var cookieValue = "";
			var search = name + "=";
			if (document.cookie.length > 0) {
				offset = document.cookie.indexOf(search);
				if (offset != -1) {
					offset += search.length;
					end = document.cookie.indexOf(";", offset);
					if (end == -1)
						end = document.cookie.length;
					cookieValue = decodeURIComponent(document.cookie.substring(
							offset, end));
				}
			}
			return cookieValue;
		};
		fn.prototype.set = function(cookieName, cookieValue, DayValue) {
			var expire = "";
			var day_value = 1;
			if (DayValue != null) {
				day_value = DayValue;
			}
			expire = new Date((new Date()).getTime() + day_value * 86400000);
			expire = "; expires=" + expire.toGMTString();
			document.cookie = cookieName + "="
					+ encodeURIComponent(cookieValue) + ";path=/" + expire;
		};
		fn.prototype.remvoe = function(cookieName) {
			var expire = "";
			expire = new Date((new Date()).getTime() - 1);
			expire = "; expires=" + expire.toGMTString();
			document.cookie = cookieName + "=" + escape("") + ";path=/"
					+ expire;
			/*path=/*/
		};

		return new fn();
	})();

	//右下角的提示框
	LG.tip = function(message) {
		if (LG.wintip) {
			LG.wintip.set('content', message);
			LG.wintip.show();
		} else {
			LG.wintip = $.ligerDialog.tip({
				content : message
			});
		}
		setTimeout(function() {
			LG.wintip.hide();
		}, 4000);
	};

	//预加载图片
	LG.prevLoadImage = function(rootpath, paths) {
		for ( var i in paths) {
			$('<img />').attr('src', rootpath + paths[i]);
		}
	};
	//显示loading
	LG.showLoading = function(message) {
		message = message || "正在加载中...";
		$('body').append(
				"<div class='jloading' style='right: 700px;top: 300px;'>"
						+ message + "</div>");
		$.ligerui.win.mask();
	};
	//隐藏loading
	LG.hideLoading = function(message) {
		$('body > div.jloading').remove();
		$.ligerui.win.unmask({
			id : new Date().getTime()
		});
	};
	//显示成功提示窗口
	LG.showSuccess = function(message, callback) {
		if (typeof (message) == "function" || arguments.length == 0) {
			callback = message;
			message = "操作成功!";
		}
		$.ligerDialog.success(message, '提示信息', callback);
	};

	//显示失败提示窗口
	LG.showError = function(message, callback) {
		if (typeof (message) == "function" || arguments.length == 0) {
			callback = message;
			message = "操作失败!";
		}
		$.ligerDialog.error(message, '提示信息', callback);
	};

	//显示操作信息，由后端直接返回
	LG.showMessage = function(data) {
		if (data.IsError) {
			$.ligerDialog.error(data.Message, '提示信息', callback);
		} else {
			$.ligerDialog.success(data.Message, '提示信息', callback);
		}
	};
	//预加载dialog的图片
	LG.prevDialogImage = function(rootPath) {
		rootPath = rootPath || "../";
		LG.prevLoadImage(rootPath + 'resource/uilib/skins/Aqua/images/win/',
				[ 'dialog-icons.gif' ]);
		LG.prevLoadImage(rootPath + 'resource/uilib/skins/Gray/images/win/',
				[ 'dialogicon.gif' ]);
	};

	//提交服务器请求
	//返回json格式
	//1,提交给类 options.type  方法 options.method 处理
	//2,并返回 AjaxResult(这也是一个类)类型的的序列化好的字符串
	LG.ajax = function(options) {
		var p = options || {};
		var url = p.url;
		var issyn = options.issyn;
		if (issyn == undefined || issyn == null) {
			issyn = true;
		}

		$.ajax({
			cache : false,
			async : issyn,
			url : url,
			data : p.data,
			dataType : 'json',
			type : 'post',
			contentType : "application/x-www-form-urlencoded; charset=gb2312",
			beforeSend : function() {
				LG.loading = true;
				if (p.beforeSend)
					p.beforeSend();
				else
					LG.showLoading(p.loading);
			},
			complete : function() {
				LG.loading = false;
				if (p.complete)
					p.complete();
				else
					LG.hideLoading();
			},
			success : function(result) {
				if (!result)
					return;
				if (!result.IsError) {
					if (p.success)
						p.success(result.Data, result.Message);
				} else {
					if (p.error)
						p.error(result.Message);
				}
			},
			error : function(result, b) {
				LG.tip('操作失败,系统异常!<br/>请联系管理员.');
			}
		});
	};

	//获取当前页面的MenuNo
	//优先级1：如果页面存在MenuNo的表单元素，那么加载它的值
	//优先级2：加载QueryString，名字为MenuNo的值
	LG.getPageMenuNo = function() {
		var menuno = $("#MenuNo").val();
		if (!menuno) {
			menuno = getQueryStringByName("MenuNo");
		}
		return menuno;
	};

	//创建按钮
	LG.createButton = function(options) {
		var p = $.extend({
			appendTo : $('body')
		}, options || {});
		var btn = $('<div ><div > </div><div > </div> <span class="btn btn-sm btn-primary"></span></div>');
		if (p.icon) {
			btn.removeClass("buttonnoicon");
			btn.append('<div class="button-icon"> <img src="../' + p.icon
					+ '" /> </div> ');
		}
		//绿色皮肤
		if (p.green) {
			btn.removeClass("button2");
		}
		if (p.style) {
			btn.css(p.style);
		}
		if (p.width) {
			btn.width(p.width);
		}
		if (p.click) {
			btn.click(p.click);
		}
		if (p.text) {
			$("span", btn).html(p.text);
		}
		if (typeof (p.appendTo) == "string")
			p.appendTo = $(p.appendTo);
		btn.appendTo(p.appendTo);
	};

	//创建过滤规则(查询表单)
	LG.bulidFilterGroup = function(form) {
		if (!form)
			return null;
		var group = {
			op : "and",
			rules : []
		};
		$(":input", form).not(":submit, :reset, :image,:button, [disabled]")
				.each(function() {
					if (!this.name)
						return;
					if (!$(this).hasClass("field"))
						return;
					if ($(this).val() == null || $(this).val() == "")
						return;
					var ltype = $(this).attr("ltype");
					var optionsJSON = $(this).attr("ligerui"), options;
					if (optionsJSON) {
						options = JSON2.parse(optionsJSON);
					}
					var op = $(this).attr("op") || "like";
					//get the value type(number or date)
					var type = $(this).attr("vt") || "string";
					var value = $(this).val();
					var name = this.name;
					//如果是下拉框，那么读取下拉框关联的隐藏控件的值(ID值,常用与外表关联)
					if (ltype == "select" && options && options.valueFieldID) {
						value = $("#" + options.valueFieldID).val();
						name = options.valueFieldID;
					}
					group.rules.push({
						op : op,
						field : name,
						value : value,
						type : type
					});
				});
		return group;
	};

	//附加表单搜索按钮：搜索、高级搜索
	LG.appendSearchButtons = function(form, grid, beforeSearch) {
		if (!form)
			return;
		grid.set("srchformid", form);
		form = $(form);
		//搜索按钮 附加到第一个li  高级搜索按钮附加到 第二个li
		var container = $(
				'<ul><li style="margin-right:8px"></li></ul><div class="l-clear"></div>')
				.appendTo(form);
		LG.addSearchButtons(form, grid, container.find("li:eq(0)"), container
				.find("li:eq(1)"), beforeSearch);

	};

	LG.appendQueryButton = function(form, grid, buttontext) {
		if (!form)
			return;
		form = $(form);
		var container = $(
				'<ul><li style="margin-right:8px"></li></ul><div class="l-clear"></div>')
				.appendTo(form);
		LG.addQueryButton(form, grid, container.find("li:eq(0)"), buttontext);
	};

	LG.addQueryButton = function(form, grid, button, buttontext) {

		if (!form)
			return;
		if (button) {

			LG.createButton({
				appendTo : button,
				text : buttontext,
				click : function() {
					var rule = LG.bulidFilterGroup(form);
					if (rule.rules.length) {
						grid.set('parms', {
							where : JSON2.stringify(rule)
						});
					} else {
						grid.set('parms', {});
					}
					grid.loadData();
				}
			});
		}
	};

	/**
	 * 导出Excel(ids)
	 */
	LG.exportExcelIds = function(grid, options, ids) {
		var columns = [];
		$(grid.columns).each(function() {
			var o = {
				name : this.name,
				display : this.display,
				width : 200
			};
			columns.push(o);
		});

		options = $.extend({
			title : "系统Excel导出",
			sheetName : "子系统",
			filename : ""
		}, options);
		var url = grid.get("url");
		var sortname = grid.get("sortName");
		var sortorder = grid.get('sortOrder');
		if (url.indexOf("?") != -1) {
			url = url.substr(url.indexOf("?") + 1);
		}
		var where = grid.get("parms").where == null ? ''
				: grid.get("parms").where;
		//alert(JSON.stringify(columns));
		window.location.href = basepath + 'SystemWeb/exportExcelIds.do?ids='
				+ ids + '&columns=' + JSON.stringify(columns) + "&where="
				+ where + "&" + url + "&title=" + options.title + "&sheetName="
				+ options.sheetName + "&filename=" + options.filename
				+ "&sortname=" + sortname + "&sortorder=" + sortorder;
	};

	/**
	 * 导出Excel
	 */
	LG.exportExcel = function(grid, options) {
		var columns = [];
		$(grid.columns).each(function() {
			var o = {
				name : this.name,
				display : this.display,
				width : 200
			};
			columns.push(o);
		});

		options = $.extend({
			title : "系统Excel导出",
			sheetName : "子系统",
			filename : ""
		}, options);
		var url = grid.get("url");
		var sortname = grid.get("sortName");
		var sortorder = grid.get('sortOrder');
		if (url.indexOf("?") != -1) {
			url = url.substr(url.indexOf("?") + 1);
		}
		var where = grid.get("parms").where == null ? ''
				: grid.get("parms").where;
		//alert(JSON.stringify(columns));
		window.location.href = basepath + 'SystemWeb/exportExcel.do?columns='
				+ JSON.stringify(columns) + "&where=" + where + "&" + url
				+ "&title=" + options.title + "&sheetName=" + options.sheetName
				+ "&filename=" + options.filename + "&sortname=" + sortname
				+ "&sortorder=" + sortorder;
	};

	/**
	 * 导出grid数据
	 * 
	 * ex : LG.exportGridData(grid, {
					title : "统计信息_"+f_name,
					sheetName : "统计信息_"+f_name,
					filename : "统计信息_"+f_name ,
					exportType:"csv",
					countColumn:JSON2.stringify(["count1","count2"]),
					hideColumn :JSON2.stringify(["XX","XX"]);
	 */
	LG.exportGridData = function(grid, options) {

		var columns = new Array();
		$(grid.get("columns")).each(function() {
			columns.push({
				name : this.name,
				display : this.display,
				width : this.width
			});
		});

		options = $.extend({
			title : "数据导出",
			sheetName : "数据导出",
			filename : ""
		}, options);
		var url = grid.get("url");
		var sortname = grid.get("sortName");
		var sortorder = grid.get('sortOrder');
		if (url.indexOf("?") != -1) {
			url = url.substr(url.indexOf("?") + 1);
		}
		var where;
		var where2;
		var rule = {};
		if (grid.get("srchformid") != null
				&& grid.get("srchformid") != undefined
				&& grid.get("srchformid") != "") {
			var rule = LG.bulidFilterGroup($(grid.get("srchformid")));
			if (!rule.rules.length) {
				rule = {};
			}
		}
		if (grid.get("customerRule") != null
				&& grid.get("customerRule") != undefined
				&& grid.get("customerRule") != "") {
			rule = grid.get("customerRule");
		}
		var datarule = grid.options.dataFilter;
		if (datarule == null || datarule == undefined) {
			datarule = {};
		}
		where = JSON2.stringify(rule);
		where2 = JSON2.stringify(datarule);

		var columnsParam = JSON2.stringify(columns);

		var hideColumn = options.hideColumn;
		if (!hideColumn) {
			hideColumn = "";
		}
		var countColumn = options.countColumn;
		if (!countColumn) {
			countColumn = "";
		}

		var formHTML = '<form action="' + basepath
				+ 'SystemWeb/exportGridData.do?' + url
				+ '" style="display:none" id="exportForm"  method="post">';
		formHTML += '<input type="hidden" name="columns" value=' + columnsParam
				+ '></input>';
		formHTML += '<input type="hidden" name="where" value=' + where
				+ '></input>';
		formHTML += '<input type="hidden" name="where2" value=' + where2
				+ '></input>';
		formHTML += '<input type="hidden" name="exportType" value='
				+ options.exportType + '></input>';
		formHTML += '<input type="hidden" name="title" value=' + options.title
				+ '></input>';
		formHTML += '<input type="hidden" name="sheetName" value='
				+ options.sheetName + '></input>';
		formHTML += '<input type="hidden" name="hideColumn" value='
				+ hideColumn + '></input>';
		formHTML += '<input type="hidden" name="countColumn" value='
				+ countColumn + '></input>';
		formHTML += '<input type="hidden" name="filename" value='
				+ options.filename + '></input>';
		formHTML += '<input type="hidden" name="sortname" value=' + sortname
				+ '></input>';
		formHTML += '<input type="hidden" name="sortorder" value=' + sortorder
				+ '></input>';
		formHTML += '</form>';

		$("#exportForm").remove();
		$('body').append($(formHTML));
		$("#exportForm").submit();

	};

	LG.exportGridData2 = function(grid, options) {

		var columns = new Array();
		$(grid.get("columns")).each(function() {
			columns.push({
				name : this.name,
				display : this.display,
				width : this.width
			});
		});

		options = $.extend({
			title : "数据导出",
			sheetName : "数据导出",
			filename : ""
		}, options);
		var url = grid.get("url");
		var sortname = grid.get("sortName");
		var sortorder = grid.get('sortOrder');
		if (url.indexOf("?") != -1) {
			url = url.substr(url.indexOf("?") + 1);
		}
		var where;
		var where2;
		var rule = {};
		if (grid.get("srchformid") != null
				&& grid.get("srchformid") != undefined
				&& grid.get("srchformid") != "") {
			var rule = LG.bulidFilterGroup($(grid.get("srchformid")));
			if (!rule.rules.length) {
				rule = {};
			}
		}
		if (grid.get("customerRule") != null
				&& grid.get("customerRule") != undefined
				&& grid.get("customerRule") != "") {
			rule = grid.get("customerRule");
		}
		var datarule = grid.options.dataFilter;
		if (datarule == null || datarule == undefined) {
			datarule = {};
		}
		where = JSON2.stringify(rule);
		where2 = JSON2.stringify(datarule);

		var columnsParam = JSON2.stringify(columns);

		var hideColumn = options.hideColumn;
		if (!hideColumn) {
			hideColumn = "";
		}
		var countColumn = options.countColumn;
		if (!countColumn) {
			countColumn = "";
		}

		var formHTML = '<form action="' + basepath
				+ 'SystemWeb/exportGridData2.do?' + url
				+ '" style="display:none" id="exportForm"  method="post">';
		formHTML += '<input type="hidden" name="columns" value=' + columnsParam
				+ '></input>';
		formHTML += '<input type="hidden" name="where" value=' + where
				+ '></input>';
		formHTML += '<input type="hidden" name="where2" value=' + where2
				+ '></input>';
		formHTML += '<input type="hidden" name="exportType" value='
				+ options.exportType + '></input>';
		formHTML += '<input type="hidden" name="title" value=' + options.title
				+ '></input>';
		formHTML += '<input type="hidden" name="sheetName" value='
				+ options.sheetName + '></input>';
		formHTML += '<input type="hidden" name="hideColumn" value='
				+ hideColumn + '></input>';
		formHTML += '<input type="hidden" name="countColumn" value='
				+ countColumn + '></input>';
		formHTML += '<input type="hidden" name="filename" value='
				+ options.filename + '></input>';
		formHTML += '<input type="hidden" name="sortname" value=' + sortname
				+ '></input>';
		formHTML += '<input type="hidden" name="sortorder" value=' + sortorder
				+ '></input>';
		formHTML += '</form>';

		$("#exportForm").remove();
		$('body').append($(formHTML));
		$("#exportForm").submit();

	};

	//创建表单搜索按钮：搜索、高级搜索
	LG.addSearchButtons = function(form, grid, btn1Container, btn2Container,
			beforeSearch) {
		if (!form)
			return;
		if (btn1Container) {
			LG.createButton({
				appendTo : btn1Container,
				text : '搜索',
				click : function() {
					if (beforeSearch) {
						beforeSearch();
					}
					var rule = LG.bulidFilterGroup(form);
					var datarule = grid.options.dataFilter;
					if (datarule == null || datarule == undefined) {
						datarule = {};
					}
					if (rule.rules.length) {
						grid.set('parms', {
							where : JSON2.stringify(rule),
							where2 : JSON2.stringify(datarule)
						});
					} else {
						grid.set('parms', {
							where2 : JSON2.stringify(datarule)
						});
					}
					grid.set("newPage", 1);
					grid.loadData();
				}
			});
		}
		if (btn2Container) {
			LG.createButton({
				appendTo : btn2Container,
				width : 80,
				text : '高级搜索',
				click : function() {
					grid.showFilter();
				}
			});
		}
	};

	//快速设置表单底部默认的按钮:保存、取消
	LG.setFormDefaultBtn = function(cancleCallback, savedCallback,
			printCallback, options) {
		//表单底部按钮
		var buttons = [];
		if (cancleCallback) {
			buttons.push({
				text : '取消',
				onclick : cancleCallback
			});
		}
		if (savedCallback) {
			buttons.push({
				text : options && options.saveStr ? options.saveStr : '保存',
				onclick : savedCallback
			});
		}
		if (printCallback) {
			buttons.push({
				text : '打印',
				onclick : printCallback
			});
		}
		LG.addFormButtons(buttons);
	};

	//增加表单底部按钮,比如：保存、取消
	LG.addFormButtons = function(buttons) {
		if (!buttons)
			return;
		var formbar = $("body > div.form-bar");
		if (formbar.length == 0)
			formbar = $(
					'<div class="form-bar"><div class="form-bar-inner"></div></div>')
					.appendTo('body');
		if (!(buttons instanceof Array)) {
			buttons = [ buttons ];
		}
		$(buttons)
				.each(
						function(i, o) {
							var btn = $('<div class="l-dialog-btn"><div class="l-dialog-btn-l"></div><div class="l-dialog-btn-r"></div><div class="l-dialog-btn-inner"></div></div> ');
							$("div.l-dialog-btn-inner:first", btn).html(
									o.text || "BUTTON");
							if (o.onclick) {
								btn.bind('click', function() {
									o.onclick(o);
								});
							}
							if (o.width) {
								btn.width(o.width);
							}
							$("> div:first", formbar).append(btn);
						});
	};

	//填充表单数据
	LG.loadForm = function(mainform, options, callback) {
		options = options || {};
		if (!mainform)
			mainform = $("form:first");
		var p = $.extend({
			beforeSend : function() {
				LG.showLoading('正在加载表单数据中...');
			},
			complete : function() {
				LG.hideLoading();
			},
			success : function(data) {
				var preID = options.preID || "";
				//根据返回的属性名，找到相应ID的表单元素，并赋值
				for ( var p in data) {
					var ele = $("[name=" + (preID + p) + "]", mainform);
					//针对复选框和单选框 处理
					if (ele.is(":checkbox,:radio")) {
						ele[0].checked = data[p] ? true : false;
					} else {
						ele.val(data[p]);
					}
				}
				//下面是更新表单的样式
				var managers = $.ligerui.find($.ligerui.controls.Input);
				for (var i = 0, l = managers.length; i < l; i++) {
					//改变了表单的值，需要调用这个方法来更新ligerui样式
					var o = managers[i];
					o.updateStyle();
					if (managers[i] instanceof $.ligerui.controls.TextBox)
						o.checkValue();
				}

				if (callback)
					callback(data);
			},
			error : function(message) {
				LG.showError('数据加载失败!<BR>错误信息：' + message);
			},
			async : false
		}, options);
		LG.ajax(p);
	};

	LG.removeValidate = function(mainform, data) {
		var preID = '';
		for ( var p in data) {
			var ele = $("[name=" + (preID + p) + "]", mainform);
			//针对复选框和单选框 处理
			if (ele.is(":checkbox,:radio")) {
				ele[0].checked = data[p] ? true : false;
			} else {
				ele.val(data[p]);
				ele.removeClass("error");

				if (ele.hasClass("l-textarea")) {
					ele.removeClass("l-textarea-invalid");
				} else if (ele.hasClass("l-text-field")) {
					ele.parent().removeClass("l-text-invalid");
				} else if (ele.attr("type") == 'hidden') {
					ele.prev().removeClass("l-text-invalid");
					ele.prev().removeAttr("title").ligerHideTip();
				}
				ele.removeAttr("title").ligerHideTip();
			}
		}
	};

	LG.loadDataForm = function(mainform, data) {
		//根据返回的属性名，找到相应ID的表单元素，并赋值
		var preID = '';
		for ( var p in data) {
			var ele = $("[name=" + (preID + p) + "]", mainform);
			//针对复选框和单选框 处理
			if (ele.is(":checkbox,:radio")) {
				ele[0].checked = data[p] ? true : false;
			} else {
				ele.val(data[p]);
				ele.removeClass("error");

				if (ele.hasClass("l-textarea")) {
					ele.removeClass("l-textarea-invalid");
				} else if (ele.hasClass("l-text-field")) {
					ele.parent().removeClass("l-text-invalid");
				} else if (ele.attr("type") == 'hidden') {
					ele.prev().removeClass("l-text-invalid");
					ele.prev().removeAttr("title").ligerHideTip();
				}
				ele.removeAttr("title").ligerHideTip();
			}
		}
		//下面是更新表单的样式
		var managers = $.ligerui.find($.ligerui.controls.Input);
		for (var i = 0, l = managers.length; i < l; i++) {
			//改变了表单的值，需要调用这个方法来更新ligerui样式
			var o = managers[i];
			o.updateStyle();
			if (managers[i] instanceof $.ligerui.controls.TextBox) {
				o.checkValue();
			}

		}
	};
	LG.Amountlowercase = function(numberValue) {
		var numberValue = new String(Math.round(numberValue * 100)); // 数字金额  
		var chineseValue = ""; // 转换后的汉字金额  
		var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
		var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
		var len = numberValue.length; // numberValue 的字符串长度  
		var Ch1; // 数字的汉语读法  
		var Ch2; // 数字位的汉字读法  
		var nZero = 0; // 用来计算连续的零值的个数  
		var String3; // 指定位置的数值  
		if (len > 15) {
			alert("超出计算范围");
			return "";
		}
		if (numberValue == 0) {
			chineseValue = "零元整";
			return chineseValue;
		}
		String2 = String2.substr(String2.length - len, len); // 取出对应位数的STRING2的值  
		for (var i = 0; i < len; i++) {
			String3 = parseInt(numberValue.substr(i, 1), 10); // 取出需转换的某一位的值  
			if (i != (len - 3) && i != (len - 7) && i != (len - 11)
					&& i != (len - 15)) {
				if (String3 == 0) {
					Ch1 = "";
					Ch2 = "";
					nZero = nZero + 1;
				} else if (String3 != 0 && nZero != 0) {
					Ch1 = "零" + String1.substr(String3, 1);
					Ch2 = String2.substr(i, 1);
					nZero = 0;
				} else {
					Ch1 = String1.substr(String3, 1);
					Ch2 = String2.substr(i, 1);
					nZero = 0;
				}
			} else { // 该位是万亿，亿，万，元位等关键位  
				if (String3 != 0 && nZero != 0) {
					Ch1 = "零" + String1.substr(String3, 1);
					Ch2 = String2.substr(i, 1);
					nZero = 0;
				} else if (String3 != 0 && nZero == 0) {
					Ch1 = String1.substr(String3, 1);
					Ch2 = String2.substr(i, 1);
					nZero = 0;
				} else if (String3 == 0 && nZero >= 3) {
					Ch1 = "";
					Ch2 = "";
					nZero = nZero + 1;
				} else {
					Ch1 = "";
					Ch2 = String2.substr(i, 1);
					nZero = nZero + 1;
				}
				if (i == (len - 11) || i == (len - 3)) { // 如果该位是亿位或元位，则必须写上  
					Ch2 = String2.substr(i, 1);
				}
			}
			chineseValue = chineseValue + Ch1 + Ch2;
		}
		if (String3 == 0) { // 最后一位（分）为0时，加上“整”  
			chineseValue = chineseValue + "整";
		}
		return chineseValue;
	};
	LG.ieplaceholder = function() {
		if (!('placeholder' in document.createElement('input'))) {

			$('input[placeholder],textarea[placeholder]').each(function() {
				var that = $(this), text = that.attr('placeholder');
				if (that.val() === "") {
					that.val(text).addClass('placeholder');
				}
				that.focus(function() {
					if (that.val() === text) {
						that.val("").removeClass('placeholder');
					}
				}).blur(function() {
					if (that.val() === "") {
						that.val(text).addClass('placeholder');
					}
				}).closest('form').submit(function() {
					if (that.val() === text) {
						that.val('');
					}
				});
			});
		}
	};

	//带验证、带loading的提交
	LG.submitForm = function(mainform, editor, editorname, success, error) {
		if (!mainform)
			mainform = $("form:first");
		if (mainform.valid()) {
			mainform
					.ajaxSubmit({
						dataType : 'json',
						success : success,
						beforeSubmit : function(formData, jqForm, options) {
							//针对复选框和单选框 处理
							$(":checkbox,:radio", jqForm).each(function() {
								if (!existInFormData(formData, this.name)) {
									formData.push({
										name : this.name,
										type : this.type,
										value : this.checked
									});
								}
							});
							for (var i = 0, l = formData.length; i < l; i++) {
								var o = formData[i];
								if (o.type == "checkbox" || o.type == "radio") {
									o.value = $("[name=" + o.name + "]", jqForm)[0].checked ? "true"
											: "false";
								}
								if (editor != null && o.name == editorname) {
									o.value = editor.html();
								}
							}

						},
						beforeSend : function(a, b, c) {
							LG.showLoading('正在保存数据中...');

						},
						complete : function() {
							LG.hideLoading();
						},
						error : function(result) {
							LG.tip('发现系统错误 <BR>错误码：' + result.status);
						}
					});
		} else {
			LG.showInvalid();
		}
		function existInFormData(formData, name) {
			for (var i = 0, l = formData.length; i < l; i++) {
				var o = formData[i];
				if (o.name == name)
					return true;
			}
			return false;
		}
	};

	//提示 验证错误信息
	LG.showInvalid = function(validator) {
		validator = validator || LG.validator;
		if (!validator)
			return;
		var message = '<div class="invalid">存在' + validator.errorList.length
				+ '个字段验证不通过，请检查!</div>';
		//top.LG.tip(message);
		$.ligerDialog.error(message);
	};

	//表单验证
	LG.validate = function(form, options) {
		if (typeof (form) == "string")
			form = $(form);
		else if (typeof (form) == "object" && form.NodeType == 1)
			form = $(form);
		options = $.extend({
			errorPlacement : function(lable, element) {
				if (!element.attr("id"))
					element.attr("id", new Date().getTime());
				if (element.hasClass("l-textarea")) {
					element.addClass("l-textarea-invalid");
				} else if (element.hasClass("l-text-field")) {
					element.parent().addClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
				$(element).attr("title", lable.html()).ligerTip({
					distanceX : 5,
					distanceY : -3,
					auto : true
				});
			},
			success : function(lable) {
				if (!lable.attr("for"))
					return;
				var element = $("#" + lable.attr("for"));

				if (element.hasClass("l-textarea")) {
					element.removeClass("l-textarea-invalid");
				} else if (element.hasClass("l-text-field")) {
					element.parent().removeClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
			}
		}, options || {});
		LG.validator = form.validate(options);
		return LG.validator;
	};

	LG.loadToolbar = function(grid, toolbarBtnItemClick) {
		var MenuNo = LG.getPageMenuNo();
		LG.ajax({
			loading : '正在加载工具条中...',
			url : basepath + 'system/limitsButton',
			data : {
				HttpContext : true,
				MenuNo : $.trim(MenuNo)
			},
			success : function(data) {

				if (!grid.toolbarManager)
					return;

				if (!data || !data.length)
					return;
				var items = [];
				for (var i = 0, l = data.length; i < l; i++) {
					var o = data[i];
					items[items.length] = {
						click : toolbarBtnItemClick,
						text : o.btnname,
						img : basepath + o.btnicon,
						id : o.btnno
					};
					items[items.length] = {
						line : true
					};
				}
				grid.toolbarManager.set('items', items);
			}
		});
	};

	//获取当前页面的MenuNo
	//优先级1：如果页面存在MenuNo的表单元素，那么加载它的值
	//优先级2：加载QueryString，名字为MenuNo的值
	LG.getPageMenuNo = function() {
		var menuno = $("#MenuNo").val();
		if (!menuno) {
			menuno = getQueryStringByName("MenuNo");
		}
		return menuno;
	};

	//关闭Tab项,如果tabid不指定，那么关闭当前显示的
	LG.closeCurrentTab = function(tabid) {
		if (!tabid) {
			tabid = $(
					"#framecenter > .l-tab-content > .l-tab-content-item:visible")
					.attr("tabid");
		}
		if (tab) {
			tab.removeTabItem(tabid);
		}
	};

	//关闭Tab项并且刷新父窗口
	LG.closeAndReloadParent = function(tabid, parentMenuNo) {
		LG.closeCurrentTab(tabid);
		var menuitem = $("#mainmenu ul.menulist li[menuno=" + parentMenuNo
				+ "]");
		var parentTabid = menuitem.attr("tabid");
		var iframe = window.frames[parentTabid];
		if (tab) {
			tab.selectTabItem(parentTabid);
		}
		if (iframe && iframe.f_reload) {
			iframe.f_reload();
		} else if (tab) {
			tab.reload(parentTabid);
		}
	};

	//
	LG.closeAndReloadParent2 = function(parentMenuNo) {
		var menuitem = $("#mainmenu ul.menulist li[menuno=" + parentMenuNo
				+ "]");
		var parentTabid = menuitem.attr("tabid");
		var iframe = window.frames[parentTabid];
		if (tab) {
			tab.selectTabItem(parentTabid);
		}
		if (iframe && iframe.f_reload) {
			iframe.f_reload();
		} else if (tab) {
			tab.reload(parentTabid);
		}
	};

	//刷新窗口
	LG.reloadTab = function(menuNo) {
		var menuitem = $("#mainmenu ul.menulist li[menuno=" + menuNo + "]");
		var parentTabid = menuitem.attr("tabid");
		var iframe = window.frames[parentTabid];
		if (tab) {
			tab.selectTabItem(parentTabid);
		}
		if (iframe && iframe.f_reload) {
			iframe.f_reload();
		} else if (tab) {
			tab.reload(parentTabid);
		}
	};

	//覆盖页面grid的loading效果
	LG.overrideGridLoading = function() {
		$.extend($.ligerDefaults.Grid, {
			onloading : function() {
				LG.showLoading('正在加载表格数据中...');
			},
			onloaded : function() {
				LG.hideLoading();
			}
		});
	};

	//根据字段权限调整 页面配置
	LG.adujestConfig = function(config, forbidFields) {
		if (config.Form && config.Form.fields) {
			for (var i = config.Form.fields.length - 1; i >= 0; i--) {
				var field = config.Form.fields[i];
				if ($.inArray(field.name, forbidFields) != -1)
					config.Form.fields.splice(i, 1);
			}
		}
		if (config.Grid && config.Grid.columns) {
			for (var i = config.Grid.columns.length - 1; i >= 0; i--) {
				var column = config.Grid.columns[i];
				if ($.inArray(column.name, forbidFields) != -1)
					config.Grid.columns.splice(i, 1);
			}
		}
		if (config.Search && config.Search.fields) {
			for (var i = config.Search.fields.length - 1; i >= 0; i--) {
				var field = config.Search.fields[i];
				if ($.inArray(field.name, forbidFields) != -1)
					config.Search.fields.splice(i, 1);
			}
		}
	};

	//查找是否存在某一个按钮
	LG.findToolbarItem = function(grid, itemID) {
		if (!grid.toolbarManager)
			return null;
		if (!grid.toolbarManager.options.items)
			return null;
		var items = grid.toolbarManager.options.items;
		for (var i = 0, l = items.length; i < l; i++) {
			if (items[i].id == itemID)
				return items[i];
		}
		return null;
	};

	//设置grid的双击事件(带权限控制)
	LG.setGridDoubleClick = function(grid, btnID, btnItemClick) {
		btnItemClick = btnItemClick || toolbarBtnItemClick;
		if (!btnItemClick)
			return;
		grid.bind('dblClickRow', function(rowdata) {
			var item = LG.findToolbarItem(grid, btnID);
			if (!item)
				return;
			grid.select(rowdata);
			btnItemClick(item);
		});
	};

	LG.getSelectData = function(selectUrl) {
		var mydata;
		$.ajax({
			type : "POST",
			async : false,
			url : selectUrl,
			success : function(data) {
				mydata = data;
			},
			dataType : "json"
		});
		return mydata;
	};

	LG.getSelectData2 = function(selectUrl, params) {
		var mydata;
		$.ajax({
			type : "POST",
			async : false,
			url : selectUrl,
			data : params,
			success : function(data) {
				mydata = data;
			},
			dataType : "json"
		});
		return mydata;
	};

	LG.formater = function(value, dateformat) {

		var date = new Date(parseInt(value));
		var g = this, p = this.options;
		if (isNaN(date))
			return null;
		var format = dateformat;
		var o = {
			"M+" : date.getMonth() + 1,
			"d+" : date.getDate(),
			"h+" : date.getHours(),
			"m+" : date.getMinutes(),
			"s+" : date.getSeconds(),
			"q+" : Math.floor((date.getMonth() + 3) / 3),
			"S" : date.getMilliseconds()
		};
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	};

	LG.showItemText = function(selItems, value, options) {
		options = $.extend({
			value : "value",
			text : "text"
		}, options);
		for (var i = 0; i < selItems.length; i++) {
			var s = selItems[i];
			if (s[options.value] == value) {
				return s[options.text];
			}
		}
		return "";
	};

	LG.validator_money = function(e) {
		e = e + "";
		var zhengshu = e.split(".")[0];
		if (zhengshu.length > 10) {
			return false;
		} else {
			var reg = /^(([0])|([1-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,3})))$/;
			if (!reg.test(e)) {
				return false;
			} else {
				return true;
			}
		}
	};
	LG.validate_file_format = function(formats, file) {
		if (file.lastIndexOf(".") != -1) {
			var extend = file.substring(file.lastIndexOf("."), file.length);
			extend = extend.toLowerCase();
			extend = extend.substring(1, extend.length);
			if ($.inArray(extend, formats) == -1) {
				alert("请上传" + formats.join(",") + "格式的文件!");
				return false;
			} else {
				return true;
			}
		} else {
			alert("请上传" + formats.join(",") + "格式的文件!");
			return false;
		}
	};
	LG.validator_money = function(e) {
		e = e + "";
		var zhengshu = e.split(".")[0];
		if (zhengshu.length > 10) {
			return false;
		} else {
			var reg = /^(([0])|([1-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,2})))$/;
			if (!reg.test(e)) {
				return false;
			} else {
				return true;
			}
		}
	};
	LG.positive_int = function(e) {
		var reg = /^[0-9]*[1-9][0-9]*$/;
		if (!reg.test(e)) {
			return false;
		} else {
			return true;
		}
	};
})(jQuery);
function banBackSpace(e) {
	var ev = e || window.event;//获取event对象     
	var obj = ev.target || ev.srcElement;//获取事件源     

	var t = obj.type || obj.getAttribute('type');//获取事件源类型    

	//获取作为判断条件的事件类型  
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	//处理null值情况  
	vReadOnly = (vReadOnly == null) ? false : vReadOnly;
	vEnabled = (vEnabled == null) ? true : vEnabled;

	//当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
	//并且readonly属性为true或enabled属性为false的，则退格键失效  
	var flag1 = (ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
			: false;

	//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
	var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;

	//判断  
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
}

//禁止后退键 作用于Firefox、Opera  
document.onkeypress = banBackSpace;
//禁止后退键  作用于IE、Chrome  
document.onkeydown = banBackSpace;

/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accAdd(arg1, arg2) {
	var r1, r2, m, c;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	c = Math.abs(r1 - r2);
	m = Math.pow(10, Math.max(r1, r2));
	if (c > 0) {
		var cm = Math.pow(10, c);
		if (r1 > r2) {
			arg1 = Number(arg1.toString().replace(".", ""));
			arg2 = Number(arg2.toString().replace(".", "")) * cm;
		} else {
			arg1 = Number(arg1.toString().replace(".", "")) * cm;
			arg2 = Number(arg2.toString().replace(".", ""));
		}
	} else {
		arg1 = Number(arg1.toString().replace(".", ""));
		arg2 = Number(arg2.toString().replace(".", ""));
	}
	return (arg1 + arg2) / m;
}

//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function(arg) {
	return accAdd(arg, this);
};
/**
 ** 减法函数，用来得到精确的减法结果
 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
 ** 调用：accSub(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accSub(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.sub = function(arg) {
	return accMul(arg, this);
};

/**
 ** 乘法函数，用来得到精确的乘法结果
 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
 ** 调用：accMul(arg1,arg2)
 ** 返回值：arg1乘以 arg2的精确结果
 **/
function accMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function(arg) {
	return accMul(arg, this);
};
function IsEmpty(value) {
	return value == '' || value == 'null' || value == 'undefined'
			|| typeof (value) == 'undefined' || value == undefined
			|| value == null;
}
function removeDuplicate(str) {
	var strArr = str.split(",");//把字符串分割成一个数组  

	strArr.sort();//排序  
	var result = new Array();//创建出一个结果数组  
	var tempStr = "";
	for ( var i in strArr) {
		if (strArr[i] != tempStr) {
			result.push(strArr[i]);
			tempStr = strArr[i];
		} else {
			continue;
		}
	}
	return result.join(",");
}
/** 
 ** 除法函数，用来得到精确的除法结果
 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
 ** 调用：accDiv(arg1,arg2)
 ** 返回值：arg1除以arg2的精确结果
 **/
function accDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = arg1.toString().split(".")[1].length;
	} catch (e) {
	}
	try {
		t2 = arg2.toString().split(".")[1].length;
	} catch (e) {
	}
	with (Math) {
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return (r1 / r2) * pow(10, t2 - t1);
	}
}

function IsEmpty(value) {
	return value == '' || value == 'null' || value == 'undefined'
			|| typeof (value) == 'undefined' || value == undefined
			|| value == null || value.length == 0;
}
function addTab(title, name, url) {
	var v = parent.$(".J_menuTab.active").attr("data-id");
	if (parent.$(".J_iframe[data-id='" + url + "']").length == 0) {
		var pm = parent.$(".J_iframe[data-id='" + v + "']").attr("menuno");
		var p = '<a href="javascript:;" parent-data-id="' + v
				+ '" parentmenuno="' + pm
				+ '" class="active J_menuTab" data-id="' + url + '">' + title
				+ '<i class="fa fa-times-circle"></i></a>';
		parent.$(".J_menuTab").removeClass("active");
		var n = '<iframe class="J_iframe" menuno="' + name + '"  name="' + name
				+ '" width="100%" height="100%" src="' + url
				+ '" frameborder="0" data-id="' + url + '" seamless></iframe>';
		parent.$(".J_mainContent").find("iframe.J_iframe").hide().parents(
				".J_mainContent").append(n);
		parent.$(".J_menuTabs .page-tabs-content").append(p);
	} else {
		parent.$(".J_menuTab").removeClass("active");
		parent.$(".J_iframe[data-id='" + v + "']").hide();
		parent.$(".J_iframe[data-id='" + url + "']").show();
		parent.$(".J_menuTab[data-id='" + url + "']").addClass('active');
	}
}
function CloseAndReloadTab() {
	var v = parent.$(".J_menuTab.active").attr("parent-data-id");
	parent.$(".J_menuTab.active").each(function() {
		parent.$('.J_menuTab[data-id="' + v + '"]').addClass("active");
		parent.$('.J_iframe[data-id="' + v + '"]').show();
		var pm = parent.$(".J_iframe[data-id='" + v + "']").attr("menuno");
		PLM.reloadMenu2(pm);
		parent.$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
		$(this).remove();
	});
	parent.$(".page-tabs-content").css("margin-left", "0");
}
function CloseAndTab() {
	var v = parent.$(".J_menuTab.active").attr("parent-data-id");
	parent.$(".J_menuTab.active").each(function() {
		parent.$('.J_menuTab[data-id="' + v + '"]').addClass("active");
		parent.$('.J_iframe[data-id="' + v + '"]').show();
		//var pm=parent.$(".J_iframe[data-id='"+v+"']").attr("menuno");
		//PLM.reloadMenu2(pm);
		parent.$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
		$(this).remove();
	});
	parent.$(".page-tabs-content").css("margin-left", "0");
}
/**
 * js去重复（以逗号分割的串）
 * @param str
 * @returns
 */
function filterRepeatStr(str) {
	var ar2 = str.split(",");
	var array = new Array();
	var j = 0
	for (var i = 0; i < ar2.length; i++) {
		if ((array == "" || array.toString().match(new RegExp(ar2[i], "g")) == null)
				&& ar2[i] != "") {
			array[j] = ar2[i];
			array.sort();
			j++;
		}
	}
	return array.toString();
}

//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function(arg) {
	return accDiv(this, arg);
};