package com.nbst.model;

/**
 * 控制图信息
 * 
 * @author huangh
 *
 */
public class ControlChartInfomation {
	private Integer kztxxId;
	// 分组id
	private Integer kztxxGroupId;
	// 控制图编号
	private String kztxxNumber;
	// 控制图类型id
	private Integer kztxxTypeId;
	// 检测项目id
	private Integer kztxxDetectionItemId;
	// 规格上限
	private String kztxxSpecificationUpperLimit;
	// 目标值
	private String kztxxTargetValue;
	// 规格下限
	private String kztxxSpecificationDownLimit;
	// 样本容量
	private Integer kztxxSampleCapacity;
	// 小数位数
	private Integer kztxxDecimalNumber;
	// 上图上控制限
	private String kztxxAboveUpperControlLimit;
	// 上图目标值
	private String kztxxAboveTargetValue;
	// 上图下控制限
	private String kztxxAboveLowerControlLimit;
	// 下图上控制限
	private String kztxxFollowGraphUpperControlLimit;
	// 下图目标值
	private String kztxxFollowGraphTargetValue;
	// 下图下控制限
	private String kztxxFollowGraphLowerControlLimit;
	// 控制图描述
	private String kztxxControlChartDiscription;
	// 自定义控制图标题
	private String kztxxCustomizedControlChartTitle;
	// 检测设备id
	private Integer kztxxDetectionDeviceId;
	// 自动检测频率（秒）
	private Integer kztxxDetectionFrequency;
	// 控制图数据状态
	private Integer kztxxdatasState;
	

	// 状态
	private String kztxxExtend1;
	// 更新时间
	private String kztxxExtend2;
	// 更新用户id
	private String kztxxExtend3;
	// seigama
	private String kztxxExtend4;
	// 被克隆次数
	private String kztxxExtend5;

	public Integer getKztxxId() {
		return kztxxId;
	}

	public void setKztxxId(Integer kztxxId) {
		this.kztxxId = kztxxId;
	}

	public Integer getKztxxGroupId() {
		return kztxxGroupId;
	}

	public void setKztxxGroupId(Integer kztxxGroupId) {
		this.kztxxGroupId = kztxxGroupId;
	}

	public String getKztxxNumber() {
		return kztxxNumber;
	}

	public void setKztxxNumber(String kztxxNumber) {
		this.kztxxNumber = kztxxNumber;
	}

	public Integer getKztxxTypeId() {
		return kztxxTypeId;
	}

	public void setKztxxTypeId(Integer kztxxTypeId) {
		this.kztxxTypeId = kztxxTypeId;
	}

	public Integer getKztxxDetectionItemId() {
		return kztxxDetectionItemId;
	}

	public void setKztxxDetectionItemId(Integer kztxxDetectionItemId) {
		this.kztxxDetectionItemId = kztxxDetectionItemId;
	}

	public String getKztxxSpecificationUpperLimit() {
		return kztxxSpecificationUpperLimit;
	}

	public void setKztxxSpecificationUpperLimit(String kztxxSpecificationUpperLimit) {
		this.kztxxSpecificationUpperLimit = kztxxSpecificationUpperLimit;
	}

	public String getKztxxTargetValue() {
		return kztxxTargetValue;
	}

	public void setKztxxTargetValue(String kztxxTargetValue) {
		this.kztxxTargetValue = kztxxTargetValue == null ? null : kztxxTargetValue.trim();
	}

	public String getKztxxSpecificationDownLimit() {
		return kztxxSpecificationDownLimit;
	}

	public void setKztxxSpecificationDownLimit(String kztxxSpecificationDownLimit) {
		this.kztxxSpecificationDownLimit = kztxxSpecificationDownLimit == null ? null
				: kztxxSpecificationDownLimit.trim();
	}

	public Integer getKztxxSampleCapacity() {
		return kztxxSampleCapacity;
	}

	public void setKztxxSampleCapacity(Integer kztxxSampleCapacity) {
		this.kztxxSampleCapacity = kztxxSampleCapacity;
	}

	public Integer getKztxxDecimalNumber() {
		return kztxxDecimalNumber;
	}

	public void setKztxxDecimalNumber(Integer kztxxDecimalNumber) {
		this.kztxxDecimalNumber = kztxxDecimalNumber;
	}

	public String getKztxxAboveUpperControlLimit() {
		return kztxxAboveUpperControlLimit;
	}

	public void setKztxxAboveUpperControlLimit(String kztxxAboveUpperControlLimit) {
		this.kztxxAboveUpperControlLimit = kztxxAboveUpperControlLimit == null ? null
				: kztxxAboveUpperControlLimit.trim();
	}

	public String getKztxxAboveTargetValue() {
		return kztxxAboveTargetValue;
	}

	public void setKztxxAboveTargetValue(String kztxxAboveTargetValue) {
		this.kztxxAboveTargetValue = kztxxAboveTargetValue == null ? null : kztxxAboveTargetValue.trim();
	}

	public String getKztxxAboveLowerControlLimit() {
		return kztxxAboveLowerControlLimit;
	}

	public void setKztxxAboveLowerControlLimit(String kztxxAboveLowerControlLimit) {
		this.kztxxAboveLowerControlLimit = kztxxAboveLowerControlLimit == null ? null
				: kztxxAboveLowerControlLimit.trim();
	}

	public String getKztxxFollowGraphUpperControlLimit() {
		return kztxxFollowGraphUpperControlLimit;
	}

	public void setKztxxFollowGraphUpperControlLimit(String kztxxFollowGraphUpperControlLimit) {
		this.kztxxFollowGraphUpperControlLimit = kztxxFollowGraphUpperControlLimit == null ? null
				: kztxxFollowGraphUpperControlLimit.trim();
	}

	public String getKztxxFollowGraphTargetValue() {
		return kztxxFollowGraphTargetValue;
	}

	public void setKztxxFollowGraphTargetValue(String kztxxFollowGraphTargetValue) {
		this.kztxxFollowGraphTargetValue = kztxxFollowGraphTargetValue == null ? null
				: kztxxFollowGraphTargetValue.trim();
	}

	public String getKztxxFollowGraphLowerControlLimit() {
		return kztxxFollowGraphLowerControlLimit;
	}

	public void setKztxxFollowGraphLowerControlLimit(String kztxxFollowGraphLowerControlLimit) {
		this.kztxxFollowGraphLowerControlLimit = kztxxFollowGraphLowerControlLimit == null ? null
				: kztxxFollowGraphLowerControlLimit.trim();
	}

	public String getKztxxControlChartDiscription() {
		return kztxxControlChartDiscription;
	}

	public void setKztxxControlChartDiscription(String kztxxControlChartDiscription) {
		this.kztxxControlChartDiscription = kztxxControlChartDiscription == null ? null
				: kztxxControlChartDiscription.trim();
	}

	public String getKztxxCustomizedControlChartTitle() {
		return kztxxCustomizedControlChartTitle;
	}

	public void setKztxxCustomizedControlChartTitle(String kztxxCustomizedControlChartTitle) {
		this.kztxxCustomizedControlChartTitle = kztxxCustomizedControlChartTitle == null ? null
				: kztxxCustomizedControlChartTitle.trim();
	}

	public Integer getKztxxDetectionDeviceId() {
		return kztxxDetectionDeviceId;
	}

	public void setKztxxDetectionDeviceId(Integer kztxxDetectionDeviceId) {
		this.kztxxDetectionDeviceId = kztxxDetectionDeviceId;
	}

	public Integer getKztxxDetectionFrequency() {
		return kztxxDetectionFrequency;
	}

	public void setKztxxDetectionFrequency(Integer kztxxDetectionFrequency) {
		this.kztxxDetectionFrequency = kztxxDetectionFrequency;
	}
	public Integer getKztxxdatasState() {
		return kztxxdatasState;
	}

	public void setKztxxdatasState(Integer kztxxdatasState) {
		this.kztxxdatasState = kztxxdatasState;
	}
	public String getKztxxExtend1() {
		return kztxxExtend1;
	}

	public void setKztxxExtend1(String kztxxExtend1) {
		this.kztxxExtend1 = kztxxExtend1 == null ? null : kztxxExtend1.trim();
	}

	public String getKztxxExtend2() {
		return kztxxExtend2;
	}

	public void setKztxxExtend2(String kztxxExtend2) {
		this.kztxxExtend2 = kztxxExtend2 == null ? null : kztxxExtend2.trim();
	}

	public String getKztxxExtend3() {
		return kztxxExtend3;
	}

	public void setKztxxExtend3(String kztxxExtend3) {
		this.kztxxExtend3 = kztxxExtend3 == null ? null : kztxxExtend3.trim();
	}

	public String getKztxxExtend4() {
		return kztxxExtend4;
	}

	public void setKztxxExtend4(String kztxxExtend4) {
		this.kztxxExtend4 = kztxxExtend4 == null ? null : kztxxExtend4.trim();
	}

	public String getKztxxExtend5() {
		return kztxxExtend5;
	}

	public void setKztxxExtend5(String kztxxExtend5) {
		this.kztxxExtend5 = kztxxExtend5 == null ? null : kztxxExtend5.trim();
	}

	@Override
	public String toString() {
		return "ControlChartInfomation [kztxxId=" + kztxxId + ", kztxxGroupId=" + kztxxGroupId + ", kztxxNumber="
				+ kztxxNumber + ", kztxxTypeId=" + kztxxTypeId + ", kztxxDetectionItemId=" + kztxxDetectionItemId
				+ ", kztxxSpecificationUpperLimit=" + kztxxSpecificationUpperLimit + ", kztxxTargetValue="
				+ kztxxTargetValue + ", kztxxSpecificationDownLimit=" + kztxxSpecificationDownLimit
				+ ", kztxxSampleCapacity=" + kztxxSampleCapacity + ", kztxxDecimalNumber=" + kztxxDecimalNumber
				+ ", kztxxAboveUpperControlLimit=" + kztxxAboveUpperControlLimit + ", kztxxAboveTargetValue="
				+ kztxxAboveTargetValue + ", kztxxAboveLowerControlLimit=" + kztxxAboveLowerControlLimit
				+ ", kztxxFollowGraphUpperControlLimit=" + kztxxFollowGraphUpperControlLimit
				+ ", kztxxFollowGraphTargetValue=" + kztxxFollowGraphTargetValue
				+ ", kztxxFollowGraphLowerControlLimit=" + kztxxFollowGraphLowerControlLimit
				+ ", kztxxControlChartDiscription=" + kztxxControlChartDiscription
				+ ", kztxxCustomizedControlChartTitle=" + kztxxCustomizedControlChartTitle + ", kztxxDetectionDeviceId="
				+ kztxxDetectionDeviceId + ", kztxxDetectionFrequency=" + kztxxDetectionFrequency + ", kztxxdatasState="
				+ kztxxdatasState + ", kztxxExtend1=" + kztxxExtend1 + ", kztxxExtend2=" + kztxxExtend2
				+ ", kztxxExtend3=" + kztxxExtend3 + ", kztxxExtend4=" + kztxxExtend4 + ", kztxxExtend5=" + kztxxExtend5
				+ "]";
	}
	

}