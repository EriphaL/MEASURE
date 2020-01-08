package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.User;

public interface UserMapper {
	int count(Map<String, Object> map);

	List<User> findByCondition(Map<String, Object> map);

	int delete(Integer yhId);

	int update(User record);

	Integer insert(User user);

	User findById(Integer yhId);
	
	User findByName(String yhUsername);

}