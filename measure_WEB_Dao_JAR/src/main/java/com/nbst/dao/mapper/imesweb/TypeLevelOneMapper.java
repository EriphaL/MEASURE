package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.TypeLevelOne;

public interface TypeLevelOneMapper {
	int delete(Integer cclxoneId);

	int insert(TypeLevelOne record);

	TypeLevelOne findById(Integer cclxoneId);

	int update(TypeLevelOne record);

	List<TypeLevelOne> findByCondition(Map<String, Object> map);

	int count(Map<String, Object> map);
	// 查询层次信息表名
	String findTypeLevelName();
}