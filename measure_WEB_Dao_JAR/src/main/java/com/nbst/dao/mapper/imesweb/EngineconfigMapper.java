package com.nbst.dao.mapper.imesweb;

import com.nbst.model.Engineconfig;

public interface EngineconfigMapper {
	int delete(Integer enginconfigId);

	int insert(Engineconfig record);

	Engineconfig findById(Integer enginconfigId);

	int update(Engineconfig record);
}