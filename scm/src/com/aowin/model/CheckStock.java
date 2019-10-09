package com.aowin.model;

/**
 * 产品盘点表
 * @author Peter
 *
 */
public class CheckStock {
	
	private String productCode; //产品编号
	private int originNum; //原始数量
	private int realNum; //真实数量
	private String stockTime; //盘点时间	
	private String createUser;	//经办人
	private String description; //损益原因
	private String type; //损益类型
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getOriginNum() {
		return originNum;
	}
	public void setOriginNum(int originNum) {
		this.originNum = originNum;
	}
	public int getRealNum() {
		return realNum;
	}
	public void setRealNum(int realNum) {
		this.realNum = realNum;
	}
	public String getStockTime() {
		return stockTime;
	}
	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
