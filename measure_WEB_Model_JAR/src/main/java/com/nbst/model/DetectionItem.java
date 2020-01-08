package com.nbst.model;

public class DetectionItem {
	private Integer jcxmId;
	// 检测项目名称
	private String jcxmName;
	// 检测项目编号
	private String jcxmCode;
	// 状态 1：正常；2：已删除
	private String jcxmExtend1;

	private String jcxmExtend2;

	private String jcxmExtend3;

	private String jcxmExtend4;

	private String jcxmExtend5;

	public Integer getJcxmId() {
		return jcxmId;
	}

	public void setJcxmId(Integer jcxmId) {
		this.jcxmId = jcxmId;
	}

	public String getJcxmName() {
		return jcxmName;
	}

	public void setJcxmName(String jcxmName) {
		this.jcxmName = jcxmName == null ? null : jcxmName.trim();
	}

	public String getJcxmCode() {
		return jcxmCode;
	}

	public void setJcxmCode(String jcxmCode) {
		this.jcxmCode = jcxmCode == null ? null : jcxmCode.trim();
	}

	public String getJcxmExtend1() {
		return jcxmExtend1;
	}

	public void setJcxmExtend1(String jcxmExtend1) {
		this.jcxmExtend1 = jcxmExtend1 == null ? null : jcxmExtend1.trim();
	}

	public String getJcxmExtend2() {
		return jcxmExtend2;
	}

	public void setJcxmExtend2(String jcxmExtend2) {
		this.jcxmExtend2 = jcxmExtend2 == null ? null : jcxmExtend2.trim();
	}

	public String getJcxmExtend3() {
		return jcxmExtend3;
	}

	public void setJcxmExtend3(String jcxmExtend3) {
		this.jcxmExtend3 = jcxmExtend3 == null ? null : jcxmExtend3.trim();
	}

	public String getJcxmExtend4() {
		return jcxmExtend4;
	}

	public void setJcxmExtend4(String jcxmExtend4) {
		this.jcxmExtend4 = jcxmExtend4 == null ? null : jcxmExtend4.trim();
	}

	public String getJcxmExtend5() {
		return jcxmExtend5;
	}

	public void setJcxmExtend5(String jcxmExtend5) {
		this.jcxmExtend5 = jcxmExtend5 == null ? null : jcxmExtend5.trim();
	}

	@Override
	public String toString() {
		return "DetectionItem [jcxmId=" + jcxmId + ", jcxmName=" + jcxmName + ", jcxmCode=" + jcxmCode
				+ ", jcxmExtend1=" + jcxmExtend1 + ", jcxmExtend2=" + jcxmExtend2 + ", jcxmExtend3=" + jcxmExtend3
				+ ", jcxmExtend4=" + jcxmExtend4 + ", jcxmExtend5=" + jcxmExtend5 + "]";
	}

}