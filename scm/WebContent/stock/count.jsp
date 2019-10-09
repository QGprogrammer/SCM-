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
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script type="text/javascript"src="script/json2.js"></script>
<script type="text/javascript"src="script/stock/count.js"></script>
<title>库存盘点</title>
</head>
<body>
	<div id="m">
			<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
			  <tr>
				<td nowrap class="title1">仓储管理 -- 库存盘点</td>
			  </tr>
			</table>
			<table width="100%"  border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td nowrap="" class="toolbar">&nbsp;</td>
			  </tr>
			</table>
	</div>
	<div id="dataList">
				<table width="100%" border="0" align="center" cellspacing="1" class="c">
					<tr>
						<td class="title1">产品编号</td>
						<td class="title1">产品名称</td>
						<td class="title1">当前库存</td>
						<td class="title1">采购在途数</td>
						<td class="title1">预销售数</td>
						<td class="title1" colspan="4">操作</td>
					</tr>
					<c:forEach items="${productList}" var="p">
						<tr>
							<td align="center">${p.productCode}</td>
							<td align="center">${p.name}</td>
							<td align="center">${p.num}</td>
							<td align="center">${p.poNum}</td>
							<td align="center">${p.soNum}</td>
							<td align="center">
								变化数量: <input type="text" size="15px">
							</td>
							<td align="center">
								损益原因: <input type="text" size="15px">
							</td>
							<td align="center">
								变化类型: <select>
											<option value="waste">损耗</option>
											<option value="profit">盈余</option>
										</select>
							</td>
							<td align="center" >
								<a onclick="save(this)" style="margin-right:20px">保存</a>
							</td>
						</tr>
					</c:forEach>
				</table>
	</div>
	<div class="pageDiv">
					当前第<span id="currentPage">${page.currentPage}</span>页
					
						<input type="button" value="首页"  onclick="goPage('start')"/>
						<input type="button" value="上一页" onclick="goPage('up')"/>
						<input type="button" value="下一页" onclick="goPage('down')"/>
						<input type="button" value="末页" onclick="goPage('end')"/>
						<input id="jump" type="text" placeholder="跳转至" onchange="goPage('jump')" style="width:40px"/>
					
					共<span id="pageCount">${page.pageCount}</span>页  <span id="allcount">${page.allcount}</span>条记录
				</div>
</body>
</html>