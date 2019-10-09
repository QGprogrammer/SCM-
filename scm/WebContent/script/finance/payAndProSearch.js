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
 * 全局变量con 存放查询条件
 */
var con;
/**
*查询
*/
function search(){
	var formMsg=$("#m form").serialize();
	con=formMsg;
	//改变页码
	$("#pageCount").text("1");
	$("#currentPage").text("1");
	$("#allcount").text("0");
	//清空table
	$(".c tr:gt(0)").remove();
	$.get("/scm/finance/payAndProSearchServlet",formMsg+"&goPage=1",function(data){
		//新增table内容
		for(var i=0;i<data.length-1;i++){
			$(".c").append("<tr>" +
					"<td align='center'>"+data[i].orderCode+"</td>" +
					"<td align='center'>"+data[i].payTime+"</td>" +
					"<td align='center'>"+data[i].payPrice+"</td>" +
					"<td align='center'>"+data[i].account+"</td>" +
					"<td align='center'>"+switchPayType(data[i].payType)+"</td>" +
					"</tr>");
		}
		//更改页面的总共页数
		$("#pageCount").text(data[data.length-1].pageCount);
		$("#allcount").text(data[data.length-1].allcount);
	},"json");
}
/**
*分页跳转
*/
function goPage(goPage,payType){
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
	//清空table  
	$(".c tr:gt(0)").remove();
		//带着最后一页的页码用ajax传递给pageServlet
		$.get("/scm/finance/payAndProSearchServlet",con+"&goPage="+goPage,function(data){
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$(".c").append("<tr>" +
						"<td align='center'>"+data[i].orderCode+"</td>" +
						"<td align='center'>"+data[i].payTime+"</td>" +
						"<td align='center'>"+data[i].payPrice+"</td>" +
						"<td align='center'>"+data[i].account+"</td>" +
						"<td align='center'>"+switchPayType(data[i].payType)+"</td>" +
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
 * 付款方式转换
 * @param payType
 * @returns
 */
function switchPayType(payType){
	if(payType=='1'){
		return '货到付款';
	}else if(payType=='2'){
		return '款到发货';
	}else if(payType=='3'){
		return '预付款到发货';
	}
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