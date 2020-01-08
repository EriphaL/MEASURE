package com.nbst.service;

import java.util.Map;

import com.nbst.model.ControlChartInfomationHierarchicalInformationRelationship;

/**
 * @author yangl
 *
 */
public interface IControlChartInfomationHierarchicalInformationRelationshipService {

	// 控制图层次信息关联修改
	Object alterRelationship(ControlChartInfomationHierarchicalInformationRelationship relationship);

	// 控制图层次信息关联查询
	Object searchRelationship(ControlChartInfomationHierarchicalInformationRelationship relationship);
	/**
	 * 查询层次类型名称
	 * 
	 * @author yangl
	 * @return Map<String, Object>
	 */
	Map<String, Object> getTypeLevelName();
}