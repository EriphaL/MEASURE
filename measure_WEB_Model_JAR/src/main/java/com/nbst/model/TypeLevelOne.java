package com.nbst.model;

public class TypeLevelOne {
	//ID
    private Integer cclxoneId;
	// 层次类型名称
    private String cclxoneName;
    //状态
    private String cclxoneExtend1;
  //状态为1时为表名 状态为2时为值
    private String cclxoneExtend2;

    private String cclxoneExtend3;

    private String cclxoneExtend4;

    private String cclxoneExtend5;

    public Integer getCclxoneId() {
        return cclxoneId;
    }

    public void setCclxoneId(Integer cclxoneId) {
        this.cclxoneId = cclxoneId;
    }

    public String getCclxoneName() {
        return cclxoneName;
    }

    public void setCclxoneName(String cclxoneName) {
        this.cclxoneName = cclxoneName == null ? null : cclxoneName.trim();
    }

    public String getCclxoneExtend1() {
        return cclxoneExtend1;
    }

    public void setCclxoneExtend1(String cclxoneExtend1) {
        this.cclxoneExtend1 = cclxoneExtend1 == null ? null : cclxoneExtend1.trim();
    }

    public String getCclxoneExtend2() {
        return cclxoneExtend2;
    }

    public void setCclxoneExtend2(String cclxoneExtend2) {
        this.cclxoneExtend2 = cclxoneExtend2 == null ? null : cclxoneExtend2.trim();
    }

    public String getCclxoneExtend3() {
        return cclxoneExtend3;
    }

    public void setCclxoneExtend3(String cclxoneExtend3) {
        this.cclxoneExtend3 = cclxoneExtend3 == null ? null : cclxoneExtend3.trim();
    }

    public String getCclxoneExtend4() {
        return cclxoneExtend4;
    }

    public void setCclxoneExtend4(String cclxoneExtend4) {
        this.cclxoneExtend4 = cclxoneExtend4 == null ? null : cclxoneExtend4.trim();
    }

    public String getCclxoneExtend5() {
        return cclxoneExtend5;
    }

    public void setCclxoneExtend5(String cclxoneExtend5) {
        this.cclxoneExtend5 = cclxoneExtend5 == null ? null : cclxoneExtend5.trim();
    }

	@Override
	public String toString() {
		return "TypeLevelOne [cclxoneId=" + cclxoneId + ", cclxoneName=" + cclxoneName + ", cclxoneExtend1="
				+ cclxoneExtend1 + ", cclxoneExtend2=" + cclxoneExtend2 + ", cclxoneExtend3=" + cclxoneExtend3
				+ ", cclxoneExtend4=" + cclxoneExtend4 + ", cclxoneExtend5=" + cclxoneExtend5 + "]";
	}
}