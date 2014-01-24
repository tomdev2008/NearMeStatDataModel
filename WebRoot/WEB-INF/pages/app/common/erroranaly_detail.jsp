<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:hidden id="id_model_distribute" value=""></s:hidden>
<s:hidden id="id_version" value=""></s:hidden>
<div class="title">
	<input type="button" value="返回" onclick="showGeneral()" />
	错误信息
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a class="errordetail" id="id_detail" href="#"  onclick="showErrorDetal()">错误信息</a>
	<a class="errordetail" id="id_model_distribute" href="#" onclick="showModelDistribute()">机型分布</a>
</div>

<div id="div_detail_model_distribute" style="display: none;"></div>
<pre id="div_detail_content" style="display: block;"></pre>
