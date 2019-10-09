package com.aowin.model;

/**
 * 采购主单类
 * @author Peter
 *
 */
public class Somain {
	
	private long soId; // 销售单编号
	private String customerCode; // 客户编号
	private String customerName; // 客户名称
	private String account; // 创建用户
	private String createTime; // 创建时间（日期+时间）
	private float tipFee; // 附加费用
	private float productTotal; // 销售产品总价
	private float poTotal; // 销售单总价格
	private int payType; // 付款方式
	private float prePayFee; // 最低预付款金额
	private int status; // 单据处理状态
	private String remark; // 备注
	private String stockTime; // 采购时间
	private String stockUser; // 采购处理用户
	private String payTime; // 付款时间
	private String payUser; // 付款处理用户
	private String prePayTime; // 预付款时间
	private String prePayUser; // 预付款处理用户
	private String endTime; // 了结时间
	private String endUser; // 了结处理用户
	public long getSoId() {
		return soId;
	}
	public void setSoId(long soId) {
		this.soId = soId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public float getTipFee() {
		return tipFee;
	}
	public void setTipFee(float tipFee) {
		this.tipFee = tipFee;
	}
	public float getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(float productTotal) {
		this.productTotal = productTotal;
	}
	public float getPoTotal() {
		return poTotal;
	}
	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public float getPrePayFee() {
		return prePayFee;
	}
	public void setPrePayFee(float prePayFee) {
		this.prePayFee = prePayFee;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStockTime() {
		return stockTime;
	}
	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}
	public String getStockUser() {
		return stockUser;
	}
	public void setStockUser(String stockUser) {
		this.stockUser = stockUser;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPayUser() {
		return payUser;
	}
	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}
	public String getPrePayTime() {
		return prePayTime;
	}
	public void setPrePayTime(String prePayTime) {
		this.prePayTime = prePayTime;
	}
	public String getPrePayUser() {
		return prePayUser;
	}
	public void setPrePayUser(String prePayUser) {
		this.prePayUser = prePayUser;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndUser() {
		return endUser;
	}
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}
	@Override
	public String toString() {
		return "Somain [soId=" + soId + ", customerCode=" + customerCode + ", customerName=" + customerName
				+ ", account=" + account + ", createTime=" + createTime + ", tipFee=" + tipFee + ", productTotal="
				+ productTotal + ", poTotal=" + poTotal + ", payType=" + payType + ", prePayFee=" + prePayFee
				+ ", status=" + status + ", remark=" + remark + ", stockTime=" + stockTime + ", stockUser=" + stockUser
				+ ", payTime=" + payTime + ", payUser=" + payUser + ", prePayTime=" + prePayTime + ", prePayUser="
				+ prePayUser + ", endTime=" + endTime + ", endUser=" + endUser + "]";
	}
	
}
