<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basepath%>">
<meta charset="UTF-8">
<title>收支查询</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script src="script/laydate/laydate.js"></script>
<script type="text/javascript" src="script/finance/payAndProSearch.js"></script>
</head>

	<body>
	<div id="m">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
	  <tr>
		<td nowrap class="title1">财务收支 -- 收支查询</td>
	  </tr>
	</table>
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td width="30px"  class="toolbar">&nbsp;</td>
		<td width="60px"  class="toolbar">&nbsp;</td>
		<td width="60px"  class="toolbar">&nbsp;</td>
		<td width="20px"  class="toolbar">&nbsp;</td>
		<td width="60px"  class="toolbar">&nbsp;</td>
		<td width="20px"  class="toolbar">&nbsp;</td>
		<td width="60px"  class="toolbar" >&nbsp;</td>
		<td width="20px"  class="toolbar">&nbsp;</td>
		<td width="60px"  class="toolbar">&nbsp;</td>
		<td width="20px"  class="toolbar">&nbsp;</td>
	  </tr>
	</table>
	<div class="query_div">
		<form>
			相关单据号：<input type="text" name="ordercode"/>
			单据付款方式：<select name="payType">
				<option value="0">请选择</option>
				<option value="1">货到付款</option>
				<option value="2">款到发货</option>
				<option value="3">预付款到发货</option>
			</select>
			登记日期：
			<input type="text" readonly="readonly"  placeholder="请选择日期" id="time1" name="time1">
			-
			<input type="text" readonly="readonly"  placeholder="请选择日期"  id="time2" name="time2">
			收支类型：
			<input type="radio" value="1" name="type" checked="checked"/>收款
			<input type="radio" value="0" name="type" />付款
			<input type="button" value="查询" onclick="search()"/>
		</form>
	</div>
	<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  <tr>
		<td class="title1">相关单据号</td>
		<td class="title1">付款/收款日期</td>
		<td class="title1">付款/收款金额</td>
		<td class="title1">经办人</td>
		<td class="title1">单据付款方式</td>
	  </tr>
	</table>
		<div class="pageDiv">
			当前第<span id="currentPage">${1}</span>页
			<input type="button" value="首页"  onclick="goPage('start')"/>
			<input type="button" value="上一页" onclick="goPage('up')"/>
			<input type="button" value="下一页" onclick="goPage('down')"/>
			<input type="button" value="末页" onclick="goPage('end')"/>
			<input id="jump" type="text" placeholder="跳转至" onchange="goPage('jump')" style="width:40px"/>
			共<span id="pageCount">${1}</span>页  <span id="allcount">${1}</span>条记录
		</div>
	</div>
	</body>
</html>