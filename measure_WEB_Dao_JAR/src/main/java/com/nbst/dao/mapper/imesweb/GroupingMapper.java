package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.Grouping;

public interface GroupingMapper {
	int delete(Integer fzId);

	int insert(Grouping record);

	Grouping findById(Integer fzId);

	int update(Grouping record);

	List<Grouping> findByCondition(Map<String,Object> map);

	int count(Map<String, Object> map);
}