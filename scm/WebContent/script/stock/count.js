/**
 * 尾页跳转
 * @returns
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
	if(true){
		//带着最后一页的页码用ajax传递给pageServlet
		$.get("/scm/stock/countPageServlet",{"goPage":goPage},function(data){
			//清空table
			$(".c tr:gt(0)").remove();
			//新增table内容
			for(var i=0;i<data.length;i++){
				$(".c").append("<tr>" +
						"<td align='center'>"+data[i].productCode+"</td>" +
						"<td align='center'>"+data[i].name+"</td>" +
						"<td align='center'>"+data[i].num+"</td>" +
						"<td align='center'>"+data[i].poNum+"</td>" +
						"<td align='center'>"+data[i].soNum+"</td>" +
						"<td align='center'>变化数量: <input type='text' size='15px'></td>" +
						"<td align='center'>损益原因: <input type='text' size='15px'></td>" +
						"<td align='center'>变化类型: <select><option value='waste'>损耗</option><option value='profit'>盈余</option></select></td>" +
						"<td align='center'><a onclick='save(this)' style='margin-right:20px'>保存</a></td>" +
						"</tr>");
			}
		
		},"json")	
		//改变页码
		$("#currentPage").text(goPage);
	}
}
/**
 * 保存按钮
 * @returns
 */
function save(obj){
	var productCode=$(obj).parents("tr").find("td:eq(0)").text();
	var stockNum=parseInt($(obj).parents("tr").find(":text:eq(0)").val());
	var description=$(obj).parents("tr").find(":text:eq(1)").val();
	var type=$(obj).parents("tr").find("select option:selected").val();
	var originNum=parseInt($(obj).parents("tr").find("td:eq(2)").text());
	if(description=="" || stockNum=="" ||(stockNum > originNum && type=="waste")){
		alert("输入有误，请重新输入");
		return;
	}
	if(!/^\d+$/.test(stockNum)){
		alert("变化数量应为数字，请重新输入");
	}else{
		if(confirm("确定提交修改么?")){
			location.href="/scm/stock/updateStockCountServlet?productCode="+productCode+"&stockNum="+stockNum+"&description="+description+"&type="+type+"&originNum="+originNum;		
		}	
	}
	
}