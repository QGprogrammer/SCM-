package com.aowin.model;

/**
 * 采购单主单类
 * @author Peter
 *
 */
public class Pomain {

	private long poId;  //采购单编号
	private String venderCode;  //供应商编号
	private String venderName;  //供应商名称
	private String account;  //用户账号
	private String createTime;  //创建时间
	private float tipfee;  //附加费用
	private float productTotal;  //产品总价
	private float poTotal;  //采购单总价
	private int payType;  //付款方式
	private float prePayFee;  //预付款金额
	private int status;  //处理状态
	private String remark;  //备注
	private String stockTime;  //入库时间
	private String stockUser;  //入库登记用户
	private String payTime;  //付款时间
	private String payUser;  //付款用户
	private String prePayTime;  //预付款时间
	private String prePayUser;  //预付款用户
	private String endTime;  //了结事件
	private String endUser;  //了结用户
	public long getPoId() {
		return poId;
	}
	public String getVenderCode() {
		return venderCode;
	}
	public String getVenderName() {
		return venderName;
	}
	public String getAccount() {
		return account;
	}
	public String getCreateTime() {
		return createTime;
	}
	public float getTipfee() {
		return tipfee;
	}
	public float getProductTotal() {
		return productTotal;
	}
	public float getPoTotal() {
		return poTotal;
	}
	public int getPayType() {
		return payType;
	}
	public float getPrePayFee() {
		return prePayFee;
	}
	public int getStatus() {
		return status;
	}
	public String getRemark() {
		return remark;
	}
	public String getStockTime() {
		return stockTime;
	}
	public String getStockUser() {
		return stockUser;
	}
	public String getPayTime() {
		return payTime;
	}
	public String getPayUser() {
		return payUser;
	}
	public String getPrePayTime() {
		return prePayTime;
	}
	public String getPrePayUser() {
		return prePayUser;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getEndUser() {
		return endUser;
	}
	public void setPoId(long poId) {
		this.poId = poId;
	}
	public void setVenderCode(String venderCode) {
		this.venderCode = venderCode;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setTipfee(float tipfee) {
		this.tipfee = tipfee;
	}
	public void setProductTotal(float productTotal) {
		this.productTotal = productTotal;
	}
	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public void setPrePayFee(float prePayFee) {
		this.prePayFee = prePayFee;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}
	public void setStockUser(String stockUser) {
		this.stockUser = stockUser;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}
	public void setPrePayTime(String prePayTime) {
		this.prePayTime = prePayTime;
	}
	public void setPrePayUser(String prePayUser) {
		this.prePayUser = prePayUser;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}
	
	
}
