/**
 *
 * @param days
 *            天数,今天为0
 * @return
 */
function dateBefore(days) {
	var today = new Date();
	today.setDate(today.getDate() - days);
	return (today.getFullYear() || today.getYear())
			+ "-"
			+ (today.getMonth() < 9 ? "0" + (today.getMonth() + 1) : (today
					.getMonth() + 1)) + "-"
			+ (today.getDate() < 10 ? "0" + today.getDate() : today.getDate());
}
var Infinity = Number.NaN;
Number.prototype.toPercent = function() {
	if (isNaN(this))
		return "N/A";
	return (Math.round(this * 10000) / 100).toFixed(2) + '%';
}
Number.prototype.toFloat2 = function() {
	if (isNaN(this))
		return "N/A";
	return this.toFixed(2);
}

var CheckEmailRegex = /^\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/;
var CheckPasswordRegex = /^[\d\w_]{8,16}$/;
var CheckIntegerRegex = /^\\d+$/;
var CheckNumberRegex = /^\d+(.\d+)?$/;

//
function decodeUrl(inputStr) {
	var resultArr = [];
	for ( var i = 0; i < inputStr.length; i++) {
		var chr = inputStr.charAt(i);
		if (chr == "+") {
			resultArr[resultArr.length] = " ";
		} else if (chr == "%") {
			var asc = inputStr.substring(i + 1, i + 3);
			if (parseInt("0x" + asc) > 0x7f) {
				resultArr[resultArr.length] = decodeURI(inputStr.substring(i,
						i + 9));
				i += 8;
			} else {
				resultArr[resultArr.length] = String.fromCharCode(parseInt("0x"
						+ asc));
				i += 2;
			}
		} else {
			resultArr[resultArr.length] = chr;
		}
	}
	return resultArr.join("");
}
//
function showHideMenu(menuId) {
	var old = document.getElementById(menuId).style.display;
	if (old != "none")
		document.getElementById(menuId).style.display = "none";
	else
		document.getElementById(menuId).style.display = "block";
}
//
function readUrlParam(key) {
	url = window.location.href;
	q = url.substr(url.indexOf("?") + 1)
	ps = q.split("&");
	if (ps == null)
		return "";
	for (i = 0; i < ps.length; i++) {
		kv = ps[i].split("=");
		if (kv != null && kv[0] == key) {
			if (kv.length >= 2)
				return decodeUrl(kv[1]);
			else
				return "";
		}
	}
	return "";
}
//注册页面onload事件
function addLoadEvent(func) {
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
		window.onload = func;
	} else {
		window.onload = function() {
			oldonload();
			func();
		}
	}
}
/**
 * labelId:显示消息的控件ID
 * msg:显示内容
 * type:0 info_pass 绿色;
 *      1 info_tip 黄色;
 *      2 info_error 红色;
 */
function showInfo(labelId, msg, type) {

	var nClass = "info_pass";
	if (type == 1) { //
		nClass = "info_tip";
	} else if (type == 2) {
		nClass = "info_error";
	}
	document.getElementById(labelId).className = nClass;
	document.getElementById(labelId).innerHTML = msg;
}
/**
 * 显示消息，到期自动消失
 * lalID:显示消息的控件ID
 * msg:消息内容
 * type:0 info_pass 绿色;
 *      1 info_tip 黄色;
 *      2 info_error 红色;
 * second:自动消失延时,单位:秒
 */
function showAutoHideInfo(lblID, msg, type, second) {
	showInfo(lblID, msg, type);
	setTimeout(function() {
		showInfo(lblID, "", 0);
	}, second * 1000);
}
/**
 * field:需要检查的控件ID
 * labelId:显示检查结果的控件ID
 * errName:控件内容解释?
 * re:检测正则表达式
 * info:输入格式提示信息
 */
function checkRequired(field, labelId, errName, re, info) {
	var value = document.getElementById(field).value;
	var isOk = false;
	if (re != null && re != undefined) {
		isOk = (value.search(re) >= 0);
	}//通过正则表达式检测
	else {
		isOk = (value.length > 0);
	}//普通型通过长度检测
	if (!isOk) {//检测不通过
		if (value.length <= 0) {//没有输入!
			if (errName != undefined && errName != null) {
				showInfo(labelId, "请填写" + errName + "!", 2);
			} else {
				showInfo(labelId, "请填写该区域!", 2);
			}
		} else {
			if (errName != undefined && errName.length > 0) {
				if (info == undefined || info == null) {
					showInfo(labelId, errName + "格式错误!", 2);
				} else {
					showInfo(labelId, errName + "格式错误!必须为:" + info, 2);
				}
			} else {
				showInfo(labelId, "请按要求填写!", 2);
			}
		}
		return false;
	} else {//检测通过
		if (value.length > 0)
			showInfo(labelId, "OK!", 0);//OK
		else
			showInfo(labelId, "", 0);//正则表达式匹配允许空,就什么都不显示
		return true;
	}
}
function checkSame(source, compareto, labelID, errMsg) {
	errMsg = errMsg || "两次输入不一致";
	if (document.getElementById(source).value != document
			.getElementById(compareto).value) {
		showInfo(labelID, errMsg, 2);
	} else {
		showInfo(labelID, "OK", 0);
	}
}
/**
 * 自动截断字符串
 */
function autoCutOffString(s, maxLen) {
	maxLen = maxLen || 10;
	s = s || "";
	if (s.length > maxLen) {
		s = s.substr(0, maxLen) + "...";
	}
	return s;
}
/**
 * 在客户端自动截断Table中某列过长的内容,并在title中提示
 * @param tblID TableID
 * @param col 列索引,从1开始
 * @param maxLen 内容最大长度
 * @return
 */
function autoCutOffStrInTable(tblID, col, maxLen) {
	var tbl = document.getElementById(tblID);
	if (!tbl)
		return;
	for ( var i = 0; i < tbl.rows.length; i++) {
		if (tbl.rows[i].cells.length < col)
			continue;//列不够
		var old = tbl.rows[i].cells[col - 1].innerHTML;
		var nw = autoCutOffString(old, maxLen);
		if (old != nw) {
			tbl.rows[i].cells[col - 1].innerHTML = nw;
			tbl.rows[i].cells[col - 1].title = old;
		}
	}
}

/*********鼠标滑过提示框相关的JS代码**********/
var Geometry = {};
if (window.innerWidth) {
	Geometry.getHorizontalScroll = function() {
		return window.pageXOffset
	};
	Geometry.getVerticalScroll = function() {
		return window.pageYOffset
	};
} else if (document.documentElement && document.documentElement.clientWidth) {
	Geometry.getHorizontalScroll = function() {
		return document.documentElement.scrollLeft
	};
	Geometry.getVerticalScroll = function() {
		return document.documentElement.scrollTop
	};
} else if (document.body.clientWidth) {
	Geometry.getHorizontalScroll = function() {
		return document.body.scrollLeft
	};
	Geometry.getVerticalScroll = function() {
		return document.body.scrollTop
	};
}
function Tooltip() {
	this.tooltip = document.createElement("div");
	this.content = document.createElement("div");
	this.tooltip.className = "tooltip";
	this.content.className = "content";
	this.tooltip.style.display = "none";
	this.tooltip.style.position = "absolute";
	this.tooltip.style.backgroundColor = "#abc";
	this.tooltip.appendChild(this.content);
}
Tooltip.prototype.show = function(url, x, y) {
	this.content.innerHTML = "<a href='"
			+ url
			+ "' target='_blank'><img alt='这是什么意思?' src='images/question.png'>这是什么意思?</a>";
	this.tooltip.style.top = y + "px";
	this.tooltip.style.left = x + "px";
	this.tooltip.style.display = "block";
	if (this.tooltip.parentNode != document.body)
		document.body.appendChild(this.tooltip);
};
Tooltip.prototype.hide = function() {
	this.tooltip.style.display = "none";
};
Tooltip.prototype.schedule = function(target, e) {
	var anchor = target.getAttribute("anchor");
	if (!anchor)
		return;
	var x = e.clientX + Geometry.getHorizontalScroll() + 10;
	var y = e.clientY + Geometry.getVerticalScroll() - 30;
	var self = this;
	var timer = window.setTimeout(function() {
		self.show(anchor, x, y);
	}, 500);
	if (target.addEventListener)
		target.addEventListener("mouseout", mouseout, false);
	else if (target.attachEvent)
		target.attachEvent("onmouseout", mouseout);
	else
		target.onmouseout = mouseout;
	function mouseout() {
		setTimeout(function() {
			self.hide();
			window.clearTimeout(timer);
			if (target.removeEventListener)
				target.removeEventListener("mouseout", mouseout, false);
			else if (target.detachEvent)
				target.detachEvent("mouseout", mouseout);
			else
				target.onmouseout = null;
		}, 2000);
	}
};
var myTooltip = new Tooltip();
/*********鼠标滑过提示框相关的JS代码 end**********/
/**************下拉框相关JS代码****************/
//1.判断select选项中 是否存在Value="paraValue"的Item
function isSelectExitItem(objSelect, objItemValue) {
	var isExit = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isExit = true;
			break;
		}
	}
	return isExit;
}
//2.向select选项中 加入一个Item
function addItemToSelect(objSelect, objItemText, objItemValue) {
	//判断是否存在
	if (isSelectExitItem(objSelect, objItemValue)) {
	} else {
		var varItem = new Option(objItemText, objItemValue);
		objSelect.options.add(varItem);
	}
}
//3.从select选项中 删除一个Item
function removeItemFromSelect(objSelect, objItemValue) {
	//判断是否存在
	if (isSelectExitItem(objSelect, objItemValue)) {
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemValue) {
				objSelect.options.remove(i);
				break;
			}
		}
	}
}
//4.删除select中选中的项    
function removeSelectedItemFromSelect(objSelect) {
	var length = objSelect.options.length - 1;
	for ( var i = length; i >= 0; i--) {
		if (objSelect[i].selected == true) {
			objSelect.options[i] = null;
		}
	}
}
//5.修改select选项中 value="paraValue"的text为"paraText"
function updateItemToSelect(objSelect, objItemText, objItemValue) {
	//判断是否存在
	if (isSelectExitItem(objSelect, objItemValue)) {
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemValue) {
				objSelect.options[i].text = objItemText;
				break;
			}
		}
	}
}
//6.清空select的项
function clearSelectItems(objSelect) {
	objSelect.options.length = 0;
}
//7.根据select控件选项的文本设置选中项目
function setSelectByText(id, value) {
	var select = document.getElementById(id);
	for ( var i = 0; i < select.options.length; i++) {
		if (select.options[i].text == value) {
			select.options[i].selected = true;
			break;
		}
	}
}
//8.根据select控件选项的value设置选中项目
function setSelectByValue(id, value) {
	var select = document.getElementById(id);
	for ( var i = 0; i < select.options.length; i++) {
		if (select.options[i].value == value) {
			select.options[i].selected = true;
			break;
		}
	}
}
/**************下拉框相关JS代码  END****************/
function showHelp(key) {
	keys = key.split(",");
	contents = "";
	titles = "";
	for (i = 0; i < keys.length; i++) {
		contents += nearme_help[keys[i]].content + "<br/><br/>";
		titles += nearme_help[keys[i]].title + ",";
	}
	div_dialog = document.getElementById("div_dialog");
	if (div_dialog == null) {
		div_dialog = document.createElement('div');
		div_dialog.id = "div_dialog";
		document.body.appendChild(div_dialog);
	}
	//div_dialog.title=titles.substring(0, titles.length-1);
	div_dialog.innerHTML = contents;
	$("#div_dialog").dialog( {
		width : "700px",
		modal : true,
		title : titles.substring(0, titles.length - 1)
	});
}
function getInnerText(obj) {
	if (obj.innerText) {
		return obj.innerText;
	}
	return obj.textContent;
}
/**
 * 将表格列数据显示成FCF单曲线
 * order：
 * font-2-end  顺序(默认)
 * end-2-front 倒序
 */
function table2FCFSingleLineChart(tblid,tindex,order){
	tbl = document.getElementById(tblid);
	var length = tbl.rows.length;
	xml = "<graph caption='" + getInnerText(tbl.rows[0].cells[tindex]).trim() + "'"
	    + " rotateNames='0' showValues='0'"
	    + " baseFontColor='5904FF' baseFontSize='12'" 
	    + " animation='1' showAlternateHGridColor='1'"
	    + " AlternateHGridColor='ff5904' alternateHGridAlpha='5'"
	    + " divLineColor='ff5904' divLineAlpha='20'"
	    + " canvasBorderColor='666666' baseFontColor='666666'"
	    + " lineColor='FF5904' lineAlpha='85'"
	    + " numdivlines='9'"
	    + " lineThickness='2' numVisiblePlot='31'"
	    + " numVDivLines='" + (length - 1) + "'"
	    + " labelDisplay='ROTATE' slantLabels='1'>";
	
	if(order=='end-2-front'){
		xml += "<categories fontSize='11'>";
		for(i=length - 1;i>=1;i--){
			xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).substr(0,32).trim() + "' />";
	    }
		xml += "</categories>";
		
		xml += "<dataset>";
		for(i=length - 1;i>=1;i--){
			xml += "<set value='" + getInnerText(tbl.rows[i].cells[tindex]).trim() + "'"
			+ " showName='1' color='FF5904'/>";
	    }
		xml += "</dataset>";
	} else {
		xml += "<categories fontSize='11'>";
		for(i=1;i<tbl.rows.length;i++){
			xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).substr(0,32).trim() + "' />";
	    }
		xml += "</categories>";
		
		xml += "<dataset>";
		for(i=1;i<tbl.rows.length;i++){
			xml += "<set value='" + getInnerText(tbl.rows[i].cells[tindex]).trim() + "'"
			+ " showName='1' color='FF5904'/>";
	    }
		xml += "</dataset>";
	}
	
	return xml + "</graph>";
}

/**
 * 将表格列数据显示成FCF单曲线
 * order：
 * font-2-end  顺序(默认)
 * end-2-front 倒序
 */
function table2MSColumn3DChart(tblid, title){
	tbl = document.getElementById(tblid);
	var length = tbl.rows.length;
	var cells = tbl.rows[0].cells.length;
	xml="<graph xaxisname='' yaxisname='' hovercapbg='DEDEBE' hovercapborder='889E6D' rotateNames='0' " +
		"yAxisMaxValue='100' numdivlines='9' divLineColor='CCCCCC' divLineAlpha='80' decimalPrecision='0' " +
		"showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC' caption='" +
		title +
		"' subcaption=''>"
	
	xml += "<categories fontSize='11'>";
	for(i=1;i<cells;i++){
		xml += "<category name='" + getInnerText(tbl.rows[0].cells[i]).trim() + "' />";
    }
	xml += "</categories>";
	
	for(i=1;i<length;i++){
		xml += "<dataset seriesname='" +
		getInnerText(tbl.rows[i].cells[0]).trim() +
		"'>";
		for(j=1;j<cells;j++){
			xml += "<set value='" + getInnerText(tbl.rows[i].cells[j]).trim() + "'"
		+ " showName='1'/>";
		}
		xml += "</dataset>";
    }
		
	return xml + "</graph>";
}

/**
 * 将表格列数据显示成FCF单曲线(周明细)
 * order：
 * font-2-end  顺序(默认)
 * end-2-front 倒序
 */
function table2FCFSingleLineChart4Week(tblid,tindex,order){
	tbl = document.getElementById(tblid);
	var length = tbl.rows.length;
	xml = "<graph caption='" + getInnerText(tbl.rows[0].cells[tindex]).trim() + "'"
	    + " rotateNames='0' showValues='0'"
	    + " baseFontColor='5904FF' baseFontSize='12'" 
	    + " animation='1' showAlternateHGridColor='1'"
	    + " AlternateHGridColor='ff5904' alternateHGridAlpha='5'"
	    + " divLineColor='ff5904' divLineAlpha='20'"
	    + " canvasBorderColor='666666' baseFontColor='666666'"
	    + " lineColor='FF5904' lineAlpha='85'"
	    + " numdivlines='9'"
	    + " lineThickness='2' numVisiblePlot='31'"
	    + " numVDivLines='" + (length - 1) + "'"
	    + " labelDisplay='ROTATE' slantLabels='1'>";
	
	if(order=='end-2-front'){
		xml += "<categories fontSize='11'>";
		for(i=length - 1;i>=1;i--){
			if(getInnerText(tbl.rows[i].cells[0]).trim().toString().length == 16){
				xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).trim().toString().substring(4,9) + "' />";
			} else {
				xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).trim().toString().substring(5,10) + "' />";
			}
	    }
		xml += "</categories>";
		
		xml += "<dataset>";
		for(i=length - 1;i>=1;i--){
			xml += "<set value='" + getInnerText(tbl.rows[i].cells[tindex]).trim() + "'"
			    + " showName='1' color='FF5904'/>";
	    }
		xml += "</dataset>";
	} else {
		xml += "<categories fontSize='11'>";
		for(i=1;i<tbl.rows.length;i++){
			if(getInnerText(tbl.rows[i].cells[0]).trim().toString().length == 16){
				xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).trim().toString().substring(4,9) + "' />";
			} else {
				xml += "<category label='" + getInnerText(tbl.rows[i].cells[0]).trim().toString().substring(5,10) + "' />";
			}
	    }
		xml += "</categories>";
		
		xml += "<dataset>";
		for(i=1;i<tbl.rows.length;i++){
			xml += "<set value='" + getInnerText(tbl.rows[i].cells[tindex]).trim() + "'"
			    + " showName='1' color='FF5904'/>";
	    }
		xml += "</dataset>";
	}
	
	return xml + "</graph>";
}

/**
 * 将表格列数据显示成FCF条形图
 */
function table2FCFBar2DChart(tblid,tindex){
	colors = ["AFD8F8","F6BD0F","8BBA00","FF8E46","008E8E","D64646","8E468E","588526",
	"B3AA00","008ED6","9D080D","A186BE"]
	colnum = colors.length;
	tbl = document.getElementById(tblid);
	xml = "<graph caption='" + getInnerText(tbl.rows[0].cells[tindex]).trim()
	         + "' xAxisName='"
	         + "' yAxisName='"
	         + "' decimalPrecision='0"
	         + "' formatNumberScale='0"
	         + "' chartRightMargin='30'" 
	         + " rotateNames='0' showValues='0' baseFontColor='5904FF' baseFontSize='12'>";
	var length = tbl.rows.length;
	var t = parseInt(length/20);
	for(i=1;i<length;i++){
		if(t > 0 && i%(2*t) != 0){
		} else {
		    xml += "<set name='" + getInnerText(tbl.rows[i].cells[0]).trim()
		        + "' value='" + getInnerText(tbl.rows[i].cells[tindex]).trim()
		        + "' color='" + colors[(i-1) % colnum] + "'/>";
		}
	}
	return xml + "</graph>";
}
/**
 * 生成多曲线
 * @param tblid tableID
 * @param tindexs 多个列索引的数组
 * @param caption 图标的标题
 * @return
 */
function table2FCFMultiLineChart(tblid,tindexs,caption){
	colors = ["1D8BD1","F1683C","2AD62A","DBDC25","56B9F9"]
	tbl = document.getElementById(tblid);
	xml = "<graph caption='" + caption + "' showvalues='0' rotateNames='0' baseFontColor='5904FF' baseFontSize='12'><categories  fontSize='11'>";
	for(i=1;i<tbl.rows.length;i++){
		xml += "<category name='"+getInnerText(tbl.rows[i].cells[0]).trim()+"'/>"
	}
	xml += "</categories>";
	
	for(i=0;i<tindexs.length;i++){
		xml += "<dataset seriesName='"+getInnerText(tbl.rows[0].cells[tindexs[i]]).trim()
			+"' color='"+colors[i]+"' anchorBorderColor='"+colors[i]+"' anchorBgColor='"+colors[i]+"'>"
		for(j=1;j<tbl.rows.length;j++){
			xml += "<set value='"+getInnerText(tbl.rows[j].cells[tindexs[i]]).trim()+"'/>";
		}
		xml += "</dataset>";
	}
	
	return xml + "</graph>";
}
/**
 * 生成多曲线
 * @param tblid tableID
 * @param tindexs 多个列索引的数组
 * @param caption 图标的标题
 * @param order 
 *        font-2-end  顺序(默认)
 *        end-2-front 倒序
 * @return
 */
function table2FCFMultiLineChart(tblid,tindexs,caption,order){
	colors = ["1D8BD1","2AD62A","560BF9","F1683C","DBDC25"]
	tbl = document.getElementById(tblid);
	var length = tbl.rows.length;
	var t = parseInt(length/32);
	xml = "<chart caption='" + caption + "' showvalues='0' rotateNames='0' baseFontColor='5904FF' baseFontSize='12' " 
	+ " subcaption='' legendPosition='BOTTOM'"
	+ " divLineIsDashed='1' palette1='3' pixelsPerPoint='15' numMinorLogDivLines='3'"
	+ " paletteThemeColor1='FF0000' anchorMinRenderDistance='20' showToolTip='1'"
	+ " connectNullData='1' rotateValues='1' palette='4' compactDataMode='1'"
	+ " numVDivLines='" + (length>31 ? length/(2*t) - 3:length - 3) + "' labelDisplay='ROTATE' slantLabels='1'"
	+ " ><categories fontSize='11'>";
	
	if(order="end-2-front"){
		for(i=length - 1;i>=1;i--){
			if(t > 0 && i%(2*t) != 0){
			} else {
		        xml += "<category name='"+getInnerText(tbl.rows[i].cells[0]).trim()+"'/>"
	        }
		}
		xml += "</categories>";
		
	    for(i=0;i<tindexs.length;i++){
		    xml += "<dataset seriesName='"+getInnerText(tbl.rows[0].cells[tindexs[i]]).trim()
			    +"' color='"+colors[i]+"' anchorBorderColor='"+colors[i]+"' lineThickness='1'>"
		    
			for(j=length - 1;j>=1;j--){
				if(t > 0 && j%(2*t) != 0){
			    } else {
			        xml += "<set value='"+getInnerText(tbl.rows[j].cells[tindexs[i]]).trim()+"'/>";
		        }
			}
		    xml += "</dataset>";
	    }
	} else {
		for(i=1;i<tbl.rows.length;i++){
		    xml += "<category name='"+getInnerText(tbl.rows[i].cells[0]).trim()+"'/>"
	    }
	    xml += "</categories>";
	
	    for(i=0;i<tindexs.length;i++){
		    xml += "<dataset seriesName='"+getInnerText(tbl.rows[0].cells[tindexs[i]]).trim()
			    +"' color='"+colors[i]+"' anchorBorderColor='"+colors[i]+"' anchorBgColor='"+colors[i]+"'>"
		    for(j=1;j<tbl.rows.length;j++){
			    xml += "<set value='"+getInnerText(tbl.rows[j].cells[tindexs[i]]).trim()+"'/>";
		    }
		    xml += "</dataset>";
	    }
	}
	
	return xml + "</chart>";
}
/**
 * 将表格列数据显示成FCF饼图
 * tblid:
 * tindex:
 * limit:
 */
function table2FCFPieChart(tblid,tindex,limit){
	tbl = document.getElementById(tblid);
	rowlen = tbl.rows.length;
	if(limit<=0){limit=9999999;}
	xml = "<graph rotateNames='0' showValues='1' baseFontColor='5904FF' baseFontSize='12' decimalPrecision='0' caption='"+getInnerText(tbl.rows[0].cells[tindex]).trim()+"'>";
	for(i=1;i<rowlen&&i<=limit;i++){
		xml += "<set name='" + getInnerText(tbl.rows[i].cells[0]).trim()
			+ "' value='" + getInnerText(tbl.rows[i].cells[tindex]).trim() + "'/>";
	}
	if(rowlen>limit){
		var other=0;
		for(i=limit;i<rowlen;i++){
			other = Number(getInnerText(tbl.rows[i].cells[tindex]).trim()) + other;
		}
		xml += "<set name='Other' value='" + other + "'/>";
	}
	return xml + "</graph>";
}
//将table生成Excel表格,只支持IE
function table2Excel(tblid){
	tbl = document.getElementById(tblid);
	if(tbl == undefined){
		return "";
	}
	var i,j;
	try {
		var xls = new ActiveXObject("Excel.Application");
	}catch(e) {
		alert("目前该功能只支持IE系列浏览器\r\n另外您必须安装Excel");
		return "";
	}
	
	xls.visible =true;  //设置excel为可见
	var xlBook = xls.Workbooks.Add;
    var xlsheet = xlBook.Worksheets(1);
    for(var row=0;row<tbl.rows.length;row++){
    	for(var cell=0;cell<tbl.rows[row].cells.length;cell++){
    		xlsheet.Cells(row+2,cell+2).Value=getInnerText(tbl.rows[row].cells[cell]);
    	}
    }
    xls.UserControl = true;  //很重要,不能省略,不然会出问题 意思是excel交由用户控制
    xls=null;
    xlBook=null;
    xlsheet=null;
}
/**
 * 定制js函数：将制定格式的json数据填充、合并到Table中
 * @param tblid
 * @param json
 * @param rowidxs 填充到哪些列,数组
 * @param createrow 原表中无基准行,是否添加行
 * @return
 */
function fillTblWithJson(tblid,json,rowidxs,createrow){
	var tbl = document.getElementById(tblid);
	for(var i=1;i<tbl.rows.length;i++){
		dt = tbl.rows[i].cells[0].innerHTML;
		for(rptr=0;rptr<json.datas.length;rptr++){
			if(dt != json.datas[rptr].date)continue;
			for(j=0;j<rowidxs.length;j++){
				if(rowidxs[j]<=0)continue;
				tbl.rows[i].cells[rowidxs[j]].innerHTML=json.datas[rptr].value[j];
			}
			json.datas.splice(rptr,1);
			break;
		}
	}
	if(createrow==true&&json.datas.length>0){
		for(i=0;i<json.datas.length;i++){
			tbl.appendChild(tbl.rows[0].cloneNode(true));
			for(j=1;j<tbl.rows[0].cells.length;j++){
				tbl.rows[tbl.rows.length-1].cells[j].innerHTML = "0";
			}
			tbl.rows[tbl.rows.length-1].cells[0].innerHTML=json.datas[i].date;
			for(j=0;j<rowidxs.length;j++){
				if(rowidxs[j]<=0)continue;
				tbl.rows[tbl.rows.length-1].cells[rowidxs[j]].innerHTML=json.datas[i].value[j];
			}
		}
	}
}

/*********表格排序相关*********/
/**
 * 删除表格全部行
 */
function clearTableRows(tblid){
	tbl = document.getElementById(tblid);
	while(tbl.rows.length>1){
		tbl.rows[1].parentNode.removeChild(tbl.rows[1]);
	}
}
/**
 * 表格排序
 * tblid:
 * idx:排序列索引
 * sort:function自定义排序,0字符升序,1字符降序,2数字升序,3数字降序
 */
function tableSort(tblid,idx,sort){
	//保存原始表格每一行、每一列数据
	var tblOldData = new Array();
	var tblOldStyles = new Array();
	tbl = document.getElementById(tblid);
	for(i=1;i<tbl.rows.length;i++){
		tblOldData[i-1] = new Array();
		for(j=0;j<tbl.rows[i].cells.length;j++){
			tblOldData[i-1][j] = tbl.rows[i].cells[j].innerHTML;
		}
		tblOldStyles[i-1] = tbl.rows[i].style.background;
	}
	//清空原来表格数据
	clearTableRows(tblid);
	
	//比较函数的参数函数
	var compare_down_num = function(a,b){return Number(a[idx])-Number(b[idx]);}
	var compare_up_num = function(a,b){return Number(b[idx])-Number(a[idx]);}
	var compare_down_str = function(a,b){return a[idx]>b[idx];}
	var compare_up_str = function(a,b){return b[idx]>a[idx];}
	
	//重新对TR进行排序
	tblOldData.sort(typeof(sort)=="function"?sort:(sort==0?compare_down_str:
												sort==1?compare_up_str:
												sort==2?compare_down_num:compare_up_num));
	
	//数据重新写回表格
	for(i=0;i<tblOldData.length;i++){
		tbl.appendChild(tbl.rows[0].cloneNode(true));
		for(j=0;j<tblOldData[i].length;j++){
			tbl.rows[i+1].cells[j].innerHTML = tblOldData[i][j];
		}
		tbl.rows[tbl.rows.length-1].style.background=tblOldStyles[i];
	}
}