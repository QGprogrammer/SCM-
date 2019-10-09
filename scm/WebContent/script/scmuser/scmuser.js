laydate.render({
	elem:'#time1',
	type: 'date',
	trigger:'click'
});
/**
 *日历结束日期
 * @returns
 */
laydate.render({
	elem:'#time2',
	type: 'date',
	trigger:'click'
});


/**
 * 全局变量 用来控制分页查询是对全部用户查询还是过滤查询
 */
var con=0;
/**
 * 
 */



/**
 *复选框的点击事件
 */
$(function(){
	$("#add :checkbox").click(function(){
		isSubmit('add');
	});
	$("#modify :checkbox").click(function(){
		isSubmit('modify');
	});
})
/**
 *日历起始日期
 * @returns
 */
/**
 * 新增
 * @returns
 */
function update(obj){
	if(obj=='add'){
		$("#m").hide();
		$("#add").show();		
	}else if(obj=='modify'){
		$("#m").hide();
		$("#modify").show();	
	}else{
		$("#m").show();
		$("#add").hide();	
		$("#modify").hide();
		clearAll("modify");
	}
}
/**
 * 选中管理员
 * @returns
 */
function check(obj,div){
	if(div=="add"){
		var checkboxes=$("#add :checkbox:gt(0)");
		if(obj.checked){
			checkboxes.each(function(){
				this.disabled="true";
				this.checked="checked";
			});
		}else{
			checkboxes.each(function(){
				this.disabled="";
				this.checked="";
			});
		}
	}else{
		var checkboxes=$("#modify :checkbox:gt(0)");
		if(obj.checked){
			checkboxes.each(function(){
				this.disabled="true";
				this.checked="checked";
			});
		}else{
			checkboxes.each(function(){
				this.disabled="";
				this.checked="";
			});
		}
	}
}
/**
 * 账号的ajax验证
 */
function verifyAccount(){
	//账号验证
	var account=$("#add [name=account]").val();
	var span=$("#add #accountMes");
	//空内容不验证
	if(account=="" || !/^\w+$/.test(account)){
		span.text("×");
		span.css({"color":"red","font-size":"13px"});
	}else{
		$.get("/scm/scmuser/accountVerifyServlet",{"account":account},function(data){
			if(data=="true"){
				span.text("√");
				span.css({"color":"green","font-size":"13px"});
				$("#add [name=password]").val(account);
				$("#passwordMes").text(" 默认同账号");
				$("#passwordMes").css({"color":"green","font-size":"13px"});
			}else{
				span.text(data);
				span.css({"color":"red","font-size":"13px"});
				$("[name=password]").val("");
				$("#passwordMes").text("");
			}
		},"text");
	}
	isSubmit('add');//是否允许保存
}
/**
 * 用户名校验
 * @returns
 */
function verifyName(obj){
	if(obj=='add'){
		var name=$("#add [name=username]").val();
		var namespan=$("#add #nameMes");
		if(name=="" || !/^\w+$/.test(name)){
			namespan.text("×");
			namespan.css({"color":"red","font-size":"13px"});
		}else{
			namespan.text("√");
			namespan.css({"color":"green","font-size":"13px"});
		}
		isSubmit('add');//是否允许保存
	}else{
		var name=$("#modify [name=username]").val();
		var namespan=$("#modify #nameMes");
		if(name=="" || !/^\w+$/.test(name)){
			namespan.text("×");
			namespan.css({"color":"red","font-size":"13px"});
		}else{
			namespan.text("√");
			namespan.css({"color":"green","font-size":"13px"});
		}
		isSubmit('modify');//是否允许保存
	}
}
/**
 * 密码验证
 * @returns
 */
function verifyPassword(obj){
	if(obj=='add'){
		var password=$("#add [name=password]").val();
		var passwordspan=$("#add #passwordMes");
		if(password=="" || !/^\w+$/.test(password)){
			passwordspan.text("×");
			passwordspan.css({"color":"red","font-size":"13px"});
		}else{
			passwordspan.text("√");
			passwordspan.css({"color":"green","font-size":"13px"});
		}
		isSubmit('add');//是否允许保存
	}else{
		var password=$("#modify [name=password]").val();
		var passwordspan=$("#modify #passwordMes");
		if(password=="" || !/^\w+$/.test(password)){
			passwordspan.text("×");
			passwordspan.css({"color":"red","font-size":"13px"});
		}else{
			passwordspan.text("√");
			passwordspan.css({"color":"green","font-size":"13px"});
		}
		isSubmit('modify');//是否允许保存
	}
}
/**
 * 是否允许提交验证
 */
function isSubmit(obj){
	if(obj=='add'){
		var accountSpan=$("#add #accountMes");
		var nameSpan=$("#add #nameMes");
		var passwordSpan=$("#add #passwordMes");
		var checkboxes=$("#add :checkbox:checked");
		if(accountSpan.text()=="√" && nameSpan.text()=="√" && (passwordSpan.text()=="√" || passwordSpan.text()==" 默认同账号") && checkboxes.length>0){
			$("#save").attr("disabled",false);
		}else{
			$("#save").attr("disabled",true);
		}
	}else{
		var nameSpan=$("#modify #nameMes");
		var passwordSpan=$("#modify #passwordMes");
		var checkboxes=$("#modify :checkbox:checked");
		var name=$("#modify :text").val();
		var password=$("#modify :password").val();
		if((nameSpan.text()=="√" || nameSpan.text()=="") && (passwordSpan.text()=="√" || passwordSpan.text()=="") && checkboxes.length>0){
			$("#modify #save").attr("disabled",false);
		}else{
			$("#modify #save").attr("disabled",true);
		}
	}
}
/**
 * 清空新增内容
 */
function clearAll(obj){
	if(obj=='add'){
		$("#add [name=password]").val("");
		$("#add #passwordMes").text("");
		$("#add [name=account]").val("");
		$("#add #accountMes").text("");
		$("#add [name=username]").val("");
		$("#add #nameMes").text("");
		$("#add :checkbox:lt(5)").removeAttr("checked");
		$("#add :checkbox:lt(6)").attr("disabled",false);
		$("#add :checkbox:eq(5)").attr("checked",true);
		$("#add [name=status]:eq(1)").attr("checked",true);
	}else{
		$("#modify [name=password]").val("");
		$("#modify #passwordMes").text("");
		$("#modify [name=account]").val("");
		$("#modify #accountMes").text("");
		$("#modify [name=username]").val("");
		$("#modify #nameMes").text("");
		$("#modify :checkbox:lt(5)").removeAttr("checked");
		$("#modify :checkbox:lt(6)").attr("disabled",false);
		$("#modify :checkbox:eq(5)").attr("checked",true);
		$("#modify [name=status]:eq(1)").attr("checked",true);
	}
}
/**
 * 保存新增信息
 */
function saveUser(obj){
	if(obj=='add'){
		var formMsg=$("#add form").serialize();
		$.post("/scm/scmuser/addScmuserServlet",formMsg,function(data){
			if(data=="success"){
				clearAll('add');
				//隐藏新增div 显示用户管理div
				goPage('start');
				$("#add").hide();
				$("#m").show();
				alert("用户新增成功");
			}else{
				alert("用户新增失败，请稍后再试");
			}
		},"text");
	}else{
		var formMsg=$("#modify form").serialize();
		$.post("/scm/scmuser/modifyScmuserServlet",formMsg,function(data){
			if(data=="success"){
				clearAll('modify');
				//隐藏新增div 显示用户管理div
				goPage('start');
				$("#modify").hide();
				$("#m").show();
				alert("用户信息修改成功");
			}else{
				alert("用户信息修改失败，请稍后再试");
			}
		},"text");
	}
}
/**
*分页
*/
function goPage(goPage){
	var currentPage=$("#currentPage").text();
	var pageCount=$("#pageCount").text();
	//首尾页
	if(goPage=="start"){
		goPage=1;
		$("#jump").val("");
	}else if(goPage=="end"){
		goPage=parseInt(pageCount);
		$("#jump").val("");
	}else if(goPage=="up"){
		//前一页
		goPage=(currentPage==1?1:parseInt(currentPage)-1);
		$("#jump").val("");
	}else if(goPage=="down"){
		//后一页
		goPage=(currentPage==pageCount?pageCount:parseInt(currentPage)+1);
		$("#jump").val("");
	}else if(goPage=="jump"){
		//跳转
		var jumpPage=parseInt($("#jump").val());
		if(jumpPage>pageCount){
			goPage=pageCount;
			$("#jump").val(pageCount);
		}else if(jumpPage<1){
			goPage=1;
			$("#jump").val(1);
		}else {
			goPage=jumpPage;
		}
	}
		//带着最后一页的页码用ajax传递给pageServlet
		$.get("/scm/scmuser/scmuserPageServlet",con+"&goPage="+goPage+"&con="+con,function(data){
			//清空table  
			$(".c:eq(0) tr:gt(0)").remove();
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$(".c:eq(0)").append("<tr>" +
						"<td align='center'>"+data[i].account+"</td>" +
						"<td align='center'>"+data[i].name+"</td>" +
						"<td align='center'>"+data[i].createDate+"</td>" +
						"<td align='center'>"+(data[i].status==0?'未锁定':'已锁定')+"</td>" +
						"<td align='center'>"+data[i].modelCode+"</td>" +
						"<td align='center'><a href='javascript:void(0)' onclick='modify(this)'>修改</a> <a href='javascript:void(0)' onclick='del(this)'>删除</a></td>" +
						"</tr>");
			}
			//更改页面的总共页数
			$("#pageCount").text(data[data.length-1].pageCount);
			$("#allcount").text(data[data.length-1].allcount);
		},"json");
		//改变页码
		$("#currentPage").text(goPage);
}
/**
*删除
*/
function del(obj){
	if(confirm("确定要删除该用户么？")){
		var account=$(obj).parents("tr").find("td:eq(0)").text();
		//传给服务器执行删除用户程序
		$.get("/scm/scmuser/delScmuserServlet","account="+account,function(data){
			if(data=="false"){
				alert("销售单或采购单存有该用户信息，不予许删除");
			}else{
				alert("用户"+account+"删除成功");
				goPage('start');
			}
		},"text");
	}
}
/**
*修改
*/
function modify(obj){
	update('modify');//页面切换
	var account=$(obj).parents("tr").find("td:eq(0)").text();
	$("#modify [name=account]").val(account);//填写用户账号
	//从服务器获取其他信息
	$.post("/scm/scmuser/scmUserServlet","account="+account,function(data){
		$("#modify [name=username]").val(data.name);  //名字
		$("#modify [name=password]").val(data.password);  //密码
		//状态
		if(data.status==0){
			$("#modify :radio:eq(1)").attr("checked",true);
		}else{
			$("#modify :radio:eq(0)").attr("checked",true);
		}
		//权限
		var md=data.modelCode.split("");
		$("#modify :checkbox").removeAttr("checked");
		for(var i=0;i<md.length;i++){
			if(md[i]==1){
				$("#modify :checkbox").attr("checked","checked");
				$("#modify :checkbox:gt(0)").attr("disabled",true);
			}
			$("#modify :checkbox:eq("+(md[i]-1)+")").attr("checked","checked");
		}
	},"json");	
}
/**
 * 过滤查询
 * @returns
 */
function search(){
	//清空table
	$(".c:eq(0) tr:gt(0)").remove();
	//改变页码
	$("#pageCount").text("1");
	$("#currentPage").text("1");
	$("#allcount").text("0");
	var formMsg=$(".query_div form").serialize();
	con=formMsg;
	$.post("/scm/scmuser/scmuserPageServlet",formMsg+"&con="+con+"&goPage=1",function(data){
		//如果返回结果为空则不会执行以下代码
		//新增table内容
		for(var i=0;i<data.length-1;i++){
			$(".c:eq(0)").append("<tr>" +
					"<td align='center'>"+data[i].account+"</td>" +
					"<td align='center'>"+data[i].name+"</td>" +
					"<td align='center'>"+data[i].createDate+"</td>" +
					"<td align='center'>"+(data[i].status==0?'未锁定':'已锁定')+"</td>" +
					"<td align='center'>"+data[i].modelCode+"</td>" +
					"<td align='center'><a href='javascript:void(0)' onclick='modify(this)'>修改</a> <a href='javascript:void(0)' onclick='del(this)'>删除</a></td>" +
					"</tr>");
		}
		//更改页面的总共页数
		$("#pageCount").text(data[data.length-1].pageCount);
		$("#allcount").text(data[data.length-1].allcount);
	},"json");
}
	