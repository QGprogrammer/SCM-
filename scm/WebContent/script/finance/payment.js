/**
 * 不同付款方式分页查询
 * @param type
 * @returns
 */
function pageSearch(payType){
	//1==货到付款
	//2==款到发货
	//3==预付款到发货
		//改变页码
		$("#pageCount").text("1");
		$("#currentPage").text("1");
		$("#allcount").text("0");
		//清空table
		$(".c tr:gt(0)").remove();
		$.post("/scm/finance/paymentPageServlet",{"payType":payType,"goPage":1},function(data){
			//如果返回结果为空则不会执行以下代码
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$(".c").append("<tr>" +
						"<td align='center'><a href='javascript:void(0)' onclick='showItem(this)'>"+data[i].poId+"</a></td>" +
						"<td align='center'>"+data[i].createTime+"</td>" +
						"<td align='center'>"+data[i].account+"</td>" +
						"<td align='center'>"+data[i].venderName+"</td>" +
						"<td align='center'>"+data[i].tipfee+"</td>" +
						"<td align='center'>"+data[i].productTotal+"</td>" +
						"<td align='center'>"+data[i].poTotal+"</td>" +
						"<td align='center'>"+switchPayType(data[i].payType)+"</td>" +
						"<td align='center'>"+data[i].prePayFee+"</td>" +
						"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
						"<td align='center'><a href='javascript:void(0)' onclick='checkin(this)'dd>登记</a></td>" +
						"</tr>");
			}
			//更改页面的总共页数
			$("#pageCount").text(data[data.length-1].pageCount);
			$("#allcount").text(data[data.length-1].allcount);
			//更改分页跳转的属性goPage(goPage,payType)
			$("#start").attr("onclick","goPage('start',"+payType+")");
			$("#end").attr("onclick","goPage('end',"+payType+")");
			$("#up").attr("onclick","goPage('up',"+payType+")");
			$("#down").attr("onclick","goPage('down',"+payType+")");
			$("#jump").attr("onclick","goPage('jump',"+payType+")");	
		},"json");
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
		$.get("/scm/finance/paymentPageServlet","&goPage="+goPage+"&payType="+payType,function(data){
			//新增table内容
			for(var i=0;i<data.length-1;i++){
				$(".c").append("<tr>" +
						"<td align='center'><a href='javascript:void(0)' onclick='showItem(this)'>"+data[i].poId+"</a></td>" +
						"<td align='center'>"+data[i].createTime+"</td>" +
						"<td align='center'>"+data[i].account+"</td>" +
						"<td align='center'>"+data[i].venderName+"</td>" +
						"<td align='center'>"+data[i].tipfee+"</td>" +
						"<td align='center'>"+data[i].productTotal+"</td>" +
						"<td align='center'>"+data[i].poTotal+"</td>" +
						"<td align='center'>"+switchPayType(data[i].payType)+"</td>" +
						"<td align='center'>"+data[i].prePayFee+"</td>" +
						"<td align='center'>"+switchStatus(data[i].status)+"</td>" +
						"<td align='center'><a href='javascript:void(0)' onclick='checkin(this)'>登记</a></td>" +
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
 * 登记
 * @param obj
 * @returns
 */
function checkin(obj){
	if(confirm("确定登记么")){
		var poId=$(obj).parents("tr").find("td:eq(0)").text();
		//servlet登记
		$.get("/scm/finance/paymentCheckinServlet",{"poId":poId},function(data){
			if(data=="success"){
				alert("登记成功");
				goPage('start','0');
			}else{
				alert("登记失败，请稍后再试");
			}
		},"text");
		
		
	}
}
/**
*返回
*/
function back(){
	$("#m").show();
	$("#item").hide();
}
/**
*查看明细
*/
function showItem(obj){
	var soId=$(obj).text();
	//向服务器发送请求，并返回详细信息
	$.get("/scm/finance/poItemServlet",{"soId":soId},function(data){
		//清空明细表
		$("#headTable td:odd").text("");
		var item=$("#headTable td:odd");
		$(item[0]).text(data.poId);
		$(item[1]).text(data.productCode);
		$(item[2]).text(data.unitName);
		$(item[3]).text(data.num);
		$(item[4]).text(data.unitPrice);
		$(item[5]).text(data.itemPrice);
		//填入明细表
	},"json");
	$("#m").hide();
	$("#item").show();
}