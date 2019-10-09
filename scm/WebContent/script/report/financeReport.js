/**
 * 查询
 * 
 * @returns
 */
function search() {
	var year = $("[name=year]").val();
	var month = $("[name=month]").val();
	// 清空td
	$("#tr2 td:eq(0)").text("");
	$("#tr2 td:eq(1)").text("");
	$("#tr2 td:eq(2)").text("");
	$("#tr2 td:eq(3)").text("");
	// 查询前校验
	if (year == 0 || month == 0) {
		alert("请选择查询日期~");
	} else {
		var formMsg = $("form").serialize();
		$.get("/scm/report/financeReportServlet", formMsg, function(data) {
			$("#tr2 td:eq(0)").text(data.proceedsSum);
			$("#tr2 td:eq(1)").text(data.proceedsCount);
			$("#tr2 td:eq(2)").text(data.paymentSum);
			$("#tr2 td:eq(3)").text(data.paymentCount);
		}, "json");
	}
}
/**
 * 分tab查询
 */
function pageSearch(obj) {
	var year = $("[name=year]").val();
	var month = $("[name=month]").val();
	var formMsg = $("form").serialize();

	if (year == 0 || month == 0) {
		alert("请选择查询日期~");
	} else {
		//改变页码
		$("#pageCount").text("1");
		$("#currentPage").text("1");
		$("#allcount").text("0");
		// 清空table
		$("#payitem #headTable tr:gt(0)").remove();
		$("#proitem #headTable tr:gt(0)").remove();
		//改变分页跳转属性
		changeAttr(obj);
		// 收款
		if (obj == 1) {
			$("#m").hide();
			$("#proitem").show();
			$("#page").show();
			$.get("/scm/report/financeReportOfItemServlet", formMsg + "&goPage=1&type=1",
			function(data) {
				//新增table内容
				for(var i=0;i<data.length-1;i++){
					$("#proitem #headTable").append("<tr>" +
							"<td align='center'>"+data[i].soId+"</td>" +
							"<td align='center'>"+data[i].createTime+"</td>" +
							"<td align='center'>"+data[i].payTime+"</td>" +
							"<td align='center'>"+data[i].prePayFee+"</td>" +
							"<td align='center'>"+data[i].payUser+"</td>" +
							"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
							"</tr>");
				}
				// 更改页面的总共页数
				$("#pageCount").text(data[data.length - 1].pageCount);
				$("#allcount").text(data[data.length - 1].allcount);
			}, "json");
			// 改变页码
			$("#currentPage").text(1);
		}else{
			$("#m").hide();
			$("#payitem").show();
			$("#page").show();
			$.get("/scm/report/financeReportOfItemServlet", formMsg + "&goPage=1&type=0",
			function(data) {
				//新增table内容
				for(var i=0;i<data.length-1;i++){
					$("#payitem #headTable").append("<tr>" +
							"<td align='center'>"+data[i].poId+"</td>" +
							"<td align='center'>"+data[i].createTime+"</td>" +
							"<td align='center'>"+data[i].payTime+"</td>" +
							"<td align='center'>"+data[i].prePayFee+"</td>" +
							"<td align='center'>"+data[i].payUser+"</td>" +
							"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
							"</tr>");
				}
				// 更改页面的总共页数
				$("#pageCount").text(data[data.length - 1].pageCount);
				$("#allcount").text(data[data.length - 1].allcount);
			}, "json");
			// 改变页码
			$("#currentPage").text(1);
		}
		
	}
}
/**
 * 分页跳转
 */
function goPage(goPage,type){
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
	// 清空table
	$("#payitem #headTable tr:gt(0)").remove();
	$("#proitem #headTable tr:gt(0)").remove();
	// 收款
	var formMsg = $("form").serialize();
	if (type == 1) {
		$.get("/scm/report/financeReportOfItemServlet", formMsg + "&goPage="+goPage+"&type="+type,
		function(data) {
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$("#proitem #headTable").append("<tr>" +
						"<td align='center'>"+data[i].soId+"</td>" +
						"<td align='center'>"+data[i].createTime+"</td>" +
						"<td align='center'>"+data[i].payTime+"</td>" +
						"<td align='center'>"+data[i].prePayFee+"</td>" +
						"<td align='center'>"+data[i].payUser+"</td>" +
						"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
						"</tr>");
			}
			// 更改页面的总共页数
			$("#pageCount").text(data[data.length - 1].pageCount);
			$("#allcount").text(data[data.length - 1].allcount);
		}, "json");
		// 改变页码
		$("#currentPage").text(goPage);
	}else{
		$.get("/scm/report/financeReportOfItemServlet", formMsg + "&goPage="+goPage+"&type="+type,
		function(data) {
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$("#payitem #headTable").append("<tr>" +
						"<td align='center'>"+data[i].poId+"</td>" +
						"<td align='center'>"+data[i].createTime+"</td>" +
						"<td align='center'>"+data[i].payTime+"</td>" +
						"<td align='center'>"+data[i].prePayFee+"</td>" +
						"<td align='center'>"+data[i].payUser+"</td>" +
						"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
						"</tr>");
			}
			// 更改页面的总共页数
			$("#pageCount").text(data[data.length - 1].pageCount);
			$("#allcount").text(data[data.length - 1].allcount);
		}, "json");
		// 改变页码
		$("#currentPage").text(goPage);
	}
}
/**
 * 返回
 */
function back() {
	$("#m").show();
	$("#proitem").hide();
	$("#payitem").hide();
	$("#page").hide();
}
/**
 * 处理状态转换
 * @param payType
 * @returns
 */
function switchStatus(status){
	if(status=='1'){
		return '新增';
	}else if(status=='2'){
		return '已到货';
	}else if(status=='3'){
		return '已付款';
	}else if(status=='4'){
		return '已了结';
	}else if(status=='5'){
		return '已预付';
	}
}
/**
*改变分页跳转的goPage参数
*/
function changeAttr(type){
	$("#start").attr("onclick","goPage('start',"+type+")");
	$("#end").attr("onclick","goPage('end',"+type+")");
	$("#up").attr("onclick","goPage('up',"+type+")");
	$("#down").attr("onclick","goPage('down',"+type+")");
	$("#jump").attr("onclick","goPage('jump',"+type+")");
}