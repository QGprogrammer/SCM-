package com.aowin.model;

import java.util.List;

/**
 * 用户模块表
 * @author Peter
 *
 */
public class UserModel {
	private String account;  //用户账号
	private List<Integer> modelCode;  //用户模块编号
	private List<String> modelUri;  //用户模块的uri
	public String getAccount() {
		return account;
	}
	public List<Integer> getModelCode() {
		return modelCode;
	}
	public List<String> getModelUri() {
		return modelUri;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setModelCode(List<Integer> modelCode) {
		this.modelCode = modelCode;
	}
	public void setModelUri(List<String> modelUri) {
		this.modelUri = modelUri;
	}
}
