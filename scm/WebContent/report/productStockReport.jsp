<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basepath%>">
<meta charset="UTF-8">
<title>产品库存报表</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="script/report/productStockReport.js"></script>
</head>
</head>
<body>
	<div id="m">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
			<tr>
				<td nowrap class="title1">业务报表 -- 产品库存报表</td>
			</tr>
			<tr>
				<td class="toolbar">&nbsp;</td>
				<td width="60px" class="toolbar">&nbsp;</td>
			</tr>
		</table>
		<div class="query_div" align="center">
			<form>
				年份： <select name="year">
					<option value="0">请选择</option>
					<option value="2016">2016年</option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
				</select> 月份： <select name="month">
					<option value="0">请选择</option>
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select> <input type="button" value="查询" onclick="search()"
					style="margin-left: 30px; width: 50px" />
			</form>
		</div>
		<table width="100%" border="0" align="center" cellspacing="1"
			class="c">
			<tr>
				<td class="title1">产品编号</td>
				<td class="title1">产品名称</td>
				<td class="title1">产品库存</td>
			</tr>
		</table>
	</div>
	<div id="item" style="display: none">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
			<tr>
				<td align="center" class="title1" width="100%" id="title">收款明细</td>
			</tr>
			<tr>
				<td width="30px" class="toolbar">&nbsp;</td>
				<td nowrap class="toolbar">&nbsp;</td>
			</tr>
		</table>
		<table id="headTable" width="100%" border="0" align="center"
			class="a1">
			<tr align="center">
				<td class="title1">产品编号</td>
				<td class="title1">产品名称</td>
				<td class="title1">产品库存</td>
			</tr>
			<tr align="center" id="tr2">
				<td></td>
				<td></td>
				<td></td>
			</tr>

		</table>
		<br /> <input type="button" value="返回" onclick="back()"
			style="margin-left: 50px; width: 100px">
	</div>
	<div class="pageDiv" id="page">
			当前第<span id="currentPage">1</span>页 <input
				id="start" type="button" value="首页" onclick="goPage('start')" />
			<input id="up" type="button" value="上一页" onclick="goPage('up')" />
			<input id="down" type="button" value="下一页"
				onclick="goPage('down','0')" /> <input id="end" type="button"
				value="末页" onclick="goPage('end')" /> <input id="jump"
				type="text" placeholder="跳转至" onchange="goPage('jump')"
				style="width: 40px" /> 共<span id="pageCount">1</span>页
			<span id="allcount">0</span>条记录
		</div>
</body>
</html>