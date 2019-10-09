
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
<title>用户管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script src="script/laydate/laydate.js"></script>
<script type="text/javascript" src="script/scmuser/scmuser.js"></script>
</head>

	<body>
	<div id="m">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
	  <tr>
		<td nowrap class="title1">您的位置：系统管理－－用户管理</td>
	  </tr>
	</table>
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td width="30px" nowrap class="toolbar">&nbsp;</td>
		<td width="40px" nowrap class="toolbar" id="newBtn" onClick="update('add')"><img src="images/new.gif">新增</td>
		<td nowrap class="toolbar">&nbsp;</td>
		<td width="60px" nowrap class="toolbar">&nbsp;</td>
		<td width="20px" nowrap class="toolbar">&nbsp;</td>
		<td width="60px" nowrap class="toolbar">&nbsp;</td>
		<td width="20px" nowrap class="toolbar">&nbsp;</td>
		<td width="60px" nowrap class="toolbar" >&nbsp;</td>
		<td width="20px" nowrap class="toolbar">&nbsp;</td>
		<td width="60px" nowrap class="toolbar">&nbsp;</td>
		<td width="20px" nowrap class="toolbar">&nbsp;</td>
	  </tr>
	</table>
	<div class="query_div">
		<form>
			用户账号：<input type="text" name="account"/>
			用户姓名：<input type="text" name="username"/>
			添加日期：
			<input type="text" readonly="readonly"  placeholder="请选择日期" id="time1" name="time1">
			-
			<input type="text" readonly="readonly"  placeholder="请选择日期"  id="time2" name="time2">
			
			锁定状态：
			<input type="radio" value="2" name="status"/>全部
			<input type="radio" value="1" name="status"/>锁定
			<input type="radio" value="0" name="status" />正常
			<input type="button" value="查询" onclick="search()"/>
		</form>
	</div>
	<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  <tr>
		<td class="title1">用户账号</td>
		<td class="title1">用户姓名</td>
		<td class="title1">添加日期</td>
		<td class="title1">锁定状态</td>
		<td class="title1">用户权限列表</td>
		<td class="title1">操作</td>
	  </tr>
	  <c:forEach items="${scmuserList}" var="user">
	  		<tr>
				<td align="center">${user.account}</td>
				<td align="center">${user.name}</td>
				<td align="center">${user.createDate}</td>
				<td align="center">${user.status==0?'未锁定':'已锁定'}</td>
				<td align="center">${user.modelCode}</td>
				<td align="center"><a href="javascript:void(0)" onClick="modify(this)">修改</a> <a href="javascript:void(0)" onclick="del(this)">删除</a></td>
	 		 </tr>
	  </c:forEach>
	</table>
		<div class="pageDiv">
			当前第<span id="currentPage">${page.currentPage}</span>页
			<input type="button" value="首页"  onclick="goPage('start')"/>
			<input type="button" value="上一页" onclick="goPage('up')"/>
			<input type="button" value="下一页" onclick="goPage('down')"/>
			<input type="button" value="末页" onclick="goPage('end')"/>
			<input id="jump" type="text" placeholder="跳转至" onchange="goPage('jump')" style="width:40px"/>
			共<span id="pageCount">${page.pageCount}</span>页  <span id="allcount">${page.allcount}</span>条记录
		</div>
	</div>
	<div id="add" class="hidd">
		<form>
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  		<tr>
	  			<td class="title1">&nbsp;</td>
	  			<td class="title1">&nbsp;</td>
			</tr>
			 <tr>
				<td align="center" width="30%">用户账号</td>
				<td width="40%"><input style="margin-left:130px" type="text" name="account" onchange="verifyAccount()"/><span id="accountMes"></span></td>
			</tr>
			<tr>
				<td align="center" width="30%">用户名</td>
				<td width="40%"><input type="text" style="margin-left:130px" name="username" onchange="verifyName('add')"/><span id="nameMes"></span></td>
			</tr>
			<tr>
				<td align="center" width="30%">密码</td>
				<td width="40%"><input type="password" style="margin-left:130px" name="password" onchange="verifyPassword('add')"/><span id="passwordMes"></span></td>
			</tr>
			<tr>
				<td align="center">锁定状态</td>
				<td align="center"><input type="radio" name="status" value="1"/>是 <input type="radio" name="status" value="0" checked="checked"/>否</td>
			</tr>
	  		<tr>
				<td align="center">用户权限</td>
				<td align="center"><input type="checkbox" value="1" onclick="check(this,'add')" name="modelCode"/>管理员<input type="checkbox" value="2" name="modelCode"/>采购<input type="checkbox" value="3" name="modelCode"/>仓管
				<input type="checkbox" value="5" name="modelCode"/>销售 <input type="checkbox" value="4" name="modelCode"/>财务<input type="checkbox" value="6" checked="checked" name="modelCode"/>月度报表
				</td>
			</tr>
	 	 	<tr>
				<td height="18" colspan="2" align="center"><input type="button" onclick="update('m')" value="返回">   <input type="button" onclick="clearAll('add')" value="重置"/>   <input type="button" value="保存" id="save" disabled="true" onclick="saveUser('add')"/></td>
			</tr>
			</table>
		</form>
	</div>
	<div id="modify" class="hidd">
	<form>
		<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  	<tr>
	  		<td class="title1">&nbsp;</td>
	  		<td class="title1">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" width="30%">用户账号</td>
			<td id="acc" width="40%"><input style="margin-left:130px" type="text" name="account" readonly="readonly"/></td>
		</tr>
	 	<tr>
			<td align="center" width="30%">用户名</td>
			<td width="40%"><input type="text" style="margin-left:130px" name="username" onchange="verifyName('modify')"/><span id="nameMes"></span></td>
		</tr>
		<tr>
			<td align="center" width="30%">密码</td>
			<td width="40%"><input type="password" style="margin-left:130px" name="password" onchange="verifyPassword('modify')"/><span id="passwordMes"></span></td>
		</tr>	
	 	<tr>
			<td align="center">锁定状态</td>
			<td align="center"><input type="radio" name="status" value="1"/>是 <input type="radio" name="status" value="0" checked="checked"/>否</td>
		</tr>
		<tr>
			<td align="center">用户权限</td>
			<td align="center"><input type="checkbox" value="1" onclick="check(this,'modify')" name="modelCode"/>管理员<input type="checkbox" value="2" name="modelCode"/>采购<input type="checkbox" value="3" name="modelCode"/>仓管
			<input type="checkbox" value="5" name="modelCode"/>销售 <input type="checkbox" value="4" name="modelCode"/>财务<input type="checkbox" value="6" checked="checked" name="modelCode"/>月度报表
			</td>
		</tr>
	  	<tr>
			<td height="18" colspan="2" align="center"><input type="button" onclick="update('m')" value="返回">   <input type="button" onclick="clearAll('modify')" value="重置"/>   <input type="button" value="保存" id="save" disabled="disabled" onclick="saveUser('modify')"/></td>
		</tr>
		</table>
	</form>
	</div>
	</body>
</html>