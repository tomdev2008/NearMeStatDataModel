/**
 * 用户设置相关UI接口
 *
 * @author 刘 超
 * @version 1.0
 * @since 1.0,2011-8-31
 */

$(document).ready(function() {
			$("#divDialog").dialog({
						modal : false,
						autoOpen : false,
						opacity : 0.9,
						position : 'center'
					});
		});

/**
 * 显示对话框
 *
 * @param {String}
 *            src 填充页面
 * @param {String}
 *            w 对话框宽度
 * @param {String}
 *            h 对话框高度
 * @param {String}
 *            title 对话框标题
 * @param {String}
 *            pos 对话框位置
 * @param {String}
 *            divID 页面寄宿DIV层
 * @param {String}
 *            ifrmID 页面寄宿iframe
 */
function showDialog(src, w, h, title, pos, divID, ifrmID) {
	$("#" + ifrmID).attr("src", "about:blank");
	$("#" + ifrmID).attr("src", src);
	$("#" + divID).dialog("option", "title", title);
	$("#" + divID).dialog("option", "width", w);
	$("#" + divID).dialog("option", "heigth", h);
	$("#" + divID).dialog("open");
	if (pos != null) {
		$("#" + divID).dialog("option", "position", pos);
	}
}

/**
 * 关闭对话框
 *
 * @param {String}
 *            divID 页面寄宿DIV层
 * @param {String}
 *            ifrmID 页面寄宿iframe
 */
function closeDialog(divID, ifrmID) {
	$("#" + ifrmID).attr("src", "about:blank");
	$("#" + ifrmID).attr("src", "");
	$("#" + divID).dialog("close");

	location.href = "login.jsp";
}

function destroyDialog(divID) {
	$("#" + divID).dialog("destroy");
}

/**
 * 显示接口实例对话框
 */
function showDetailInfo(url, title) {
	showDialog(url, "550", "450",
			title, "right", "divDialog", "ifrmDialog");
	$("#ifrmDialog").css("height", "400px");
}
