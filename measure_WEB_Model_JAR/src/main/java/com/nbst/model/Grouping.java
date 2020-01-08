package com.nbst.model;

public class Grouping {
	private Integer fzId;
	// 分组名称
	private String fzName;
	// ERP代码
	private String fzErpCode;
	// 描述信息
	private String fzRemark;
	// 分组父id
	private String fzExtend1;
	// 修改/删除状态
	private String fzExtend2;
	// 状态
	private String fzExtend3;

	private String fzExtend4;

	private String fzExtend5;

	public Integer getFzId() {
		return fzId;
	}

	public void setFzId(Integer fzId) {
		this.fzId = fzId;
	}

	public String getFzName() {
		return fzName;
	}

	public void setFzName(String fzName) {
		this.fzName = fzName == null ? null : fzName.trim();
	}

	public String getFzErpCode() {
		return fzErpCode;
	}

	public void setFzErpCode(String fzErpCode) {
		this.fzErpCode = fzErpCode == null ? null : fzErpCode.trim();
	}

	public String getFzRemark() {
		return fzRemark;
	}

	public void setFzRemark(String fzRemark) {
		this.fzRemark = fzRemark == null ? null : fzRemark.trim();
	}

	public String getFzExtend1() {
		return fzExtend1;
	}

	public void setFzExtend1(String fzExtend1) {
		this.fzExtend1 = fzExtend1 == null ? null : fzExtend1.trim();
	}

	public String getFzExtend2() {
		return fzExtend2;
	}

	public void setFzExtend2(String fzExtend2) {
		this.fzExtend2 = fzExtend2 == null ? null : fzExtend2.trim();
	}

	public String getFzExtend3() {
		return fzExtend3;
	}

	public void setFzExtend3(String fzExtend3) {
		this.fzExtend3 = fzExtend3 == null ? null : fzExtend3.trim();
	}

	public String getFzExtend4() {
		return fzExtend4;
	}

	public void setFzExtend4(String fzExtend4) {
		this.fzExtend4 = fzExtend4 == null ? null : fzExtend4.trim();
	}

	public String getFzExtend5() {
		return fzExtend5;
	}

	public void setFzExtend5(String fzExtend5) {
		this.fzExtend5 = fzExtend5 == null ? null : fzExtend5.trim();
	}

	@Override
	public String toString() {
		return "Grouping [fzId=" + fzId + ", fzName=" + fzName + ", fzErpCode=" + fzErpCode + ", fzRemark=" + fzRemark
				+ ", fzExtend1=" + fzExtend1 + ", fzExtend2=" + fzExtend2 + ", fzExtend3=" + fzExtend3 + ", fzExtend4="
				+ fzExtend4 + ", fzExtend5=" + fzExtend5 + "]";
	}
}