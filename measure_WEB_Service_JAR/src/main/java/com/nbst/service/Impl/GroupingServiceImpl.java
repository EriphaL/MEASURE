package com.nbst.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbst.dao.mapper.imesweb.GroupingMapper;
import com.nbst.model.Grouping;
import com.nbst.service.IGroupingService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangl
 *
 */
@Slf4j
@Service
@Transactional
public class GroupingServiceImpl implements IGroupingService {
	@Autowired
	private GroupingMapper groupMapper;


	/**
	 * 分组新增
	 * 
	 * @param group
	 * @return
	 */
	@Override
	public Object addGroup(Grouping group) {
		Map<String, Object> resultMap = new HashMap<>();
		// 判断name是否重复
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("fzName", group.getFzName());
		conditionMap.put("fzExtend3", "1");
		List<Grouping> names = groupMapper.findByCondition(conditionMap);
		if (!names.isEmpty()) {
			resultMap.put("message", "名称重复，新增失败");
			resultMap.put("code", "9999");
			return resultMap;
		}

		// 若name无重复则插入
		group.setFzExtend3("1");
		int flag = groupMapper.insert(group);
		if (flag != 0) {
			resultMap.put("message", "新增成功");
			resultMap.put("code", "0000");
			resultMap.put("id", group.getFzId());
		} else {
			resultMap.put("message", "新增失败");
			resultMap.put("code", "9997");
			resultMap.put("id", "0");
		}

		return resultMap;
	}

	/**
	 * 分组修改/删除,state=0：删除,state=1:修改
	 * 
	 * @param group
	 * @return
	 */
	@Override
	public Object alterGroup(Grouping group) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> conditionMap = new HashMap<>();
		// 删除
		if (group.getFzExtend2().equals("0")) {
			// 判断是否有子分组
			conditionMap.put("fzExtend1", group.getFzId());
			conditionMap.put("fzExtend3", "1");
			List<Grouping> list1 = groupMapper.findByCondition(conditionMap);
			if (!list1.isEmpty()) {
				resultMap.put("message", "有子分组，删除失败");
				resultMap.put("code", "9991");
				return resultMap;
			}
			Grouping g = groupMapper.findById(group.getFzId());
			g.setFzExtend3("2");
			int flag = groupMapper.update(g);
			if (flag > 0) {
				resultMap.put("message", "删除成功");
				resultMap.put("code", "0000");
			} else {
				resultMap.put("message", "删除失败");
				resultMap.put("code", "9996");
			}
			return resultMap;
		}
		// 修改
		// 判断name是否重复
		conditionMap.clear();
		conditionMap.put("fzName", group.getFzName());
		conditionMap.put("fzExtend3", "1");
		List<Grouping> names = groupMapper.findByCondition(conditionMap);
		for (Grouping u : names) {
			if (!u.getFzId().equals(group.getFzId())) {
				resultMap.put("message", "名称重复，修改失败");
				resultMap.put("code", "9995");
				return resultMap;
			}
		}
		// name不重复则更新
		int flag = groupMapper.update(group);
		if (flag > 0) {
			resultMap.put("message", "修改成功");
			resultMap.put("code", "0000");
		} else {
			resultMap.put("message", "修改失败");
			resultMap.put("code", "9994");
		}
		return resultMap;
	}

	/**
	 * 分组查询
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	@Override
	public Object searchGroup(Integer limit, Integer offset) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> conditionMap = new HashMap<>();

		conditionMap.put("limit", limit);
		conditionMap.put("offset", offset);
		conditionMap.put("fzExtend2", "1");

		List<Grouping> list = groupMapper.findByCondition(conditionMap);
		List<Map<String, Object>> rowMap = new ArrayList<>();
		for (Grouping g : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", g.getFzName());
			map.put("id", g.getFzId());
			map.put("pId", g.getFzExtend1());
			rowMap.add(map);
		}
		resultMap.put("code", "0000");
		resultMap.put("message", "查找成功");
		resultMap.put("rows", rowMap);
		return resultMap;
	}
}