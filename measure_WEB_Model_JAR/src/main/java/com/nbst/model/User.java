package com.nbst.model;

public class User {
	private Integer yhId;// ID

	private Integer yhGsId;// 公司ID

	private String yhUsername;// 用户名

	private String yhPassword;// 密码

	private String yhName;// 真实姓名

	private String yhTel;// 电话

	private String yhEmail;// 邮箱

	private String yhWechat;// 微信

	private String yhAddress;// 地址

	private Integer yhState;// 状态：1，正常；2，已删除

	private Long yhCreateTime;// 创建时间

	private String yhExtend1;// 用户的角色

	private String yhExtend2;// 扩展字段2

	private String yhExtend3;// 扩展字段3

	private String yhExtend4;// 扩展字段4

	private String yhExtend5;// 扩展字段5

	private Long yhUpdateTime;// 修改时间

	private Long yhDeleteTime;// 删除时间

	public Integer getYhId() {
		return yhId;
	}

	public void setYhId(Integer yhId) {
		this.yhId = yhId;
	}

	public Integer getYhGsId() {
		return yhGsId;
	}

	public void setYhGsId(Integer yhGsId) {
		this.yhGsId = yhGsId;
	}

	public String getYhUsername() {
		return yhUsername;
	}

	public void setYhUsername(String yhUsername) {
		this.yhUsername = yhUsername;
	}

	public String getYhPassword() {
		return yhPassword;
	}

	public void setYhPassword(String yhPassword) {
		this.yhPassword = yhPassword;
	}

	public String getYhName() {
		return yhName;
	}

	public void setYhName(String yhName) {
		this.yhName = yhName;
	}

	public String getYhTel() {
		return yhTel;
	}

	public void setYhTel(String yhTel) {
		this.yhTel = yhTel;
	}

	public String getYhEmail() {
		return yhEmail;
	}

	public void setYhEmail(String yhEmail) {
		this.yhEmail = yhEmail;
	}

	public String getYhWechat() {
		return yhWechat;
	}

	public void setYhWechat(String yhWechat) {
		this.yhWechat = yhWechat;
	}

	public String getYhAddress() {
		return yhAddress;
	}

	public void setYhAddress(String yhAddress) {
		this.yhAddress = yhAddress;
	}

	public Integer getYhState() {
		return yhState;
	}

	public void setYhState(Integer yhState) {
		this.yhState = yhState;
	}

	public Long getYhCreateTime() {
		return yhCreateTime;
	}

	public void setYhCreateTime(Long yhCreateTime) {
		this.yhCreateTime = yhCreateTime;
	}

	public String getYhExtend1() {
		return yhExtend1;
	}

	public void setYhExtend1(String yhExtend1) {
		this.yhExtend1 = yhExtend1;
	}

	public String getYhExtend2() {
		return yhExtend2;
	}

	public void setYhExtend2(String yhExtend2) {
		this.yhExtend2 = yhExtend2;
	}

	public String getYhExtend3() {
		return yhExtend3;
	}

	public void setYhExtend3(String yhExtend3) {
		this.yhExtend3 = yhExtend3;
	}

	public String getYhExtend4() {
		return yhExtend4;
	}

	public void setYhExtend4(String yhExtend4) {
		this.yhExtend4 = yhExtend4;
	}

	public String getYhExtend5() {
		return yhExtend5;
	}

	public void setYhExtend5(String yhExtend5) {
		this.yhExtend5 = yhExtend5;
	}

	public Long getYhUpdateTime() {
		return yhUpdateTime;
	}

	public void setYhUpdateTime(Long yhUpdateTime) {
		this.yhUpdateTime = yhUpdateTime;
	}

	public Long getYhDeleteTime() {
		return yhDeleteTime;
	}

	public void setYhDeleteTime(Long yhDeleteTime) {
		this.yhDeleteTime = yhDeleteTime;
	}

	@Override
	public String toString() {
		return "User [yhId=" + yhId + ", yhGsId=" + yhGsId + ", yhUsername=" + yhUsername + ", yhPassword=" + yhPassword
				+ ", yhName=" + yhName + ", yhTel=" + yhTel + ", yhEmail=" + yhEmail + ", yhWechat=" + yhWechat
				+ ", yhAddress=" + yhAddress + ", yhState=" + yhState + ", yhCreateTime=" + yhCreateTime
				+ ", yhExtend1=" + yhExtend1 + ", yhExtend2=" + yhExtend2 + ", yhExtend3=" + yhExtend3 + ", yhExtend4="
				+ yhExtend4 + ", yhExtend5=" + yhExtend5 + ", yhUpdateTime=" + yhUpdateTime + ", yhDeleteTime="
				+ yhDeleteTime + "]";
	}
	

}