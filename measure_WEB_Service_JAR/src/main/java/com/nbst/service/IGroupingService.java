package com.nbst.service;

import com.nbst.model.Grouping;

/**
 * @author yangl
 *
 */
public interface IGroupingService {
	// 分组新增
	Object addGroup(Grouping group);

	// 分组修改/删除
	Object alterGroup(Grouping group);

	// 分组查询
	Object searchGroup(Integer limit, Integer offset);

}
