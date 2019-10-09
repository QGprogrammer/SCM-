package com.aowin.model;

/**
 * 用户类
 * 新增加了modelcode属性 用于联合查询用
 * @author Peter
 *
 */
public class Scmuser {
	private String account; //用户账号
	private String password;  //用户密码
	private String name;  //用户名
	private String createDate;  //创建日期
	private int status;  //用户状态
	private String modelCode; //模块编号---权限模块
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
}
