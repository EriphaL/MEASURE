package com.nbst.model;

/**
 * 检测数据
 *
 */
public class DetectionData {
	private Integer jcsjId;
	// 录入时间
	private Long jcsjRecordTime;
	// 抽取时间
	private Long jcsjExtractTime;
	// 序号
	private Integer jcsjNo;
	// 对应控制图id
	private Integer jcsjControlChartId;
	// 数据点层次数据
	private String jcsjDataPointHierarchicaData;
	// 样本数据
	private String jcsjDataSample;
	// 状态
	private Integer jcsjState;
	// 计算数据
	private String jcsjControlChartData;
	// 录入用户id
	private Integer jcsjUserId;
	// 录入设备id
	private Integer jcsjDeviceId;
	// UCL控制上限
	private String jcsjExtend1;
	// Cl 目标值
	private String jcsjExtend2;
	// LCL 控制上限
	private String jcsjExtend3;
	// 触发的判异规则信息
	private String jcsjExtend4;

	private String jcsjExtend5;
	// 用户名称
	private String yhName;
	// 检测设备名称
	private String sbName;
	// 规格上限
	private String jcsSpecificationUpperLimit;
	// 目标值
	private String jcsTargetValue;
	// 规格下限
	private String jcsSpecificationDownLimit;

	public Integer getJcsjId() {
		return jcsjId;
	}

	public String getJcsSpecificationUpperLimit() {
		return jcsSpecificationUpperLimit;
	}

	public void setJcsSpecificationUpperLimit(String jcsSpecificationUpperLimit) {
		this.jcsSpecificationUpperLimit = jcsSpecificationUpperLimit;
	}

	public String getJcsTargetValue() {
		return jcsTargetValue;
	}

	public void setJcsTargetValue(String jcsTargetValue) {
		this.jcsTargetValue = jcsTargetValue;
	}

	public String getJcsSpecificationDownLimit() {
		return jcsSpecificationDownLimit;
	}

	public void setJcsSpecificationDownLimit(String jcsSpecificationDownLimit) {
		this.jcsSpecificationDownLimit = jcsSpecificationDownLimit;
	}

	public String getYhName() {
		return yhName;
	}

	public void setYhName(String yhName) {
		this.yhName = yhName;
	}

	public String getSbName() {
		return sbName;
	}

	public void setSbName(String sbName) {
		this.sbName = sbName;
	}

	public void setJcsjId(Integer jcsjId) {
		this.jcsjId = jcsjId;
	}

	public Long getJcsjRecordTime() {
		return jcsjRecordTime;
	}

	public void setJcsjRecordTime(Long jcsjRecordTime) {
		this.jcsjRecordTime = jcsjRecordTime;
	}

	public Long getJcsjExtractTime() {
		return jcsjExtractTime;
	}

	public void setJcsjExtractTime(Long jcsjExtractTime) {
		this.jcsjExtractTime = jcsjExtractTime;
	}

	public Integer getJcsjNo() {
		return jcsjNo;
	}

	public void setJcsjNo(Integer jcsjNo) {
		this.jcsjNo = jcsjNo;
	}

	public Integer getJcsjControlChartId() {
		return jcsjControlChartId;
	}

	public void setJcsjControlChartId(Integer jcsjControlChartId) {
		this.jcsjControlChartId = jcsjControlChartId;
	}

	public String getJcsjDataPointHierarchicaData() {
		return jcsjDataPointHierarchicaData;
	}

	public void setJcsjDataPointHierarchicaData(String jcsjDataPointHierarchicaData) {
		this.jcsjDataPointHierarchicaData = jcsjDataPointHierarchicaData == null ? null
				: jcsjDataPointHierarchicaData.trim();
	}

	public String getJcsjDataSample() {
		return jcsjDataSample;
	}

	public void setJcsjDataSample(String jcsjDataSample) {
		this.jcsjDataSample = jcsjDataSample == null ? null : jcsjDataSample.trim();
	}

	public Integer getJcsjState() {
		return jcsjState;
	}

	public void setJcsjState(Integer jcsjState) {
		this.jcsjState = jcsjState;
	}

	public String getJcsjControlChartData() {
		return jcsjControlChartData;
	}

	public void setJcsjControlChartData(String jcsjControlChartData) {
		this.jcsjControlChartData = jcsjControlChartData == null ? null : jcsjControlChartData.trim();
	}

	public Integer getJcsjUserId() {
		return jcsjUserId;
	}

	public void setJcsjUserId(Integer jcsjUserId) {
		this.jcsjUserId = jcsjUserId;
	}

	public Integer getJcsjDeviceId() {
		return jcsjDeviceId;
	}

	public void setJcsjDeviceId(Integer jcsjDeviceId) {
		this.jcsjDeviceId = jcsjDeviceId;
	}

	public String getJcsjExtend1() {
		return jcsjExtend1;
	}

	public void setJcsjExtend1(String jcsjExtend1) {
		this.jcsjExtend1 = jcsjExtend1 == null ? null : jcsjExtend1.trim();
	}

	public String getJcsjExtend2() {
		return jcsjExtend2;
	}

	public void setJcsjExtend2(String jcsjExtend2) {
		this.jcsjExtend2 = jcsjExtend2 == null ? null : jcsjExtend2.trim();
	}

	public String getJcsjExtend3() {
		return jcsjExtend3;
	}

	public void setJcsjExtend3(String jcsjExtend3) {
		this.jcsjExtend3 = jcsjExtend3 == null ? null : jcsjExtend3.trim();
	}

	public String getJcsjExtend4() {
		return jcsjExtend4;
	}

	public void setJcsjExtend4(String jcsjExtend4) {
		this.jcsjExtend4 = jcsjExtend4 == null ? null : jcsjExtend4.trim();
	}

	public String getJcsjExtend5() {
		return jcsjExtend5;
	}

	public void setJcsjExtend5(String jcsjExtend5) {
		this.jcsjExtend5 = jcsjExtend5 == null ? null : jcsjExtend5.trim();
	}

	@Override
	public String toString() {
		return "DetectionData [jcsjId=" + jcsjId + ", jcsjRecordTime=" + jcsjRecordTime + ", jcsjExtractTime="
				+ jcsjExtractTime + ", jcsjNo=" + jcsjNo + ", jcsjControlChartId=" + jcsjControlChartId
				+ ", jcsjDataPointHierarchicaData=" + jcsjDataPointHierarchicaData + ", jcsjDataSample="
				+ jcsjDataSample + ", jcsjState=" + jcsjState + ", jcsjControlChartData=" + jcsjControlChartData
				+ ", jcsjUserId=" + jcsjUserId + ", jcsjDeviceId=" + jcsjDeviceId + ", jcsjExtend1=" + jcsjExtend1
				+ ", jcsjExtend2=" + jcsjExtend2 + ", jcsjExtend3=" + jcsjExtend3 + ", jcsjExtend4=" + jcsjExtend4
				+ ", jcsjExtend5=" + jcsjExtend5 + ", yhName=" + yhName + ", sbName=" + sbName
				+ ", jcsSpecificationUpperLimit=" + jcsSpecificationUpperLimit + ", jcsTargetValue=" + jcsTargetValue
				+ ", jcsSpecificationDownLimit=" + jcsSpecificationDownLimit + "]";
	}

}