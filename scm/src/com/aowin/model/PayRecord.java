package com.aowin.model;

/**
 * 收付款登记表
 * @author Peter
 *
 */
public class PayRecord {

	private String payTime;  //付款时间
	private float payPrice;  //付款价格
	private String account;  //经办人
	private long orderCode;  //相关单据号
	private int payType;  //付款类型
	public String getPayTime() {
		return payTime;
	}
	public float getPayPrice() {
		return payPrice;
	}
	public String getAccount() {
		return account;
	}
	public long getOrderCode() {
		return orderCode;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public void setPayPrice(float payPrice) {
		this.payPrice = payPrice;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setOrderCode(long orderCode) {
		this.orderCode = orderCode;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	@Override
	public String toString() {
		return "payRecord [payTime=" + payTime + ", payPrice=" + payPrice + ", account=" + account + ", orderCode="
				+ orderCode + ", payType=" + payType + "]";
	}
	
}
