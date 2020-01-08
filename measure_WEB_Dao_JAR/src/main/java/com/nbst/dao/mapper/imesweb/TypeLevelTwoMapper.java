package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.TypeLevelTwo;

public interface TypeLevelTwoMapper {
	int delete(Integer cclxtwoId);

	int insert(TypeLevelTwo record);

	TypeLevelTwo findById(Integer cclxtwoId);

	int update(TypeLevelTwo record);

	List<TypeLevelTwo> findByCondition(Map<String, Object> map);

	int count(Map<String, Object> map);
	// 查询层次信息表名
	String findTypeLevelName();
}