package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.DetectionData;

public interface DetectionDataMapper {
	int delete(Integer jcsjId);

	// 删除对应控制图Id的表内数据
	int deleteAll(Integer chartId);

	int insert(DetectionData record);

	DetectionData findById(Integer jcsjId);

	int update(DetectionData record);

	List<DetectionData> findByCondition(Map<String, Object> map);

	int count(Map<String, Object> map);

	// 按照多个检测数据id删除样本数据
	int deleteids(Map<String, Object> map);

	// 按照时间查询
	List<DetectionData> searchtime(Map<String, Object> map);

	// 计数
	int searchtimecount(Map<String, Object> map);

	// 根据状态和控制图id统计数据
	int countByState(Map<String, Object> map);

	// 根据控制图id以及limit和offset查询检测数据
	List<String> findDetectionDataByControlChartId(Map<String, Object> map);

	// 根据控制图id查询检测数据的id
	List<Integer> findDetectionDataIdByControlChartId(Map<String, Object> map);

	// 按照多个检测数据id清空判异记录
	int clearOCCRuleByIds(Map<String, Object> map);

	// 根据控制图id克隆检测数据
	int copyDataByControlChartIds(Map<String, Object> map);

	// 根据控制图id查询最后一条数据
	DetectionData findLastDataByControlChartId(Integer jcsjControlChartId);
}