package com.nbst.model;

public class TypeLevelTwo {
	// ID
	private Integer cclxtwoId;
	// 层次类型名称
	private String cclxtwoName;
	//状态
	private String cclxtwoExtend1;
	//状态为1时为表名 状态为2时为值
	private String cclxtwoExtend2;

	private String cclxtwoExtend3;

	private String cclxtwoExtend4;

	private String cclxtwoExtend5;

	public Integer getCclxtwoId() {
		return cclxtwoId;
	}

	public void setCclxtwoId(Integer cclxtwoId) {
		this.cclxtwoId = cclxtwoId;
	}

	public String getCclxtwoName() {
		return cclxtwoName;
	}

	public void setCclxtwoName(String cclxtwoName) {
		this.cclxtwoName = cclxtwoName == null ? null : cclxtwoName.trim();
	}

	public String getCclxtwoExtend1() {
		return cclxtwoExtend1;
	}

	public void setCclxtwoExtend1(String cclxtwoExtend1) {
		this.cclxtwoExtend1 = cclxtwoExtend1 == null ? null : cclxtwoExtend1.trim();
	}

	public String getCclxtwoExtend2() {
		return cclxtwoExtend2;
	}

	public void setCclxtwoExtend2(String cclxtwoExtend2) {
		this.cclxtwoExtend2 = cclxtwoExtend2 == null ? null : cclxtwoExtend2.trim();
	}

	public String getCclxtwoExtend3() {
		return cclxtwoExtend3;
	}

	public void setCclxtwoExtend3(String cclxtwoExtend3) {
		this.cclxtwoExtend3 = cclxtwoExtend3 == null ? null : cclxtwoExtend3.trim();
	}

	public String getCclxtwoExtend4() {
		return cclxtwoExtend4;
	}

	public void setCclxtwoExtend4(String cclxtwoExtend4) {
		this.cclxtwoExtend4 = cclxtwoExtend4 == null ? null : cclxtwoExtend4.trim();
	}

	public String getCclxtwoExtend5() {
		return cclxtwoExtend5;
	}

	public void setCclxtwoExtend5(String cclxtwoExtend5) {
		this.cclxtwoExtend5 = cclxtwoExtend5 == null ? null : cclxtwoExtend5.trim();
	}

	@Override
	public String toString() {
		return "TypeLevelTwo [cclxtwoId=" + cclxtwoId + ", cclxtwoName=" + cclxtwoName + ", cclxtwoExtend1="
				+ cclxtwoExtend1 + ", cclxtwoExtend2=" + cclxtwoExtend2 + ", cclxtwoExtend3=" + cclxtwoExtend3
				+ ", cclxtwoExtend4=" + cclxtwoExtend4 + ", cclxtwoExtend5=" + cclxtwoExtend5 + "]";
	}
}