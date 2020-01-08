package com.nbst.enums;

import lombok.Getter;

/**
 * @author huangh
 *
 */
@Getter
public enum Enum {

	UPDATE(0, "修改操作"), 
	DELETE(1, "删除操作"), 
	TYPE_LEVEL_STATE(1, "控制图层次信息标识"), 
	DETECION_PASS_STATE(1, "检测通过状态"),
	SAMPLING_TIME(1, "抽检时间"), 
	INPUT_TIME(2, "录入时间"), 
	NULL_TIME(3, "未录入时间"), 
	CONTROL_CHART_TYPE_STATE(1, "计量型控制图"),
	CONTROL_CHART_XR(1, "x-r控制图"), 
	CONTROL_CHART_XS(2, "x-s控制图"), 
	CONTROL_CHART_IMR(3, "x-r控制图"), 
	CONTROL_CHART_P(4, "p控制图"), 
	CONTROL_CHART_NP(5, "np控制图"),
	CONTROL_CHART_U(6, "u控制图"), 
	CONTROL_CHART_C(7, "c控制图"), 
	CONTROL_CHART_Y(8, "y控制图"),
	CONTROL_CHART_DISPLAY_TYPE(1, "返回数据是否为单条"),
	DETECTION_DATA_NORMAL(1, "检测数据正常"),
	SUCCESS_DATA(1, "函数是否执行"),
	DETECTION_DATA_WORING(2, "检测数据异常"),

	;

	Enum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private Integer code;
	private String desc;
}
