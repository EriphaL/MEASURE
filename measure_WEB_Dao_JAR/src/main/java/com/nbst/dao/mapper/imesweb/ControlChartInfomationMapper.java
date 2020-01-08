package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.ControlChartInfomation;

public interface ControlChartInfomationMapper {
	int delete(Integer kztxxId);

	int insert(ControlChartInfomation record);

	ControlChartInfomation findById(Integer kztxxId);

	int update(ControlChartInfomation record);

	List<ControlChartInfomation> findByCondition(Map<String, Object> map);

	int count(Map<String, Object> map);

	// 根据分组id查询所有关联的计数图id以及编号
	List<Map<String, Integer>> findByGroupIdOfCountFigure(Map<String, Object> map);

	// 根据分组id查询所有关联的计量图信息
	List<Map<String, Object>> findByConditionOfMeasuringFigure(Integer kztxxGroupId);

	// 根据分组id查询控制图列表相关数据
	List<Map<String, Object>> findListByGroupId(Map<String, Object> map);
	
	// 控制图过滤
	List<Map<String, Object>> findListByControlFilter(Map<String, Object> map);

	// 根据控制图id查询控制图编号、检测项目及层次信息
	Map<String, Object> findByControlChartId(Integer kztxxId);

	// 根据控制图id查询控制图编号、检测项目并拼接
	String findNumberItemByControlChartId(Integer kztxxId);
	
	// 根据分组id查询所有关联的计数图信息
	List<Map<String, Object>> findByConditionOfCountFigure(Integer kztxxId);
	
	// 根据控制图id以及分组id复制控制图信息
	int copeByControlChartIdAndGroupId(Map<String, Object> map);

	// 更新克隆次数
	int updateCopeCount(Integer kztxxId);

	// 查询所有已绑定设备且自动录入状态为1的控制图
	List<Map<String, Object>> findControlChartIdByAutomaticEntryState();
}