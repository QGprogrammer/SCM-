package com.aowin.model;

/**
 * 销售明细单
 * @author Peter
 *
 */
public class Soitem {

	private long soId;  //销售单编号
	private String productCode;  //产品编号
	private float unitPrice;  //产品单价
	private int num;  //产品数量 
	private String unitName;  //数量单位
	private float itemPrice;  //明细总价
	public long getSoId() {
		return soId;
	}
	public String getProductCode() {
		return productCode;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public int getNum() {
		return num;
	}
	public String getUnitName() {
		return unitName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setSoId(long soId) {
		this.soId = soId;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "Soitem [soId=" + soId + ", productCode=" + productCode + ", unitPrice=" + unitPrice + ", num=" + num
				+ ", unitName=" + unitName + ", itemPrice=" + itemPrice + "]";
	}
	
}
