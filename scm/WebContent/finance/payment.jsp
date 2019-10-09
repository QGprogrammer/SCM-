<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>收款登记</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="script/finance/payment.js"></script>
</head>
</head>
<body>
	<div id="m">
		<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
			  <tr>
				<td nowrap class="title1">财务收支 -- 付款登记</td>
			  </tr>
		</table>
		<table width="100%"  border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td nowrap="" class="toolbar">&nbsp;</td>
				<td width="60px" class="toolbar" >&nbsp;</td>
				<td width="20px" class="toolbar">|</td>
				<td width="60px" class="toolbar"><span class="toolBtn" onclick="pageSearch('2')">款到发货</span></td>
				<td width="20px" class="toolbar">|</td>
				<td width="80px" class="toolbar"><span class="toolBtn" onclick="pageSearch('3')">预付款到发货</span></td>
				<td width="20px" class="toolbar">|</td>
				<td width="60px" class="toolbar" ><span class="toolBtn" onclick="pageSearch('1')">货到付款</span></td>
				<td width="20px" class="toolbar">|</td>
			  </tr>
		</table>
	<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  <tr>
		<td class="title1">采购单编号</td>
		<td class="title1">创建时间</td>
		<td class="title1">供应商名称</td>
		<td class="title1">创建用户</td>
		<td class="title1">附加费用</td>
		<td class="title1">采购产品总价</td>
		<td class="title1">采购单总价格</td>
		<td class="title1">付款方式</td>
		<td class="title1">最低预付款金额</td>
		<td class="title1">处理状态</td>
		<td class="title1">操作</td>
	  </tr>
	  <c:forEach items="${pomainList}"  var="list">
	  <tr>
	  	<td align="center"><a href="javascript:void(0)" onclick="showItem(this)">${list.poId}</a></td>
	  	<td align="center">${list.createTime}</td>
	  	<td align="center">${list.venderName}</td>
	  	<td align="center">${list.account}</td>
	  	<td align="center">${list.tipfee}</td>
	  	<td align="center">${list.productTotal}</td>
	  	<td align="center">${list.poTotal}</td>
	  	<td align="center">
	  		<c:choose>
	  			<c:when test="${list.payType=='1'}">货到付款</c:when>
	  			<c:when test="${list.payType=='2'}">款到发货</c:when>
	  			<c:when test="${list.payType=='3'}">预付款到发货</c:when>
	  		</c:choose>
	  	</td>
	  	<td align="center">${list.prePayFee}</td>
	  	<td align="center">
	  		<c:choose>
	  			<c:when test="${list.status=='1'}">新增</c:when>
	  			<c:when test="${list.status=='2'}">已到货</c:when>
	  			<c:when test="${list.status=='3'}">已付款</c:when>
	  			<c:when test="${list.status=='4'}">已了结</c:when>
	  			<c:when test="${list.status=='5'}">已预付</c:when>
	  		</c:choose>
	  	</td>  
	  	<td align="center"><a href="javascript:void(0)" onclick="checkin(this)">登记</a></td>
	  </tr>
	  </c:forEach>
	</table>
	<div class="pageDiv">
		当前第<span id="currentPage">${page.currentPage}</span>页
		<input id="start" type="button" value="首页"  onclick="goPage('start','0')"/>
		<input id="up" type="button" value="上一页" onclick="goPage('up','0')"/>
		<input id="down" type="button" value="下一页" onclick="goPage('down','0')"/>
		<input id="end" type="button" value="末页" onclick="goPage('end','0')"/>
		<input id="jump" type="text" placeholder="跳转至" onchange="goPage('jump','0')" style="width:40px"/>
		共<span id="pageCount">${page.pageCount}</span>页  <span id="allcount">${page.allcount}</span>条记录
	</div>
</div>
<div id="item" style="display:none">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
		<tr>
			<td align="center" class="title1" width="100%">采购单详情</td>
		</tr>
		<tr>
		    <td width="30px" class="toolbar">&nbsp;</td>
			<td nowrap class="toolbar">&nbsp;</td>
		</tr>
	</table>
		<table id="headTable" width="100%"  border="0" align="center" class="a1">
		  <tr align="center">
		    <td>采购单编号</td>
		    <td id="soId">20170621142653</td>
		    <td>产品编号</td>
		    <td id="productCode">2018020306</td>
		  </tr>
		  <tr align="center">
		   	<td>产品数量单位</td>
		    <td id="unitName">kg</td>
		    <td>产品数量</td>
		    <td id="num">50</td>
		  </tr>
		   <tr align="center">
		   	<td>采购单价</td>
		    <td id="unitPrice">4.8</td>
		    <td>产品明细总价</td>
		    <td id="itemPrice">350</td>
		  </tr>
		</table>
		<br/>
		<input type="button" value="返回" onclick="back()" style="margin-left:50px;width:100px">
</div>
</body>
</html>