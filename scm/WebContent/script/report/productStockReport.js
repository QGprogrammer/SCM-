/**
 * 查询
 * 
 * @returns
 */
function search() {
	var year = $("[name=year]").val();
	var month = $("[name=month]").val();
	// 清空table
	$(".c tr:gt(0)").remove();
	$("#currentPage").text("1");
	$("#pageCount").text("1");
	$("#allcount").text("0");
	// 查询前校验
	if (year == 0 || month == 0) {
		alert("请选择查询日期~");
	} else {
		var formMsg = $("form").serialize();
		$.get("/scm/report/productStockReportServlet", formMsg+"&goPage=1", function(data) {
			if (data.status == 'failure'){
				alert("那是未来的时间，请重新选择");
			}else {
				//新增table内容
				for(var i=0;i<data.length-1;i++){
					$(".c").append("<tr>" +
							"<td align='center'><a href='javascript:void(0)' onclick=\"showItem("+data[i].productCode+")\">"+data[i].productCode+"</a></td>" +
							"<td align='center'>"+data[i].name+"</td>" +
							"<td align='center'>"+data[i].num+"</td>" +
							"</tr>");
				}
				// 更改页面的总共页数
				$("#pageCount").text(data[data.length - 1].pageCount);
				$("#allcount").text(data[data.length - 1].allcount);
			}
		}, "json");
		// 改变页码
		$("#currentPage").text(1);
	}
}
/**
 * 分页跳转
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
	// 清空table
	$(".c tr:gt(0)").remove();
	$("#currentPage").text("1");
	$("#pageCount").text("1");
	$("#allcount").text("0");
	var formMsg = $("form").serialize();
	$.get("/scm/report/productStockReportServlet", formMsg+"&goPage="+goPage, function(data) {
		if (data.status == 'failure'){
			alert("那是未来的时间，请重新选择");
		}else {
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$(".c").append("<tr>" +
						"<td align='center'><a href='javascript:void(0)' onclick=\"showItem("+data[i].productCode+")\">"+data[i].productCode+"</a></td>" +
						"<td align='center'>"+data[i].name+"</td>" +
						"<td align='center'>"+data[i].num+"</td>" +
						"</tr>");
			}
			// 更改页面的总共页数
			$("#pageCount").text(data[data.length - 1].pageCount);
			$("#allcount").text(data[data.length - 1].allcount);
		}
	}, "json");
	// 改变页码
	$("#currentPage").text(goPage);
}
/**
 * 返回
 */
function back() {
	$("#m").show();
	$("#item").hide();
	$("#page").show();
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
*明细单
*/
function showItem(productCode){
	$("#m").hide();
	$("#page").hide();
	$("#item").show();
	var formMsg = $("form").serialize();
	//清空tr2的内容
	$("#tr2 td:eq(0)").text("");
	$("#tr2 td:eq(1)").text("");
	$("#tr2 td:eq(2)").text("");
	//访问服务器ajax
	$.get("/scm/report/productStockReportItemServlet",formMsg+"&productCode="+productCode,function(data){
		//添入tr2内容
		$("#tr2 td:eq(0)").text(data.productCode);
		$("#tr2 td:eq(1)").text(data.name);
		$("#tr2 td:eq(2)").text(data.num);
	},"json");
}