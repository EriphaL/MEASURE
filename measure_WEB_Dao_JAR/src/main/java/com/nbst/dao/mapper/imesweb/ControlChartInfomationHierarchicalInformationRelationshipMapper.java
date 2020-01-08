package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.ControlChartInfomationHierarchicalInformationRelationship;

public interface ControlChartInfomationHierarchicalInformationRelationshipMapper {
	int delete(Integer kztxxccglId);

	int insert(ControlChartInfomationHierarchicalInformationRelationship record);

	ControlChartInfomationHierarchicalInformationRelationship findById(Integer kztxxccglId);

	int update(ControlChartInfomationHierarchicalInformationRelationship record);

	List<ControlChartInfomationHierarchicalInformationRelationship> findByCondition(Map<String, Object> map);

	int count(Map<String, Object> map);

	Map<String, Object> getLevels(ControlChartInfomationHierarchicalInformationRelationship relationship);

	Integer getGlIdByChartId(Integer kztxxccglControlChartId);

	// 根据控制图id复制层次关联信息
	int copeByControlChartId(Map<String, Object> map);
}